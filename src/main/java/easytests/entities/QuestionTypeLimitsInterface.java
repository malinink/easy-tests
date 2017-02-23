package easytests.entities;

/**
 * @author SingularityA
 */
public interface QuestionTypeLimitsInterface extends EntityInterface {

    QuestionTypeLimitsInterface setId(Integer id);

    Integer getQuestionTypeId();

    QuestionTypeLimitsInterface setQuestionTypeId(Integer questionTypeId);

    Integer getMinQuestions();

    QuestionTypeLimitsInterface setMinQuestions(Integer minQuestions);

    Integer getMaxQuestions();

    QuestionTypeLimitsInterface setMaxQuestions(Integer maxQuestions);

    Integer getTimeLimit();

    QuestionTypeLimitsInterface setTimeLimit(Integer timeLimit);

    Integer getIssueStandardId();

    QuestionTypeLimitsInterface setIssueStandardId(Integer issueStandardId);

    String toString();
}
