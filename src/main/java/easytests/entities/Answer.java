package easytests.entities;

/**
 * @author rezenbekk
 */
public class Answer implements AnswerInterface {

    private Integer id;

    private String txt;

    private Integer questionId;

    private boolean isRight;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public AnswerInterface setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public String getTxt() {
        return this.txt;
    }

    @Override
    public AnswerInterface setTxt(String txt) {
        this.txt = txt;
        return this;
    }

    @Override
    public Integer getQuestionId() {
        return this.questionId;
    }

    @Override
    public AnswerInterface setQuestionId(Integer id) {
        this.questionId = id;
        return this;
    }

    @Override
    public boolean getIsRight() {
        return this.isRight;
    }

    @Override
    public AnswerInterface setIsRight(boolean isRight) {
        this.isRight = isRight;
        return this;
    }
}
