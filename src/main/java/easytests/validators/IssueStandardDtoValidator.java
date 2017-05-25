package easytests.validators;

import easytests.dto.IssueStandardDto;
import easytests.models.IssueStandardModelInterface;
import easytests.models.SubjectModelInterface;
import easytests.services.IssueStandardsService;
import easytests.services.SubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author SingularityA
 */
@Component
public class IssueStandardDtoValidator implements Validator {

    @Autowired
    private IssueStandardsService issueStandardsService;

    @Autowired
    private SubjectsService subjectsService;

    public boolean supports(Class clazz) {
        return IssueStandardDto.class.isAssignableFrom(clazz);
    }

    public void validate(Object object, Errors errors) {
        // basic checks
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subjectId", "subjectId.required");

        final IssueStandardDto issueStandardDto = (IssueStandardDto) object;
        final String errorCodeNegative = "negativeValue";

        if (issueStandardDto.getId() != null && issueStandardDto.getId() <= 0) {
            errors.rejectValue("id", errorCodeNegative, new Object[]{"'id'"}, "id can't be negative");
        }
        if (issueStandardDto.getTimeLimit() != null && issueStandardDto.getTimeLimit() <= 0) {
            errors.rejectValue("timeLimit", errorCodeNegative, new Object[]{"'timeLimit'"},
                    "timeLimit can't be negative");
        }
        if (issueStandardDto.getQuestionsNumber() != null && issueStandardDto.getQuestionsNumber() <= 0) {
            errors.rejectValue("questionsNumber", errorCodeNegative, new Object[]{"'questionsNumber'"},
                    "questionsNumber can't be negative");
        }

        // data checks
        final SubjectModelInterface subjectModel = this.subjectsService.find(issueStandardDto.getSubjectId());

        if (subjectModel == null) {
            errors.reject("subject.required",
                    new Object[]{"'subject'"},
                    "subject with id  = [" + issueStandardDto.getSubjectId() + "] doesn't exist");
            return;
        }

        final IssueStandardModelInterface issueStandardModel
                = this.issueStandardsService.findBySubject(subjectModel);

        if (issueStandardDto.getId() != null) {
            if (issueStandardModel == null) {
                errors.reject("",
                        new Object[]{"'issueStandard '"},
                        "subject with id = [" + issueStandardDto.getSubjectId() + "] doesn't have issueStandard");
            } else if (!issueStandardDto.getId().equals(issueStandardModel.getId())) {
                errors.reject("",
                        new Object[]{"' issueStandard'"},
                        "IssueStandard with id = [" + issueStandardDto.getId() + "] doesn't belong to subject"
                                + " with id = [" + issueStandardDto.getSubjectId() + "]");
            }
        } else {
            if (issueStandardModel != null) {
                errors.reject("",
                        new Object[]{"'issueStandard'"},
                        "issueStandard for subject with id = [" + issueStandardDto.getSubjectId() + "] already exists");
            }
        }
    }
}
