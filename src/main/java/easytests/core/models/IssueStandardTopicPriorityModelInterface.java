package easytests.core.models;

import easytests.core.entities.IssueStandardTopicPriorityEntity;

/**
 * @author SingularityA
 */
public interface IssueStandardTopicPriorityModelInterface extends ModelInterface {

    void setId(Integer id);

    TopicModelInterface getTopic();

    void setTopic(TopicModelInterface topic);

    Boolean getIsPreferable();

    void setIsPreferable(Boolean isPreferable);

    IssueStandardModelInterface getIssueStandard();

    void setIssueStandard(IssueStandardModelInterface issueStandard);

    void map(IssueStandardTopicPriorityEntity topicPriorityEntity);
}
