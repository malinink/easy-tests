package easytests.options;

import easytests.models.QuizModelInterface;
import easytests.services.IssuesServiceInterface;
import easytests.services.PointsServiceInterface;
import easytests.services.QuizzesServiceInterface;
import easytests.services.TesteesServiceInterface;

import java.util.List;

/**
 * @author fortyways
 */
public interface QuizzesOptionsInterface extends OptionsInterface {
    void setPointsService(PointsServiceInterface pointsService);

    void setQuizzesService(QuizzesServiceInterface quizzesService);

    void setIssuesService(IssuesServiceInterface issuesService);

    void setTesteesService(TesteesServiceInterface testeesService);

    QuizzesOptionsInterface withIssue(IssuesOptionsInterface issuesOptions);

    QuizzesOptionsInterface withPoints(PointsOptionsInterface pointsOptions);

    QuizzesOptionsInterface withTestee(TesteesOptionsInterface testeeOptions);

    QuizModelInterface withRelations(QuizModelInterface subjectModel);

    List<QuizModelInterface> withRelations(List<QuizModelInterface> subjectsModels);

    void saveWithRelations(QuizModelInterface quizModel);

    void deleteWithRelations(QuizModelInterface quizModel);
}
