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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (getClass() != object.getClass()) {
            return false;
        }
        final AbstractModelEmpty other = (AbstractModelEmpty) object;
        if (this.id == other.id) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.id == null ? -1 : this.id;
    }
}
