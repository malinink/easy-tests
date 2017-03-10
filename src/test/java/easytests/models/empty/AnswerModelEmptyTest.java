package easytests.models.empty;

import easytests.models.exceptions.CreateEmptyModelWithNullIdException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author rezenbekk
 */
@RunWith(SpringRunner.class)
@Ignore
@SpringBootTest
public class AnswerModelEmptyTest extends AbstractModelEmptyTest {
    public AnswerModelEmptyTest() {
        modelEmpty = AnswerModelEmpty.class;
    }

    @Test
    public void testConstructorFailsWithNullArgument() {
        exception.expect(CreateEmptyModelWithNullIdException.class);
        new AnswerModelEmpty(null);
    }
}
