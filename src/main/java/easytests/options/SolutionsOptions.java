package easytests.options;

import easytests.models.PointModel;
import easytests.models.SolutionModelInterface;
import easytests.services.PointsServiceInterface;
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

    @Setter
    private PointsServiceInterface pointsService;

    private PointsOptionsInterface pointsOptions;

    public SolutionsOptionsInterface withPoint(PointsOptionsInterface pointsOptions) {
        this.pointsOptions = pointsOptions;
        return this;
    }

    public SolutionsOptionsInterface withRelations(SolutionModelInterface solutionModel) {
        if (this.pointsOptions != null) {
            solutionModel.setPoint(this.pointsService.find(
                    solutionModel.getPoint().getId(), this.pointsOptions));
        }

        return solutionModel;
    }


    public void saveWithRelations(SolutionModelInterface solutionModel) {
        if (this.pointsOptions != null) {
            this.pointsOptions.withSolutions(this);
            this.pointsOptions.saveWithRelations(solutionModel.getPoint());
            return;
        }
    }


    public void deleteWithRelations(SolutionModelInterface solutionModel) {
        if (this.pointsOptions != null) {
            this.pointsOptions.withSolutions(this);
            this.pointsOptions.deleteWithRelations(solutionModel.getPoint());
            return;
        }

        this.solutionsService.delete(solutionModel);
    }
}
