package easytests.core.mappers;

import easytests.core.entities.TopicEntity;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author malinink
 */
public class TopicsMapperTest extends AbstractMapperTest {
    @Autowired
    private TopicsMapper topicsMapper;

    @Test
    public void testFindAll() throws Exception {
        final List<TopicEntity> topicsEntities = this.topicsMapper.findAll();
        Assert.assertEquals((Integer) 3, (Integer) topicsEntities.size());
    }

    @Test
    public void testFind() throws Exception {
        final TopicEntity topicEntity = this.topicsMapper.find(1);
        Assert.assertEquals((Integer) 1, topicEntity.getId());
        Assert.assertEquals("Name1", topicEntity.getName());
        Assert.assertEquals((Integer) 2, topicEntity.getSubjectId());
    }

    @Test
    public void testFindBySubjectId() throws Exception {
        final List<TopicEntity> topicEntities = this.topicsMapper.findBySubjectId(3);
        Assert.assertEquals(1, topicEntities.size());
        Assert.assertEquals("Name3", topicEntities.get(0).getName());
    }

    @Test
    public void testInsert() throws Exception {
        final ArgumentCaptor<Integer> id = ArgumentCaptor.forClass(Integer.class);
        final String name = "FirstName";
        final Integer subjectId = 3;

        TopicEntity topicEntity = Mockito.mock(TopicEntity.class);
        Mockito.when(topicEntity.getName()).thenReturn(name);
        Mockito.when(topicEntity.getSubjectId()).thenReturn(subjectId);

        this.topicsMapper.insert(topicEntity);

        verify(topicEntity, times(1)).setId(id.capture());

        Assert.assertNotNull(id.getValue());

        topicEntity = this.topicsMapper.find(id.getValue());
        Assert.assertEquals(id.getValue(), topicEntity.getId());
        Assert.assertEquals(name, topicEntity.getName());
        Assert.assertEquals(subjectId, topicEntity.getSubjectId());
    }

    @Test
    public void testUpdate() throws Exception {
        final Integer id = 1;
        final String name = "NewName";
        final Integer subjectId = 5;

        TopicEntity topicEntity = this.topicsMapper.find(id);
        Assert.assertNotNull(topicEntity);
        Assert.assertEquals(id, topicEntity.getId());
        Assert.assertNotEquals(name, topicEntity.getName());
        Assert.assertNotEquals(subjectId, topicEntity.getSubjectId());

        topicEntity = Mockito.mock(TopicEntity.class);
        Mockito.when(topicEntity.getId()).thenReturn(id);
        Mockito.when(topicEntity.getName()).thenReturn(name);
        Mockito.when(topicEntity.getSubjectId()).thenReturn(subjectId);

        this.topicsMapper.update(topicEntity);

        topicEntity = this.topicsMapper.find(id);
        Assert.assertEquals(id, topicEntity.getId());
        Assert.assertEquals(name, topicEntity.getName());
        Assert.assertEquals(subjectId, topicEntity.getSubjectId());
    }

    @Test
    public void testDelete() throws Exception {
        TopicEntity topicEntity = this.topicsMapper.find(1);
        Assert.assertNotNull(topicEntity);

        this.topicsMapper.delete(topicEntity);
        topicEntity = this.topicsMapper.find(1);
        Assert.assertNull(topicEntity);
    }
}
