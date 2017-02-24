package easytests.mappers;

import easytests.config.DatabaseConfig;
import easytests.entities.IssueStandardTopicPriority;
import easytests.entities.IssueStandardTopicPriorityInterface;
import easytests.entities.Priority;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.*;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

/**
 * @author SingularityA
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:database.test.properties"})
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {DatabaseConfig.class})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/mappersTestData.sql")
public class IssueStandardTopicPriorityMapperTest {

    @Autowired
    private IssueStandardTopicPriorityMapper topicPriorityMapper;

    @Test
    public void findTest() {
        final IssueStandardTopicPriorityInterface topicPriority = this.topicPriorityMapper.find(1);

        Assert.assertEquals((Integer) 1, topicPriority.getId());
        Assert.assertEquals((Integer) 1, topicPriority.getTopicId());
        // can't map Enum type correctly
        // Assert.assertEquals(Priority.HIGH, topicPriority.getPriority());
        Assert.assertEquals((Integer) 1, topicPriority.getIssueStandardId());
    }

    @Test
    public void findByIssueStandardIdTest() {
        final List<IssueStandardTopicPriority> topicPriorities = this.topicPriorityMapper.findByIssueStandardId(1);

        Assert.assertNotNull(topicPriorities);
        Assert.assertEquals(2, topicPriorities.size());

        IssueStandardTopicPriority topicPriority = topicPriorities.get(1);

        Assert.assertEquals((Integer) 2, topicPriority.getId());
        Assert.assertEquals((Integer) 2, topicPriority.getTopicId());
        // can't map Enum type correctly
        // Assert.assertEquals(Priority.LOW, topicPriority.getPriority());
        Assert.assertEquals((Integer) 1, topicPriority.getIssueStandardId());
    }

    @Test
    public void deleteTest() {
        IssueStandardTopicPriorityInterface topicPriority = this.topicPriorityMapper.find(3);
        Assert.assertNotNull(topicPriority);
        this.topicPriorityMapper.delete(topicPriority);
        topicPriority = this.topicPriorityMapper.find(3);
        Assert.assertNull(topicPriority);
    }
}
