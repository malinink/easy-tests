package easytests.personal.validators;

import easytests.common.validators.AbstractDtoValidator;
import easytests.personal.dto.TopicDto;
import org.springframework.validation.Errors;

/**
 * @author DoZor-80
 */
public class TopicModelDtoValidator extends AbstractDtoValidator {

    public boolean supports(Class candidate) {
        return TopicDto.class.isAssignableFrom(candidate);
    }

    public void validate(Object obj, Errors errors) {

    }
}
