package easytests.models;

import easytests.entities.AnswerEntity;

/**
 * @author rezenbekk
 */
public interface AnswerModelInterface extends ModelInterface {
    void setId(Integer id);

    String getTxt();

    void setTxt(String txt);

    QuestionModelInterface getQuestion();

    void setQuestion(QuestionModelInterface question);

    Boolean getRight();

    void setRight(Boolean right);

    void map(AnswerEntity answerEntity);
}
