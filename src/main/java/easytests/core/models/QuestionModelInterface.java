package easytests.core.models;

import easytests.core.entities.QuestionEntity;
import java.util.List;

/**
 * @author firkhraag
 */
public interface QuestionModelInterface extends ModelInterface {

    void setId(Integer id);

    String getText();

    void setText(String text);

    QuestionTypeModelInterface getQuestionType();

    void setQuestionType(QuestionTypeModelInterface questionType);

    TopicModelInterface getTopic();

    void setTopic(TopicModelInterface topic);

    List<AnswerModelInterface> getAnswers();

    void setAnswers(List<AnswerModelInterface> answers);

    void map(QuestionEntity questionEntity);
}
