package easytests.models.empty;

import easytests.models.exceptions.CreateEmptyModelWithNullIdException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author fortyways
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IssueModelEmptyTest extends AbstractModelEmptyTest {
    public IssueModelEmptyTest() {
        this.modelEmpty = IssueModelEmpty.class;
    }

    @Test
    public void testConstructorFailsWithNullArgument() {
        exception.expect(CreateEmptyModelWithNullIdException.class);
        new UserModelEmpty(null);
    }

}
