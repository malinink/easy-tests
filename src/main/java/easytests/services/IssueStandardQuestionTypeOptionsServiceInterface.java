package easytests.services;

import easytests.models.IssueStandardModelInterface;
import easytests.models.IssueStandardQuestionTypeOptionModelInterface;
import easytests.options.IssueStandardQuestionTypeOptionsOptionsInterface;
import java.util.List;

/**
 * @author SingularityA
 */
public interface IssueStandardQuestionTypeOptionsServiceInterface extends ServiceInterface {

    List<IssueStandardQuestionTypeOptionModelInterface> findAll();

    List<IssueStandardQuestionTypeOptionModelInterface> findAll(
            IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions);

    IssueStandardQuestionTypeOptionModelInterface find(Integer id);

    IssueStandardQuestionTypeOptionModelInterface find(
            Integer id,
            IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions);

    List<IssueStandardQuestionTypeOptionModelInterface> findByIssueStandard(
            IssueStandardModelInterface issueStandard);

    List<IssueStandardQuestionTypeOptionModelInterface> findByIssueStandard(
            IssueStandardModelInterface issueStandard,
            IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions);

    void save(IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel);

    void save(List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels);

    void save(IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel,
              IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions);

    void save(List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels,
              IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions);

    void delete(IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel);

    void delete(List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels);

    void delete(IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel,
                IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions);

    void delete(List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels,
                IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions);
}
