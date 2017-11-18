package easytests.support;

import easytests.core.entities.TopicEntity;
import easytests.core.models.TopicModel;
import easytests.core.models.TopicModelInterface;
import easytests.core.models.empty.ModelsListEmpty;
import easytests.core.models.empty.SubjectModelEmpty;
import org.junit.Assert;
import org.mockito.Mockito;

/**
 * @author lelay
 */
public class TopicsSupport {

    protected static Object[][] additional = new Object[][]{
            {
                    // for insert entity
                    null,
                    "name",
                    1
            }
    };

    public TopicEntity getEntityAdditionalMock(Integer index) {
        return this.getEntityMock(additional[index]);
    }

    public TopicEntity getEntityMock(Object[] data) {
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

    public TopicModelInterface getModelMock(
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

    public void assertEquals(TopicModelInterface first, TopicEntity second) {
        Assert.assertEquals(first.getId(), second.getId());
        Assert.assertEquals(first.getName(), second.getName());
        Assert.assertEquals(first.getSubject().getId(), second.getSubjectId());
    }
}
