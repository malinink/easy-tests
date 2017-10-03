package easytests.core.models;

import easytests.core.entities.AnswerEntity;
import easytests.core.models.empty.QuestionModelEmpty;
import lombok.*;

/**
 * @author rezenbekk
 */
@Data
public class AnswerModel implements AnswerModelInterface {
    private Integer id;

    private String txt;

    private QuestionModelInterface question;

    private Integer serialNumber;

    private Boolean right;

    public void map(AnswerEntity answerEntity) {
        this.setId(answerEntity.getId());
        this.setTxt(answerEntity.getTxt());
        this.setSerialNumber(answerEntity.getSerialNumber());
        this.setQuestion(new QuestionModelEmpty(answerEntity.getQuestionId()));
        this.setRight(answerEntity.getRight());
    }
}
