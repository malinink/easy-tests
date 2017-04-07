package easytests.services;

import easytests.models.AnswerModelInterface;
import easytests.models.QuestionModelInterface;
import java.util.List;

/**
 * @author rezenbekk
 */
public interface AnswersServiceInterface extends ServiceInterface {

    List<AnswerModelInterface> findAll();

    List<AnswerModelInterface> findByQuestion(QuestionModelInterface questionModel);

    AnswerModelInterface find(Integer id);

    void save(AnswerModelInterface answerModel);

    void delete(AnswerModelInterface answerModel);
}
