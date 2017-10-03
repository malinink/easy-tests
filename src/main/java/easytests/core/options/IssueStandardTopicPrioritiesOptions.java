package easytests.core.options;

import easytests.core.models.IssueStandardTopicPriorityModelInterface;
import easytests.core.services.IssueStandardTopicPrioritiesServiceInterface;
import easytests.core.services.IssueStandardsServiceInterface;
import easytests.core.services.TopicsServiceInterface;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Setter;

/**
 * @author SingularityA
 */
@EqualsAndHashCode
public class IssueStandardTopicPrioritiesOptions implements IssueStandardTopicPrioritiesOptionsInterface {

    @Setter
    private IssueStandardTopicPrioritiesServiceInterface topicPrioritiesService;

    @Setter
    private TopicsServiceInterface topicsService;

    @Setter
    private IssueStandardsServiceInterface issueStandardsService;

    private TopicsOptionsInterface topicsOptions;

    private IssueStandardsOptionsInterface issueStandardsOptions;

    @Override
    public IssueStandardTopicPrioritiesOptionsInterface
        withTopic(TopicsOptionsInterface topicsOptions) {
        this.topicsOptions = topicsOptions;
        return this;
    }

    @Override
    public IssueStandardTopicPrioritiesOptionsInterface
        withIssueStandard(IssueStandardsOptionsInterface issueStandardsOptions) {
        this.issueStandardsOptions = issueStandardsOptions;
        return this;
    }

    @Override
    public IssueStandardTopicPriorityModelInterface
        withRelations(IssueStandardTopicPriorityModelInterface topicPriorityModel) {

        if (topicPriorityModel == null) {
            return topicPriorityModel;
        }
        if (this.topicsOptions != null) {
            topicPriorityModel.setTopic(this.topicsService
                    .find(topicPriorityModel.getTopic().getId(), this.topicsOptions));
        }
        if (this.issueStandardsOptions != null) {
            topicPriorityModel.setIssueStandard(this.issueStandardsService
                    .find(topicPriorityModel.getIssueStandard().getId(), this.issueStandardsOptions));
        }
        return topicPriorityModel;
    }

    @Override
    public List<IssueStandardTopicPriorityModelInterface>
        withRelations(List<IssueStandardTopicPriorityModelInterface> topicPriorityModels) {

        for (IssueStandardTopicPriorityModelInterface topicPriorityModel: topicPriorityModels) {
            this.withRelations(topicPriorityModel);
        }
        return topicPriorityModels;
    }

    @Override
    public void saveWithRelations(IssueStandardTopicPriorityModelInterface topicPriorityModel) {
        if (this.issueStandardsOptions != null) {
            this.issueStandardsService.save(topicPriorityModel.getIssueStandard(), this.issueStandardsOptions);
        }
        this.topicPrioritiesService.save(topicPriorityModel);
    }

    @Override
    public void deleteWithRelations(IssueStandardTopicPriorityModelInterface topicPriorityModel) {
        this.topicPrioritiesService.delete(topicPriorityModel);
        if (this.issueStandardsOptions != null) {
            this.issueStandardsService.delete(topicPriorityModel.getIssueStandard(), this.issueStandardsOptions);
        }
    }
}
