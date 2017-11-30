package easytests.core.mappers;

import easytests.core.entities.TopicEntity;
import easytests.support.TopicsSupport;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author Ielay
 */
public class TopicsMapperTest extends AbstractMapperTest {

    private TopicsSupport topicsSupport = new TopicsSupport();

    @Autowired
    private TopicsMapper topicsMapper;

    @Test
    public void testFindAll() throws Exception {
        final List<TopicEntity> topicsFoundedEntities = this.topicsMapper.findAll();

        Assert.assertEquals(3, topicsFoundedEntities.size());

        Integer index = 0;
        for (TopicEntity topicEntity: topicsFoundedEntities) {
            this.topicsSupport.assertEquals(this.topicsSupport.getEntityFixtureMock(index), topicEntity);
            index++;
        }
    }

    @Test
    public void testFind() throws Exception {
        final TopicEntity topicFixtureEntity = this.topicsSupport.getEntityFixtureMock(0);

        final TopicEntity topicFoundedEntity = this.topicsMapper.find(topicFixtureEntity.getId());

        this.topicsSupport.assertEquals(topicFixtureEntity, topicFoundedEntity);
    }

    @Test
    public void testFindBySubjectId() throws Exception {
        final List<TopicEntity> topicsFixtureEntities = new ArrayList<>();
        topicsFixtureEntities.add(this.topicsSupport.getEntityFixtureMock(0));
        topicsFixtureEntities.add(this.topicsSupport.getEntityFixtureMock(1));
        final List<TopicEntity> topicsFoundedEntities = this.topicsMapper.findBySubjectId(2);

        Assert.assertEquals(topicsFixtureEntities.size(), topicsFoundedEntities.size());

        Integer index = 0;
        for (TopicEntity topicEntity: topicsFoundedEntities) {
            this.topicsSupport.assertEquals(topicsFixtureEntities.get(index), topicEntity);
            index++;
        }
    }

    @Test
    public void testInsert() throws Exception {
        final ArgumentCaptor<Integer> id = ArgumentCaptor.forClass(Integer.class);
        final TopicEntity topicUnidentifiedEntity = this.topicsSupport.getEntityAdditionalMock(0);

        this.topicsMapper.insert(topicUnidentifiedEntity);

        verify(topicUnidentifiedEntity, times(1)).setId(id.capture());
        Assert.assertNotNull(id.getValue());

        final TopicEntity topicInsertedEntity = this.topicsMapper.find(id.getValue());

        Assert.assertNotNull(topicInsertedEntity);
        this.topicsSupport.assertEqualsWithoutId(topicUnidentifiedEntity, topicInsertedEntity);
    }

    @Test
    public void testUpdate() throws Exception {
        final TopicEntity topicChangedEntity = this.topicsSupport.getEntityAdditionalMock(1);
        final TopicEntity topicBeforeUpdateEntity = this.topicsMapper.find(topicChangedEntity.getId());

        Assert.assertNotNull(topicBeforeUpdateEntity);
        this.topicsSupport.assertNotEqualsWithoutId(topicChangedEntity, topicBeforeUpdateEntity);

        this.topicsMapper.update(topicChangedEntity);

        this.topicsSupport.assertEquals(topicChangedEntity, this.topicsMapper.find(topicChangedEntity.getId()));
    }

    @Test
    public void testDelete() throws Exception {
        final Integer id = this.topicsSupport.getEntityFixtureMock(0).getId();
        final TopicEntity topicFoundedEntity = this.topicsMapper.find(id);

        Assert.assertNotNull(topicFoundedEntity);

        this.topicsMapper.delete(topicFoundedEntity);

        Assert.assertNull(this.topicsMapper.find(id));
    }
}
