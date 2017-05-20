package easytests.options;

import easytests.services.SolutionsServiceInterface;
import lombok.EqualsAndHashCode;
import lombok.Setter;

/**
 * @author loriens
 */
@EqualsAndHashCode
public class SolutionsOptions implements SolutionsOptionsInterface {
    @Setter
    private SolutionsServiceInterface solutionsService;

/*    @Setter
    private PointsServiceInterface pointsService;

    private PointsOptionsInterface pointsOptions;

    public SoltionsOptionsInterface withPoint(PointsOptionsInterface pointsOptions) {
        this.pointsOptions = pointsOptions;
        return this;
    }*/
}
