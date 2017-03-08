package easytests.models.empty;

import easytests.models.ModelInterface;
import easytests.models.exceptions.CallMethodOnEmptyModelException;
import easytests.models.exceptions.CreateEmptyModelWithNullIdException;
import easytests.models.exceptions.CreateEmptyModelWithoutIdException;
import lombok.Getter;


/**
 * @author malinink
 */
public abstract class AbstractModelEmpty implements ModelInterface {
    @Getter
    private Integer id;

    public AbstractModelEmpty(Integer id) throws CreateEmptyModelWithNullIdException {
        if (id == null) {
            throw new CreateEmptyModelWithNullIdException();
        }
        this.id = id;
    }

    public AbstractModelEmpty() throws CreateEmptyModelWithoutIdException {
        throw new CreateEmptyModelWithoutIdException();
    }

    protected void throwException() throws CallMethodOnEmptyModelException {
        throw new CallMethodOnEmptyModelException();
    }
}
