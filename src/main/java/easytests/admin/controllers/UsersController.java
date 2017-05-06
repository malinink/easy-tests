package easytests.admin.controllers;

import easytests.admin.dto.UserModelDto;
import easytests.admin.validators.UserModelDtoValidator;
import easytests.common.controllers.AbstractCrudController;
import easytests.common.exceptions.NotFoundException;
import easytests.models.UserModel;
import easytests.models.UserModelInterface;
import easytests.options.UsersOptionsInterface;
import easytests.options.builder.UsersOptionsBuilder;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


/**
 * @author malinink
 */
@Controller
@SuppressWarnings("checkstyle:MultipleStringLiterals")
@RequestMapping("/admin/users/")
public class UsersController extends AbstractCrudController {
    @Autowired
    private UserModelDtoValidator userModelDtoValidator;

    @Autowired
    private UsersOptionsBuilder usersOptionsBuilder;

    @GetMapping("")
    public String list(Model model) {
        final List<UserModelInterface> users = this.usersService.findAll();
        model.addAttribute("users", users);
        return "admin/users/list";
    }

    @GetMapping("{userId}/")
    public String view(Model model, @PathVariable Integer userId) {
        final UserModelInterface userModel = this.getUserModel(userId);
        model.addAttribute("user", userModel);
        return "admin/users/view";
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

    @PostMapping("delete/{userId}/")
    public String delete(@PathVariable Integer userId) {
        this.getUserModel(userId);
        final UsersOptionsInterface usersOptions = this.usersOptionsBuilder.forDelete();
        final UserModelInterface userModel = this.usersService.find(userId, usersOptions);

        this.usersService.delete(userModel, usersOptions);

        return "redirect:/admin/users/";
    }

    private UserModelInterface getUserModel(Integer id, UsersOptionsInterface userOptions) {
        final UserModelInterface userModel = this.usersService.find(id, userOptions);
        if (userModel == null) {
            throw new NotFoundException();
        }
        return userModel;
    }

    private UserModelInterface getUserModel(Integer id) {
        return getUserModel(id, this.usersOptionsBuilder.forAuth());
    }

    @ModelAttribute("usersListUrl")
    public String getUsersListUrl() {
        return "/admin/users/";
    }

    @ModelAttribute("usersCreateUrl")
    public String getUsersCreateUrl() {
        return "/admin/users/create/";
    }

    @ModelAttribute("userViewUrlTemplate")
    public String getUserViewUrlTemplate() {
        return "/admin/users/%d/";
    }

    @ModelAttribute("userUpdateUrlTemplate")
    public String getUserUpdateUrlTemplate() {
        return "/admin/users/update/%d/";
    }

    @ModelAttribute("userDeleteUrlTemplate")
    public String getUserDeleteUrlTemplate() {
        return "/admin/users/delete/%d/";
    }
}
