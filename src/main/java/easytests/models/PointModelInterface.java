package easytests.models;

import easytests.entities.PointEntity;

/**
 * @author nikitalpopov
 */
public interface PointModelInterface extends ModelInterface {

    void setId(Integer id);

    String getType();

    void setType(String type);

    String getText();

    void setText(String text);

    QuizModelInterface getQuiz();

    void setQuiz(QuizModelInterface quiz);

    void map(PointEntity pointEntity);

}
