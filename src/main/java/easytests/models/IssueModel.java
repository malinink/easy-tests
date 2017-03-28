package easytests.models;

import easytests.entities.IssueEntity;
import lombok.Data;

/**
 * @author fortyways
 */
@Data
public class IssueModel implements IssueModelInterface {
    private Integer id;

    private String name;

    private Integer authorId;

    //private List<QuizModelInterface> quizzes;

    public void map(IssueEntity issueEntity) {
        this.setId(issueEntity.getId());
        this.setName(issueEntity.getName());
        this.setAuthorId(issueEntity.getAuthorId());
    }

}
