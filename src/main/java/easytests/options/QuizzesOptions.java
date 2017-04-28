package easytests.options;

import easytests.models.QuizModelInterface;
import easytests.services.IssuesServiceInterface;
import easytests.services.QuizzesServiceInterface;
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

    private IssuesOptionsInterface issuesOptions;

    public QuizzesOptionsInterface withIssue(IssuesOptionsInterface issuesOptions) {
        this.issuesOptions = issuesOptions;
        return this;
    }

    public QuizModelInterface withRelations(QuizModelInterface quizModel) {

        if (quizModel == null) {
            return quizModel;
        }

        if (this.issuesOptions != null) {
            quizModel.setIssue(this.issuesService.find(quizModel.getIssue().getId(), this.issuesOptions));
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

    }

    public void deleteWithRelations(QuizModelInterface quizModel) {

        if (this.issuesOptions != null) {
            this.issuesOptions.withQuizzes(this);
            this.issuesOptions.deleteWithRelations(quizModel.getIssue());
            return;
        }

        this.quizzesService.delete(quizModel);

    }
}
