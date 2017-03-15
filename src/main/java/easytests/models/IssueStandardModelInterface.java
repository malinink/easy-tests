package easytests.models;

import easytests.entities.IssueStandardEntity;

import java.util.List;

/**
 * @author SingularityA
 */
public interface IssueStandardModelInterface extends ModelInterface {

    void setId(Integer id);

    Integer getTimeLimit();

    void setTimeLimit(Integer timeLimit);

    Integer getQuestionsNumber();

    void setQuestionsNumber(Integer questionsNumber);

    List<IssueStandardTopicPriorityModelInterface> getTopicPriorities();

    void setTopicPriorities(List<IssueStandardTopicPriorityModelInterface> topicPriorities);

    List<IssueStandardQuestionTypeOptionModelInterface> getQuestionTypeOptions();

    void setQuestionTypeOptions(List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptions);

    SubjectModelInterface getSubject();

    void setSubject(SubjectModelInterface subject);

    void map(IssueStandardEntity issueStandardEntity);
}
