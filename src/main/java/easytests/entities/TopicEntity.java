package easytests.entities;

import easytests.models.TopicModelInterface;
import lombok.Data;


/**
 * @author malinink
 */
@Data
public class TopicEntity {
    private Integer id;

    private String name;

    private Integer subjectId;

    public void map(TopicModelInterface topicModel) {
        this.setId(topicModel.getId());
        this.setName(topicModel.getName());
        this.setSubjectId(topicModel.getSubject().getId());
    }
}
