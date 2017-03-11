package easytests.models;

import easytests.entities.QuestionEntity;
import java.util.List;

/**
 * @author firkhraag
 */
public interface QuestionModelInterface extends ModelInterface {

    void setId(Integer id);

    String getText();

    void setText(String text);

    Integer getType();

    void setType(Integer type);

    TopicModelInterface getTopic();

    void setTopic(TopicModelInterface user);

    List<AnswerModelInterface> getAnswers();

    void setAnswers(List<AnswerModelInterface> answers);

    void map(QuestionEntity questionEntity);
}
