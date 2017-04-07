package easytests.models;

import easytests.entities.TesteeEntity;

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

    void map(TesteeEntity testeeEntity);
}
