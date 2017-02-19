package easytests.entities.topic;

import easytests.entities.Question;

import java.util.List;

/**
 * @author loriens
 */
public interface CommonFieldsInterface {
    Integer getId();

    String getName();

    List<Question> getQuestions();
}
