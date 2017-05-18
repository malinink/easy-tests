package easytests.entities;

import easytests.models.QuizModelInterface;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * @author fortyways
 */
@Data
public class QuizEntity {

    private Integer id;

    private Integer issueId;

    private String inviteCode;

    private Boolean codeExpired;

    private LocalDateTime startedAt;

    private LocalDateTime finishedAt;

    public void map(QuizModelInterface quizModel) {

        this.setId(quizModel.getId());
        this.setCodeExpired(quizModel.getCodeExpired());
        this.setStartedAt(quizModel.getStartedAt());
        this.setFinishedAt(quizModel.getFinishedAt());
        this.setInviteCode(quizModel.getInviteCode());
        this.setIssueId(quizModel.getIssue().getId());

    }
}
