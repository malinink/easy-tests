package easytests.core.options;

import easytests.core.models.QuizModelInterface;
import easytests.core.services.IssuesServiceInterface;
import easytests.core.services.PointsServiceInterface;
import easytests.core.services.QuizzesServiceInterface;
import easytests.core.services.TesteesServiceInterface;

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
