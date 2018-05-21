package easytests.api.v1.models;

import lombok.Data;


/**
 * @author miron97
 */
@Data
public class Testee {
    private Integer id;

    private String firstName;

    private String lastName;

    private String surname;

    private Integer groupNumber;

    private Identity quiz;
}
