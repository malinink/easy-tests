package easytests.options;

import easytests.models.AnswerModelInterface;
import easytests.services.*;

import java.util.List;


/**
 * @author rezenbekk
 */
public interface AnswersOptionsInterface {

    AnswersOptionsInterface withQuestion(QuestionsOptionsInterface questionsOptions);

    AnswerModelInterface withRelations(AnswerModelInterface answerModel);

    List<AnswerModelInterface> withRelations(List<AnswerModelInterface> answerModels);

    void saveWithRelations(AnswerModelInterface answerModel);

    void deleteWithRelations(AnswerModelInterface answerModel);
}
