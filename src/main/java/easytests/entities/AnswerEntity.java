package easytests.entities;

import easytests.models.AnswerModelInterface;
import lombok.*;

/**
 * @author rezenbekk
 */
@Data
public class AnswerEntity {
    private Integer id;

    private String txt;

    private Integer questionId;

    @Getter(AccessLevel.NONE)
    private Boolean right;

    public Boolean isRight() {
        return right;
    }

    public void map(AnswerModelInterface answerModel) {
        this.setId(answerModel.getId());
        this.setTxt(answerModel.getTxt());
        this.setQuestionId(answerModel.getQuestionId());
        this.setRight(answerModel.isRight());
    }
}
