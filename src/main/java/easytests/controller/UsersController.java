package easytests.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import easytests.entities.User;

import java.util.ArrayList;

@Controller
public class UsersController {

    @RequestMapping("/")
    public ModelAndView GetUsers(Model model)
    {
        ArrayList<User> Users=new ArrayList<>();
        Users.add(new User("Ivan","Ivanov","Ivanovich"));
        Users.add(new User("Test","Testov","Testovich"));
        return new ModelAndView("template", "Users", Users);
    }

}