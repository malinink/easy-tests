package easytests.api.v1.controllers;

import easytests.api.v1.mappers.UsersMapper;
import easytests.api.v1.models.User;
import easytests.common.controllers.AbstractCrudController;
import easytests.common.exceptions.NotFoundException;
import easytests.core.models.UserModelInterface;
import easytests.core.options.UsersOptionsInterface;
import easytests.core.options.builder.UsersOptionsBuilder;
import easytests.core.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;


/**
 * @author malinink
 */
@RestController("UsersControllerV1")
@SuppressWarnings("checkstyle:MultipleStringLiterals")
@RequestMapping("/v1/users/")
public class UsersController {

    @Autowired
    protected UsersService usersService;

    @Autowired
    private UsersOptionsBuilder usersOptionsBuilder;

    @Autowired
    @Qualifier("UsersMapperV1")
    private UsersMapper usersMapper;

    @GetMapping("{userId}/")
    public User view(@PathVariable Integer userId) {
        final UserModelInterface userModel = this.getUserModel(userId);
        return this.usersMapper.map(userModel, User.class);
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
