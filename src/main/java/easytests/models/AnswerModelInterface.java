package easytests.models;

import easytests.entities.AnswerEntity;

/**
 * @author rezenbekk
 */
public interface AnswerModelInterface extends ModelInterface {
    void setId(Integer id);

    String getTxt();

    void setTxt(String txt);

    Integer getQuestionId();

    void setQuestionId(Integer id);

    Boolean isRight();

    void setRight(Boolean right);

    void map(AnswerEntity answerEntity);
}
