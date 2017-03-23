package easytests.entities;

import easytests.models.TopicModelInterface;
import lombok.Data;

/**
 * @author vkpankov
 */
@Data
public class TopicEntity {

    private Integer id;

    private Integer subjectId;

    public void map(TopicModelInterface topicModel) {
        this.setId(topicModel.getId());
        this.setSubjectId(topicModel.getSubject().getId());
    }

}
