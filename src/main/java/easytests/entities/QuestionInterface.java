package easytests.entities;

import java.util.List;

/**
 * @author firkhraag
 */
public interface QuestionInterface extends EntityInterface {

    QuestionInterface setId(Integer id);

    String getText();

    QuestionInterface setText(String text);

    Integer getType();

    QuestionInterface setType(Integer type);

    Integer getTopicId();

    QuestionInterface setTopicId(Integer topicId);

    List<AnswerInterface> getAnswers();

    QuestionInterface setAnswers(List<AnswerInterface> answers);
}
