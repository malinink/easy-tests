package easytests.support;

import easytests.core.entities.IssueEntity;
import easytests.core.models.IssueModelInterface;
import easytests.core.models.empty.ModelsListEmpty;
import easytests.core.models.empty.SubjectModelEmpty;
import org.junit.Assert;
import org.mockito.Mockito;

import java.util.List;

/**
 * @author greenbarrow
 */
public class IssueSupport {

    protected static Object[][] fixtures = new Object[][]{
            {
                    1,
                    "Name1",
                    1
            },
            {
                    2,
                    "Name2",
                    2
            },
            {
                    3,
                    "Name3",
                    3
            },
    };

    protected static Object[][] additional = new Object[][]{
            {
                    // for insert entity
                    null,
                    "Issue",
                    2
            },
            {
                    // for update entity with id = 1
                    1,
                    "newIssue",
                    2
            },
    };

    public IssueEntity getEntityFixtureMock(Integer index) {
        return this.getEntityMock(fixtures[index]);
    }

    public IssueEntity getEntityAdditionalMock(Integer index) {
        return this.getEntityMock(additional[index]);
    }

    private IssueEntity getEntityMock(Object[] data) {
        return this.getEntityMock(
                (Integer) data[0],
                (String) data[1],
                (Integer) data[2]
        );
    }

    private IssueEntity getEntityMock(
            Integer id,
            String name,
            Integer subjectId
    ) {
        final IssueEntity issueEntity = Mockito.mock(IssueEntity.class);
        Mockito.when(issueEntity.getId()).thenReturn(id);
        Mockito.when(issueEntity.getName()).thenReturn(name);
        Mockito.when(issueEntity.getSubjectId()).thenReturn(subjectId);
        return issueEntity;
    }

    public IssueModelInterface getModelFixtureMock(Integer index) {
        return this.getModelMock(fixtures[index]);
    }

    public IssueModelInterface getModelAdditionalMock(Integer index) {
        return this.getModelMock(additional[index]);
    }

    private IssueModelInterface getModelMock(Object[] data) {
        return this.getModelMock(
                (Integer) data[0],
                (String) data[1],
                (Integer) data[2]
        );
    }

    private IssueModelInterface getModelMock(
            Integer id,
            String name,
            Integer subjectId
    ) {
        final IssueModelInterface issueModel = Mockito.mock(IssueModelInterface.class);
        Mockito.when(issueModel.getId()).thenReturn(id);
        Mockito.when(issueModel.getName()).thenReturn(name);
        Mockito.when(issueModel.getSubject()).thenReturn(new SubjectModelEmpty(subjectId));
        Mockito.when(issueModel.getQuizzes()).thenReturn(new ModelsListEmpty());
        return issueModel;
    }

    public void assertEquals(IssueEntity expected, IssueEntity actual) {
        assertEquals(expected, actual, false);
    }

    public void assertEqualsWithoutId(IssueEntity expected, IssueEntity actual) {
        assertEquals(expected, actual, true);
    }

    private void assertEquals(IssueEntity expected, IssueEntity actual, Boolean exceptId) {
        if (!exceptId) {
            Assert.assertEquals(expected.getId(), actual.getId());
        }
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getSubjectId(), actual.getSubjectId());
    }

    public void assertNotEquals(IssueEntity unexpected, IssueEntity actual) {
        assertNotEquals(unexpected, actual, false);
    }

    public void assertNotEqualsWithoutId(IssueEntity unexpected, IssueEntity actual) {
        assertNotEquals(unexpected, actual, true);
    }

    private void assertNotEquals(IssueEntity unexpected, IssueEntity actual, Boolean exceptId) {
        if (!exceptId) {
            Assert.assertNotEquals(unexpected.getId(), actual.getId());
        }
        Assert.assertNotEquals(unexpected.getName(), actual.getName());
        Assert.assertNotEquals(unexpected.getSubjectId(), actual.getSubjectId());
    }

    public void assertEquals(IssueModelInterface expected, IssueModelInterface actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getSubject(), actual.getSubject());
        // TODO is it a good idea to assertEquals on models list and models objects here ? @malinink
        Assert.assertEquals(expected.getQuizzes(), actual.getQuizzes());
    }

    public void assertEquals(IssueModelInterface expected, IssueEntity actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getSubject().getId(), actual.getSubjectId());

    }

    public void assertEquals(IssueEntity expected, IssueModelInterface actual) {
        assertEquals(actual, expected);
    }

    public void assertModelsListEquals(List<IssueModelInterface> expected, List<IssueModelInterface> actual) {
        Assert.assertEquals(expected.size(), actual.size());
        Integer i = 0;
        for (IssueModelInterface issueModel : expected) {
            this.assertEquals(issueModel, actual.get(i));
            i++;
        }
    }

    public void assertEntitiesListEquals(List<IssueEntity> expected, List<IssueEntity> actual) {
        Assert.assertEquals(expected.size(), actual.size());
        Integer i = 0;
        for (IssueEntity issueEntity : expected) {
            this.assertEquals(issueEntity, actual.get(i));
            i++;
        }
    }

}
