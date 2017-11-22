package easytests.core.mappers;

import easytests.core.entities.TopicEntity;
import easytests.support.TopicsSupport;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author malinink
 */
public class TopicsMapperTest extends AbstractMapperTest {

    private TopicsSupport topicsSupport = new TopicsSupport();

    @Autowired
    private TopicsMapper topicsMapper;

    @Test
    public void testFindAll() throws Exception {
        final List<TopicEntity> topicEntities = this.topicsMapper.findAll();

        Assert.assertEquals(3, topicEntities.size());

        Integer index = 0;
        for(TopicEntity topicEntity: topicEntities) {

            this.topicsSupport.assertEquals(this.topicsSupport.getEntityFixtureMock(index), topicEntity);

            index++;
        }
    }

    @Test
    public void testFind() throws Exception {
        final TopicEntity topicFixtureEntity = this.topicsSupport.getEntityFixtureMock(0);
        final TopicEntity topicEntity = this.topicsMapper.find(topicFixtureEntity.getId());

        this.topicsSupport.assertEquals(topicFixtureEntity, topicEntity);
    }

    @Test
    public void testFindBySubjectId() throws Exception {
        List<TopicEntity> topicEntities = this.topicsMapper.findBySubjectId(3);
        Assert.assertEquals(1, topicEntities.size());

        this.topicsSupport.assertEquals(topicEntities.get(0), this.topicsSupport.getEntityFixtureMock(2));

        topicEntities = this.topicsMapper.findBySubjectId(2);
        Assert.assertEquals(2, topicEntities.size());

        Integer index = 0;
        for(TopicEntity topicEntity: topicEntities) {

            this.topicsSupport.assertEquals(this.topicsSupport.getEntityFixtureMock(index), topicEntity);

            index++;
        }
    }

    @Test
    public void testInsert() throws Exception {
        final ArgumentCaptor<Integer> id = ArgumentCaptor.forClass(Integer.class);
        final TopicEntity topicAdditionalEntity = this.topicsSupport.getEntityAdditionalMock(0);

        this.topicsMapper.insert(topicAdditionalEntity);

        verify(topicAdditionalEntity, times(1)).setId(id.capture());
        Assert.assertNotNull(id.getValue());

        final TopicEntity topicInsertedEntity = this.topicsMapper.find(id.getValue());

        Assert.assertNotNull(topicAdditionalEntity);
        this.topicsSupport.assertEqualsWithoutId(topicAdditionalEntity, topicInsertedEntity);
    }

    @Test
    public void testUpdate() throws Exception {
        final TopicEntity topicAdditionalEntity = this.topicsSupport.getEntityAdditionalMock(1);
        final Integer id = topicAdditionalEntity.getId();
        final TopicEntity topicEntity = this.topicsMapper.find(id);

        Assert.assertNotNull(topicEntity);
        this.topicsSupport.assertNotEqualsWithoutId(topicAdditionalEntity, topicEntity);

        this.topicsMapper.update(topicAdditionalEntity);

        this.topicsSupport.assertEquals(topicAdditionalEntity, this.topicsMapper.find(id));
    }

    @Test
    public void testDelete() throws Exception {
        final Integer id = this.topicsSupport.getEntityFixtureMock(0).getId();
        TopicEntity topicEntity = this.topicsMapper.find(id);

        Assert.assertNotNull(topicEntity);

        this.topicsMapper.delete(topicEntity);
        topicEntity = this.topicsMapper.find(id);

        Assert.assertNull(topicEntity);
    }
}
