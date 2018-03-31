package easytests.core.models;

import easytests.core.entities.TopicEntity;
import easytests.support.TopicsSupport;
import org.junit.Test;
import org.meanbean.test.ConfigurationBuilder;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lelay
 */
public class TopicModelTest extends AbstractModelTest {

    private TopicsSupport topicsSupport = new TopicsSupport();

    @Override
    protected ConfigurationBuilder getConfigurationBuilder() {
        return super.getConfigurationBuilder()
                .ignoreProperty("subject");
    }

    @Test
    public void testCommon() throws Exception {
        this.testCommon(TopicModel.class);
    }

    @Test
    public void testMap() throws Exception {
        final TopicEntity topicEntity = this.topicsSupport.getEntityFixtureMock(0);
        final TopicModel topicModel = new TopicModel();

        topicModel.map(topicEntity);

        this.topicsSupport.assertEquals(topicEntity, topicModel);
    }
}
