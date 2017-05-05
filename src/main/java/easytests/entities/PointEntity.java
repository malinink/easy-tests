package easytests.entities;

import easytests.models.PointModelInterface;
import lombok.Data;

/**
 * @author nikitalpopov
 */
@Data
public class PointEntity {

    private Integer id;

    private String type;

    private String text;

    private Integer quizId;

    public void map(PointModelInterface pointModel) {

        this.setId(pointModel.getId());
        this.setType(pointModel.getType());
        this.setText(pointModel.getText());
        this.setQuizId(pointModel.getQuiz().getId());

    }

}
