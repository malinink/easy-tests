package easytests.models;

import easytests.entities.QuizEntity;
import easytests.models.empty.IssueModelEmpty;
import easytests.models.empty.ModelsListEmpty;
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

    private List<PointModelInterface> points;

    public void map(QuizEntity quizEntity) {
        this.setId(quizEntity.getId());
        this.setIssue(new IssueModelEmpty(quizEntity.getIssueId()));
        this.setInviteCode(quizEntity.getInviteCode());
        this.setPoints(new ModelsListEmpty());
    }
}
