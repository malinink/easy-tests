package easytests.entities;

/**
 * @author malinink
 */
public interface UserInterface extends EntityInterface {
    UserInterface setId(Integer id);

    String getFirstName();

    UserInterface setFirstName(String firstName);

    String getLastName();

    UserInterface setLastName(String lastName);

    String getSurname();

    UserInterface setSurname(String surname);
}
