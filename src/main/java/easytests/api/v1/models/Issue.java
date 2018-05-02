package easytests.api.v1.models;

import lombok.Data;


/**
 * @author Yarik2308
 */
@Data
public class Issue {
    private Integer id;

    private String name;

    private Integer subjectId;
}
