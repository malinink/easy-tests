package easytests.personal.dto;

import easytests.core.models.QuestionModelInterface;
import easytests.core.services.QuestionTypesService;
import javax.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author firkhraag
 */
@Data
public class QuestionModelDto {

    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String text;

    private Integer questionTypeId;

    public void map(QuestionModelInterface questionModel) {
        this.setText(questionModel.getText());
        this.setQuestionTypeId(questionModel.getQuestionType().getId());
    }

    public void mapInto(QuestionModelInterface questionModel, QuestionTypesService questionTypesService) {
        questionModel.setText(this.getText());
        questionModel.setQuestionType(questionTypesService.find(questionTypeId));
    }
}
