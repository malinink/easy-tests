package easytests.core.models;

import easytests.core.entities.AnswerEntity;

/**
 * @author rezenbekk
 */
public interface AnswerModelInterface extends ModelInterface {
    void setId(Integer id);

    String getTxt();

    void setTxt(String txt);

    QuestionModelInterface getQuestion();

    void setQuestion(QuestionModelInterface question);

    Integer getSerialNumber();

    void setSerialNumber(Integer serialNumber);

    Boolean getRight();

    void setRight(Boolean right);

    void map(AnswerEntity answerEntity);
}
