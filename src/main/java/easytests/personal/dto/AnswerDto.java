package easytests.personal.dto;

import easytests.models.AnswerModelInterface;
import easytests.services.AnswersService;
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

    private Boolean right;

    @NotNull
    private Integer questionId;

    public void map(AnswerModelInterface answerModel) {
        this.setTxt(answerModel.getTxt());
        this.setRight(answerModel.getRight());
        this.setQuestionId(answerModel.getQuestion().getId());
    }

    public void mapInto(AnswerModelInterface answerModel) {
        answerModel.setTxt(this.getTxt());
        answerModel.setRight(this.getRight());
        answerModel.setQuestion(answersService.find(this.getQuestionId()).getQuestion());
    }
}
