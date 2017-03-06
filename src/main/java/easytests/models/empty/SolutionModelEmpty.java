package easytests.models.empty;

import easytests.entities.SolutionEntity;
import easytests.models.AnswerModelInterface;
import easytests.models.PointModelInterface;
import easytests.models.SolutionModelInterface;

/**
 * @author SingularityA
 */
public class SolutionModelEmpty extends AbstractModelEmpty implements SolutionModelInterface {

    public SolutionModelEmpty(Integer id) {
        super(id);
    }

    public SolutionModelEmpty() {
        super();
    }

    @Override
    public void setId(Integer id) {
        this.throwException();
    }

    @Override
    public AnswerModelInterface getAnswer() {
        this.throwException();
        return null;
    }

    @Override
    public void setAnswer(AnswerModelInterface answer) {
        this.throwException();
    }

    @Override
    public PointModelInterface getPoint() {
        this.throwException();
        return null;
    }

    @Override
    public void setPoint(PointModelInterface point) {
        this.throwException();
    }

    @Override
    public void map(SolutionEntity solutionEntity) {
        this.throwException();
    }
}
