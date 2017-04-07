package easytests.services;

import easytests.models.QuestionModelInterface;
import java.util.List;


/**
 * @author malinink
 */
public interface QuestionsServiceInterface extends ServiceInterface {
    List<QuestionModelInterface> findAll();

    QuestionModelInterface find(Integer id);

    void save(QuestionModelInterface questionModel);

    void delete(QuestionModelInterface questionModel);
}
