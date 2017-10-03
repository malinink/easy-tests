package easytests.core.models;

import easytests.core.entities.TesteeEntity;

/**
 * @author DoZor-80
 */
public interface TesteeModelInterface extends ModelInterface {
    void setId(Integer id);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getSurname();

    void setSurname(String surname);

    Integer getGroupNumber();

    void setGroupNumber(Integer groupNumber);

    QuizModelInterface getQuiz();

    void setQuiz(QuizModelInterface quiz);

    void map(TesteeEntity testeeEntity);
}
