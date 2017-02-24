package easytests.entities;

import java.util.List;

/**
 * @author vkpankov
 */
public class Subject implements SubjectInterface {

    private Integer id;

    private String name;

    private List<TopicInterface> topics;

    private Integer userId;

    private IssueStandardInterface issueStandard;

    @Override
    public Integer getId() {
        return this.id;
    }

    public SubjectInterface setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public SubjectInterface setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public List<TopicInterface> getTopics() {
        return topics;
    }

    @Override
    public SubjectInterface setTopics(List<TopicInterface> topics) {
        this.topics = topics;
        return this;
    }

    @Override
    public Integer getUserId() {
        return userId;
    }

    @Override
    public SubjectInterface setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public IssueStandardInterface getIssueStandard() {
        return issueStandard;
    }

    @Override
    public SubjectInterface setIssueStandard(IssueStandardInterface issueStandard) {
        this.issueStandard = issueStandard;
        return this;
    }

}
