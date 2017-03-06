package easytests.entities;

import java.util.List;

/**
 * @author fortyways
 */
public class Issue implements IssueInterface {

    private Integer id;

    private String name;

    private Integer authorId;

    private List<QuizInterface> quizzes;

    private IssueStandard standard;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public IssueInterface setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public IssueInterface setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Integer getAuthorId() {
        return authorId;
    }

    @Override
    public IssueInterface setAuthorId(Integer authorId) {
        this.authorId = authorId;
        return this;
    }

    @Override
    public List<QuizInterface> getQuizzes() {
        return quizzes;
    }

    @Override
    public IssueInterface setQuizzes(List<QuizInterface> quizzes) {
        this.quizzes = quizzes;
        return this;
    }

    public IssueStandard getStandard() {
        return standard;
    }

    public void setStandard(IssueStandard standard) {
        this.standard = standard;
    }

    @Override
    public Integer getTimeLimit() {
        return standard.getTimeLimit();
    }

    @Override
    public IssueStandardInterface setTimeLimit(Integer timeLimit) {
        standard.setTimeLimit(timeLimit);
        return this;
    }

    @Override
    public Integer getQuestionsNumber() {
        return standard.getQuestionsNumber();
    }

    @Override
    public IssueStandardInterface setQuestionsNumber(Integer questionsNumber) {
        standard.setQuestionsNumber(questionsNumber);
        return this;
    }

    @Override
    public List<IssueStandardTopicPriorityInterface> getIssueStandardTopicPriorities() {
        return standard.getIssueStandardTopicPriorities();
    }

    @Override
    public IssueStandardInterface setIssueStandardTopicPriorities(
            List<IssueStandardTopicPriorityInterface> issueStandardTopicPriorities) {
        standard.setIssueStandardTopicPriorities(issueStandardTopicPriorities);
        return this;
    }

    @Override
    public List<IssueStandardQuestionTypeOptionInterface> getIssueStandardQuestionTypeOptions() {
        return standard.getIssueStandardQuestionTypeOptions();
    }

    @Override
    public IssueStandardInterface setIssueStandardQuestionTypeOptions(
            List<IssueStandardQuestionTypeOptionInterface> issueStandardQuestionTypeOptions) {
        standard.setIssueStandardQuestionTypeOptions(issueStandardQuestionTypeOptions);
        return this;
    }

    @Override
    public SubjectInterface getSubject() {
        return standard.getSubject();
    }

    @Override
    public IssueStandardInterface setSubject(SubjectInterface subject) {
        standard.setSubject(subject);
        return this;
    }

    @Override
    public String toString() {
        return standard.toString();
    }

}
