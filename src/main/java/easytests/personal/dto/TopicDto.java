package easytests.personal.dto;

import easytests.models.TopicModelInterface;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author DoZor-80
 */
@Data
public class TopicDto {

    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String name;

    public void map(TopicModelInterface topicModel) {
        this.setName(topicModel.getName());
    }

    public void mapInto(TopicModelInterface topicModel) {
        topicModel.setName(this.getName());
    }
}
