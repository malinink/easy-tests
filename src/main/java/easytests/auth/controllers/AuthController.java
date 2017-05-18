package easytests.auth.controllers;

import easytests.auth.helpers.SessionLoginStoreHelper;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.annotation.Bean;
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
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        final ReloadableResourceBundleMessageSource resourseBundle = new
                ReloadableResourceBundleMessageSource();
        resourseBundle.setBasename("classpath:/locales/sign-in");
        resourseBundle.setCacheSeconds(1);
        resourseBundle.setDefaultEncoding("UTF-8");
        return resourseBundle;
    }

    @GetMapping("/sign-in")
    public String signIn(Model model, HttpServletRequest request) {
        final ReloadableResourceBundleMessageSource msg = messageSource();

        model.addAttribute("login", new SessionLoginStoreHelper(request.getSession(false)).getLogin());
        model.addAttribute("sign_in", msg.getMessage("text", null, "sign-in_ru", null));
        model.addAttribute("enter_login", msg.getMessage("enter_login", null, "sign-in_ru", null));
        model.addAttribute("enter_password", msg.getMessage("enter_password", null, "sign-in_ru", null));
        model.addAttribute("forgot_password", msg.getMessage("forgot_password", null, "sign-in_ru", null));
        model.addAttribute("login_text", msg.getMessage("login_text", null, "sign-in_ru", null));
        return "auth/sign-in";
    }
}
