package easytests.options;

import easytests.models.IssueStandardTopicPriorityModelInterface;
import easytests.services.IssueStandardTopicPrioritiesServiceInterface;
import easytests.services.IssueStandardsServiceInterface;
import easytests.services.TopicsServiceInterface;

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
