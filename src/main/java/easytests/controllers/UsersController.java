package easytests.controllers;

import easytests.entities.User;
import easytests.mappers.UsersMapper;
import java.util.List;
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
    private UsersMapper usersMapper;

    @RequestMapping("/users")
    public String list(Model model) {
        final List<User> users = this.usersMapper.readAll();
        model.addAttribute("users", users);
        return "users/list";
    }
}
