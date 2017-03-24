package easytests.options;

import easytests.models.IssueStandardQuestionTypeOptionModelInterface;
import easytests.services.IssueStandardQuestionTypeOptionsServiceInterface;
import easytests.services.IssueStandardsServiceInterface;
import java.util.List;
import lombok.Setter;

/**
 * @author SingularityA
 */
public class IssueStandardQuestionTypeOptionsOptions implements IssueStandardQuestionTypeOptionsOptionsInterface {

    @Setter
    private IssueStandardQuestionTypeOptionsServiceInterface questionTypeOptionsService;

    @Setter
    private IssueStandardsServiceInterface issueStandardsService;

    private IssueStandardsOptionsInterface issueStandardsOptions;

    @Override
    public IssueStandardQuestionTypeOptionsOptionsInterface
        withIssueStandard(IssueStandardsOptionsInterface issueStandardsOptions) {
        this.issueStandardsOptions = issueStandardsOptions;
        return this;
    }

    @Override
    public IssueStandardQuestionTypeOptionModelInterface
        withRelations(IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel) {

        if (this.issueStandardsOptions != null) {
            questionTypeOptionModel.setIssueStandard(
                    this.issueStandardsService.find(questionTypeOptionModel.getIssueStandard().getId()));
        }
        return questionTypeOptionModel;
    }

    @Override
    public List<IssueStandardQuestionTypeOptionModelInterface>
        withRelations(List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels) {

        for (IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel: questionTypeOptionModels) {
            this.withRelations(questionTypeOptionModel);
        }
        return questionTypeOptionModels;
    }

    @Override
    public void saveWithRelations(IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel) {
        if (this.issueStandardsOptions != null) {
            this.issueStandardsService.save(questionTypeOptionModel.getIssueStandard(), this.issueStandardsOptions);
        }
        this.questionTypeOptionsService.save(questionTypeOptionModel);
    }

    @Override
    public void deleteWithRelations(IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel) {
        this.questionTypeOptionsService.delete(questionTypeOptionModel);
        if (this.issueStandardsOptions != null) {
            this.issueStandardsService.delete(questionTypeOptionModel.getIssueStandard(), this.issueStandardsOptions);
        }
    }
}
