package easytests.api.v1.models;

import lombok.Data;

/**
 * @author lelay
 */
@Data
public class Topic {
    private Integer id;

    private String name;

    private Identity subject;
}
