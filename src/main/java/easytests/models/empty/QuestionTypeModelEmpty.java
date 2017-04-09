package easytests.models.empty;

import easytests.entities.QuestionTypeEntity;
import easytests.models.QuestionTypeModelInterface;
import easytests.models.exceptions.CallMethodOnEmptyModelException;


/**
 * @author malinink
 */
public class QuestionTypeModelEmpty extends AbstractModelEmpty implements QuestionTypeModelInterface {
    public QuestionTypeModelEmpty() {
        super();
    }

    public QuestionTypeModelEmpty(Integer id) {
        super(id);
    }

    @Override
    public void setId(Integer id) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public String getName() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setName(String name) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void map(QuestionTypeEntity questionTypeEntity) {
        throw new CallMethodOnEmptyModelException();
    }
}
