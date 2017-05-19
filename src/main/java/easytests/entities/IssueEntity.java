package easytests.entities;

import easytests.models.IssueModelInterface;
import lombok.*;

/**
 *@author fortyways
 */

@Data
public class IssueEntity {

    private Integer id;

    private String name;

    private Integer subjectId;

    public void map(IssueModelInterface issueModel) {
        this.setId(issueModel.getId());
        this.setName(issueModel.getName());
        this.setSubjectId(issueModel.getSubject().getId());
    }

}
