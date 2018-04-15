package easytests.api.v1.controllers;

import easytests.api.v1.exceptions.BadRequestException;
import easytests.api.v1.exceptions.IdentifiedModelException;
import easytests.api.v1.exceptions.NotFoundException;
import easytests.api.v1.exceptions.UnidentifiedModelException;
import easytests.api.v1.mappers.ObjectsMapper;
import easytests.api.v1.models.Identity;
import easytests.api.v1.models.Object;
import easytests.core.models.UserModel;
import easytests.core.models.UserModelInterface;
import easytests.core.options.SubjectsOptions;
import easytests.core.options.UsersOptions;
import easytests.core.options.UsersOptionsInterface;
import easytests.core.options.builder.UsersOptionsBuilder;
import easytests.core.services.UsersService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


/**
 * @author malinink
 */
@RestController("ObjectsControllerV1")
@SuppressWarnings("checkstyle:MultipleStringLiterals")
@RequestMapping("/v1/objects")
public class ObjectsController {

    @Autowired
    protected UsersService usersService;

    @Autowired
    private UsersOptionsBuilder usersOptionsBuilder;

    @Autowired
    @Qualifier("ObjectsMapperV1")
    private ObjectsMapper objectsMapper;

    /**
     * TODO
     * Add permissions check
     */

    @GetMapping("")
    public List<Object> list() {
        final List<UserModelInterface> usersModels = this.usersService.findAll();

        return usersModels
            .stream()
            .map(model -> this.objectsMapper.map(model, Object.class))
            .collect(Collectors.toList());
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Identity create(@RequestBody Object object) throws Exception {
        if (object.getId() != null) {
            throw new IdentifiedModelException();
        }
        this.verifySubjectsIsNull(object);

        /**
         * We need to check for email existence in usersService
         * TODO
         */

        final UserModelInterface userModel = this.objectsMapper.map(object, UserModel.class);

        /**
         * Temporary set password cause it must be not null
         */
        userModel.setPassword("");

        this.usersService.save(userModel);

        return this.objectsMapper.map(userModel, Identity.class);
    }

    @PutMapping("")
    public void update(@RequestBody Object object) throws Exception {
        if (object.getId() == null) {
            throw new UnidentifiedModelException();
        }
        this.verifySubjectsIsNull(object);

        /**
         * We need to check for email existence in usersService
         * TODO
         */

        final UserModelInterface userModel = this.getUserModel(object.getId());

        this.objectsMapper.map(object, userModel);

        this.usersService.save(userModel);
    }

    @GetMapping("/{userId}")
    public Object show(@PathVariable Integer userId) {
        final UserModelInterface userModel = this.getUserModel(
            userId,
            (new UsersOptions()).withSubjects(new SubjectsOptions())
        );
        return this.objectsMapper.map(userModel, Object.class);
    }

    private UserModelInterface getUserModel(Integer id, UsersOptionsInterface userOptions) {
        final UserModelInterface userModel = this.usersService.find(id, userOptions);
        if (userModel == null) {
            throw new NotFoundException();
        }
        return userModel;
    }

    private UserModelInterface getUserModel(Integer id) {
        return this.getUserModel(id, this.usersOptionsBuilder.forAuth());
    }

    private void verifySubjectsIsNull(Object object) throws BadRequestException {
        if (object.getSubjects() != null) {
            throw new BadRequestException("subjects must be null");
        }
    }
}
