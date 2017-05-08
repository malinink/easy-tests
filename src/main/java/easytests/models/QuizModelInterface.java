package easytests.models;

import easytests.entities.QuizEntity;

import java.util.List;

/**
 * @author fortyways
 */
public interface QuizModelInterface extends ModelInterface {
    void setId(Integer id);

    String getInviteCode();

    void setInviteCode(String inviteCode);

    List<PointModelInterface> getPoints();

    void setPoints(List<PointModelInterface> points);

    void map(QuizEntity quizEntity);

    TesteeModelInterface getTestee();

    void setTestee(TesteeModelInterface testee);

    IssueModelInterface getIssue();

    void setIssue(IssueModelInterface issue);

}
