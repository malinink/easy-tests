package easytests.entities;

/**
 * @author SingularityA
 */
public class IssueStandardTopicPriority implements IssueStandardTopicPriorityInterface {

    private Integer id;

    private Integer topicId;

    private Priority priority;

    private Integer issueStandardId;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public IssueStandardTopicPriorityInterface setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public Integer getTopicId() {
        return topicId;
    }

    @Override
    public IssueStandardTopicPriorityInterface setTopicId(Integer topicId) {
        this.topicId = topicId;
        return this;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public IssueStandardTopicPriorityInterface setPriority(Priority priority) {
        this.priority = priority;
        return this;
    }

    @Override
    public Integer getIssueStandardId() {
        return issueStandardId;
    }

    @Override
    public IssueStandardTopicPriorityInterface setIssueStandardId(Integer issueStandardId) {
        this.issueStandardId = issueStandardId;
        return this;
    }

    @Override
    public String toString() {
        return "IssueStandardTopicPriority : ["
                + " id = " + getId()
                + ", topicId = " + getTopicId()
                + ", priority = " + getPriority()
                + ", issueStandardId = " + getIssueStandardId()
                + " ]";
    }
}
