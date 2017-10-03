package easytests.core.models;

import easytests.core.entities.TopicEntity;
import java.util.List;


/**
 * @author malinink
 */
public interface TopicModelInterface extends ModelInterface {
    void setId(Integer id);

    String getName();

    void setName(String name);

    SubjectModelInterface getSubject();

    void setSubject(SubjectModelInterface subject);

    List<QuestionModelInterface> getQuestions();

    void setQuestions(List<QuestionModelInterface> questions);

    void map(TopicEntity topicEntity);
}
