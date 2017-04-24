package easytests.common.controllers;

import easytests.models.UserModelInterface;
import easytests.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;


/**
 * @author malinink
 */
@Controller
public class AbstractPersonalController {
    @Autowired
    protected UsersService usersService;

    private UserModelInterface userModel;

    private Boolean userModelFetched = false;

    @ModelAttribute("currentUserModel")
    public UserModelInterface getCurrentUserModel() {
        if (!this.userModelFetched) {
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                this.userModel = usersService.findByEmail(authentication.getName());
            }
            this.userModelFetched = true;
        }
        return this.userModel;
    }
}
