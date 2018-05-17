package easytests.api.v1.models;

import lombok.Data;


/**
 * @author VeronikaRevjakina
 */
@Data
public class Subject {
    private Integer id;

    private String name;

    private String description;

    private Identity user;
}
