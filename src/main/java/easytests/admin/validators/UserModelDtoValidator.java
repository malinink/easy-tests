package easytests.admin.validators;

import easytests.admin.dto.UserModelDto;
import easytests.common.validators.AbstractDtoValidator;
import easytests.models.UserModelInterface;
import easytests.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;


/**
 * @author malinink
 */
@Service
public class UserModelDtoValidator extends AbstractDtoValidator {
    @Autowired
    private UsersService usersService;

    public boolean supports(Class candidate) {
        return UserModelDto.class.isAssignableFrom(candidate);
    }

    public void validate(Object obj, Errors errors) {
        final UserModelDto userModelDto = (UserModelDto) obj;
        this.validateIdEquals(errors, userModelDto);
        this.validateEmailIsUnique(errors, userModelDto);
        this.validatePassword(errors, userModelDto);
        this.validatePasswordRepeat(errors, userModelDto);
    }

    private void validateEmailIsUnique(Errors errors, UserModelDto userModelDto) {
        final UserModelInterface userModel = this.usersService.findByEmail(userModelDto.getEmail());
        if (userModel != null) {
            reject(errors, "email", "This Email already present");
        }
    }

    private void validatePassword(Errors errors, UserModelDto userModelDto) {
        final String password = userModelDto.getPassword();
        final String passwordField = "password";
        if ("".equals(password) || (password == null)) {
            if (userModelDto.getRouteId() == null) {
                reject(errors, passwordField, "Password is required on creating user procedure");
            }
            return;
        }
        if (password.length() < 8) {
            reject(errors, passwordField, "Password must be at least 8 symbols");
        }
        // TODO add more specific checks on password
    }

    private void validatePasswordRepeat(Errors errors, UserModelDto userModelDto) {
        final String password = userModelDto.getPassword();
        final String passwordRepeat = userModelDto.getPasswordRepeat();
        if (((password == null) && (passwordRepeat != null))
                || ((password != null) && !password.equals(passwordRepeat))) {
            reject(errors, "passwordRepeat", "Password repeat differs from password");
        }
    }
}
