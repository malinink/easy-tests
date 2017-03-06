package easytests.entities;

import java.util.List;

/**
 * @author malinink
 */
public interface IssueInterface extends IssueStandardInterface {

    IssueInterface setId(Integer id);

    String getName();

    IssueInterface setName(String name);

    Integer getAuthorId();

    IssueInterface setAuthorId(Integer authorId);

    List<QuizInterface> getQuizzes();

    IssueInterface setQuizzes(List<QuizInterface> quizzes);
}
