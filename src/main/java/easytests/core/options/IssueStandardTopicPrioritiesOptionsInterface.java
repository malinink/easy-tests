package easytests.core.options;

import easytests.core.models.IssueStandardTopicPriorityModelInterface;
import easytests.core.services.IssueStandardTopicPrioritiesServiceInterface;
import easytests.core.services.IssueStandardsServiceInterface;
import easytests.core.services.TopicsServiceInterface;

import java.util.List;

/**
 * @author SingularityA
 */
public interface IssueStandardTopicPrioritiesOptionsInterface extends OptionsInterface {

    void setTopicPrioritiesService(IssueStandardTopicPrioritiesServiceInterface topicPrioritiesService);

    void setTopicsService(TopicsServiceInterface topicsService);

    void setIssueStandardsService(IssueStandardsServiceInterface issueStandardService);

    IssueStandardTopicPrioritiesOptionsInterface
    withTopic(TopicsOptionsInterface topicsOptions);

    IssueStandardTopicPrioritiesOptionsInterface
    withIssueStandard(IssueStandardsOptionsInterface issueStandardsOptions);

    IssueStandardTopicPriorityModelInterface
    withRelations(IssueStandardTopicPriorityModelInterface topicPriorityModel);

    List<IssueStandardTopicPriorityModelInterface>
    withRelations(List<IssueStandardTopicPriorityModelInterface> topicPriorityModels);

    void saveWithRelations(IssueStandardTopicPriorityModelInterface topicPriorityModel);

    void deleteWithRelations(IssueStandardTopicPriorityModelInterface topicPriorityModel);
}
