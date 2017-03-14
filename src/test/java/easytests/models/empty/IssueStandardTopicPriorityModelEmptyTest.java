package easytests.models.empty;

import easytests.models.exceptions.CreateEmptyModelWithNullIdException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author SingularityA
 */

// newInstance не работает для Boolean
@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class IssueStandardTopicPriorityModelEmptyTest extends AbstractModelEmptyTest {

    public IssueStandardTopicPriorityModelEmptyTest() {
        this.modelEmpty = IssueStandardTopicPriorityModelEmpty.class;
    }

    @Test
    public void testConstructorFailsWithNullArgument() {
        exception.expect(CreateEmptyModelWithNullIdException.class);
        new IssueStandardTopicPriorityModelEmpty(null);
    }
}
