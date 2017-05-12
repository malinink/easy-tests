package easytests.models.empty;

import easytests.entities.AnswerEntity;
import easytests.models.AnswerModelInterface;
import easytests.models.QuestionModelInterface;
import easytests.models.exceptions.CallMethodOnEmptyModelException;

/**
 * @author rezenbekk
 */
public class AnswerModelEmpty extends AbstractModelEmpty implements AnswerModelInterface {
    public AnswerModelEmpty() {
        super();
    }

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
    public QuestionModelInterface getQuestion() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setQuestion(QuestionModelInterface question) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public Integer getSerialNumber() { throw new CallMethodOnEmptyModelException(); }

    @Override
    public void setSerialNumber(Integer serialNumber) { throw new CallMethodOnEmptyModelException(); }

    @Override
    public Boolean getRight() {
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
