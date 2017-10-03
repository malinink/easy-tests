package easytests.core.options;

import easytests.core.models.AnswerModelInterface;
import easytests.core.services.*;

import java.util.List;


/**
 * @author rezenbekk
 */
public interface AnswersOptionsInterface extends OptionsInterface {
    
    void setAnswersService(AnswersServiceInterface answersService);

    void setQuestionsService(QuestionsServiceInterface questionsService);

    AnswersOptionsInterface withQuestion(QuestionsOptionsInterface questionsOptions);

    AnswerModelInterface withRelations(AnswerModelInterface answerModel);

    List<AnswerModelInterface> withRelations(List<AnswerModelInterface> answerModels);

    void saveWithRelations(AnswerModelInterface answerModel);

    void deleteWithRelations(AnswerModelInterface answerModel);
}
