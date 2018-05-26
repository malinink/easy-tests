package easytests.api.v1.models;

import lombok.Data;




/**
 * @author SvetlanaTselikova
 */
@Data
public class User {
    private Integer id;

    private String firstName;

    private String lastName;

    private String surname;

    private String email;

    private Boolean isAdmin;

    private Integer state;

}

