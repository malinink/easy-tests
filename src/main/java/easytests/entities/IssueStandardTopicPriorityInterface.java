package easytests.entities;

/**
 * @author SingularityA
 */
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
