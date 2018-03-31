package easytests.controllers;

import easytests.core.models.UserModelInterface;
import easytests.core.services.UsersService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author malinink
 */
@Controller("temporary.users.controller")
@RequestMapping("/")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping("")
    public String root(Model model) {
        return "users/root";
    }

    @RequestMapping("users")
    public String list(Model model) {
        final List<UserModelInterface> users = this.usersService.findAll();
        model.addAttribute("users", users);
        return "users/list";
    }
}
