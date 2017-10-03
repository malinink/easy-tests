package easytests.core.entities;

import easytests.core.models.IssueStandardModelInterface;
import lombok.Data;

/**
 * @author SingularityA
 */
@Data
public class IssueStandardEntity {

    private Integer id;

    private Integer timeLimit;

    private Integer questionsNumber;

    private Integer subjectId;

    public void map(IssueStandardModelInterface issueStandardModel) {
        this.setId(issueStandardModel.getId());
        this.setTimeLimit(issueStandardModel.getTimeLimit());
        this.setQuestionsNumber(issueStandardModel.getQuestionsNumber());
        this.setSubjectId(issueStandardModel.getSubject().getId());
    }
}
