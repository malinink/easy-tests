package easytests.controller;

import easytests.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import easytests.service.UserService;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
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