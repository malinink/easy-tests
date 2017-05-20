package easytests.models;

import easytests.entities.IssueEntity;
import easytests.models.empty.ModelsListEmpty;
import easytests.models.empty.SubjectModelEmpty;
import java.util.List;
import lombok.Data;



/**
 * @author fortyways
 */
@Data
public class IssueModel implements IssueModelInterface {
    private Integer id;

    private String name;

    private SubjectModelInterface subject;

    private List<QuizModelInterface> quizzes;

    public void map(IssueEntity issueEntity) {
        this.setId(issueEntity.getId());
        this.setName(issueEntity.getName());
        this.setSubject(new SubjectModelEmpty(issueEntity.getSubjectId()));
        this.setQuizzes(new ModelsListEmpty());
    }

}
