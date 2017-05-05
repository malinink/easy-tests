package easytests.models.empty;

import easytests.entities.QuizEntity;
import easytests.models.IssueModelInterface;
import easytests.models.PointModelInterface;
import easytests.models.QuizModelInterface;
import easytests.models.exceptions.CallMethodOnEmptyModelException;

import java.util.List;

/**
 * @author nikitalpopov
 */
public class QuizModelEmpty extends AbstractModelEmpty implements QuizModelInterface {

    public QuizModelEmpty() {

        super();

    }

    public QuizModelEmpty(Integer id) {

        super(id);

    }

    @Override
    public void setId(Integer id) {

        this.throwException();

    }

    @Override
    public String getInviteCode() {

        this.throwException();
        return null;

    }

    @Override
    public void setInviteCode(String inviteCode) {

        this.throwException();

    }

    @Override
    public List<PointModelInterface> getPoints() {

        this.throwException();
        return null;

    }

    @Override
    public void setPoints(List<PointModelInterface> points) {

        this.throwException();

    }

    @Override
    public IssueModelInterface getIssue() {

        this.throwException();
        return null;

    }

    @Override
    public void setIssue(IssueModelInterface issue) {

        this.throwException();

    }

    @Override
    public void map(QuizEntity quizEntity) {

        this.throwException();

    }

    private void throwException() throws CallMethodOnEmptyModelException {

        throw new CallMethodOnEmptyModelException();

    }

}
