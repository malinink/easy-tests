package easytests.options;

import easytests.models.SolutionModelInterface;
import easytests.services.SolutionsServiceInterface;

/**
 * @author loriens
 */
public interface SolutionsOptionsInterface {
    void setSolutionsService(SolutionsServiceInterface solutionsService);

    void setPointsService(PointsServiceInterface pointsService);

    SolutionsOptionsInterface withPoint(PointsOptionsInterface pointsOptions);

    saveWithRelations(SolutionModelInterface solutionModel);

    deleteWithRelations(SolutionModelInterface solutionModel);
}
