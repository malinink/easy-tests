package easytests.models.empty;

import easytests.entities.PointEntity;
import easytests.models.PointModelInterface;
import easytests.models.QuizModelInterface;
import easytests.models.SolutionModelInterface;
import easytests.models.exceptions.CallMethodOnEmptyModelException;

import java.util.List;

/**
 * @author nikitalpopov
 */
public class PointModelEmpty extends AbstractModelEmpty implements PointModelInterface {

    public PointModelEmpty(Integer id) {
        super(id);
    }

    public PointModelEmpty() {
        super();
    }

    @Override
    public void setId(Integer id) {
        this.throwException();
    }

    @Override
    public String getType() {
        this.throwException();
        return null;
    }

    @Override
    public void setType(String type) {
        this.throwException();
    }

    @Override
    public String getText() {
        this.throwException();
        return null;
    }

    @Override
    public void setText(String text) {
        this.throwException();
    }

    @Override
    public QuizModelInterface getQuiz() {
        this.throwException();
        return null;
    }

    @Override
    public void setQuiz(QuizModelInterface quiz) {
        this.throwException();
    }

    @Override
    public List<SolutionModelInterface> getSolutions() {
        this.throwException();
        return null;
    }

    @Override
    public void setSolutions(List<SolutionModelInterface> solutions) {
        this.throwException();
    }

    @Override
    public void map(PointEntity pointEntity) {
        this.throwException();
    }

    private void throwException() throws CallMethodOnEmptyModelException {
        throw new CallMethodOnEmptyModelException();
    }
}
