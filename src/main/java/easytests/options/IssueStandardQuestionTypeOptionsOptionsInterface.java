package easytests.options;

import easytests.models.IssueStandardQuestionTypeOptionModelInterface;
import easytests.services.IssueStandardQuestionTypeOptionsServiceInterface;
import easytests.services.IssueStandardsServiceInterface;
import java.util.List;

/**
 * @author SingularityA
 */
public interface IssueStandardQuestionTypeOptionsOptionsInterface extends OptionsInterface {

    void setQuestionTypeOptionsService(IssueStandardQuestionTypeOptionsServiceInterface questionTypeOptionsService);

    void setIssueStandardsService(IssueStandardsServiceInterface issueStandardsService);

    IssueStandardQuestionTypeOptionsOptionsInterface
        withIssueStandard(IssueStandardsOptionsInterface issueStandardsOptions);

    IssueStandardQuestionTypeOptionModelInterface
        withRelations(IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel);

    List<IssueStandardQuestionTypeOptionModelInterface>
        withRelations(List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels);

    void saveWithRelations(IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel);

    void deleteWithRelations(IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel);
}
