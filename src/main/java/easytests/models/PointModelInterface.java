package easytests.models;

import easytests.entities.PointEntity;

import java.util.List;

/**
 * @author nikitalpopov
 */
public interface PointModelInterface extends ModelInterface {

    void setId(Integer id);

    QuestionModelInterface getQuestion();

    void setQuestion(QuestionModelInterface question);

    QuizModelInterface getQuiz();

    void setQuiz(QuizModelInterface quiz);

    List<SolutionModelInterface> getSolutions();

    void setSolutions(List<SolutionModelInterface> solutions);

    void map(PointEntity pointEntity);

}
