package easytests.options;

import easytests.models.QuizModelInterface;
import easytests.services.IssuesServiceInterface;
import easytests.services.PointsServiceInterface;
import easytests.services.QuizzesServiceInterface;
import java.util.List;

/**
 * @author fortyways
 */
public interface QuizzesOptionsInterface extends OptionsInterface {

    QuizzesOptionsInterface withIssue(IssuesOptionsInterface issuesOptions);

    QuizzesOptionsInterface withPoints(PointsOptionsInterface pointsOptions);

    void setPointsService(PointsServiceInterface pointsService);

    void setQuizzesService(QuizzesServiceInterface quizzesService);

    void setIssuesService(IssuesServiceInterface issuesService);

    QuizzesOptionsInterface withTestee(TesteesOptionsInterface testeeOptions);

    QuizzesOptionsInterface withPoint(PointsOptionsInterface pointsOptions);

    QuizModelInterface withRelations(QuizModelInterface subjectModel);

    List<QuizModelInterface> withRelations(List<QuizModelInterface> subjectsModels);

    void saveWithRelations(QuizModelInterface quizModel);

    void deleteWithRelations(QuizModelInterface quizModel);
}
