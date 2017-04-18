package easytests.models;

import easytests.entities.IssueStandardTopicPriorityEntity;
import easytests.models.empty.IssueStandardModelEmpty;
import easytests.models.empty.TopicModelEmpty;
import lombok.Data;

/**
 * @author SingularityA
 */
@Data
public class IssueStandardTopicPriorityModel implements IssueStandardTopicPriorityModelInterface {

    private Integer id;

    private TopicModelInterface topic;

    private Boolean isPreferable;

    private IssueStandardModelInterface issueStandard;

    public void map(IssueStandardTopicPriorityEntity topicPriorityEntity) {
        this.setId(topicPriorityEntity.getId());
        this.setTopic(new TopicModelEmpty(topicPriorityEntity.getTopicId()));
        this.setIsPreferable(topicPriorityEntity.getIsPreferable());
        this.setIssueStandard(new IssueStandardModelEmpty(topicPriorityEntity.getIssueStandardId()));
    }
}
