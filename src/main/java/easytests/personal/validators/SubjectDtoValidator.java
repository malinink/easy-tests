package easytests.personal.validators;

import easytests.models.SubjectModelInterface;
import easytests.personal.dto.SubjectDto;
import easytests.services.SubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author vkpankov
 */
@Component
public class SubjectDtoValidator implements Validator {

    @Autowired
    private SubjectsService subjectsService;

    public boolean supports(Class clazz) {
        return SubjectDto.class.isAssignableFrom(clazz);
    }

    public void validate(Object object, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subjectId", "subjectId.required");
        final SubjectDto subjectDto = (SubjectDto) object;
        final String errorCodeNegative = "negativeValue";

        if (subjectDto.getId() != null) {

            if (subjectDto.getId() <= 0) {
                errors.rejectValue("id", errorCodeNegative, new Object[]{"'id'"}, "id can't be negative");
            }

            final SubjectModelInterface foundSubject = subjectsService.find(subjectDto.getId());
            if (foundSubject == null || foundSubject.getUser().getId() != subjectDto.getUserId()) {
                errors.rejectValue("subject", "", new Object[]{"'subject'"}, "Subject not found");
            }

        }
        if (subjectDto.getName() == null || subjectDto.getName().isEmpty()) {
            errors.rejectValue("name", errorCodeNegative, new Object[]{"'name'"}, "name can't be empty");
        }
    }
}
