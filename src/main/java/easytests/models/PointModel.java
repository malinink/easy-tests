package easytests.models;

import easytests.entities.PointEntity;
import lombok.Data;

import java.util.List;

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

    }

}
