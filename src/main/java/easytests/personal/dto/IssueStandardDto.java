package easytests.personal.dto;

import easytests.models.IssueStandardModelInterface;
import lombok.Data;

/**
 * @author SingularityA
 */
@Data
public class IssueStandardDto {

    private Integer id;

    private Integer timeLimit;

    private Integer questionsNumber;

    private Integer subjectId;

    public void map(IssueStandardModelInterface issueStandardModel) {
        this.setId(issueStandardModel.getId());
        this.setQuestionsNumber(issueStandardModel.getQuestionsNumber());
        this.setTimeLimit(issueStandardModel.getTimeLimit());
        this.setSubjectId(issueStandardModel.getSubject().getId());
    }
}
