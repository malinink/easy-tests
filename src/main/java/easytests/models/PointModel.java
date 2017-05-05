package easytests.models;

import easytests.entities.PointEntity;
import java.util.List;

import easytests.models.empty.QuizModelEmpty;
import lombok.Data;

/**
 * @author nikitalpopov
 */
@Data
public class PointModel implements PointModelInterface {

    private Integer id;

    private String type;

    private String text;

    private QuizModelInterface quiz;

    private List<SolutionModelInterface> solutions;

    public void map(PointEntity pointEntity) {

        this.setId(pointEntity.getId());
        this.setType(pointEntity.getType());
        this.setText(pointEntity.getText());
        this.setQuiz(new QuizModelEmpty(pointEntity.getQuizId()));

    }

}
