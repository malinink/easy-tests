package easytests.personal.validators;

import easytests.common.exceptions.NotFoundException;
import easytests.common.validators.AbstractDtoValidator;
import easytests.core.services.QuestionsService;
import easytests.personal.dto.AnswerDto;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


/**
 * @author rezenbekk
 */
@SuppressWarnings({
        "checkstyle:LineLength",
        "checkstyle:CyclomaticComplexity" })
@Component
public class AnswerDtoValidator extends AbstractDtoValidator {

    @Autowired
    private QuestionsService questionsService;

    public boolean supports(Class className) {
        return AnswerDto.class.isAssignableFrom(className);
    }

    public void validate(Object object, Errors errors) {
        final AnswerDto answerDto = (AnswerDto) object;
        final String emptyTextError = "emptyText";
        final String textTooLongError = "textTooLong";
        final String invalidQuestionError = "invalidQuestion";

        if (answerDto.getTxt() == null || answerDto.getTxt().isEmpty()) {
            errors.reject(emptyTextError, "Answer text can not be empty");
        }
        if (answerDto.getTxt().length() > 255) {
            errors.reject(textTooLongError, "Answer text too long");
        }
        if (answerDto.getQuestionId() == null || questionsService.find(answerDto.getQuestionId()) == null) {
            errors.reject(invalidQuestionError, "Invalid question settings");
        }
    }

    public void validateWithQuestionType(Object object, Integer questionType, Errors errors) {
        final List<AnswerDto> answers = (List<AnswerDto>) object;
        if (answers == null) {
            return;
        }
        Integer rightCount = 0;
        Boolean serialNumbersConflict = false;
        final Set<Integer> serialNumbers = new HashSet<Integer>();
        for (AnswerDto answer
                : answers) {
            if (answer.getRight() != null) {
                if (answer.getRight().equals("on")) {
                    rightCount = rightCount + 1;
                }
            }
            if (serialNumbers.contains(answer.getSerialNumber()) || answer.getSerialNumber() == null) {
                serialNumbersConflict = true;
            } else {
                serialNumbers.add(answer.getSerialNumber());
            }
        }
        final String rightCountError = "right";
        final String serialNumberConflictError = "serialNumber";
        final String answersCountError = "answersCount";
        switch (questionType) {
            //Один ответ
            case 1:
                if (rightCount != 1) {
                    errors.reject(rightCountError, "Not one answer in an one-answer question");
                }
                break;
            //Много ответов
            case 2:
                break;
            //Нумерация
            case 3:
                if (serialNumbersConflict) {
                    errors.reject(serialNumberConflictError, "Invalid numeration. Check for repeating and missing order values");
                }
                break;
            //Текст
            case 4:
                if (answers.size() != 1) {
                    errors.reject(answersCountError, "There is more than one answer");
                }
                break;
            default:
                throw new NotFoundException();
        }
    }
}
