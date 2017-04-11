package easytests.models;

import easytests.entities.IssueStandardQuestionTypeOptionEntity;

/**
 * @author SingularityA
 */
public interface IssueStandardQuestionTypeOptionModelInterface extends ModelInterface {

    void setId(Integer id);

    QuestionTypeModelInterface getQuestionType();

    void setQuestionType(QuestionTypeModelInterface questionType);

    Integer getMinQuestions();

    void setMinQuestions(Integer minQuestions);

    Integer getMaxQuestions();

    void setMaxQuestions(Integer maxQuestions);

    Integer getTimeLimit();

    void setTimeLimit(Integer timeLimit);

    IssueStandardModelInterface getIssueStandard();

    void setIssueStandard(IssueStandardModelInterface issueStandard);

    void map(IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity);
}
