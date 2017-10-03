package easytests.core.entities;

import java.util.List;

/**
 * @author malinink
 * @deprecated because of models
 */

@Deprecated
public interface IssueInterface extends IssueStandardInterface {
    IssueInterface setId(Integer id);

    String getName();

    IssueInterface setName(String name);

    Integer getAuthorId();

    IssueInterface setAuthorId(Integer authorId);

    List<QuizInterface> getQuizzes();

    IssueInterface setQuizzes(List<QuizInterface> quizzes);
}
