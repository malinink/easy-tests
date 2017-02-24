package easytests.entities;

import java.util.List;

/**
 * @author malinink
 */
public interface TopicInterface extends EntityInterface {
    Integer getId();

    String getName();

    List<Question> getQuestions();
}
