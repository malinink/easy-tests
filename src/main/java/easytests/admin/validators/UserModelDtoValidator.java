package easytests.admin.validators;

import easytests.admin.dto.UserModelDto;
import easytests.common.validators.AbstractValidator;
import easytests.models.UserModelInterface;
import easytests.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;


/**
 * @author malinink
 */
@Service
public class UserModelDtoValidator extends AbstractValidator {
    @Autowired
    private UsersService usersService;

    public boolean supports(Class candidate) {
        return UserModelDto.class.isAssignableFrom(candidate);
    }

    public void validate(Object obj, Errors errors) {
        final UserModelDto userModelDto = (UserModelDto) obj;
        this.validateEmailIsUnique(errors, userModelDto);
    }

    private void validateEmailIsUnique(Errors errors, UserModelDto userModelDto) {
        final UserModelInterface userModel = this.usersService.findByEmail(userModelDto.getEmail());
        if (userModel != null) {
            reject(errors, "email", "This Email already present");
        }
    }
}
