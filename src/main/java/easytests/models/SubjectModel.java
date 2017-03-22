package easytests.models;

import easytests.entities.SubjectEntity;
import java.util.List;

import easytests.models.empty.IssueStandardModelEmpty;
import easytests.models.empty.ModelsListEmpty;
import easytests.models.empty.UserModelEmpty;
import lombok.*;

/**
 * @author vkpankov
 */
@Data
public class SubjectModel implements SubjectModelInterface {

    private Integer id;

    private String name;

    private List<TopicModelInterface> topics;

    private UserModelInterface user;

    private IssueStandardModelInterface issueStandard;

    public void map(SubjectEntity subjectEntity) {

        this.setId(subjectEntity.getId());
        this.setName(subjectEntity.getName());
        this.setTopics(new ModelsListEmpty());
        this.setUser(new UserModelEmpty(subjectEntity.getUserId()));
        this.setIssueStandard(new IssueStandardModelEmpty());
    }
}
