package easytests.admin.controllers;

import easytests.admin.dto.UserModelDto;
import easytests.admin.validators.UserModelDtoValidator;
import easytests.common.controllers.AbstractCrudController;
import easytests.common.exceptions.NotFoundException;
import easytests.models.UserModel;
import easytests.models.UserModelInterface;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author malinink
 */
@Controller
@SuppressWarnings("checkstyle:MultipleStringLiterals")
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

        model.addAttribute("user", userModelDto);
        setCreateBehaviour(model);
        return "admin/users/form";
    }

    @PostMapping("create/")
    public String insert(Model model, @Valid UserModelDto userModelDto, BindingResult bindingResult) {
        this.userModelDtoValidator.validate(userModelDto, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userModelDto);
            model.addAttribute("errors", bindingResult);
            setCreateBehaviour(model);
            return "admin/users/form";
        }

        final UserModelInterface userModel = new UserModel();
        userModelDto.mapInto(userModel);
        this.usersService.save(userModel);
        return "redirect:/admin/users/";
    }

    @GetMapping("update/{userId}/")
    public String update(Model model, @PathVariable Integer userId) {
        final UserModelInterface userModel = this.getUserModel(userId);
        final UserModelDto userModelDto = new UserModelDto();

        userModelDto.map(userModel);
        model.addAttribute("user", userModelDto);
        setUpdateBehaviour(model);
        return "admin/users/form";
    }

    @PostMapping("update/{userId}/")
    public String save(
            Model model,
            @PathVariable Integer userId,
            @Valid UserModelDto userModelDto,
            BindingResult bindingResult) {
        final UserModelInterface userModel = this.getUserModel(userId);
        userModelDto.setId(userId);
        this.userModelDtoValidator.validate(userModelDto, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userModelDto);
            model.addAttribute("errors", bindingResult);
            setUpdateBehaviour(model);
            return "admin/users/form";
        }

        userModelDto.mapInto(userModel);
        this.usersService.save(userModel);
        return "redirect:/admin/users/";
    }

    private UserModelInterface getUserModel(Integer id) {
        final UserModelInterface userModel = this.usersService.find(id);
        if (userModel == null) {
            throw new NotFoundException();
        }
        return userModel;
    }
}
