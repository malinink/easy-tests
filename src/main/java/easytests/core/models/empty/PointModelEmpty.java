package easytests.core.models.empty;

import easytests.core.entities.PointEntity;
import easytests.core.models.PointModelInterface;
import easytests.core.models.QuestionModelInterface;
import easytests.core.models.QuizModelInterface;
import easytests.core.models.SolutionModelInterface;
import easytests.core.models.exceptions.CallMethodOnEmptyModelException;

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
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public QuestionModelInterface getQuestion() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setQuestion(QuestionModelInterface question) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public QuizModelInterface getQuiz() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setQuiz(QuizModelInterface quiz) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public List<SolutionModelInterface> getSolutions() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setSolutions(List<SolutionModelInterface> solutions) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void map(PointEntity pointEntity) {
        throw new CallMethodOnEmptyModelException();
    }

}
