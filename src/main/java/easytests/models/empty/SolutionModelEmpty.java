package easytests.models.empty;

import easytests.entities.SolutionEntity;
import easytests.models.AnswerModelInterface;
import easytests.models.PointModelInterface;
import easytests.models.SolutionModelInterface;
import easytests.models.exceptions.CallMethodOnEmptyModelException;

/**
 * @author SingularityA
 */
public class SolutionModelEmpty extends AbstractModelEmpty implements SolutionModelInterface {

    public SolutionModelEmpty(Integer id) {
        super(id);
    }

    @Override
    public void setId(Integer id) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public AnswerModelInterface getAnswer() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setAnswer(AnswerModelInterface answer) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public PointModelInterface getPoint() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setPoint(PointModelInterface point) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void map(SolutionEntity solutionEntity) {
        throw new CallMethodOnEmptyModelException();
    }
}
