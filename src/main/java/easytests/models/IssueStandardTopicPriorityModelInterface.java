package easytests.models;

import easytests.entities.IssueStandardTopicPriorityEntity;

/**
 * @author SingularityA
 */
public interface IssueStandardTopicPriorityModelInterface extends ModelInterface {

    void setId(Integer id);

    Integer getTopicId();

    void setTopicId(Integer topicId);

    Boolean getIsPreferable();

    void setIsPreferable(Boolean isPreferable);

    IssueStandardModelInterface getIssueStandard();

    void setIssueStandard(IssueStandardModelInterface issueStandard);

    void map(IssueStandardTopicPriorityEntity topicPriorityEntity);
}
