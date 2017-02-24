package easytests.entities;

/**
 * @author malinink
 */
public class User implements UserInterface {

    private Integer id;

    private String firstName;

    private String lastName;

    private String surname;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public UserInterface setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public UserInterface setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public UserInterface setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Override
    public String getSurname() {
        return this.surname;
    }

    @Override
    public UserInterface setSurname(String surname) {
        this.surname = surname;
        return this;
    }
}
