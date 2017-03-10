package easytests.models.empty;

import easytests.models.exceptions.CreateEmptyModelWithNullIdException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author firkhraag
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionModelEmptyTest extends AbstractModelEmptyTest {
    public QuestionModelEmptyTest() {
        this.modelEmpty = QuestionModelEmpty.class;
    }

    @Test
    public void testConstructorFailsWithNullArgument() {
        exception.expect(CreateEmptyModelWithNullIdException.class);
        new QuestionModelEmpty(null);
    }
}
