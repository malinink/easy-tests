package easytests.core.services;

import easytests.core.models.IssueModelInterface;
import easytests.core.models.SubjectModelInterface;
import easytests.core.options.IssuesOptionsInterface;

import java.util.List;

/**
 * @author fortyways
 */
public interface IssuesServiceInterface extends ServiceInterface {

    List<IssueModelInterface> findAll();

    List<IssueModelInterface> findAll(IssuesOptionsInterface issueOptions);

    IssueModelInterface find(Integer id);

    IssueModelInterface find(Integer id, IssuesOptionsInterface issueOptions);

    List<IssueModelInterface> findBySubject(SubjectModelInterface subjectModel);

    List<IssueModelInterface> findBySubject(SubjectModelInterface subjectModel, IssuesOptionsInterface issueOptions);

    void save(IssueModelInterface issueModel);

    void save(IssueModelInterface issueModel, IssuesOptionsInterface issueOptions);

    void save(List<IssueModelInterface> issuesModels);

    void save(List<IssueModelInterface> issuesModels, IssuesOptionsInterface issueOptions);

    void delete(IssueModelInterface issueModel);

    void delete(IssueModelInterface issueModel, IssuesOptionsInterface issueOptions);

    void delete(List<IssueModelInterface> issuesModels);

    void delete(List<IssueModelInterface> issuesModels, IssuesOptionsInterface issueOptions);
}
