package easytests.options;

import easytests.models.IssueStandardTopicPriorityModelInterface;
import easytests.services.IssueStandardTopicPrioritiesServiceInterface;
import easytests.services.IssueStandardsServiceInterface;
import java.util.List;
import lombok.Setter;

/**
 * @author SingularityA
 */
public class IssueStandardTopicPrioritiesOptions implements IssueStandardTopicPrioritiesOptionsInterface {

    @Setter
    private IssueStandardTopicPrioritiesServiceInterface topicPrioritiesService;

    @Setter
    private IssueStandardsServiceInterface issueStandardsService;

    private IssueStandardsOptionsInterface issueStandardsOptions;

    @Override
    public IssueStandardTopicPrioritiesOptionsInterface
        withIssueStandard(IssueStandardsOptionsInterface issueStandardsOptions) {
        this.issueStandardsOptions = issueStandardsOptions;
        return this;
    }

    @Override
    public IssueStandardTopicPriorityModelInterface
        withRelations(IssueStandardTopicPriorityModelInterface topicPriorityModel) {

        if (this.issueStandardsOptions != null) {
            topicPriorityModel.setIssueStandard(
                    this.issueStandardsService.find(topicPriorityModel.getIssueStandard().getId()));
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
            this.issueStandardsService.save(topicPriorityModel.getIssueStandard());
        }
        this.topicPrioritiesService.save(topicPriorityModel);
    }

    @Override
    public void deleteWithRelations(IssueStandardTopicPriorityModelInterface topicPriorityModel) {
        this.topicPrioritiesService.delete(topicPriorityModel);
        if (this.issueStandardsOptions != null) {
            this.issueStandardsService.delete(topicPriorityModel.getIssueStandard());
        }
    }
}
