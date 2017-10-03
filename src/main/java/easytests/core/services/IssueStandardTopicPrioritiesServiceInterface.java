package easytests.core.services;

import easytests.core.models.IssueStandardModelInterface;
import easytests.core.models.IssueStandardTopicPriorityModelInterface;
import easytests.core.options.IssueStandardTopicPrioritiesOptionsInterface;
import java.util.List;

/**
 * @author SingularityA
 */
public interface IssueStandardTopicPrioritiesServiceInterface extends ServiceInterface {

    List<IssueStandardTopicPriorityModelInterface> findAll();

    List<IssueStandardTopicPriorityModelInterface> findAll(
            IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions);

    IssueStandardTopicPriorityModelInterface find(
            Integer id);

    IssueStandardTopicPriorityModelInterface find(
            Integer id,
            IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions);

    List<IssueStandardTopicPriorityModelInterface> findByIssueStandard(
            IssueStandardModelInterface issueStandard);

    List<IssueStandardTopicPriorityModelInterface> findByIssueStandard(
            IssueStandardModelInterface issueStandard,
            IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions);

    void save(IssueStandardTopicPriorityModelInterface topicPriorityModel);

    void save(List<IssueStandardTopicPriorityModelInterface> topicPriorityModels);

    void save(IssueStandardTopicPriorityModelInterface topicPriorityModel,
              IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions);

    void save(List<IssueStandardTopicPriorityModelInterface> topicPriorityModels,
              IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions);

    void delete(IssueStandardTopicPriorityModelInterface topicPriorityModel);

    void delete(List<IssueStandardTopicPriorityModelInterface> topicPriorityModels);

    void delete(IssueStandardTopicPriorityModelInterface topicPriorityModel,
                IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions);

    void delete(List<IssueStandardTopicPriorityModelInterface> topicPriorityModels,
                IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions);
}
