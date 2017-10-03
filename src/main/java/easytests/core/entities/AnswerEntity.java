package easytests.core.entities;

import easytests.core.models.AnswerModelInterface;
import lombok.*;

/**
 * @author rezenbekk
 */
@Data
public class AnswerEntity {
    private Integer id;

    private String txt;

    private Integer questionId;

    private Integer serialNumber;

    private Boolean right;

    public void map(AnswerModelInterface answerModel) {
        this.setId(answerModel.getId());
        this.setTxt(answerModel.getTxt());
        this.setQuestionId(answerModel.getQuestion().getId());
        this.setSerialNumber(answerModel.getSerialNumber());
        this.setRight(answerModel.getRight());
    }
}
