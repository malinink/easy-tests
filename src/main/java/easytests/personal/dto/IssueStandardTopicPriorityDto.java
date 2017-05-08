package easytests.personal.dto;

import easytests.models.IssueStandardTopicPriorityModelInterface;
import easytests.models.empty.IssueStandardModelEmpty;
import easytests.models.empty.TopicModelEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * @author Singularity
 */
@Data
@SuppressWarnings("checkstyle:MultipleStringLiterals")
public class IssueStandardTopicPriorityDto {

    private Integer id;

    @NotNull(message = "Topic may not be null")
    private Integer topicId;

    @Size(max = 2)
    private String isPreferable;

    public void map(IssueStandardTopicPriorityModelInterface topicPriorityModel) {
        this.setId(topicPriorityModel.getId());
        this.setTopicId(topicPriorityModel.getTopic().getId());
        if (topicPriorityModel.getIsPreferable()) {
            this.isPreferable = "on";
        }
    }

    public void mapInto(IssueStandardTopicPriorityModelInterface topicPriorityModel, Integer issueStandardId) {
        topicPriorityModel.setId(this.getId());
        topicPriorityModel.setTopic(new TopicModelEmpty(this.getTopicId()));
        if ("on".equals(this.isPreferable)) {
            topicPriorityModel.setIsPreferable(true);
        } else {
            topicPriorityModel.setIsPreferable(false);
        }
        topicPriorityModel.setIssueStandard(new IssueStandardModelEmpty(issueStandardId));
    }
}
