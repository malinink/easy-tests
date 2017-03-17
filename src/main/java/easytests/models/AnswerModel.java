package easytests.models;

import easytests.entities.AnswerEntity;
import lombok.*;

/**
 * @author rezenbekk
 */
@Data
public class AnswerModel implements AnswerModelInterface {
    private Integer id;

    private String txt;

    private QuestionModelInterface question;

    private Boolean right;

    public void map(AnswerEntity answerEntity) {
        this.setId(answerEntity.getId());
        this.setTxt(answerEntity.getTxt());
        this.setRight(answerEntity.getRight());
    }
}
