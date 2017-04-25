package easytests.admin.controllers;

import easytests.admin.dto.UserModelDto;
import easytests.admin.validators.UserModelDtoValidator;
import easytests.common.controllers.AbstractCrudController;
import easytests.models.UserModel;
import easytests.models.UserModelInterface;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author malinink
 */
@Controller
@RequestMapping("/admin/users/")
public class UsersController extends AbstractCrudController {
    @Autowired
    private UserModelDtoValidator userModelDtoValidator;

    @GetMapping("")
    public String list(Model model) {
        final List<UserModelInterface> users = this.usersService.findAll();
        model.addAttribute("users", users);
        return "admin/users/list";
    }

    @GetMapping("create/")
    public String create(Model model) {
        final UserModelDto userModelDto = new UserModelDto();
        injectUserModelDto(model, userModelDto);
        setCreateBehaviour(model);
        return form();
    }

    @PostMapping("create/")
    public String insert(Model model, @Valid UserModelDto userModelDto, BindingResult bindingResult) {
        userModelDto.setRouteId(null);
        this.userModelDtoValidator.validate(userModelDto, bindingResult);
        if (bindingResult.hasErrors()) {
            injectUserModelDto(model, userModelDto);
            setCreateBehaviour(model);
            model.addAttribute("errors", bindingResult);
            return form();
        }

        final UserModelInterface userModel = new UserModel();
        userModelDto.mapInto(userModel);
        this.usersService.save(userModel);
        return redirectToList();
    }

    private static void injectUserModelDto(Model model, UserModelDto userModelDto) {
        model.addAttribute("user", userModelDto);
    }

    private static String form() {
        return "admin/users/form";
    }

    private static String redirectToList() {
        return "redirect:/admin/users/";
    }
}
