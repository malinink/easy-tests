package easytests.mappers;

import easytests.config.DatabaseConfig;
import easytests.entities.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
    public void findAllTest() throws Exception {
        List<IssueStandardTopicPriority> topicPriorities = this.topicPriorityMapper.findAll();

        Assert.assertNotNull(topicPriorities);
        Assert.assertEquals(3, topicPriorities.size());
    }

    @Test
    public void findTest() throws Exception {
        final IssueStandardTopicPriorityInterface topicPriority = this.topicPriorityMapper.find(1);

        Assert.assertEquals((Integer) 1, topicPriority.getId());
        Assert.assertEquals((Integer) 1, topicPriority.getTopicId());
        Assert.assertEquals(Priority.HIGH, topicPriority.getPriority());
        Assert.assertEquals((Integer) 1, topicPriority.getIssueStandardId());
    }

    @Test
    public void findByIssueStandardTest() throws Exception {
        IssueStandardInterface issueStandard = Mockito.mock(IssueStandardInterface.class);
        Mockito.when(issueStandard.getId()).thenReturn(1);

        final List<IssueStandardTopicPriority> topicPriorities
                = this.topicPriorityMapper.findByIssueStandard(issueStandard);

        Assert.assertNotNull(topicPriorities);
        Assert.assertEquals(2, topicPriorities.size());

        IssueStandardTopicPriority topicPriority = topicPriorities.get(1);

        Assert.assertEquals((Integer) 2, topicPriority.getTopicId());
        Assert.assertEquals(Priority.LOW, topicPriority.getPriority());
        Assert.assertEquals((Integer) 1, topicPriority.getIssueStandardId());
    }

    // вставка enum некорректная
    @Ignore
    @Test
    public void insertTest() throws Exception {
        IssueStandardTopicPriorityInterface topicPriority = new IssueStandardTopicPriority();
        topicPriority.setTopicId(5).setPriority(Priority.LOW).setIssueStandardId(3);
        this.topicPriorityMapper.insert(topicPriority);

        IssueStandardInterface issueStandard = Mockito.mock(IssueStandardInterface.class);
        Mockito.when(issueStandard.getId()).thenReturn(3);

        List<IssueStandardTopicPriority> topicPriorities
                = this.topicPriorityMapper.findByIssueStandard(issueStandard);

        Assert.assertNotNull(topicPriorities);
        Assert.assertEquals(topicPriorities.get(0).getTopicId(), topicPriority.getTopicId());
        Assert.assertEquals(topicPriorities.get(0).getPriority(), topicPriority.getPriority());
    }

    // вставка enum некорректная
    @Ignore
    @Test
    public void updateTest() throws Exception {
        IssueStandardTopicPriorityInterface topicPriority = this.topicPriorityMapper.find(3);
        Assert.assertNotNull(topicPriority);
        Assert.assertEquals(Priority.HIGH, topicPriority.getPriority());

        topicPriority.setPriority(Priority.LOW);
        this.topicPriorityMapper.update(topicPriority);
        topicPriority = this.topicPriorityMapper.find(3);
        Assert.assertEquals(Priority.LOW, topicPriority.getPriority());
    }

    @Test
    public void deleteTest() throws Exception {
        IssueStandardTopicPriorityInterface topicPriority = this.topicPriorityMapper.find(3);
        Assert.assertNotNull(topicPriority);
        this.topicPriorityMapper.delete(topicPriority);
        topicPriority = this.topicPriorityMapper.find(3);
        Assert.assertNull(topicPriority);
    }
}
