package easytests.api.v1.models;

import lombok.Data;


/**
 * @author malinink
 */
@Data
public class Object {
    private Integer id;

    private String firstName;

    private String lastName;

    private String surname;

    private String email;

    private Boolean isAdmin;

    private Integer state;
}
