package easytests.services;

import easytests.models.IssueStandardModelInterface;
import easytests.models.SubjectModelInterface;
import easytests.options.IssueStandardsOptionsInterface;

/**
 * @author xxx
 */
public interface IssueStandardsServiceInterface {

    IssueStandardModelInterface findBySubject(SubjectModelInterface subject);

    IssueStandardModelInterface findBySubject(SubjectModelInterface subjectModel,
                                              IssueStandardsOptionsInterface issueStandardsOptions);

    void save(IssueStandardModelInterface subjectModel, IssueStandardsOptionsInterface subjectsOptions);

    void delete(IssueStandardModelInterface subjectModel, IssueStandardsOptionsInterface subjectsOptions);

}
