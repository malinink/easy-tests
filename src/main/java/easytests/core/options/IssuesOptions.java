package easytests.core.options;

import easytests.core.models.IssueModelInterface;
import easytests.core.services.IssuesServiceInterface;
import easytests.core.services.QuizzesServiceInterface;
import easytests.core.services.SubjectsServiceInterface;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Setter;


/**
 * @author fortyways
 */
@EqualsAndHashCode
public class IssuesOptions implements IssuesOptionsInterface {

    @Setter
    private IssuesServiceInterface issuesService;

    @Setter
    private QuizzesServiceInterface quizzesService;

    @Setter
    private SubjectsServiceInterface subjectsService;

    private SubjectsOptionsInterface subjectsOptions;

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
        if (this.subjectsOptions != null) {
            issueModel.setSubject(this.subjectsService.find(issueModel.getSubject().getId(), this.subjectsOptions));
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

        if (this.subjectsOptions != null) {
            this.subjectsService.save(issueModel.getSubject(), this.subjectsOptions);
        }

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

        if (this.subjectsOptions != null) {
            this.subjectsService.delete(issueModel.getSubject(), this.subjectsOptions);
        }

    }

    @Override
    public IssuesOptionsInterface withSubject(SubjectsOptionsInterface subjectsOptions) {
        this.subjectsOptions = subjectsOptions;
        return this;
    }

}
