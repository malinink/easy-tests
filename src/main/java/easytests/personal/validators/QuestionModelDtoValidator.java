package easytests.personal.validators;

import easytests.common.validators.AbstractDtoValidator;
import easytests.personal.dto.QuestionModelDto;
import easytests.services.QuestionTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;


/**
 * @author firkhraag
 */
@Service
public class QuestionModelDtoValidator extends AbstractDtoValidator {

    @Autowired
    private QuestionTypesService questionTypesService;

    public boolean supports(Class candidate) {
        return QuestionModelDto.class.isAssignableFrom(candidate);
    }

    public void validate(Object obj, Errors errors) {
        final QuestionModelDto questionModelDto = (QuestionModelDto) obj;
        this.validateQuestionTypes(errors, questionModelDto);
    }

    private void validateQuestionTypes(Errors errors, QuestionModelDto questionModelDto) {
        final Integer questionTypeId = questionModelDto.getQuestionTypeId();
        final String questionTypeIdField = "questionTypeId";
        final Integer size = questionTypesService.findAll().size();
        if (questionTypeId == null) {
            reject(errors, questionTypeIdField, "Select question type");
        } else if (!((questionTypeId >= 1) && (questionTypeId <= size))) {
            reject(errors, questionTypeIdField, "Wrong question type");
        }
    }
}
