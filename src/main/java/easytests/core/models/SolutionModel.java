package easytests.core.models;

import easytests.core.entities.SolutionEntity;
import easytests.core.models.empty.AnswerModelEmpty;
import easytests.core.models.empty.PointModelEmpty;
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
        this.setAnswer(new AnswerModelEmpty(solutionEntity.getAnswerId()));
        this.setPoint(new PointModelEmpty(solutionEntity.getPointId()));
    }
}
