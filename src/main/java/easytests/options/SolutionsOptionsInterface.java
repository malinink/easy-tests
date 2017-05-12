package easytests.options;

import easytests.services.SolutionsServiceInterface;

/**
 * @author loriens
 */
public interface SolutionsOptionsInterface {
    void setSolutionsService(SolutionsServiceInterface solutionsService);

    void setPointsService(PointsServiceInterface pointsService);

    SolutionsOptionsInterface withPoints(PointsOptionsInterface pointsOptions);
}
