package easytests.core.entities;

/**
 * @author SingularityA
 * @deprecated cause of models
 */
@Deprecated
public interface IssueStandardTopicPriorityInterface extends EntityInterface {

    IssueStandardTopicPriorityInterface setId(Integer id);

    Integer getTopicId();

    IssueStandardTopicPriorityInterface setTopicId(Integer topicId);

    Priority getPriority();

    IssueStandardTopicPriorityInterface setPriority(Priority priority);

    Integer getIssueStandardId();

    IssueStandardTopicPriorityInterface setIssueStandardId(Integer issueStandardId);

    String toString();
}
