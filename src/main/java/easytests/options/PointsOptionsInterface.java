package easytests.options;

import easytests.models.PointModelInterface;
import easytests.services.PointsServiceInterface;
import easytests.services.QuestionsServiceInterface;
import easytests.services.QuizzesServiceInterface;
import easytests.services.SolutionsServiceInterface;

import java.util.List;

/**
 * @author Loriens
 */
public interface PointsOptionsInterface extends OptionsInterface {
    void setPointsService(PointsServiceInterface pointsService);

    void setSolutionsService(SolutionsServiceInterface solutionsService);

    void setQuestionsService(QuestionsServiceInterface questionsService);

    void setQuizzesService(QuizzesServiceInterface quizzesService);

    PointsOptionsInterface withSolutions(SolutionsOptionsInterface solutionsOptions);

    PointsOptionsInterface withQuestion(QuestionsOptionsInterface questionsOptions);

    PointsOptionsInterface withQuiz(QuizzesOptionsInterface quizzesOptions);

    PointModelInterface withRelations(PointModelInterface pointModel);

    List<PointModelInterface> withRelations(List<PointModelInterface> pointModels);

    void saveWithRelations(PointModelInterface pointModel);

    void deleteWithRelations(PointModelInterface pointModel);
}
