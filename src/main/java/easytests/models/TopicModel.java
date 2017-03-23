package easytests.models;

import easytests.entities.TopicEntity;
import easytests.models.empty.SubjectModelEmpty;
import lombok.Data;

/**
 * @author xxx
 */
@Data
public class TopicModel implements TopicModelInterface {

    private Integer id;

    private SubjectModelInterface subjectModel;

    public Integer getId() {
        return this.id;
    }

    public SubjectModelInterface getSubject() {
        return this.subjectModel;
    }

    public void map(TopicEntity topicEntity) {

        this.setId(topicEntity.getId());
        this.setSubjectModel(new SubjectModelEmpty(topicEntity.getSubjectId()));

    }
}
