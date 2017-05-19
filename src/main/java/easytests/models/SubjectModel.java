package easytests.models;

import easytests.entities.SubjectEntity;
import easytests.models.empty.IssueStandardModelEmpty;
import easytests.models.empty.ModelsListEmpty;
import easytests.models.empty.UserModelEmpty;
import java.util.List;
import lombok.*;

/**
 * @author vkpankov
 */
@Data
public class SubjectModel implements SubjectModelInterface {

    private Integer id;

    private String name;

    private String description;

    private List<TopicModelInterface> topics;

    private UserModelInterface user;

    private IssueStandardModelInterface issueStandard;

    private List<IssueModelInterface> issues;

    public void map(SubjectEntity subjectEntity) {

        this.setId(subjectEntity.getId());
        this.setName(subjectEntity.getName());
        this.setDescription(subjectEntity.getDescription());
        this.setTopics(new ModelsListEmpty());
        this.setUser(new UserModelEmpty(subjectEntity.getUserId()));
        this.setIssueStandard(new IssueStandardModelEmpty());
        this.setIssues(new ModelsListEmpty());
    }
}
