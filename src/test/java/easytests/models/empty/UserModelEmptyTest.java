package easytests.models.empty;

import easytests.models.exceptions.CreateEmptyModelWithNullIdException;
import org.junit.Test;


/**
 * @author malinink
 */
public class UserModelEmptyTest extends AbstractModelEmptyTest {
    public UserModelEmptyTest() {
        this.modelEmpty = UserModelEmpty.class;
    }

    @Test
    public void testConstructorFailsWithNullArgument() {
        exception.expect(CreateEmptyModelWithNullIdException.class);
        new UserModelEmpty(null);
    }
}
