package easytests.entities.topic;

import java.util.List;

/**
 * @author loriens
 */
public interface CommonFieldsInterface {
    Integer getId();

    String getName();

    void setName(String name);

    List<Question> getQuestions();

    void addQuestion(Question question);
}
