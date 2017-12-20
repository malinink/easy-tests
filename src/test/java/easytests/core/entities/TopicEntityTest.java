package easytests.core.entities;

import easytests.core.models.TopicModelInterface;
import easytests.support.TopicsSupport;
import org.junit.Test;

/**
 * @author lelay
 */
public class TopicEntityTest extends AbstractEntityTest {

    private TopicsSupport topicsSupport = new TopicsSupport();

    @Test
    public void testCommon() throws Exception {
        this.testCommon(TopicEntity.class);
    }

    @Test
    public void testMap() throws Exception {
        final TopicModelInterface topicModelMock = this.topicsSupport.getModelFixtureMock(0);
        final TopicEntity topicEntity = new TopicEntity();
        topicEntity.map(topicModelMock);

        this.topicsSupport.assertEquals(topicModelMock, topicEntity);
    }
}
