package easytests.models;

import easytests.entities.SolutionEntity;
import lombok.*;

/**
 * @author SingularityA
 */
@Data
public class SolutionModel implements SolutionModelInterface {

    private Integer id;

    private AnswerModelInterface answer;

    private PointModelInterface point;

    public void map(SolutionEntity solutionEntity) {
        this.setId(solutionEntity.getId());
    }
}
