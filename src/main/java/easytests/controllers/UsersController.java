package easytests.controllers;

import easytests.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;

/**
 * Created by vkpankov on 31.10.2016.
 * Edited by SingularityA on 03.11.2016
 * Edited by malinink on 05.11.2016
 */
@Controller
@RequestMapping("/")
public class UsersController {

    @RequestMapping("/users")
    public String list(Model model) {

        ArrayList<User> users = new ArrayList<>(15);

        users.add(new User("Артем","Багаев","Робизонович"));
        users.add(new User("Андрей","Влад","Иванович"));
        users.add(new User("Зоригто","Доржиев","Жаргалович"));
        users.add(new User("Алексей","Ермолаев","Николаевич"));
        users.add(new User("Илья","Живолуп","Дмитриевич"));
        users.add(new User("Василий","Каминский","Владимирович"));
        users.add(new User("Владислав","Марков","Викторович"));
        users.add(new User("Дмитрий","Марулин","Алексеевич"));
        users.add(new User("Викентий","Панков","Дмитриевич"));
        users.add(new User("Антон","Панченко","Дмитриевич"));
        users.add(new User("Никита","Попов","Алексеевич"));
        users.add(new User("Карина","Сахарова","Сергеевна"));
        users.add(new User("Лия","Халиуллина","Рауфофна"));
        users.add(new User("Джамиль","Шайморданов","Амирович"));
        users.add(new User("Валентин","Шульга","Александрович"));

        model.addAttribute("users", users);
        return "users/list";
    }
}
