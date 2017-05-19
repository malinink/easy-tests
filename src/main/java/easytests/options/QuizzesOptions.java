package easytests.options;

import easytests.models.QuizModelInterface;
import easytests.models.TesteeModelInterface;
import easytests.services.IssuesServiceInterface;
import easytests.services.PointsServiceInterface;
import easytests.services.QuizzesServiceInterface;
import easytests.services.TesteesServiceInterface;
import java.util.List;
import lombok.Setter;

/**
 * @author DoZor-80
 */
public class QuizzesOptions implements QuizzesOptionsInterface {
    @Setter
    private IssuesServiceInterface issuesService;

    @Setter
    private QuizzesServiceInterface quizzesService;

    @Setter
    private TesteesServiceInterface testeesService;

    @Setter
    private PointsServiceInterface pointsService;

    private IssuesOptionsInterface issuesOptions;

    private TesteesOptionsInterface testeesOptions;

    private PointsOptionsInterface pointsOptions;

    public QuizzesOptionsInterface withIssue(IssuesOptionsInterface issuesOptions) {
        this.issuesOptions = issuesOptions;
        return this;
    }

    public QuizzesOptionsInterface withTestee(TesteesOptionsInterface testeeOptions) {
        this.testeesOptions = testeeOptions;
        return this;
    }

    public QuizzesOptionsInterface withPoints(PointsOptionsInterface pointsOptions) {
        this.pointsOptions = pointsOptions;
        return this;
    }

    public QuizModelInterface withRelations(QuizModelInterface quizModel) {

        if (quizModel == null) {
            return quizModel;
        }

        if (this.issuesOptions != null) {
            quizModel.setIssue(this.issuesService.find(quizModel.getIssue().getId(), this.issuesOptions));
        }

        if (this.testeesOptions != null) {
            final TesteeModelInterface testee =
                    this.testeesService.findByQuiz(quizModel, this.testeesOptions);

            quizModel.setTestee(testee);
        }

        if (this.pointsOptions != null) {
            quizModel.setPoints(this.pointsService.findByQuiz(quizModel, this.pointsOptions));
        }

        return quizModel;

    }

    public List<QuizModelInterface> withRelations(List<QuizModelInterface> quizzesModels) {

        for (QuizModelInterface quizModel: quizzesModels) {
            this.withRelations(quizModel);
        }
        return quizzesModels;

    }

    public void saveWithRelations(QuizModelInterface quizModel) {

        if (this.issuesOptions != null) {

            this.issuesOptions.withQuizzes(this);
            this.issuesOptions.saveWithRelations(quizModel.getIssue());
            return;

        }

        this.quizzesService.save(quizModel);

        if (this.testeesOptions != null) {

            this.testeesService.save(quizModel.getTestee(), this.testeesOptions);

        }

        if (this.pointsOptions != null) {
            this.pointsService.save(quizModel.getPoints(), this.pointsOptions);
        }

    }

    public void deleteWithRelations(QuizModelInterface quizModel) {

        if (this.issuesOptions != null) {
            this.issuesOptions.withQuizzes(this);
            this.issuesOptions.deleteWithRelations(quizModel.getIssue());
            return;
        }
        if (this.pointsOptions != null) {
            this.pointsService.delete(quizModel.getPoints(), this.pointsOptions);
        }

        if (this.testeesOptions != null) {
            this.testeesService.delete(quizModel.getTestee(), this.testeesOptions);
        }

        this.quizzesService.delete(quizModel);

    }
}
