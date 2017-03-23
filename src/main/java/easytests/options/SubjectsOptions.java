package easytests.options;

import easytests.models.IssueStandardModelInterface;
import easytests.models.SubjectModelInterface;
import easytests.services.*;
import java.util.List;
import lombok.Setter;


/**
 * @author vkpankov
 */
public class SubjectsOptions implements SubjectsOptionsInterface {

    @Setter
    private UsersServiceInterface usersService;

    @Setter
    private SubjectsServiceInterface subjectsService;

    @Setter
    private TopicsServiceInterface topicsService;

    @Setter
    private IssueStandardsServiceInterface issueStandardsService;

    private UsersOptionsInterface usersOptions;

    private TopicsOptionsInterface topicsOptions;

    private IssueStandardsOptionsInterface issueStandardsOptions;

    public SubjectsOptionsInterface withUser(UsersOptionsInterface usersOptions) {
        this.usersOptions = usersOptions;
        return this;
    }

    public SubjectsOptionsInterface withIssueStandard(IssueStandardsOptionsInterface issueStandardOptions) {
        this.issueStandardsOptions = issueStandardOptions;
        return this;
    }

    public SubjectsOptionsInterface withTopics(TopicsOptionsInterface topicsOptions) {
        this.topicsOptions = topicsOptions;
        return this;
    }

    public SubjectModelInterface withRelations(SubjectModelInterface subjectModel) {

        if (this.usersOptions != null) {
            subjectModel.setUser(this.usersService.find(subjectModel.getUser().getId(), this.usersOptions));
        }
        if (this.topicsOptions != null) {
            subjectModel.setTopics(this.topicsService.findBySubject(subjectModel, this.topicsOptions));
        }
        if (this.issueStandardsOptions != null) {
            final IssueStandardModelInterface issueStandard =
                    this.issueStandardsService.findBySubject(subjectModel, this.issueStandardsOptions);

            subjectModel.setIssueStandard(issueStandard);
        }

        return subjectModel;

    }

    public List<SubjectModelInterface> withRelations(List<SubjectModelInterface> subjectsModels) {

        for (SubjectModelInterface subjectModel: subjectsModels) {
            this.withRelations(subjectModel);
        }
        return subjectsModels;

    }

    public void saveWithRelations(SubjectModelInterface subjectModel) {

        if (this.usersOptions != null) {
            this.usersOptions.withSubjects(this);
            this.usersOptions.saveWithRelations(subjectModel.getUser());
            return;
        }

        this.subjectsService.save(subjectModel);

        if (this.issueStandardsOptions != null) {
            this.issueStandardsOptions.saveWithRelations(subjectModel.getIssueStandard());

        }

        if (this.topicsOptions != null) {
            this.topicsOptions.saveWithRelations(subjectModel.getTopics());
        }
    }

    public void deleteWithRelations(SubjectModelInterface subjectModel) {

        if (this.usersOptions != null) {
            this.usersOptions.withSubjects(this);
            this.usersOptions.deleteWithRelations(subjectModel.getUser());
            return;
        }

        if (this.topicsOptions != null) {
            this.topicsOptions.deleteWithRelations(subjectModel.getTopics());

        }
        if (this.issueStandardsOptions != null) {
            this.issueStandardsOptions.deleteWithRelations(subjectModel.getIssueStandard());
        }

        this.subjectsService.delete(subjectModel);

    }
}
