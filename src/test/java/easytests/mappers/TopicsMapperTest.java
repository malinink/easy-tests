package easytests.mappers;

import easytests.config.DatabaseConfig;
import easytests.entities.*;
import easytests.mappers.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * @author loriens
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:database.test.properties"})
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {DatabaseConfig.class})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/mappersTestData.sql")
public class TopicsMapperTest {
    @Autowired
    private TopicsMapper topicsMapper;

    @Test
    public void testFind() throws Exception {
        final TopicInterface topic = this.topicsMapper.find(1);

        Assert.assertEquals((long) 1, (long) topic.getId());
        Assert.assertEquals("Theme1", topic.getName());
    }

    @Test
    public void testDelete() throws Exception {
        TopicInterface topic = this.topicsMapper.find(1);
        Assert.assertNotNull(topic);
        this.topicsMapper.delete(topic);
        topic = this.topicsMapper.find(1);
        Assert.assertNull(topic);
    }

}
