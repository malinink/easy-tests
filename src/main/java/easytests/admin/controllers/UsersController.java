package easytests.admin.controllers;

import easytests.admin.dto.UserModelDto;
import easytests.models.UserModelInterface;
import easytests.personal.controllers.AbstractCrudController;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author malinink
 */
@Controller
@RequestMapping("/admin/users/")
public class UsersController extends AbstractCrudController {
    @GetMapping("")
    public String list(Model model) {
        final List<UserModelInterface> users = this.usersService.findAll();
        model.addAttribute("users", users);
        return "admin/users/list";
    }

    @GetMapping("create/")
    public String create(Model model) {
        final UserModelDto user = new UserModelDto();
        model.addAttribute("user", user);
        setCreateBehaviour(model);
        return form();
    }

    @PostMapping("create/")
    public String insert(Model model) {
        if (false) {
            return redirectToList();
        }
        return form();
    }

    private static String form() {
        return "admin/users/form";
    }

    private static String redirectToList() {
        return "redirect:/admin/users/";
    }
}
