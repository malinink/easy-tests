package easytests.options;

import easytests.models.PointModelInterface;
import easytests.services.AnswersServiceInterface;
import easytests.services.PointsServiceInterface;
import easytests.services.QuizzesServiceInterface;

/**
 * @author loriens
 */
public interface PointsOptionsInterface {
    void setPointsService(PointsServiceInterface pointsService);

    void setQuizzesService(QuizzesServiceInterface quizzesService);

    PointsOptionsInterface withQuiz(QuizzesOptionsInterface quizzesOptions);

    PointsOptionsInterface withRelations(PointModelInterface pointModel);

    saveWithRelations(PointModelInterface pointModel);

    deleteWithRelations(PointModelInterface pointModel);
}
