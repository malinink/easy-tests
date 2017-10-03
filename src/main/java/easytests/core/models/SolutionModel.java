package easytests.core.models;

import easytests.core.entities.SolutionEntity;
import lombok.*;

/**
 * @author SingularityA
 */
@Data
public class SolutionModel implements SolutionModelInterface {

    private Integer id;

    private AnswerModelInterface answer;

    private PointModelInterface point;

    //private Integer sort;

    public void map(SolutionEntity solutionEntity) {
        this.setId(solutionEntity.getId());
    }
}
