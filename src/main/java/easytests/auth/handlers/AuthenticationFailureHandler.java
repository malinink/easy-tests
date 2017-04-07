package easytests.auth.handlers;

import easytests.auth.helpers.SessionLoginStoreHelper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;


/**
 * @author malinink
 */
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private String usernameParameter;

    public AuthenticationFailureHandler(String defaultFailureUrl, String usernameParameter) {
        super(defaultFailureUrl);
        this.usernameParameter = usernameParameter;
    }

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception)
            throws IOException, ServletException {
        super.onAuthenticationFailure(request, response, exception);

        final HttpSession session = request.getSession(false);
        final SessionLoginStoreHelper sessionLoginStoreHelper = new SessionLoginStoreHelper(session);
        if (sessionLoginStoreHelper.exists() || this.isAllowSessionCreation()) {
            final String login = request.getParameter(this.usernameParameter);
            sessionLoginStoreHelper.setLogin(login);
        }
    }
}
