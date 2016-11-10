package easytests.controllers;

import easytests.entities.User;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import easytests.service.UserService;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by vkpankov on 31.10.2016.
 * Edited by SingularityA on 03.11.2016
 * Edited by malinink on 05.11.2016
 */
@Controller
public class UsersController {
    @RequestMapping("/")
    @ResponseBody
    public String list() throws Exception {
        UserService userService = new UserService();
        if(userService.getAllUsers().size()<3) {
            userService.addUser(new User("Test1", "Test1", "Test1"));
            userService.addUser(new User("Test2", "Test2", "Test2"));
            userService.addUser(new User("Test3", "Test3", "Test3"));
        }
        return "Users were added successfully!";
    }
}