package easytests.core.entities;

/**
 * @author rezenbekk
 * @deprecated (models)
 */
@Deprecated
public interface AnswerInterface extends EntityInterface {
    AnswerInterface setId(Integer id);

    String getTxt();

    AnswerInterface setTxt(String txt);

    Integer getQuestionId();

    AnswerInterface setQuestionId(Integer id);

    Boolean getIsRight();

    AnswerInterface setIsRight(Boolean isRight);
}
