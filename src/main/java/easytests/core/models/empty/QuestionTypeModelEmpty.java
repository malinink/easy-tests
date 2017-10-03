package easytests.core.models.empty;

import easytests.core.entities.QuestionTypeEntity;
import easytests.core.models.QuestionTypeModelInterface;
import easytests.core.models.exceptions.CallMethodOnEmptyModelException;


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
