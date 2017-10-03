package easytests.core.entities;

import easytests.core.models.IssueStandardTopicPriorityModelInterface;
import lombok.Data;

/**
 * @author SingularityA
 */
@Data
public class IssueStandardTopicPriorityEntity {

    private Integer id;

    private Integer topicId;

    private Boolean isPreferable;

    private Integer issueStandardId;

    public void map(IssueStandardTopicPriorityModelInterface topicPriorityModel) {
        this.setId(topicPriorityModel.getId());
        this.setTopicId(topicPriorityModel.getTopic().getId());
        this.setIsPreferable(topicPriorityModel.getIsPreferable());
        this.setIssueStandardId(topicPriorityModel.getIssueStandard().getId());
    }
}
