package easytests.api.v1.controllers;

import easytests.api.v1.mappers.UsersMapper;
import easytests.api.v1.models.User;
import easytests.core.models.UserModelInterface;
import easytests.core.options.builder.UsersOptionsBuilderInterface;
import easytests.core.services.UsersServiceInterface;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @GetMapping("")
    public List<User> list() {
        final List<UserModelInterface> usersModels = this.usersService.findAll();

        return usersModels
                .stream()
                .map(model -> this.usersMapper.map(model, User.class))
                .collect(Collectors.toList());
    }
    /**
     * create
     */
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
