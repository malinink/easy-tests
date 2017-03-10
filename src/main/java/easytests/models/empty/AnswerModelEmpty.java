package easytests.models.empty;

import easytests.entities.AnswerEntity;
import easytests.models.AnswerModelInterface;
import easytests.models.exceptions.CallMethodOnEmptyModelException;

/**
 * @author rezenbekk
 */
public class AnswerModelEmpty extends AbstractModelEmpty implements AnswerModelInterface {
    public AnswerModelEmpty(Integer id) {
        super(id);
    }

    @Override
    public void setId(Integer id) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public String getTxt() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setTxt(String txt) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public Integer getQuestionId() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setQuestionId(Integer id) {
        throw new CallMethodOnEmptyModelException();
    }

    //@Override
    public Boolean isRight() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setRight(Boolean right) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void map(AnswerEntity answerEntity) {
        throw new CallMethodOnEmptyModelException();
    }
}
