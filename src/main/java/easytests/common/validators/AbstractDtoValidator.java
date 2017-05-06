package easytests.common.validators;

import org.springframework.validation.Errors;


/**
 * @author malinink
 */
public abstract class AbstractDtoValidator extends AbstractValidator {
    protected void validateIdEquals(Errors errors, Integer origin, Integer compare) {
        this.validateEquals(errors, "id", origin, compare, "Id must be equal");
    }

    protected void validateEquals(Errors errors, String fieldName, Integer origin, Integer compare, String message) {
        if (((origin == null) && (compare != null)) || ((origin != null) && !origin.equals(compare))) {
            reject(errors, fieldName, message);
        }
    }
}
