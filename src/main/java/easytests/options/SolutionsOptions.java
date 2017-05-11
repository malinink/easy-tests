package easytests.options;

import easytests.services.AnswersServiceInterface;
import easytests.services.SolutionServiceInterface;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.util.List;

/**
 * @author loriens
 */
@EqualsAndHashCode
public class SolutionsOptions implements SolutionsOptionsInterface {
    @Setter
    private SolutionServiceInterface solutionsService;

/*    @Setter
    private PointsServiceInterface pointsService;

    private PointsOptionsInterface pointsOptions;

    public SoltionsOptionsInterface withPoint(PointsOptionsInterface pointsOptions) {
        this.pointsOptions = pointsOptions;
        return this;
    }*/
}
