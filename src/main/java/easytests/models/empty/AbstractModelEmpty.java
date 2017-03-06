package easytests.models.empty;

import easytests.models.UserModelInterface;
import easytests.models.exceptions.CreateEmptyModelWithoutIdException;
import lombok.Getter;


/**
 * @author malinink
 */
public abstract class AbstractModelEmpty implements UserModelInterface {
    @Getter
    protected Integer id;

    public AbstractModelEmpty(Integer id) {
        this.id = id;
    }

    public AbstractModelEmpty() throws CreateEmptyModelWithoutIdException {
        throw new CreateEmptyModelWithoutIdException();
    }
}
