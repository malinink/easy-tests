package easytests.personal.dto;

import easytests.models.AnswerModelInterface;
import easytests.services.AnswersService;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author rezenbekk
 */
@Data
public class AnswerDto {

    @Autowired
    private AnswersService answersService;

    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String txt;

    @NotNull
    private Boolean right;

    @NotNull
    @Min(0)
    private Integer questionId;

    @NotNull
    @Min(0)
    private Integer serialNumber;

    public void map(AnswerModelInterface answerModel) {
        this.setTxt(answerModel.getTxt());
        this.setRight(answerModel.getRight());
        this.setQuestionId(answerModel.getQuestion().getId());
        this.setSerialNumber(answerModel.getSerialNumber());
    }

    public void mapInto(AnswerModelInterface answerModel) {
        answerModel.setTxt(this.getTxt());
        answerModel.setRight(this.getRight());
        answerModel.setQuestion(answersService.find(this.getQuestionId()).getQuestion());
        answerModel.setSerialNumber(this.getSerialNumber());
    }
}
