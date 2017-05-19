package easytests.auth.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author DoZor-80
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationFailureHandlerTest {
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private HttpSession httpSession;

    @Test
    public void testOnAuthenticationFailure() throws Exception {
        final String usernameParameter = "login";
        final String defaultFailureUrl = "/login?error=401";
        HttpServletRequest servletRequest = mock(HttpServletRequest.class);
        MockHttpServletResponse response = new MockHttpServletResponse();
        AuthenticationException exception = new BadCredentialsException("error_message");
        when(servletRequest.getSession()).thenReturn(httpSession);

        authenticationFailureHandler = new AuthenticationFailureHandler(defaultFailureUrl, usernameParameter);
        authenticationFailureHandler.onAuthenticationFailure(servletRequest, response, exception);

        verify(servletRequest, times(1)).getParameter(usernameParameter);
    }
}
