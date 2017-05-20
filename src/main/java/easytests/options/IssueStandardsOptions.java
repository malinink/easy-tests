package easytests.options;

import easytests.models.IssueStandardModelInterface;
import easytests.services.IssueStandardQuestionTypeOptionsServiceInterface;
import easytests.services.IssueStandardTopicPrioritiesServiceInterface;
import easytests.services.IssueStandardsServiceInterface;
import easytests.services.SubjectsServiceInterface;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Setter;

/**
 * @author SingularityA
 */
@EqualsAndHashCode
public class IssueStandardsOptions implements IssueStandardsOptionsInterface {

    @Setter
    private IssueStandardsServiceInterface issueStandardsService;

    @Setter
    private IssueStandardTopicPrioritiesServiceInterface topicPrioritiesService;

    @Setter
    private IssueStandardQuestionTypeOptionsServiceInterface questionTypeOptionsService;

    @Setter
    private SubjectsServiceInterface subjectsService;

    private IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions;

    private IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions;

    private SubjectsOptionsInterface subjectsOptions;

    @Override
    public IssueStandardsOptionsInterface
        withTopicPriorities(IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions) {
        this.topicPrioritiesOptions = topicPrioritiesOptions;
        return this;
    }

    @Override
    public IssueStandardsOptionsInterface
        withQuestionTypeOptions(IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions) {
        this.questionTypeOptionsOptions = questionTypeOptionsOptions;
        return this;
    }

    @Override
    public IssueStandardsOptionsInterface
        withSubject(SubjectsOptionsInterface subjectsOptions) {
        this.subjectsOptions = subjectsOptions;
        return this;
    }

    @Override
    public IssueStandardModelInterface withRelations(IssueStandardModelInterface issueStandardModel) {
        if (issueStandardModel == null) {
            return issueStandardModel;
        }
        if (this.topicPrioritiesOptions != null) {
            issueStandardModel.setTopicPriorities(this.topicPrioritiesService
                    .findByIssueStandard(issueStandardModel, this.topicPrioritiesOptions));
        }
        if (this.questionTypeOptionsOptions != null) {
            issueStandardModel.setQuestionTypeOptions(this.questionTypeOptionsService
                    .findByIssueStandard(issueStandardModel, this.questionTypeOptionsOptions));
        }
        if (this.subjectsOptions != null) {
            issueStandardModel.setSubject(this.subjectsService
                    .find(issueStandardModel.getSubject().getId(), this.subjectsOptions));
        }
        return issueStandardModel;
    }

    @Override
    public List<IssueStandardModelInterface> withRelations(List<IssueStandardModelInterface> issueStandardModels) {
        for (IssueStandardModelInterface issueStandardModel: issueStandardModels) {
            this.withRelations(issueStandardModel);
        }
        return issueStandardModels;
    }

    @Override
    public void saveWithRelations(IssueStandardModelInterface issueStandardModel) {
        if (this.subjectsOptions != null) {
            this.subjectsService.save(issueStandardModel.getSubject(), this.subjectsOptions);
        }

        this.issueStandardsService.save(issueStandardModel);

        if (this.topicPrioritiesOptions != null) {
            this.topicPrioritiesService
                    .save(issueStandardModel.getTopicPriorities(), this.topicPrioritiesOptions);
        }
        if (this.questionTypeOptionsOptions != null) {
            this.questionTypeOptionsService
                    .save(issueStandardModel.getQuestionTypeOptions(), this.questionTypeOptionsOptions);
        }
    }

    @Override
    public void deleteWithRelations(IssueStandardModelInterface issueStandardModel) {
        if (this.topicPrioritiesOptions != null) {
            this.topicPrioritiesService
                    .delete(issueStandardModel.getTopicPriorities(), this.topicPrioritiesOptions);
        }
        if (this.questionTypeOptionsOptions != null) {
            this.questionTypeOptionsService
                    .delete(issueStandardModel.getQuestionTypeOptions(), this.questionTypeOptionsOptions);
        }

        this.issueStandardsService.delete(issueStandardModel);

        if (this.subjectsOptions != null) {
            this.subjectsService.delete(issueStandardModel.getSubject(), subjectsOptions);
        }
    }
}
