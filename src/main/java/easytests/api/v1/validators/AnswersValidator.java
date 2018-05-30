package easytests.api.v1.validators;

import easytests.api.v1.exceptions.BadRequestException;
import easytests.core.models.AnswerModelInterface;

/**
 * @author SakhPrace
 */
public class AnswersValidator {

    public void validateAnswer(AnswerModelInterface answerModel) throws Exception {

        if (answerModel.getTxt().length() > 255) {
            throw new BadRequestException("Answer text too long");
        }
        if (!(answerModel.getId() == null)) {
            throw new BadRequestException("Answer Id must be absent");
        }
        if (!answerModel.getTxt().getClass().getName().equals("String".getClass().getName())) {
            throw new BadRequestException("Text in Answer must be String");
        }
        if (answerModel.getTxt().equals("") || answerModel.getTxt() == null) {
            throw new BadRequestException("Text in answer must be not absent");
        }
        if (!answerModel.getRight().getClass().getName().equals(Boolean.class.getName())) {
            throw new BadRequestException("Type of isRight must be boolean");
        }
        if (!answerModel.getSerialNumber().getClass().getName().equals(Integer.class.getName())) {
            throw new BadRequestException("Serial Number must be Integer");
        }
    }

}
