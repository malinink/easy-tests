package easytests.core.entities;

import easytests.core.models.IssueStandardQuestionTypeOptionModelInterface;
import lombok.Data;

/**
 * @author SingularityA
 */
@Data
public class IssueStandardQuestionTypeOptionEntity {

    private Integer id;

    private Integer questionTypeId;

    private Integer minQuestions;

    private Integer maxQuestions;

    private Integer timeLimit;

    private Integer issueStandardId;

    public void map(IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel) {
        this.setId(questionTypeOptionModel.getId());
        this.setQuestionTypeId(questionTypeOptionModel.getQuestionType().getId());
        this.setMinQuestions(questionTypeOptionModel.getMinQuestions());
        this.setMaxQuestions(questionTypeOptionModel.getMaxQuestions());
        this.setTimeLimit(questionTypeOptionModel.getTimeLimit());
        this.setIssueStandardId(questionTypeOptionModel.getIssueStandard().getId());
    }
}
