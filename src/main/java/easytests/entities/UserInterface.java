package easytests.entities;

/**
 * @author malinink
 * @deprecated cause of models
 */
@Deprecated
public interface UserInterface extends EntityInterface {
    void setId(Integer id);

    String getFirstName();

    UserInterface setFirstName(String firstName);

    String getLastName();

    UserInterface setLastName(String lastName);

    String getSurname();

    UserInterface setSurname(String surname);
}
