package easytests.models.empty;

import easytests.entities.QuizEntity;
import easytests.models.IssueModelInterface;
import easytests.models.PointModelInterface;
import easytests.models.QuizModelInterface;
import easytests.models.TesteeModelInterface;
import easytests.models.exceptions.CallMethodOnEmptyModelException;
import java.util.List;

/**
 * @author DoZor-80
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
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public String getInviteCode() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setInviteCode(String inviteCode) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public IssueModelInterface getIssue() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setIssue(IssueModelInterface issue) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public List<PointModelInterface> getPoints() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setPoints(List<PointModelInterface> points) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public TesteeModelInterface getTestee() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setTestee(TesteeModelInterface testee) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void map(QuizEntity quizEntity) {
        throw new CallMethodOnEmptyModelException();
    }
}
