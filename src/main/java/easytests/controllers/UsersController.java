package easytests.controllers;

import easytests.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;


/**
 * Created by fortyways on 03.11.16.
 */
@Controller
public class UsersController {

    @RequestMapping(value="/users")
    public String getUsers(Model model){
        //Creating list
        ArrayList<User> Users=new ArrayList<>();
        //Filling the list
        Users.add(new User("Dmitry","Alexeevich","Marulin"));
        Users.add(new User("Zorigto","Zhargalovich","Dorzhiev"));
        Users.add(new User("Djamil","Amirovich","Shaimordanov"));
        //Transfer to view
        model.addAttribute("Users",Users);
        return "users";
    }

}
