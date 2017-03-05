package easytests.controllers;

import easytests.models.UserModelInterface;
import easytests.services.UsersService;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author malinink
 */
@Controller
@RequestMapping("/")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping("/users")
    public String list(Model model) {
        final List<UserModelInterface> users = this.usersService.findAll();
        model.addAttribute("users", users);
        return "users/list";
    }
}
