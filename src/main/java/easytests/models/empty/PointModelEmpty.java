package easytests.models.empty;

import easytests.entities.PointEntity;
import easytests.models.PointModelInterface;
import easytests.models.QuestionModelInterface;
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
