package easytests.common.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * @author malinink
 */
public abstract class AbstractValidator implements Validator {
    public void reject(Errors errors, String fieldName, String message) {
        errors.rejectValue(fieldName, null, null, message);
    }
}
