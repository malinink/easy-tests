package easytests.api.v1.models;

import lombok.Data;


/**
 * @author RisaMagpie
 */
@Data
public class AdminAnswer {
    private Integer id;

    private String text;

    private Boolean isRight;

    private Integer number;
}
