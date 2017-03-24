package easytests.services;

import easytests.models.IssueStandardModelInterface;
import easytests.models.SubjectModelInterface;
import easytests.options.IssueStandardsOptionsInterface;
import java.util.List;

/**
 * @author SingularityA
 */
public interface IssueStandardsServiceInterface extends ServiceInterface {

    List<IssueStandardModelInterface> findAll();

    List<IssueStandardModelInterface> findAll(IssueStandardsOptionsInterface issueStandardsOptions);

    IssueStandardModelInterface find(Integer id);

    IssueStandardModelInterface find(Integer id, IssueStandardsOptionsInterface issueStandardsOptions);

    IssueStandardModelInterface findBySubject(SubjectModelInterface subjectModel);
    
    IssueStandardModelInterface findBySubject(SubjectModelInterface subjectModel,
                                              IssueStandardsOptionsInterface issueStandardsOptions);

    void save(IssueStandardModelInterface issueStandardModel);

    void save(List<IssueStandardModelInterface> issueStandardModels);

    void save(IssueStandardModelInterface issueStandardModel,
              IssueStandardsOptionsInterface issueStandardsOptions);

    void save(List<IssueStandardModelInterface> issueStandardModels,
              IssueStandardsOptionsInterface issueStandardsOptions);

    void delete(IssueStandardModelInterface issueStandardModel);

    void delete(List<IssueStandardModelInterface> issueStandardModels);

    void delete(IssueStandardModelInterface issueStandardModel,
                IssueStandardsOptionsInterface issueStandardsOptions);

    void delete(List<IssueStandardModelInterface> issueStandardModels,
                IssueStandardsOptionsInterface issueStandardsOptions);
}
