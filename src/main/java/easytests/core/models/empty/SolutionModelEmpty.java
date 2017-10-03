package easytests.core.models.empty;

import easytests.core.entities.SolutionEntity;
import easytests.core.models.AnswerModelInterface;
import easytests.core.models.PointModelInterface;
import easytests.core.models.SolutionModelInterface;
import easytests.core.models.exceptions.CallMethodOnEmptyModelException;

/**
 * @author SingularityA
 */
public class SolutionModelEmpty extends AbstractModelEmpty implements SolutionModelInterface {
    public SolutionModelEmpty() {
        super();
    }

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
