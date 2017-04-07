package easytests.services;

import easytests.models.QuestionModelInterface;
import easytests.models.TopicModelInterface;
import easytests.options.QuestionsOptionsInterface;

import java.util.List;


/**
 * @author malinink
 */
public interface QuestionsServiceInterface extends ServiceInterface {
    List<QuestionModelInterface> findAll();

    QuestionModelInterface find(Integer id);

    List<QuestionModelInterface> findByTopic(TopicModelInterface topic);

    List<QuestionModelInterface> findByTopic(
            TopicModelInterface topicModel,
            QuestionsOptionsInterface questionsOptions);

    void save(QuestionModelInterface questionModel);

    void save(List<QuestionModelInterface> questionsModel, QuestionsOptionsInterface questionsOptions);

    void delete(QuestionModelInterface questionModel);

    void delete(List<QuestionModelInterface> questionsModel, QuestionsOptionsInterface questionsOptions);
}
