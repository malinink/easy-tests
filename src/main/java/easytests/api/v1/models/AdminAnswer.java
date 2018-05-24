package easytests.api.v1.models;

import lombok.Data;


/**
 * @author RisaMagpie
 */
@Data
public class AdminAnswer {
    private Integer id;

    private String txt;

    private Boolean right;

    private Integer number;
}
