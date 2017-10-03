package easytests.core.options;

import easytests.core.models.QuizModelInterface;
import easytests.core.models.TesteeModelInterface;
import easytests.core.services.IssuesServiceInterface;
import easytests.core.services.PointsServiceInterface;
import easytests.core.services.QuizzesServiceInterface;
import easytests.core.services.TesteesServiceInterface;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Setter;

/**
 * @author DoZor-80
 */
@EqualsAndHashCode
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

            this.issuesService.save(quizModel.getIssue(), this.issuesOptions);

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

        if (this.pointsOptions != null) {
            this.pointsService.delete(quizModel.getPoints(), this.pointsOptions);
        }

        this.quizzesService.delete(quizModel);

        if (this.issuesOptions != null) {
            this.issuesService.delete(quizModel.getIssue(), this.issuesOptions);
        }

        if (this.testeesOptions != null) {
            this.testeesService.delete(quizModel.getTestee(), this.testeesOptions);
        }

    }
}
