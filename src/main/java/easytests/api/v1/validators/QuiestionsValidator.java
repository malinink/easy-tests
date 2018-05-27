package easytests.api.v1.validators;

import easytests.api.v1.exceptions.BadRequestException;
import easytests.core.models.AnswerModelInterface;
import easytests.core.models.QuestionModelInterface;

/**
 * @author SakhPrace
 */
public class QuiestionsValidator {

    private AnswersValidator answersValidator = new AnswersValidator();

    public void validateQuestion(QuestionModelInterface questionModel) throws Exception {
        int rightAnswersCount = 0;

        //for (AnswerModelInterface answerModel: questionModel.getAnswers()) {
        //    this.answersValidator.validateAnswer(answerModel);
        //    if (answerModel.getRight()) {
        //        ++rightAnswersCount;
        //    }
        //}
        switch (questionModel.getQuestionType().getId()) {
            case 1:
                if (rightAnswersCount == 0) {
                    throw new BadRequestException("Right Answer must be exist");
                }
                if (rightAnswersCount > 1) {
                    throw new BadRequestException("This Question can have only one right Answer");
                }
                break;

            case 2:
                if (rightAnswersCount == 0) {
                    throw new BadRequestException("Right Answers must be exist");
                }
                if (rightAnswersCount == 1) {
                    throw new BadRequestException("This Question can't have only one right Answer");
                }
                if (rightAnswersCount == questionModel.getAnswers().size()) {
                    throw new BadRequestException("This Question can't have all right Answers");
                }
                break;

            case 3:
                break;

            /**
             * TODO
             * Add check for Numeric And Text Answers
             */
            default: break;
        }
    }
}
