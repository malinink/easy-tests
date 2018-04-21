package easytests.api.v1.controllers;

import easytests.api.v1.exceptions.BadRequestException;
import easytests.api.v1.exceptions.IdentifiedModelException;
import easytests.api.v1.exceptions.NotFoundException;
import easytests.api.v1.exceptions.UnidentifiedModelException;
import easytests.api.v1.mappers.UsersMapper;
import easytests.api.v1.models.Identity;
import easytests.api.v1.models.User;
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


@RestController("UsersControllerV1")
@SuppressWarnings("checkstyle:MultipleStringLiterals")
@RequestMapping("/v1/users")
public class UsersController {

    @Autowired
    protected UsersService usersService;

    @Autowired
    private UsersOptionsBuilder usersOptionsBuilder;

    @Autowired
    @Qualifier("UsersMapperV1")
    private UsersMapper usersMapper;
    /**
     * list
     */
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
