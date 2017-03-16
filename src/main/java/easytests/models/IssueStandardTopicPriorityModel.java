package easytests.models;

import easytests.entities.IssueStandardTopicPriorityEntity;
import lombok.Data;

/**
 * @author SingularityA
 */
@Data
public class IssueStandardTopicPriorityModel implements IssueStandardTopicPriorityModelInterface {

    private Integer id;

    private Integer topicId;

    private Boolean isPreferable;

    private IssueStandardModelInterface issueStandard;

    public void map(IssueStandardTopicPriorityEntity topicPriorityEntity) {
        this.setId(topicPriorityEntity.getId());
        this.setTopicId(topicPriorityEntity.getTopicId());
        this.setIsPreferable(topicPriorityEntity.getIsPreferable());
    }
}
