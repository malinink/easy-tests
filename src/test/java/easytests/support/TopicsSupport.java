package easytests.support;

import easytests.core.entities.TopicEntity;
import easytests.core.models.TopicModel;
import easytests.core.models.TopicModelInterface;
import easytests.core.models.empty.ModelsListEmpty;
import easytests.core.models.empty.SubjectModelEmpty;
import java.util.List;
import org.junit.Assert;
import org.mockito.Mockito;


/**
 * @author lelay
 */
public class TopicsSupport {

    protected static Object[][] fixtures = new Object[][]{
            {
                    1,
                    "Name1",
                    2
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
            }
    };

    protected static Object[][] additional = new Object[][]{
            {
                    // for insert entity
                    null,
                    "name",
                    1
            },
            {
                    // for update entity with id = 1
                    1,
                    "newName",
                    5
            }
    };

    public TopicEntity getEntityFixtureMock(Integer index) {
        return this.getEntityMock(fixtures[index]);
    }

    public TopicEntity getEntityAdditionalMock(Integer index) {
        return this.getEntityMock(additional[index]);
    }

    private TopicEntity getEntityMock(Object[] data) {
        return this.getEntityMock(
                (Integer) data[0],
                (String)  data[1],
                (Integer) data[2]
        );
    }

    private TopicEntity getEntityMock (
            Integer id,
            String name,
            Integer subjectId
    ) {
        final TopicEntity topicEntityMock = Mockito.mock(TopicEntity.class);

        Mockito.when(topicEntityMock.getId()).thenReturn(id);
        Mockito.when(topicEntityMock.getName()).thenReturn(name);
        Mockito.when(topicEntityMock.getSubjectId()).thenReturn(subjectId);

        return topicEntityMock;
    }

    public TopicModelInterface getModelFixtureMock(Integer index) {
        return this.getModelMock(fixtures[index]);
    }

    public TopicModelInterface getModelAdditionalMock(Integer index) {
        return this.getModelMock(additional[index]);
    }

    private TopicModelInterface getModelMock(Object[] data) {
        return this.getModelMock(
                (Integer) data[0],
                (String)  data[1],
                (Integer) data[2]
        );
    }

    private TopicModelInterface getModelMock(
        Integer id,
        String name,
        Integer subjectId
    ) {
        final TopicModelInterface topicModelMock = Mockito.mock(TopicModelInterface.class);

        Mockito.when(topicModelMock.getId()).thenReturn(id);
        Mockito.when(topicModelMock.getName()).thenReturn(name);
        Mockito.when(topicModelMock.getSubject()).thenReturn(new SubjectModelEmpty(subjectId));

        Mockito.when(topicModelMock.getQuestions()).thenReturn(new ModelsListEmpty());

        return topicModelMock;
    }

    private void assertEquals(TopicEntity expected, TopicEntity actual, Boolean exceptId) {
        if (!exceptId) {
            Assert.assertEquals(expected.getId(), actual.getId());
        }
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getSubjectId(), actual.getSubjectId());
    }

    public void assertEquals(TopicEntity expected, TopicEntity actual) {
        this.assertEquals(expected, actual, false);
    }

    public void assertEqualsWithoutId(TopicEntity expected, TopicEntity actual) {
        this.assertEquals(expected, actual, true);
    }

    public void assertNotEquals(TopicEntity unexpected, TopicEntity actual) {
        this.assertNotEquals(unexpected, actual, false);
    }

    public void assertNotEqualsWithoutId(TopicEntity unexpected, TopicEntity actual) {
        this.assertNotEquals(unexpected, actual, true);
    }

    private void assertNotEquals(TopicEntity unexpected, TopicEntity actual, Boolean exceptId) {
        if (!exceptId) {
            Assert.assertNotEquals(unexpected.getId(), actual.getId());
        }
        Assert.assertNotEquals(unexpected.getName(), actual.getName());
        Assert.assertNotEquals(unexpected.getSubjectId(), actual.getSubjectId());
    }

    public void assertEquals(TopicModelInterface expected, TopicModelInterface actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());

        Assert.assertEquals(expected.getSubject(), actual.getSubject());
        Assert.assertEquals(expected.getQuestions(), actual.getQuestions());
    }

    public void assertEquals(TopicModelInterface expected, TopicEntity actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getSubject().getId(), actual.getSubjectId());
    }

    public void assertEquals(TopicEntity expected, TopicModelInterface actual) {
        this.assertEquals(actual, expected);
        Assert.assertEquals(new ModelsListEmpty(), actual.getQuestions());
    }

    public void assertModelsListEquals(List<TopicModelInterface> expected, List<TopicModelInterface> actual) {
        Assert.assertEquals(expected.size(), actual.size());

        Integer i = 0;
        for(TopicModelInterface topicModel: expected) {
            this.assertEquals(topicModel, actual.get(i));
            i++;
        }
    }

    public void assertEntitiesListEquals(List<TopicEntity> expected, List<TopicEntity> actual) {
        Assert.assertEquals(expected.size(), actual.size());

        Integer i = 0;
        for(TopicEntity topicEntity: expected) {
            this.assertEquals(topicEntity, actual.get(i));
            i++;
        }
    }
}
