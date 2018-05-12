package easytests.auth.services;

import easytests.core.models.UserModelInterface;
import easytests.core.services.UsersServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


/**
 * @author malinink
 */
@Service
public class SessionService implements SessionServiceInterface {

    @Autowired
    protected UsersServiceInterface usersService;

    private Boolean userModelFetched = false;

    private UserModelInterface userModel;

    @Override
    public Boolean isUser() {
        return this.isUser(this.getUserModel());
    }

    private Boolean isUser(UserModelInterface userModel) {
        return userModel != null;
    }

    @Override
    public UserModelInterface getUserModel() {
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
