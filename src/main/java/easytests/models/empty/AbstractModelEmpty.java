package easytests.models.empty;

import easytests.models.ModelInterface;
import easytests.models.exceptions.CallMethodOnEmptyModelException;
import easytests.models.exceptions.CreateEmptyModelWithNullIdException;


/**
 * @author malinink
 */
public abstract class AbstractModelEmpty implements ModelInterface {
    private Integer id;

    public AbstractModelEmpty() {
    }

    public AbstractModelEmpty(Integer id) {
        if (id == null) {
            throw new CreateEmptyModelWithNullIdException();
        }
        this.id = id;
    }

    @Override
    public Integer getId() {
        if (this.id == null) {
            throw new CallMethodOnEmptyModelException();
        }
        return this.id;
    }
}
