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

    List<Integer> getHighPriorityTopics();

    IssueStandardInterface setHighPriorityTopics(List<Integer> highPriorityTopics);

    List<Integer> getLowPriorityTopics();

    IssueStandardInterface setLowPriorityTopics(List<Integer> lowPriorityTopics);

    List<QuestionTypeLimitsInterface> getQuestionTypeLimits();

    IssueStandardInterface setQuestionTypeLimits(List<QuestionTypeLimitsInterface> questionTypesLimits);

    Integer getSubjectId();

    IssueStandardInterface setSubjectId(Integer subjectId);

    String toString();
}
