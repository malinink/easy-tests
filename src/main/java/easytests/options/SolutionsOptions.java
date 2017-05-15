package easytests.options;

import easytests.models.SolutionModelInterface;
import easytests.services.PointsServiceInterface;
import easytests.services.SolutionsServiceInterface;
import groovy.transform.EqualsAndHashCode;
import java.util.List;
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

    @Override
    public SolutionModelInterface withRelations(SolutionModelInterface solutionModel) {
        if (solutionModel == null) {
            return solutionModel;
        }

        if (this.pointsOptions != null) {
            solutionModel.setPoint(this.pointsService.find(solutionModel.getPoint().getId(), this.pointsOptions));
        }

        return solutionModel;
    }

    @Override
    public List<SolutionModelInterface> withRelations(List<SolutionModelInterface> solutionModels) {

        for (SolutionModelInterface solutionModel: solutionModels) {
            this.withRelations(solutionModel);
        }
        return solutionModels;

    }

    public void saveWithRelations(SolutionModelInterface solutionModel) {
        this.solutionsService.save(solutionModel);

        if (this.pointsOptions != null) {
            this.pointsService.save(solutionModel.getPoint(), this.pointsOptions);
        }
    }

    public void deleteWithRelations(SolutionModelInterface solutionModel) {
        if (this.pointsOptions != null) {
            this.pointsService.delete(solutionModel.getPoint(), this.pointsOptions);
            this.pointsOptions.deleteWithRelations(solutionModel.getPoint(), pointsOptions);
        }

        this.solutionsService.delete(solutionModel);
    }

}
