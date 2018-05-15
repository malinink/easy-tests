package easytests.api.v1.models;

import java.util.List;
import lombok.Data;


/**
 * @author RisaMagpie
 */
@Data
public class Question {
    private Integer id;

    private String text;

    private Integer type;

    private Identity topic;

    private List<AdminAnswer> answers;
}
