package easytests.core.entities;

import easytests.core.models.SolutionModelInterface;
import lombok.*;

/**
 * @author SingularityA
 */
@Data
public class SolutionEntity {

    private Integer id;

    //private Integer sort;

    private Integer answerId;

    private Integer pointId;

    public void map(SolutionModelInterface solutionModel) {
        this.setId(solutionModel.getId());
        this.setAnswerId(solutionModel.getAnswer().getId());
        this.setPointId(solutionModel.getPoint().getId());
    }
}
