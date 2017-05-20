package easytests.options;

import easytests.models.AnswerModelInterface;
import easytests.services.*;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Setter;


/**
 * @author rezenbekk
 */
@EqualsAndHashCode
public class AnswersOptions implements AnswersOptionsInterface {

    @Setter
    private QuestionsServiceInterface questionsService;

    @Setter
    private AnswersServiceInterface answersService;

    private QuestionsOptionsInterface questionsOptions;

    @Override
    public AnswersOptionsInterface withQuestion(QuestionsOptionsInterface questionsOptions) {
        this.questionsOptions = questionsOptions;
        return this;
    }

    @Override
    public AnswerModelInterface withRelations(AnswerModelInterface answerModel) {

        if (answerModel == null) {
            return answerModel;
        }

        if (this.questionsOptions != null) {
            answerModel.setQuestion(this.questionsService.find(
                    answerModel.getQuestion().getId(), this.questionsOptions));
        }

        return answerModel;
    }

    @Override
    public List<AnswerModelInterface> withRelations(List<AnswerModelInterface> answerModels) {

        for (AnswerModelInterface answerModel: answerModels) {
            this.withRelations(answerModel);
        }
        return answerModels;

    }

    @Override
    public void saveWithRelations(AnswerModelInterface answerModel) {

        if (this.questionsOptions != null) {
            this.questionsOptions.withAnswers(this);
            this.questionsOptions.saveWithRelations(answerModel.getQuestion());
            return;
        }

    }

    @Override
    public void deleteWithRelations(AnswerModelInterface answerModel) {

        if (this.questionsOptions != null) {
            this.questionsOptions.withAnswers(this);
            this.questionsOptions.deleteWithRelations(answerModel.getQuestion());
            return;
        }

        this.answersService.delete(answerModel);

    }
}
