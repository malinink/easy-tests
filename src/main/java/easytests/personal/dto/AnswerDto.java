package easytests.personal.dto;

import easytests.models.AnswerModelInterface;
import easytests.models.empty.QuestionModelEmpty;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author rezenbekk
 */
@Data
@SuppressWarnings("checkstyle:MultipleStringLiterals")
public class AnswerDto {

    private Integer id;

    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String txt;

    @Size(max = 2)
    private String right;

    @NotNull
    @Min(0)
    private Integer questionId;

    @NotNull
    @Min(0)
    private Integer serialNumber;

    public void map(AnswerModelInterface answerModel) {
        this.setId(answerModel.getId());
        this.setTxt(answerModel.getTxt());
        if (answerModel.getRight()) {
            this.right = "on";
        }
        this.setQuestionId(answerModel.getQuestion().getId());
        this.setSerialNumber(answerModel.getSerialNumber());
    }

    public void mapInto(AnswerModelInterface answerModel) {
        answerModel.setId(this.getId());
        answerModel.setTxt(this.getTxt());
        if ("on".equals(this.right)) {
            answerModel.setRight(true);
        } else {
            answerModel.setRight(false);
        }
        answerModel.setQuestion(new QuestionModelEmpty(getQuestionId()));
        answerModel.setSerialNumber(this.getSerialNumber());
    }
}
