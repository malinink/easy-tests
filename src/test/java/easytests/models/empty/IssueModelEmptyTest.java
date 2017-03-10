package easytests.models.empty;

import easytests.entities.IssueEntity;
import easytests.models.IssueModelInterface;
import easytests.models.exceptions.CallMethodOnEmptyModelException;
import easytests.models.exceptions.CreateEmptyModelWithNullIdException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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

    @Test
    public void testConstructor() {
        Integer id = 1;
        IssueModelInterface issueModel = new IssueModelEmpty(id);
        Assert.assertEquals(id, issueModel.getId());
    }

    @Test
    public void testId() {
        Integer id = 1;
        final IssueModelInterface issueModel = new IssueModelEmpty(id);
        Assert.assertEquals(id, issueModel.getId());

        exception.expect(CallMethodOnEmptyModelException.class);
        issueModel.setId(11);
    }

    @Test
    public void testName() {
        Integer id = 1;
        final IssueModelInterface issueModel = new IssueModelEmpty(id);

        exception.expect(CallMethodOnEmptyModelException.class);
        issueModel.setName("ss");
        issueModel.getName();
    }

    @Test
    public void testAuthorId() {
        Integer id = 1;
        final IssueModelInterface issueModel = new IssueModelEmpty(id);

        exception.expect(CallMethodOnEmptyModelException.class);
        issueModel.setAuthorId(11);
        issueModel.getAuthorId();
    }

    @Test
    public void testMap() {
        Integer id = 1;
        final IssueEntity issueEntity = Mockito.mock(IssueEntity.class);
        final IssueModelInterface issueModel = new IssueModelEmpty(id);

        exception.expect(CallMethodOnEmptyModelException.class);
        issueModel.map(issueEntity);
    }
}
