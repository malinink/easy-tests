package easytests.models.empty;

import easytests.entities.QuizEntity;
import easytests.models.IssueModelInterface;
import easytests.models.PointModelInterface;
import easytests.models.QuizModelInterface;
import easytests.models.exceptions.CallMethodOnEmptyModelException;
import easytests.models.exceptions.CallMethodOnEmptyModelsListException;

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
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public String getInviteCode() {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public void setInviteCode(String inviteCode) {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public List<PointModelInterface> getPoints() {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public void setPoints(List<PointModelInterface> points) {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public IssueModelInterface getIssue() {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public void setIssue(IssueModelInterface issue) {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public void map(QuizEntity quizEntity) {
        throw new CallMethodOnEmptyModelsListException();
    }

}
