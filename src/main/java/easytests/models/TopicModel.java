package easytests.models;

import easytests.entities.TopicEntity;
import easytests.models.empty.ModelsListEmpty;
import easytests.models.empty.SubjectModelEmpty;
import java.util.List;
import lombok.Data;

/**
 * @author malinink
 */
@Data
public class TopicModel implements TopicModelInterface {
    private Integer id;

    private String name;

    private SubjectModelInterface subject;

    private List<QuestionModelInterface> questions;

    public void map(TopicEntity topicEntity) {
        this.setId(topicEntity.getId());
        this.setName(topicEntity.getName());
        this.setSubject(new SubjectModelEmpty(topicEntity.getSubjectId()));
        this.setQuestions(new ModelsListEmpty());
    }
}
