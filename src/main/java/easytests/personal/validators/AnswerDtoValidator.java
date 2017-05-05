package easytests.personal.validators;

import easytests.common.validators.AbstractDtoValidator;
import easytests.personal.dto.AnswerDto;
import easytests.services.AnswersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * @author rezenbekk
 */
@Component
public class AnswerDtoValidator extends AbstractDtoValidator {

    @Autowired
    private AnswersService answersService;

    public boolean supports(Class className) {
        return AnswerDto.class.isAssignableFrom(className);
    }

    public void validate(Object object, Errors errors) {
        final AnswerDto answerDto = (AnswerDto) object;
        final String errorCodeNegative = "negativeValue";

        if (answerDto.getTxt() == null || answerDto.getTxt().isEmpty()) {
            errors.rejectValue("txt", errorCodeNegative, new Object[]{"'txt'"}, "Answer text can not be empty");
        }
    }
}
