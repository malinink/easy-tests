package easytests.options;

import easytests.models.PointModelInterface;
import easytests.services.PointsServiceInterface;
import easytests.services.QuestionsServiceInterface;
import easytests.services.QuizzesServiceInterface;
import easytests.services.SolutionsServiceInterface;

import java.util.List;

/**
 * @author nikitalpopov
 */
public interface PointsOptionsInterface extends OptionsInterface {

    void setPointsService(PointsServiceInterface pointsService);

    void setQuizzesService(QuizzesServiceInterface quizzesService);

    void setQuestionsService(QuestionsServiceInterface questionsService);

    void setSolutionsService(SolutionsServiceInterface solutionsService);

    PointsOptionsInterface withQuiz(QuizzesOptionsInterface quizzesOptions);

    PointsOptionsInterface withQuestion(QuestionsOptionsInterface questionsOptions);

    PointsOptionsInterface withSolutions(SolutionsOptionsInterface solutionsOptions);

    PointModelInterface withRelations(PointModelInterface pointModel);

    List<PointModelInterface> withRelations(List<PointModelInterface> pointModels);

    void saveWithRelations(PointModelInterface pointModel);

    void deleteWithRelations(PointModelInterface pointModel);

}
