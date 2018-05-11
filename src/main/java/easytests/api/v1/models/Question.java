package easytests.api.v1.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;


/**
 * @author RisaMagpie
 */
@Data
public class Question {
    private Integer id;

    private String text;

    private Integer type;

    private Identity topic;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Identity> answers;
}
