package easytests.models;

import easytests.entities.QuizEntity;
import easytests.models.empty.IssueModelEmpty;
import easytests.models.empty.ModelsListEmpty;
import easytests.models.empty.TesteeModelEmpty;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

/**
 * @author DoZor-80
 */
@Data
public class QuizModel implements QuizModelInterface {

    private Integer id;

    private IssueModelInterface issue;

    private String inviteCode;

    private boolean codeExpired;

    private LocalDateTime startedAt;

    private LocalDateTime finishedAt;

    private List<PointModelInterface> points;

    private TesteeModelInterface testee;

    public void map(QuizEntity quizEntity) {
        this.setId(quizEntity.getId());
        this.setCodeExpired(quizEntity.isCodeExpired());
        this.setStartedAt(quizEntity.getStartedAt());
        this.setFinishedAt(quizEntity.getFinishedAt());
        this.setIssue(new IssueModelEmpty(quizEntity.getIssueId()));
        this.setInviteCode(quizEntity.getInviteCode());
        this.setPoints(new ModelsListEmpty());
        this.setTestee(new TesteeModelEmpty());
    }
}
