package easytests.models.empty;

import easytests.models.exceptions.CreateEmptyModelWithNullIdException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author SingularityA
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IssueStandardQuestionTypeOptionModelEmptyTest extends AbstractModelEmptyTest {
    public IssueStandardQuestionTypeOptionModelEmptyTest() {
        this.modelEmpty = IssueStandardQuestionTypeOptionModelEmpty.class;
    }

    @Test
    public void testConstructorFailsWithNullArgument() {
        exception.expect(CreateEmptyModelWithNullIdException.class);
        new IssueStandardQuestionTypeOptionModelEmpty(null);
    }
}
