package easytests.api.v1.controllers;

import easytests.api.v1.exceptions.*;
import easytests.api.v1.mappers.UsersMapper;
import easytests.api.v1.models.Identity;
import easytests.api.v1.models.User;
import easytests.core.models.UserModel;
import easytests.core.models.UserModelInterface;
import easytests.core.options.builder.UsersOptionsBuilderInterface;
import easytests.core.services.UsersServiceInterface;
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
    @Qualifier("UsersMapperV1")
    private UsersMapper usersMapper;

    private String passgenerator(int n) {
        final String dict = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOPASDFGHJKLZXCVBNM";
        String pass = "";
        for (int i = 0; i < 6; i++) {
            pass = pass + (dict.charAt(0 + (int) (Math.random() * dict.length())));
        }
        return pass;
    }
    /**
     * list
     */
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Identity create(@RequestBody User user) throws BadRequestException {
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
    /**
     * show(userId)
     */
    /**
     * delete(userId)
     */
    /**
     * showMe
     */
}
