package easytests.entities;

import easytests.entities.user.CommonFieldsInterface;

/**
 * @author malinink
 */
public class User implements CommonFieldsInterface {

    private Integer id;

    private String firstName;

    private String lastName;

    private String surname;

    public Integer getId() {
        return this.id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Override
    public String getSurname() {
        return this.surname;
    }

    public User setSurname(String surname) {
        this.surname = surname;
        return this;
    }
}
