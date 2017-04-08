package easytests.auth.controllers;

import easytests.auth.helpers.SessionLoginStoreHelper;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author malinink
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/sign-in")
    public String signIn(Model model, HttpServletRequest request) {
        final String csrfTokenName = "_csrf";
        final CsrfToken token = (CsrfToken) request.getAttribute(csrfTokenName);
        model.addAttribute(csrfTokenName, token);
        model.addAttribute("login", new SessionLoginStoreHelper(request.getSession(false)).getLogin());
        return "auth/sign-in";
    }
}
