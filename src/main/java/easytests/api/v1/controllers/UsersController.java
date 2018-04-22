package easytests.api.v1.controllers;

import easytests.api.v1.mappers.UsersMapper;
import easytests.core.options.builder.UsersOptionsBuilder;
import easytests.core.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;



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
