package easytests.common.controllers;

import easytests.auth.services.SessionServiceInterface;
import easytests.core.models.UserModelInterface;
import easytests.core.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;


/**
 * @author malinink
 */
@Controller
public class AbstractPersonalController {

    @Autowired
    protected UsersService usersService;

    @Autowired
    private SessionServiceInterface sessionService;

    @ModelAttribute("currentUserModel")
    public UserModelInterface getCurrentUserModel() {
        return this.sessionService.getUserModel();
    }
}
