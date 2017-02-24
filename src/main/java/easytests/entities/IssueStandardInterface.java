package easytests.entities;

import java.util.List;

/**
 * @author malinink
 */
public interface IssueStandardInterface extends EntityInterface {

    IssueStandardInterface setId(Integer id);

    Integer getTimeLimit();

    IssueStandardInterface setTimeLimit(Integer timeLimit);

    Integer getQuestionsNumber();

    IssueStandardInterface setQuestionsNumber(Integer questionsNumber);

    List<IssueStandardTopicPriorityInterface> getIssueStandardTopicPriorities();

    IssueStandardInterface setIssueStandardTopicPriorities(
            List<IssueStandardTopicPriorityInterface> issueStandardTopicPriorities);

    List<IssueStandardQuestionTypeOptionInterface> getIssueStandardQuestionTypeOptions();

    IssueStandardInterface setIssueStandardQuestionTypeOptions(
            List<IssueStandardQuestionTypeOptionInterface> issueStandardQuestionTypeOptions);

    Integer getSubjectId();

    IssueStandardInterface setSubjectId(Integer subjectId);

    String toString();
}
