package easytests.options;

import easytests.models.SolutionModelInterface;
import easytests.services.PointsServiceInterface;
import easytests.services.SolutionsServiceInterface;

/**
 * @author loriens
 */
public interface SolutionsOptionsInterface {
    void setSolutionsService(SolutionsServiceInterface solutionsService);

    void setPointsService(PointsServiceInterface pointsService);

    SolutionsOptionsInterface withPoint(PointsOptionsInterface pointsOptions);

    SolutionModelInterface withRelations(SolutionModelInterface solutionModel);

    void saveWithRelations(SolutionModelInterface solutionModel);

    void deleteWithRelations(SolutionModelInterface solutionModel);
}
