package easytests.personal.dto;

import easytests.core.models.IssueStandardQuestionTypeOptionModelInterface;
import easytests.core.models.empty.IssueStandardModelEmpty;
import easytests.core.models.empty.QuestionTypeModelEmpty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author SingularityA
 */
@Data
public class IssueStandardQuestionTypeOptionDto {

    private Integer id;

    @NotNull(message = "Question Type may not be null")
    private Integer questionTypeId;

    @Min(1)
    @Max(3000)
    private Integer minQuestions;

    @Min(1)
    @Max(3000)
    private Integer maxQuestions;

    @Min(1)
    @Max(3600)
    private Integer timeLimit;

    public void map(IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel) {
        this.setId(questionTypeOptionModel.getId());
        this.setQuestionTypeId(questionTypeOptionModel.getQuestionType().getId());
        this.setMinQuestions(questionTypeOptionModel.getMinQuestions());
        this.setMaxQuestions(questionTypeOptionModel.getMaxQuestions());
        this.setTimeLimit(questionTypeOptionModel.getTimeLimit());
    }

    public void mapInto(IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel,
                        Integer issueStandardId) {
        questionTypeOptionModel.setId(this.getId());
        questionTypeOptionModel.setQuestionType(new QuestionTypeModelEmpty(this.getQuestionTypeId()));
        questionTypeOptionModel.setMinQuestions(this.getMinQuestions());
        questionTypeOptionModel.setMaxQuestions(this.getMaxQuestions());
        questionTypeOptionModel.setTimeLimit(this.getTimeLimit());
        questionTypeOptionModel.setIssueStandard(new IssueStandardModelEmpty(issueStandardId));
    }
}
