package easytests.entities;

import java.util.List;

/**
 * @author SingularityA
 */
public class IssueStandard implements IssueStandardInterface {

    private Integer id;

    private Integer timeLimit;

    private Integer questionsNumber;

    private List<IssueStandardTopicPriorityInterface> issueStandardTopicPriorities;

    private List<IssueStandardQuestionTypeOptionInterface> issueStandardQuestionTypeOptions;

    private Integer subjectId;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public IssueStandardInterface setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public Integer getTimeLimit() {
        return timeLimit;
    }

    @Override
    public IssueStandardInterface setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
        return this;
    }

    @Override
    public Integer getQuestionsNumber() {
        return questionsNumber;
    }

    @Override
    public IssueStandardInterface setQuestionsNumber(Integer questionsNumber) {
        this.questionsNumber = questionsNumber;
        return this;
    }

    @Override
    public List<IssueStandardTopicPriorityInterface> getIssueStandardTopicPriorities() {
        return issueStandardTopicPriorities;
    }

    @Override
    public IssueStandardInterface setIssueStandardTopicPriorities(
            List<IssueStandardTopicPriorityInterface> issueStandardTopicPriorities) {
        this.issueStandardTopicPriorities = issueStandardTopicPriorities;
        return this;
    }

    @Override
    public List<IssueStandardQuestionTypeOptionInterface> getIssueStandardQuestionTypeOptions() {
        return issueStandardQuestionTypeOptions;
    }

    @Override
    public IssueStandardInterface setIssueStandardQuestionTypeOptions(
            List<IssueStandardQuestionTypeOptionInterface> issueStandardQuestionTypeOptions) {
        this.issueStandardQuestionTypeOptions = issueStandardQuestionTypeOptions;
        return this;
    }

    @Override
    public Integer getSubjectId() {
        return subjectId;
    }

    @Override
    public IssueStandardInterface setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
        return this;
    }

    @Override
    public String toString() {
        return "IssueStandard : ["
                + " id = " + getId()
                + ", timeLimit = " + getTimeLimit()
                + ", questionsNumber = " + getQuestionsNumber()
                + ", topicsPriorities = " + getIssueStandardTopicPriorities()
                + ", questionTypeOptions = " + getIssueStandardQuestionTypeOptions()
                + ", subjectId=" + getSubjectId()
                + " ]";
    }
}
