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

    private Integer questionId;

    @Getter(AccessLevel.NONE)
    private Boolean right;

    public Boolean isRight() {
        return this.right;
    }

    public void map(AnswerEntity answerEntity) {
        this.setId(answerEntity.getId());
        this.setTxt(answerEntity.getTxt());
        this.setQuestionId(answerEntity.getQuestionId());
        this.setRight(answerEntity.isRight());
    }
}
