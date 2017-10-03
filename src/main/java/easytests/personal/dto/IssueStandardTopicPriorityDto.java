package easytests.personal.dto;

import easytests.core.models.IssueStandardTopicPriorityModelInterface;
import easytests.core.models.empty.IssueStandardModelEmpty;
import easytests.core.models.empty.TopicModelEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Singularity
 */
@Data
public class IssueStandardTopicPriorityDto {

    private Integer id;

    @NotNull(message = "Topic may not be null")
    private Integer topicId;

    @NotNull
    private Boolean isPreferable = false;

    public void map(IssueStandardTopicPriorityModelInterface topicPriorityModel) {
        this.setId(topicPriorityModel.getId());
        this.setTopicId(topicPriorityModel.getTopic().getId());
        this.setIsPreferable(topicPriorityModel.getIsPreferable());
    }

    public void mapInto(IssueStandardTopicPriorityModelInterface topicPriorityModel, Integer issueStandardId) {
        topicPriorityModel.setId(this.getId());
        topicPriorityModel.setTopic(new TopicModelEmpty(this.getTopicId()));
        topicPriorityModel.setIsPreferable(this.getIsPreferable());
        topicPriorityModel.setIssueStandard(new IssueStandardModelEmpty(issueStandardId));
    }
}
