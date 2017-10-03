package easytests.core.services;

import easytests.core.models.AnswerModelInterface;
import easytests.core.models.QuestionModelInterface;
import easytests.core.options.AnswersOptionsInterface;
import java.util.List;

/**
 * @author rezenbekk
 */
public interface AnswersServiceInterface extends ServiceInterface {

    List<AnswerModelInterface> findAll();

    List<AnswerModelInterface> findByQuestion(QuestionModelInterface questionModel);
    
    List<AnswerModelInterface> findByQuestion(
            QuestionModelInterface questionModel, 
            AnswersOptionsInterface answersOptions);

    AnswerModelInterface find(Integer id);

    AnswerModelInterface find(Integer id, AnswersOptionsInterface answersOptions);

    void save(AnswerModelInterface answerModel);

    void save(AnswerModelInterface answerModel, AnswersOptionsInterface answersOptions);

    void save(List<AnswerModelInterface> answerModel);

    void save(List<AnswerModelInterface> answerModel, AnswersOptionsInterface answersOptions);

    void delete(AnswerModelInterface answerModel);

    void delete(AnswerModelInterface answerModel, AnswersOptionsInterface answersOptions);

    void delete(List<AnswerModelInterface> answerModel);

    void delete(List<AnswerModelInterface> answerModel, AnswersOptionsInterface answersOptions);
    
}
