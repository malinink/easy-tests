package easytests.core.models;

import easytests.core.entities.QuizEntity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author fortyways
 */
public interface QuizModelInterface extends ModelInterface {
    void setId(Integer id);

    String getInviteCode();

    void setInviteCode(String inviteCode);

    Boolean getCodeExpired();

    void setCodeExpired(Boolean codeExpired);

    LocalDateTime getStartedAt();

    LocalDateTime getFinishedAt();

    void setStartedAt(LocalDateTime startedAt);

    void setFinishedAt(LocalDateTime finishedAt);

    List<PointModelInterface> getPoints();

    void setPoints(List<PointModelInterface> points);

    void map(QuizEntity quizEntity);

    TesteeModelInterface getTestee();

    void setTestee(TesteeModelInterface testee);

    IssueModelInterface getIssue();

    void setIssue(IssueModelInterface issue);

}
