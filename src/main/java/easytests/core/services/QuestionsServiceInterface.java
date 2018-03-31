package easytests.core.services;

import easytests.core.models.QuestionModelInterface;
import easytests.core.models.TopicModelInterface;
import easytests.core.options.QuestionsOptionsInterface;

import java.util.List;

/**
 * @author firkhraag
 */
public interface QuestionsServiceInterface extends ServiceInterface {

    List<QuestionModelInterface> findAll();

    List<QuestionModelInterface> findAll(QuestionsOptionsInterface questionsOptions);

    List<QuestionModelInterface> findByTopic(TopicModelInterface topicModel);

    List<QuestionModelInterface> findByTopic(
            TopicModelInterface topicModel,
            QuestionsOptionsInterface questionsOptions);

    QuestionModelInterface find(Integer id);

    QuestionModelInterface find(Integer id, QuestionsOptionsInterface questionsOptions);

    void save(QuestionModelInterface questionModel);

    void save(QuestionModelInterface questionModel, QuestionsOptionsInterface questionsOptions);

    void save(List<QuestionModelInterface> questionModel);

    void save(List<QuestionModelInterface> questionModel, QuestionsOptionsInterface questionsOptions);

    void delete(QuestionModelInterface questionModel);

    void delete(QuestionModelInterface questionModel, QuestionsOptionsInterface questionsOptions);

    void delete(List<QuestionModelInterface> questionModel);

    void delete(List<QuestionModelInterface> questionModel, QuestionsOptionsInterface questionsOptions);

}
