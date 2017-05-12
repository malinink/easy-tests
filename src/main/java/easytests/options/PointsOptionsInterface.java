package easytests.options;

import easytests.models.PointModelInterface;
import easytests.services.AnswersServiceInterface;
import easytests.services.PointsServiceInterface;
import easytests.services.QuizzesServiceInterface;
import easytests.services.SolutionsServiceInterface;

/**
 * @author loriens
 */
public interface PointsOptionsInterface {
    void setPointsService(PointsServiceInterface pointsService);

    void setSolutionsService(SolutionsServiceInterface solutionsService);

    void setQuizzesService(QuizzesServiceInterface quizzesService);

    PointsOptionsInterface withQuiz(QuizzesOptionsInterface quizzesOptions);

    PointModelInterface withRelations(PointModelInterface pointModel);

    void saveWithRelations(PointModelInterface pointModel);

    void deleteWithRelations(PointModelInterface pointModel);
}
