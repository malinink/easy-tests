package easytests.entities;

/**
 * @author SingularityA
 */
public class QuestionTypeLimits implements QuestionTypeLimitsInterface {

    private Integer id;

    private Integer questionTypeId;

    private Integer minQuestions;

    private Integer maxQuestions;

    private Integer timeLimit;

    private Integer issueStandardId;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public QuestionTypeLimitsInterface setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public Integer getQuestionTypeId() {
        return questionTypeId;
    }

    @Override
    public QuestionTypeLimitsInterface setQuestionTypeId(Integer questionTypeId) {
        this.questionTypeId = questionTypeId;
        return this;
    }

    @Override
    public Integer getMinQuestions() {
        return minQuestions;
    }

    @Override
    public QuestionTypeLimitsInterface setMinQuestions(Integer minQuestions) {
        this.minQuestions = minQuestions;
        return this;
    }

    @Override
    public Integer getMaxQuestions() {
        return maxQuestions;
    }

    @Override
    public QuestionTypeLimitsInterface setMaxQuestions(Integer maxQuestions) {
        this.maxQuestions = maxQuestions;
        return this;
    }

    @Override
    public Integer getTimeLimit() {
        return timeLimit;
    }

    @Override
    public QuestionTypeLimitsInterface setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
        return this;
    }

    @Override
    public Integer getIssueStandardId() {
        return issueStandardId;
    }

    @Override
    public QuestionTypeLimitsInterface setIssueStandardId(Integer issueStandardId) {
        this.issueStandardId = issueStandardId;
        return this;
    }

    @Override
    public String toString() {
       return "QuestionTypeLimits : ["
               + " id = " + getId()
               + ", typeId = " + getQuestionTypeId()
               + ", minQuestions = " + getMinQuestions()
               + ", maxQuestions = " + getMaxQuestions()
               + ", timeLimit = " + getTimeLimit()
               + ", iSId = " + getIssueStandardId()
               + " ]";
    }
}
