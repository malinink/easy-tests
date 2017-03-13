package easytests.entities;

import easytests.models.SubjectModelInterface;
import lombok.*;

/**
 * @author vkpankov
 */
@Data
public class SubjectEntity {

    private Integer id;

    private String name;

    private Integer userId;

    private Integer issueStandardId;

    public void map(SubjectModelInterface subjectModel) {

        this.setId(subjectModel.getId());

        this.setName(subjectModel.getName());

        this.setUserId(subjectModel.getUser().getId());

        this.setIssueStandardId(subjectModel.getIssueStandard().getId());

    }

}
