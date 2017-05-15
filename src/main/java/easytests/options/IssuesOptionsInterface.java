package easytests.options;

import easytests.models.IssueModelInterface;
import easytests.services.IssuesServiceInterface;
import easytests.services.QuizzesServiceInterface;
import easytests.services.SubjectsServiceInterface;

import java.util.List;

/**
 * @author fortyways
 */
public interface IssuesOptionsInterface {

    void setQuizzesService(QuizzesServiceInterface quizzesService);

    void setSubjectsService(SubjectsServiceInterface subjectsService);

    void setIssuesService(IssuesServiceInterface issuesService);

    IssuesOptionsInterface withSubject(SubjectsOptionsInterface subjectsOptions);

    IssuesOptionsInterface withQuizzes(QuizzesOptionsInterface quizzesService);

    IssueModelInterface withRelations(IssueModelInterface issueModel);

    List<IssueModelInterface> withRelations(List<IssueModelInterface> issuesModels);

    void saveWithRelations(IssueModelInterface issueModel);

    void deleteWithRelations(IssueModelInterface issueModel);
}
