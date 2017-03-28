package easytests.models;

import easytests.entities.IssueStandardEntity;
import java.util.List;
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
    }
}
