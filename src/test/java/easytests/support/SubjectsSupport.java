package easytests.support;

import easytests.core.entities.SubjectEntity;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.empty.IssueStandardModelEmpty;
import easytests.core.models.empty.ModelsListEmpty;
import easytests.core.models.empty.UserModelEmpty;
import org.junit.Assert;
import org.mockito.Mockito;


/**
 * @author malinink
 */
public class SubjectsSupport {

    protected static Object[][] fixtures = new Object[][]{
            {
                    1,
                    "Subject1",
                    "Subject Description 1",
                    2
            },
            {
                    2,
                    "Subject2",
                    "Subject Description 2",
                    2
            },
            {
                    3,
                    "Subject3",
                    "Subject Description 3",
                    3
            },
    };

    protected static Object[][] additional = new Object[][]{
            {
                    // for insert entity
                    null,
                    "Subject",
                    "Subject description",
                    2
            },
            {
                    // for update entity with id = 1
                    1,
                    "newSubject",
                    "New Subject description",
                    1
            },
    };

    public SubjectEntity getEntityFixtureMock(Integer index) {
        return this.getEntityMock(fixtures[index]);
    }

    public SubjectEntity getEntityAdditionalMock(Integer index) {
        return this.getEntityMock(additional[index]);
    }

    private SubjectEntity getEntityMock(Object[] data) {
        return this.getEntityMock(
                (Integer) data[0],
                (String) data[1],
                (String) data[2],
                (Integer) data[3]
        );
    }

    public SubjectEntity getEntityMock(
            Integer id,
            String name,
            String description,
            Integer userId
    ) {
        final SubjectEntity subjectEntity = Mockito.mock(SubjectEntity.class);
        Mockito.when(subjectEntity.getId()).thenReturn(id);
        Mockito.when(subjectEntity.getName()).thenReturn(name);
        Mockito.when(subjectEntity.getDescription()).thenReturn(description);
        Mockito.when(subjectEntity.getUserId()).thenReturn(userId);
        return subjectEntity;
    }

    public SubjectModelInterface getModelFixtureMock(Integer index) {
        return this.getModelMock(fixtures[index]);
    }

    public SubjectModelInterface getModelAdditionalMock(Integer index) {
        return this.getModelMock(additional[index]);
    }

    private SubjectModelInterface getModelMock(Object[] data) {
        return this.getModelMock(
                (Integer) data[0],
                (String) data[1],
                (String) data[2],
                (Integer) data[3]
        );
    }

    public SubjectModelInterface getModelMock(
            Integer id,
            String name,
            String description,
            Integer userId
    ) {
        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);
        Mockito.when(subjectModel.getId()).thenReturn(id);
        Mockito.when(subjectModel.getName()).thenReturn(name);
        Mockito.when(subjectModel.getDescription()).thenReturn(description);
        Mockito.when(subjectModel.getUser()).thenReturn(new UserModelEmpty(userId));
        Mockito.when(subjectModel.getTopics()).thenReturn(new ModelsListEmpty());
        Mockito.when(subjectModel.getIssueStandard()).thenReturn(new IssueStandardModelEmpty());
        Mockito.when(subjectModel.getIssues()).thenReturn(new ModelsListEmpty());
        return subjectModel;
    }

    public void assertEquals(SubjectEntity first, SubjectEntity second) {
        assertEquals(first, second, false);
    }

    public void assertEqualsWithoutId(SubjectEntity first, SubjectEntity second) {
        assertEquals(first, second, true);
    }

    private void assertEquals(SubjectEntity first, SubjectEntity second, Boolean exceptId) {
        if (!exceptId) {
            Assert.assertEquals(first.getId(), second.getId());
        }
        Assert.assertEquals(first.getName(), second.getName());
        Assert.assertEquals(first.getDescription(), second.getDescription());
        Assert.assertEquals(first.getUserId(), second.getUserId());
    }

    public void assertNotEquals(SubjectEntity first, SubjectEntity second) {
        assertNotEquals(first, second, false);
    }

    public void assertNotEqualsWithoutId(SubjectEntity first, SubjectEntity second) {
        assertNotEquals(first, second, true);
    }

    private void assertNotEquals(SubjectEntity first, SubjectEntity second, Boolean exceptId) {
        if (!exceptId) {
            Assert.assertNotEquals(first.getId(), second.getId());
        }
        Assert.assertNotEquals(first.getName(), second.getName());
        Assert.assertNotEquals(first.getDescription(), second.getDescription());
        Assert.assertNotEquals(first.getUserId(), second.getUserId());
    }

    public void assertEquals(SubjectModelInterface first, SubjectModelInterface second) {
        Assert.assertEquals(first.getId(), second.getId());
        Assert.assertEquals(first.getName(), second.getName());
        Assert.assertEquals(first.getDescription(), second.getDescription());
        // TODO is it a good idea to assertEquals on models list and models objects here ? @malinink
        Assert.assertEquals(first.getUser(), second.getUser());
        Assert.assertEquals(first.getTopics(), second.getTopics());
        Assert.assertEquals(first.getIssueStandard(), second.getIssueStandard());
        Assert.assertEquals(first.getIssues(), second.getIssues());
    }

    public void assertEquals(SubjectModelInterface first, SubjectEntity second) {
        Assert.assertEquals(first.getId(), second.getId());
        Assert.assertEquals(first.getName(), second.getName());
        Assert.assertEquals(first.getDescription(), second.getDescription());
        Assert.assertEquals(first.getUser().getId(), second.getUserId());

    }

    public void assertEquals(SubjectEntity first, SubjectModelInterface second) {
        assertEquals(second, first);
        Assert.assertEquals(new UserModelEmpty(first.getUserId()), second.getUser());
        Assert.assertEquals(new ModelsListEmpty(), second.getTopics());
        Assert.assertEquals(new IssueStandardModelEmpty(), second.getIssueStandard());
        Assert.assertEquals(new ModelsListEmpty(), second.getIssues());
    }

}
