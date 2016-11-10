package easytests.entities;

import easytests.entities.user.CommonFieldsInterface;

public class User implements CommonFieldsInterface {
    private String firstName;
    private String lastName;
    private String surname;

    public User(String firstName, String lastName, String surname) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.surname = surname;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String getSurname() {
        return this.surname;
    }

}
