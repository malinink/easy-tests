package easytests.api.v1.controllers;

import easytests.api.v1.exceptions.*;
import easytests.api.v1.mappers.UsersMapper;
import easytests.api.v1.models.Identity;
import easytests.api.v1.models.User;
import easytests.auth.services.SessionServiceInterface;
import easytests.core.models.UserModel;
import easytests.core.models.UserModelInterface;
import easytests.core.options.UsersOptionsInterface;
import easytests.core.options.builder.UsersOptionsBuilderInterface;
import easytests.core.services.UsersServiceInterface;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


/**
 * @author SvetlanaTselikova
 */
@RestController("UsersControllerV1")
@SuppressWarnings("checkstyle:MultipleStringLiterals")
@RequestMapping("/v1/users")
public class UsersController {

    @Autowired
    protected UsersServiceInterface usersService;

    @Autowired
    private UsersOptionsBuilderInterface usersOptionsBuilder;

    @Autowired
    private SessionServiceInterface sessionService;

    @Autowired
    @Qualifier("UsersMapperV1")
    private UsersMapper usersMapper;

    private Boolean isAdmin() {
        if (!this.sessionService.isUser()) {
            return false;
        }
        return this.sessionService.getUserModel().getIsAdmin();
    }

    @GetMapping("")
    public List<User> list() throws ForbiddenException {
        if (!this.isAdmin()) {
            throw new ForbiddenException();
        }
        final List<UserModelInterface> usersModels = this.usersService.findAll();

        return usersModels
                .stream()
                .map(model -> this.usersMapper.map(model, User.class))
                .collect(Collectors.toList());
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Identity create(@RequestBody User user) throws BadRequestException, ForbiddenException {
        if (!this.isAdmin()) {
            throw new ForbiddenException();
        }
        if (user.getId() != null) {
            throw new IdentifiedModelException();
        }
        if (this.usersService.findByEmail(user.getEmail()) != null) {
            throw new BadRequestException("This email already exist.");
        }

        final UserModelInterface userModel = this.usersMapper.map(user, UserModel.class);

        userModel.setPassword(passgenerator(6));

        this.usersService.save(userModel);

        return this.usersMapper.map(userModel, Identity.class);
    }

    /**
     * update
     */
    @GetMapping("/{userId}")
    public User show(@PathVariable Integer userId) throws NotFoundException, ForbiddenException {
        final UserModelInterface userModel = this.usersService.find(userId);

        if (!this.isAdmin()) {
            throw new ForbiddenException();
        }

        if (userModel == null) {
            throw new NotFoundException();
        }

        return this.usersMapper.map(userModel, User.class);
    }

    @PutMapping("")
    public void update(@RequestBody User user) throws Exception {
        if (!this.isAdmin()) {
            throw new ForbiddenException();
        }

        if (user.getId() == null) {
            throw new UnidentifiedModelException();
        }

        final UserModelInterface userModel = this.usersService.find(user.getId());

        if (userModel == null) {
            throw new NotFoundException();
        }

        this.usersMapper.map(user, userModel);

        this.usersService.save(userModel);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable Integer userId) throws NotFoundException, ForbiddenException {
        if (!this.isAdmin()) {
            throw new ForbiddenException();
        }

        final UsersOptionsInterface usersOptions = this.usersOptionsBuilder.forDelete();
        final UserModelInterface userModel = this.usersService.find(userId, usersOptions);

        if (userModel == null) {
            throw new NotFoundException();
        }

        this.usersService.delete(userModel, usersOptions);
    }

    @GetMapping("/me")
    public User showme() throws ForbiddenException {

        if (!this.sessionService.isUser()) {
            throw new ForbiddenException();
        }
        final UserModelInterface userModel = this.sessionService.getUserModel();
        return this.usersMapper.map(userModel, User.class);
    }

    private String passgenerator(int n) {
        final String dict = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOPASDFGHJKLZXCVBNM";
        String pass = "";
        for (int i = 0; i < n; i++) {
            pass = pass + (dict.charAt(0 + (int) (Math.random() * dict.length())));
        }
        return pass;
    }
}
