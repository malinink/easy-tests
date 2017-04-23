package easytests.options;

import easytests.models.IssueModelInterface;
import easytests.services.IssuesServiceInterface;
import easytests.services.QuizzesServiceInterface;
import java.util.List;
import lombok.Setter;



/**
 * @author fortyways
 */
public class IssuesOptions implements IssuesOptionsInterface {

    @Setter
    private IssuesServiceInterface issuesService;

    @Setter
    private QuizzesServiceInterface quizzesService;

    private QuizzesOptionsInterface quizzesOptions;

    @Override
    public IssuesOptionsInterface withQuizzes(QuizzesOptionsInterface quizzesOptions) {
        this.quizzesOptions = quizzesOptions;
        return this;
    }

    @Override
    public IssueModelInterface withRelations(IssueModelInterface issueModel) {
        if (issueModel == null) {
            return issueModel;
        }
        if (this.quizzesOptions != null) {
            issueModel.setQuizzes(this.quizzesService.findByIssue(issueModel, this.quizzesOptions));
        }
        return issueModel;
    }

    @Override
    public List<IssueModelInterface> withRelations(List<IssueModelInterface> issuesModels) {
        for (IssueModelInterface issueModel: issuesModels) {
            this.withRelations(issueModel);
        }
        return issuesModels;
    }

    @Override
    public void saveWithRelations(IssueModelInterface issueModel) {
        this.issuesService.save(issueModel);
        if (this.quizzesOptions != null) {
            this.quizzesService.save(issueModel.getQuizzes(), this.quizzesOptions);
        }
    }

    @Override
    public void deleteWithRelations(IssueModelInterface issueModel) {
        if (this.quizzesOptions != null) {
            this.quizzesService.delete(issueModel.getQuizzes(), this.quizzesOptions);
        }
        this.issuesService.delete(issueModel);
    }
}
