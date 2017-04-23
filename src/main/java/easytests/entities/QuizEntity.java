package easytests.entities;

import easytests.models.QuizModelInterface;
import lombok.Data;

/**
 * @author fortyways
 */
@Data
public class QuizEntity {

    private Integer id;

    private Integer issueId;

    private String inviteCode;

    public void map(QuizModelInterface quizModel) {

        this.setId(quizModel.getId());

        this.setInviteCode(quizModel.getInviteCode());

        this.setIssueId(quizModel.getIssue().getId());

    }
}
