package easytests.entities;

/**
 * @author DoZor-80
 */
public class Testee implements TesteeInterface {

    private Integer id;

    private String firstName;

    private String lastName;

    private String surname;

    private Integer groupNumber;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public Testee setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public Testee setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public Testee setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Override
    public String getSurname() {
        return this.surname;
    }

    @Override
    public Testee setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    @Override
    public Integer getGroupNumber() {
        return this.groupNumber;
    }

    @Override
    public Testee setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
        return this;
    }
}
