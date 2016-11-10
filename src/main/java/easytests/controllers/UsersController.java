package easytests.controllers;

import easytests.entities.User;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.UserService;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by vkpankov on 31.10.2016.
 * Edited by SingularityA on 03.11.2016
 * Edited by malinink on 05.11.2016
 */
@Controller
@RequestMapping("/")
public class UsersController {

    @RequestMapping("/users")
    public String list(Model model) throws Exception {

        UserService userService = new UserService();

        if(userService.getAllUsers().size()<2) {
            userService.addUser(new User("Артем", "Багаев", "Робизонович"));
            userService.addUser(new User("Андрей", "Влад", "Иванович"));
            userService.addUser(new User("Зоригто", "Доржиев", "Жаргалович"));
            userService.addUser(new User("Алексей", "Ермолаев", "Николаевич"));
            userService.addUser(new User("Илья", "Живолуп", "Дмитриевич"));
            userService.addUser(new User("Василий", "Каминский", "Владимирович"));
            userService.addUser(new User("Владислав", "Марков", "Викторович"));
            userService.addUser(new User("Дмитрий", "Марулин", "Алексеевич"));
            userService.addUser(new User("Викентий", "Панков", "Дмитриевич"));
            userService.addUser(new User("Антон", "Панченко", "Дмитриевич"));
            userService.addUser(new User("Никита", "Попов", "Алексеевич"));
            userService.addUser(new User("Карина", "Сахарова", "Сергеевна"));
            userService.addUser(new User("Лия", "Халиуллина", "Рауфофна"));
            userService.addUser(new User("Джамиль", "Шайморданов", "Амирович"));
            userService.addUser(new User("Валентин", "Шульга", "Александрович"));
        }

        model.addAttribute("users", userService.getAllUsers());
        return "users/list";
    }
}
