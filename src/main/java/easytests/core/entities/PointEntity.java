package easytests.core.entities;

import easytests.core.models.PointModelInterface;
import lombok.Data;


/**
 * @author nikitalpopov
 */
@Data
public class PointEntity {

    private Integer id;

    private Integer questionId;

    private Integer quizId;

    public void map(PointModelInterface pointModel) {

        this.setId(pointModel.getId());
        this.setQuestionId(pointModel.getQuestion().getId());
        this.setQuizId(pointModel.getQuiz().getId());

    }
}
