package easytests.entities;

/**
 * @author malinink
 */
public interface TesteeInterface extends EntityInterface {
    TesteeInterface setId(Integer id);

    String getFirstName();

    TesteeInterface setFirstName(String firstName);

    String getLastName();

    TesteeInterface setLastName(String lastName);

    String getSurname();

    TesteeInterface setSurname(String surname);

    Integer getGroupNumber();

    TesteeInterface setGroupNumber(Integer groupNumber);
}
