package easytests.personal.validators;

import easytests.common.validators.AbstractDtoValidator;
import easytests.personal.dto.SubjectDto;
import org.springframework.validation.Errors;

/**
 * @author xxx
 */
public class SubjectModelDtoValidator extends AbstractDtoValidator {

    public boolean supports(Class candidate) {
        return SubjectDto.class.isAssignableFrom(candidate);
    }

    public void validate(Object obj, Errors errors) {
        validateIdEquals(errors, (SubjectDto) obj);
    }
}
