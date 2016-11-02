package easytests.controllers;

import easytests.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;


/**
 * Created by vkpankov on 31.10.2016.
*/
@Controller
@RequestMapping("/")
public class UsersController {
    @RequestMapping("/users")
    public ModelAndView GetUsersList(Model model)
    {

        ArrayList<User> Users = new ArrayList<>(5);
        Users.add(new User("Dmitriy","Ivanov","Ivanovich"));
        Users.add(new User("Michael","Lightman","Smirnovich"));
        Users.add(new User("Mark","Petrov","Petrovich"));
        Users.add(new User("Dmitriy","Ivanov","Ivanovich"));
        Users.add(new User("Dmitriy","Ivanov","Ivanovich"));
        return new ModelAndView("UsersView", "UsersList", Users);


    }

}
