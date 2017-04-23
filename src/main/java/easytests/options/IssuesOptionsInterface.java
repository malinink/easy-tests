package easytests.options;

import easytests.models.IssueModelInterface;
import easytests.services.IssuesServiceInterface;
import easytests.services.QuizzesServiceInterface;

import java.util.List;

/**
 * @author fortyways
 */
public interface IssuesOptionsInterface {

    void setQuizzesService(QuizzesServiceInterface quizzesService);

    void setIssuesService(IssuesServiceInterface issuesService);

    IssuesOptionsInterface withQuizzes(QuizzesOptionsInterface quizzesService);

    IssueModelInterface withRelations(IssueModelInterface issueModel);

    List<IssueModelInterface> withRelations(List<IssueModelInterface> issuesModels);

    void saveWithRelations(IssueModelInterface issueModel);

    void deleteWithRelations(IssueModelInterface issueModel);
}
