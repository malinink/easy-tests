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

    Integer getTopicId();

    void setTopicId(Integer topicId);

    List<AnswerModelInterface> getAnswers();

    void setAnswers(List<AnswerModelInterface> answers);

    void map(QuestionEntity questionEntity);
}
