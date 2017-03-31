package easytests.models;

import easytests.entities.IssueStandardEntity;
import java.util.List;

import easytests.models.empty.ModelsListEmpty;
import easytests.models.empty.SubjectModelEmpty;
import lombok.Data;

/**
 * @author SingularityA
 */
@Data
public class IssueStandardModel implements IssueStandardModelInterface {

    private Integer id;

    private Integer timeLimit;

    private Integer questionsNumber;

    private List<IssueStandardTopicPriorityModelInterface> topicPriorities;

    private List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptions;

    private SubjectModelInterface subject;

    public void map(IssueStandardEntity issueStandardEntity) {
        this.setId(issueStandardEntity.getId());
        this.setTimeLimit(issueStandardEntity.getTimeLimit());
        this.setQuestionsNumber(issueStandardEntity.getQuestionsNumber());
        this.setTopicPriorities(new ModelsListEmpty());
        this.setQuestionTypeOptions(new ModelsListEmpty());
        this.setSubject(new SubjectModelEmpty(issueStandardEntity.getSubjectId()));
    }
}
