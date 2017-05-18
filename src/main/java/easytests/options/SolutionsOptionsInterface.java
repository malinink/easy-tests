package easytests.options;

import easytests.models.SolutionModelInterface;
import easytests.services.AnswersServiceInterface;
import easytests.services.PointsServiceInterface;
import easytests.services.SolutionsServiceInterface;

import java.util.List;

/**
 * @author Loriens
 */
public interface SolutionsOptionsInterface {
    void setSolutionsService(SolutionsServiceInterface solutionsService);

    void setAnswersService(AnswersServiceInterface answersService);

    void setPointsService(PointsServiceInterface pointsService);

    SolutionsOptionsInterface withPoint(PointsOptionsInterface pointsOptions);

    SolutionsOptionsInterface withAnswer(AnswersOptionsInterface answersOptions);

    SolutionModelInterface withRelations(SolutionModelInterface solutionModel);

    List<SolutionModelInterface> withRelations(List<SolutionModelInterface> solutionModels);

    void saveWithRelations(SolutionModelInterface solutionModel);

    void deleteWithRelations(SolutionModelInterface solutionModel);
}
