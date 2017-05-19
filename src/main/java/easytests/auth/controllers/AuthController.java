package easytests.auth.controllers;

import easytests.auth.helpers.SessionLoginStoreHelper;
import easytests.config.LocalizationConfig;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author malinink
 * @author Loriens
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final LocalizationConfig localization = new LocalizationConfig();

    @GetMapping("/sign-in")
    public String signIn(Model model, HttpServletRequest request) {
        final ReloadableResourceBundleMessageSource msg = localization.messageSource();
        final String enterEmail = new String("enter_email");
        final String enterPassword = new String("enter_password");
        final String forgotPassword = new String("forgot_password");
        final String loginText = new String("login_text");
        final String language = new String("sign-in_ru");

        model.addAttribute("login", new SessionLoginStoreHelper(request.getSession(false)).getLogin());
        model.addAttribute("sign_in", msg.getMessage("text", null, language, null));
        model.addAttribute(enterEmail, msg.getMessage(enterEmail, null, language, null));
        model.addAttribute(enterPassword, msg.getMessage(enterPassword, null, language, null));
        model.addAttribute(forgotPassword, msg.getMessage(forgotPassword, null, language, null));
        model.addAttribute(loginText, msg.getMessage(loginText, null, language, null));
        return "auth/sign-in";
    }
}
