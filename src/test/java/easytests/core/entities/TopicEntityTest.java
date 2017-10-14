package easytests.core.entities;

import easytests.core.models.TopicModelInterface;
import easytests.core.models.empty.SubjectModelEmpty;
import org.junit.Assert;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
import org.mockito.Mockito;

/**
 * @author ielay
 */
public class TopicEntityTest    {
    @Test
    public void testCommon() throws Exception {
        new BeanTester().testBean(TopicEntity.class);
        new EqualsMethodTester().testEqualsMethod(TopicEntity.class);
        new HashCodeMethodTester().testHashCodeMethod(TopicEntity.class);
    }

    @Test
    public void testMap() throws Exception {
        final Integer id = 3;
        final String name = "Name";
        final Integer subjectId = 5;

        final TopicModelInterface topicModelMock = Mockito.mock(TopicModelInterface.class);
        Mockito.when(topicModelMock.getId()).thenReturn(id);
        Mockito.when(topicModelMock.getName()).thenReturn(name);
        Mockito.when(topicModelMock.getSubject()).thenReturn(new SubjectModelEmpty(subjectId));

        final TopicEntity topicEntity = new TopicEntity();
        topicEntity.map(topicModelMock);

        Assert.assertEquals(id, topicEntity.getId());
        Assert.assertEquals(name, topicEntity.getName());
        Assert.assertEquals(subjectId, topicEntity.getSubjectId());
    }

}
