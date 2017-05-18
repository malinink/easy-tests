package easytests.auth.controllers;

import easytests.auth.helpers.SessionLoginStoreHelper;
import javax.servlet.http.HttpServletRequest;

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
        final String enterLogin = new String("enter_login");
        final String enterPassword = new String("enter_password");
        final String forgotPassword = new String("forgot_password");
        final String loginText = new String("login_text");
        final String language = new String("sign-in_ru");

        model.addAttribute("login", new SessionLoginStoreHelper(request.getSession(false)).getLogin());
        model.addAttribute("sign_in", msg.getMessage("text", null, language, null));
        model.addAttribute(enterLogin, msg.getMessage(enterLogin, null, language, null));
        model.addAttribute(enterPassword, msg.getMessage(enterPassword, null, language, null));
        model.addAttribute(forgotPassword, msg.getMessage(forgotPassword, null, language, null));
        model.addAttribute(loginText, msg.getMessage(loginText, null, language, null));
        return "auth/sign-in";
    }
}
