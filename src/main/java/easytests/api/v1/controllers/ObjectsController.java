package easytests.api.v1.controllers;

import easytests.api.v1.mappers.ObjectsMapper;
import easytests.api.v1.models.Object;
import easytests.common.exceptions.NotFoundException;
import easytests.core.models.UserModelInterface;
import easytests.core.options.SubjectsOptions;
import easytests.core.options.UsersOptions;
import easytests.core.options.UsersOptionsInterface;
import easytests.core.options.builder.UsersOptionsBuilder;
import easytests.core.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;


/**
 * @author malinink
 */
@RestController("ObjectsControllerV1")
@SuppressWarnings("checkstyle:MultipleStringLiterals")
@RequestMapping("/v1/objects/")
public class ObjectsController {

    @Autowired
    protected UsersService usersService;

    @Autowired
    private UsersOptionsBuilder usersOptionsBuilder;

    @Autowired
    @Qualifier("ObjectsMapperV1")
    private ObjectsMapper objectsMapper;

    @GetMapping("{userId}/")
    public Object view(@PathVariable Integer userId) {
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
}
