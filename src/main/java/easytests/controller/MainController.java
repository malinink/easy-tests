package easytests.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "Use the following urls to invoke controllers methods and see the interactions with the database:\n" +
                "/create?firstname=[firstname]&lastname=[lastname]&surname=[surname]: create a new user with an auto-generated id and firstname, lastname, surname as passed values.\n" +
                "/delete?id=[id]: delete the user with the passed id.\n" +
                "/get-by-lastname?lastname=[lastname]: retrieve the id for the user with the passed lastname (not working for multiple users).\n" +
                "/update?id=[id]&firstname=[firstname]&lastname=[lastname]&surname=[surname]: update firstname, lastname, surname  for the user indentified by the passed id.\n";
    }

}