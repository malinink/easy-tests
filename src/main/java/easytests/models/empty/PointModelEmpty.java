package easytests.models.empty;

import easytests.entities.PointEntity;
import easytests.models.PointModelInterface;
import easytests.models.QuizModelInterface;
import easytests.models.SolutionModelInterface;
import easytests.models.exceptions.CallMethodOnEmptyModelException;
import easytests.models.exceptions.CallMethodOnEmptyModelsListException;

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

        throw new CallMethodOnEmptyModelsListException();

    }

    @Override
    public String getType() {

        throw new CallMethodOnEmptyModelsListException();

    }

    @Override
    public void setType(String type) {

        throw new CallMethodOnEmptyModelsListException();

    }

    @Override
    public String getText() {

        throw new CallMethodOnEmptyModelsListException();

    }

    @Override
    public void setText(String text) {

        throw new CallMethodOnEmptyModelsListException();

    }

    @Override
    public QuizModelInterface getQuiz() {

        throw new CallMethodOnEmptyModelsListException();

    }

    @Override
    public void setQuiz(QuizModelInterface quiz) {

        throw new CallMethodOnEmptyModelsListException();

    }

    @Override
    public List<SolutionModelInterface> getSolutions() {

        throw new CallMethodOnEmptyModelsListException();

    }

    @Override
    public void setSolutions(List<SolutionModelInterface> solutions) {

        throw new CallMethodOnEmptyModelsListException();

    }

    @Override
    public void map(PointEntity pointEntity) {

        throw new CallMethodOnEmptyModelsListException();

    }

    private void throwException() throws CallMethodOnEmptyModelException {

        throw new CallMethodOnEmptyModelException();

    }
}
