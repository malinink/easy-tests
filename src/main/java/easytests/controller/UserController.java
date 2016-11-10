package easytests.controller;

import easytests.entities.User;
import easytests.entities.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping("/create")
    @ResponseBody
    public String create(String firstname, String lastname, String surname) {
        User user = null;
        try {
            user = new User(firstname, lastname, surname);
            userDao.save(user);
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created! (id = " + user.getId() + ")";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(int id) {
        try {
            User user = new User(id);
            userDao.delete(user);
        }
        catch (Exception ex) {
            return "Error deleting the user: " + ex.toString();
        }
        return "User succesfully deleted!";
    }

    @RequestMapping("/get-by-lastname")
    @ResponseBody
    public String getByEmail(String lastname) {
        String userId;
        try {
            User user = userDao.findByLastName(lastname);
            userId = String.valueOf(user.getId());
        }
        catch (Exception ex) {
            return "User not found";
        }
        return "The user id is: " + userId;
    }

    @RequestMapping("/update")
    @ResponseBody
    public String updateUser(long id, String firstname, String lastname, String surname) {
        try {
            User user = userDao.findOne(id);
            user.setFirstName(firstname);
            user.setLastName(lastname);
            user.setSurname(surname);
            userDao.save(user);
        }
        catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "User succesfully updated!";
    }
    @Autowired
    private UserDao userDao;
}