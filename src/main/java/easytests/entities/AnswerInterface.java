package easytests.entities;

/**
 * @author rezenbekk
 */
public interface AnswerInterface extends EntityInterface {
    AnswerInterface setId(Integer id);

    String getTxt();

    AnswerInterface setTxt(String txt);

    Integer getQuestionId();

    AnswerInterface setQuestionId(Integer id);

    boolean getIsRight();

    AnswerInterface setIsRight(boolean isRight);
}
