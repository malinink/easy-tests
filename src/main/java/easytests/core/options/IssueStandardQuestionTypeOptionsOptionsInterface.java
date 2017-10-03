package easytests.core.options;

import easytests.core.models.IssueStandardQuestionTypeOptionModelInterface;
import easytests.core.services.IssueStandardQuestionTypeOptionsServiceInterface;
import easytests.core.services.IssueStandardsServiceInterface;
import easytests.core.services.QuestionTypesServiceInterface;

import java.util.List;

/**
 * @author SingularityA
 */
public interface IssueStandardQuestionTypeOptionsOptionsInterface extends OptionsInterface {

    void setQuestionTypeOptionsService(IssueStandardQuestionTypeOptionsServiceInterface questionTypeOptionsService);

    void setQuestionTypesService(QuestionTypesServiceInterface questionTypeService);

    void setIssueStandardsService(IssueStandardsServiceInterface issueStandardsService);

    IssueStandardQuestionTypeOptionsOptionsInterface
        withQuestionType(QuestionTypesOptionsInterface questionTypesOptions);

    IssueStandardQuestionTypeOptionsOptionsInterface
        withIssueStandard(IssueStandardsOptionsInterface issueStandardsOptions);

    IssueStandardQuestionTypeOptionModelInterface
        withRelations(IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel);

    List<IssueStandardQuestionTypeOptionModelInterface>
        withRelations(List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels);

    void saveWithRelations(IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel);

    void deleteWithRelations(IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel);
}
