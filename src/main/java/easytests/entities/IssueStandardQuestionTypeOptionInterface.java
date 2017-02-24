package easytests.entities;

/**
 * @author SingularityA
 */
public interface IssueStandardQuestionTypeOptionInterface extends EntityInterface {

    IssueStandardQuestionTypeOptionInterface setId(Integer id);

    Integer getQuestionTypeId();

    IssueStandardQuestionTypeOptionInterface setQuestionTypeId(Integer questionTypeId);

    Integer getMinQuestions();

    IssueStandardQuestionTypeOptionInterface setMinQuestions(Integer minQuestions);

    Integer getMaxQuestions();

    IssueStandardQuestionTypeOptionInterface setMaxQuestions(Integer maxQuestions);

    Integer getTimeLimit();

    IssueStandardQuestionTypeOptionInterface setTimeLimit(Integer timeLimit);

    Integer getIssueStandardId();

    IssueStandardQuestionTypeOptionInterface setIssueStandardId(Integer issueStandardId);

    String toString();
}
