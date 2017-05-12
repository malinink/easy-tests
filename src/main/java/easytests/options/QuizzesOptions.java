package easytests.options;

import easytests.models.QuizModelInterface;
import easytests.models.TesteeModelInterface;
import easytests.services.IssuesServiceInterface;
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

    private IssuesOptionsInterface issuesOptions;

    private TesteesOptionsInterface testeesOptions;

    public QuizzesOptionsInterface withIssue(IssuesOptionsInterface issuesOptions) {
        this.issuesOptions = issuesOptions;
        return this;
    }

    public QuizzesOptionsInterface withTestee(TesteesOptionsInterface testeeOptions) {
        this.testeesOptions = testeeOptions;
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

    }

    public void deleteWithRelations(QuizModelInterface quizModel) {

        if (this.issuesOptions != null) {
            this.issuesOptions.withQuizzes(this);
            this.issuesOptions.deleteWithRelations(quizModel.getIssue());
            return;
        }

        if (this.testeesOptions != null) {
            this.testeesService.delete(quizModel.getTestee(), this.testeesOptions);
        }

        this.quizzesService.delete(quizModel);

    }
}
