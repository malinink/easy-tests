package easytests.models.empty;

import easytests.models.IssueStandardModelInterface;
import easytests.models.SubjectModelInterface;
import easytests.models.TopicModelInterface;
import easytests.models.UserModelInterface;
import easytests.models.exceptions.CallMethodOnEmptyModelException;
import easytests.models.exceptions.CreateEmptyModelWithNullIdException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;


/**
 * @author vkpankov
 */
public class SubjectModelEmptyTest extends AbstractModelEmptyTest {
    public SubjectModelEmptyTest() {
        this.modelEmpty = SubjectModelEmpty.class;
    }

    @Test
    public void testConstructorFailsWithNullArgument() {
        exception.expect(CreateEmptyModelWithNullIdException.class);
        new SubjectModelEmpty(null);
    }

    @Test
    public void testId() {
        Integer id = 1;
        final SubjectModelInterface subjectModel = new SubjectModelEmpty(id);
        Assert.assertEquals(id, subjectModel.getId());

        exception.expect(CallMethodOnEmptyModelException.class);
        subjectModel.setId(2);
    }

    @Test
    public void testName() {
        Integer id = 1;
        final SubjectModelInterface subjectModel = new SubjectModelEmpty(id);

        exception.expect(CallMethodOnEmptyModelException.class);
        subjectModel.setName("");
        subjectModel.getName();
    }

    @Test
    public void testUser() {
        Integer id = 1;
        UserModelInterface user = Mockito.mock(UserModelInterface.class);

        final SubjectModelInterface subjectModel = new SubjectModelEmpty(id);

        exception.expect(CallMethodOnEmptyModelException.class);
        subjectModel.setUser(user);
        subjectModel.getUser();
    }

    @Test
    public void testIssueStandard() {
        Integer id = 1;
        IssueStandardModelInterface issueStandard = Mockito.mock(IssueStandardModelInterface.class);

        final SubjectModelInterface subjectModel = new SubjectModelEmpty(id);

        exception.expect(CallMethodOnEmptyModelException.class);
        subjectModel.setIssueStandard(issueStandard);
        subjectModel.getIssueStandard();
    }
}
