package easytests.models.empty;

import easytests.entities.IssueEntity;
import easytests.models.IssueModelInterface;
import easytests.models.QuizModelInterface;
import easytests.models.SubjectModelInterface;
import easytests.models.exceptions.CallMethodOnEmptyModelException;

import java.util.List;

/**
 * @author fortyways
 */
public class IssueModelEmpty extends AbstractModelEmpty implements IssueModelInterface {
    public IssueModelEmpty() {
        super();
    }

    public IssueModelEmpty(Integer id) {
        super(id);
    }

    @Override
    public void setId(Integer id) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setName(String name) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public String getName() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void map(IssueEntity issueEntity) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public List<QuizModelInterface> getQuizzes() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setQuizzes(List<QuizModelInterface> quizzes) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public SubjectModelInterface getSubject() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setSubject(SubjectModelInterface subject) {
        throw new CallMethodOnEmptyModelException();
    }

}

