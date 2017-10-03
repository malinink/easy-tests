package easytests.core.models.empty;

import easytests.core.entities.QuizEntity;
import easytests.core.models.IssueModelInterface;
import easytests.core.models.PointModelInterface;
import easytests.core.models.QuizModelInterface;
import easytests.core.models.TesteeModelInterface;
import easytests.core.models.exceptions.CallMethodOnEmptyModelException;

import java.time.LocalDateTime;
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
    public void setCodeExpired(Boolean codeExpired) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setFinishedAt(LocalDateTime finishedAt) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setStartedAt(LocalDateTime startedAt) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public Boolean getCodeExpired() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public LocalDateTime getStartedAt() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public LocalDateTime getFinishedAt() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void map(QuizEntity quizEntity) {
        throw new CallMethodOnEmptyModelException();
    }
}
