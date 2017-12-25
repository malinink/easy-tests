package easytests.support;

import easytests.core.entities.SubjectEntity;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.empty.IssueStandardModelEmpty;
import easytests.core.models.empty.ModelsListEmpty;
import easytests.core.models.empty.UserModelEmpty;
import java.util.List;
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

    private SubjectEntity getEntityMock(
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

    private SubjectModelInterface getModelMock(
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

    public void assertEquals(SubjectEntity expected, SubjectEntity actual) {
        assertEquals(expected, actual, false);
    }

    public void assertEqualsWithoutId(SubjectEntity expected, SubjectEntity actual) {
        assertEquals(expected, actual, true);
    }

    private void assertEquals(SubjectEntity expected, SubjectEntity actual, Boolean exceptId) {
        if (!exceptId) {
            Assert.assertEquals(expected.getId(), actual.getId());
        }
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getUserId(), actual.getUserId());
    }

    public void assertNotEquals(SubjectEntity unexpected, SubjectEntity actual) {
        assertNotEquals(unexpected, actual, false);
    }

    public void assertNotEqualsWithoutId(SubjectEntity unexpected, SubjectEntity actual) {
        assertNotEquals(unexpected, actual, true);
    }

    private void assertNotEquals(SubjectEntity unexpected, SubjectEntity actual, Boolean exceptId) {
        if (!exceptId) {
            Assert.assertNotEquals(unexpected.getId(), actual.getId());
        }
        Assert.assertNotEquals(unexpected.getName(), actual.getName());
        Assert.assertNotEquals(unexpected.getDescription(), actual.getDescription());
        Assert.assertNotEquals(unexpected.getUserId(), actual.getUserId());
    }

    public void assertEquals(SubjectModelInterface expected, SubjectModelInterface actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        // TODO is it a good idea to assertEquals on models list and models objects here ? @malinink
        Assert.assertEquals(expected.getUser(), actual.getUser());
        Assert.assertEquals(expected.getTopics(), actual.getTopics());
        Assert.assertEquals(expected.getIssueStandard(), actual.getIssueStandard());
        Assert.assertEquals(expected.getIssues(), actual.getIssues());
    }

    public void assertEquals(SubjectModelInterface expected, SubjectEntity actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getUser().getId(), actual.getUserId());

    }

    public void assertEquals(SubjectEntity expected, SubjectModelInterface actual) {
        assertEquals(actual, expected);
        Assert.assertEquals(new UserModelEmpty(expected.getUserId()), actual.getUser());
        Assert.assertEquals(new ModelsListEmpty(), actual.getTopics());
        Assert.assertEquals(new IssueStandardModelEmpty(), actual.getIssueStandard());
        Assert.assertEquals(new ModelsListEmpty(), actual.getIssues());
    }

    public void assertModelsListEquals(List<SubjectModelInterface> expected, List<SubjectModelInterface> actual) {
        Assert.assertEquals(expected.size(), actual.size());
        Integer i = 0;
        for (SubjectModelInterface subjectModel: expected) {
            this.assertEquals(subjectModel, actual.get(i));
            i++;
        }
    }

    public void assertEntitiesListEquals(List<SubjectEntity> expected, List<SubjectEntity> actual) {
        Assert.assertEquals(expected.size(), actual.size());
        Integer i = 0;
        for (SubjectEntity subjectEntity: expected) {
            this.assertEquals(subjectEntity, actual.get(i));
            i++;
        }
    }

}
