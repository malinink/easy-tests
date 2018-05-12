package easytests.support;

import easytests.core.entities.IssueStandardTopicPriorityEntity;
import easytests.core.models.IssueStandardTopicPriorityModelInterface;
import easytests.core.models.empty.IssueStandardModelEmpty;
import easytests.core.models.empty.TopicModelEmpty;

import java.util.List;
import org.junit.Assert;
import org.mockito.Mockito;


/**
 * @author SakhPrace
 */
public class IssueStandardTopicPrioritySupport {

    protected static Object[][] fixtures = new Object[][]{
            {
                1,
                1,
                true,
                1
            },
            {
                2,
                2,
                false,
                1
            },
            {
                3,
                3,
                true,
                2
            }
    };

    protected static Object[][] additional = new Object[][]{
            {
                // for insert entity
                null,
                4,
                true,
                2
            },
            {
                // for update entity with id = 1
                1,
                1,
                false,
                2
            }
    };

    public IssueStandardTopicPriorityEntity getEntityFixtureMock(Integer index) {
        return this.getEntityMock(fixtures[index]);
    }


    public IssueStandardTopicPriorityEntity getEntityAdditionalMock(Integer index) {
        return this.getEntityMock(additional[index]);
    }

    private IssueStandardTopicPriorityEntity getEntityMock(Object[] data) {
        return this.getEntityMock(
                (Integer) data[0],
                (Integer) data[1],
                (Boolean) data[2],
                (Integer) data[3]
        );
    }

    private IssueStandardTopicPriorityEntity getEntityMock(
            Integer id,
            Integer topicId,
            Boolean isPreferable,
            Integer issueStandardId
    ) {
        final IssueStandardTopicPriorityEntity issueStandardTopicPriorityEntity = Mockito.mock(IssueStandardTopicPriorityEntity.class);
        Mockito.when(issueStandardTopicPriorityEntity.getId()).thenReturn(id);
        Mockito.when(issueStandardTopicPriorityEntity.getTopicId()).thenReturn(topicId);
        Mockito.when(issueStandardTopicPriorityEntity.getIsPreferable()).thenReturn(isPreferable);
        Mockito.when(issueStandardTopicPriorityEntity.getIssueStandardId()).thenReturn(issueStandardId);
        return issueStandardTopicPriorityEntity ;
    }

    public IssueStandardTopicPriorityModelInterface getModelFixtureMock(Integer index) {
        return this.getModelMock(fixtures[index]);
    }

    public IssueStandardTopicPriorityModelInterface getModelAdditionalMock(Integer index) {
        return this.getModelMock(additional[index]);
    }

    private IssueStandardTopicPriorityModelInterface getModelMock(Object[] data) {
        return this.getModelMock(
                (Integer) data[0],
                (Integer) data[1],
                (Boolean) data[2],
                (Integer) data[3]
        );
    }

    private IssueStandardTopicPriorityModelInterface getModelMock(
            Integer id,
            Integer topicId,
            Boolean isPreferable,
            Integer issueStandardId
    ) {
        final IssueStandardTopicPriorityModelInterface issueStandardTopicPriorityModel = Mockito.mock(IssueStandardTopicPriorityModelInterface.class);
        Mockito.when(issueStandardTopicPriorityModel.getId()).thenReturn(id);
        Mockito.when(issueStandardTopicPriorityModel.getIsPreferable()).thenReturn(isPreferable);
        Mockito.when(issueStandardTopicPriorityModel.getTopic()).thenReturn(new TopicModelEmpty(topicId));
        Mockito.when(issueStandardTopicPriorityModel.getIssueStandard()).thenReturn(new IssueStandardModelEmpty(issueStandardId));
        return issueStandardTopicPriorityModel;
    }

    public void assertEquals(IssueStandardTopicPriorityEntity expected, IssueStandardTopicPriorityEntity actual) {

        assertEquals(expected, actual, false);

        assertEquals(expected, actual, false);

    }

    public void assertEqualsWithoutId(IssueStandardTopicPriorityEntity expected, IssueStandardTopicPriorityEntity actual) {
        assertEquals(expected, actual, true);
    }

    private void assertEquals(IssueStandardTopicPriorityEntity expected, IssueStandardTopicPriorityEntity actual, Boolean exceptId) {
        if (!exceptId) {
            Assert.assertEquals(expected.getId(), actual.getId());
        }
        Assert.assertEquals(expected.getIsPreferable(), actual.getIsPreferable());
        Assert.assertEquals(expected.getIssueStandardId(), actual.getIssueStandardId());
        Assert.assertEquals(expected.getTopicId(), actual.getTopicId());
    }

    public void assertNotEquals(IssueStandardTopicPriorityEntity unexpected, IssueStandardTopicPriorityEntity actual) {
        assertNotEquals(unexpected, actual, false);
    }

    public void assertNotEqualsWithoutId(IssueStandardTopicPriorityEntity unexpected, IssueStandardTopicPriorityEntity actual) {
        assertNotEquals(unexpected, actual, true);
    }

    private void assertNotEquals(IssueStandardTopicPriorityEntity unexpected, IssueStandardTopicPriorityEntity actual, Boolean exceptId) {
        if (!exceptId) {
            Assert.assertNotEquals(unexpected.getId(), actual.getId());
        }
        Assert.assertNotEquals(unexpected.getIsPreferable(), actual.getIsPreferable());
        Assert.assertNotEquals(unexpected.getTopicId(), actual.getTopicId());
        Assert.assertNotEquals(unexpected.getIssueStandardId(), actual.getIssueStandardId());
    }

    public void assertEquals(IssueStandardTopicPriorityModelInterface expected, IssueStandardTopicPriorityModelInterface actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getIsPreferable(), actual.getIsPreferable());
        Assert.assertEquals(expected.getTopic(), actual.getTopic());
        Assert.assertEquals(expected.getIssueStandard(), actual.getIssueStandard());
    }

    public void assertEquals(IssueStandardTopicPriorityModelInterface expected, IssueStandardTopicPriorityEntity actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getTopic().getId(), actual.getTopicId());
        Assert.assertEquals(expected.getIsPreferable(), actual.getIsPreferable());
        Assert.assertEquals(expected.getIssueStandard().getId(), actual.getIssueStandardId());
    }

    public void assertEquals(IssueStandardTopicPriorityEntity expected, IssueStandardTopicPriorityModelInterface actual) {
        assertEquals(actual, expected);
        Assert.assertEquals(new TopicModelEmpty(expected.getTopicId()), actual.getTopic());
        Assert.assertEquals(new IssueStandardModelEmpty(expected.getIssueStandardId()), actual.getIssueStandard());
    }

    public void assertModelsListEquals(List<IssueStandardTopicPriorityModelInterface> expected, List<IssueStandardTopicPriorityModelInterface> actual) {
        Assert.assertEquals(expected.size(), actual.size());
        Integer i = 0;
        for (IssueStandardTopicPriorityModelInterface issueStandardTopicPriorityModel: expected) {
            this.assertEquals(issueStandardTopicPriorityModel, actual.get(i));
            i++;
        }
    }

    public void assertEntitiesListEquals(List<IssueStandardTopicPriorityEntity> expected, List<IssueStandardTopicPriorityEntity> actual) {
        Assert.assertEquals(expected.size(), actual.size());
        Integer i = 0;
        for (IssueStandardTopicPriorityEntity issueStandardTopicPriorityEntity: expected) {
            this.assertEquals(issueStandardTopicPriorityEntity, actual.get(i));
            i++;
        }
    }
}
