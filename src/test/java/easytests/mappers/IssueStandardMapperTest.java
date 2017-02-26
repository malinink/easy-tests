package easytests.mappers;

import easytests.config.DatabaseConfig;
import easytests.entities.IssueStandard;
import easytests.entities.IssueStandardInterface;
import org.junit.Assert;
import org.junit.Ignore;
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
public class IssueStandardMapperTest {

    @Autowired
    private IssueStandardMapper issueStandardMapper;

    @Test
    public void findTest() {
        final IssueStandardInterface issueStandard = this.issueStandardMapper.find(1);

        Assert.assertEquals((Integer) 1, issueStandard.getId());
        Assert.assertEquals((Integer) 300, issueStandard.getTimeLimit());
        Assert.assertEquals((Integer) 30, issueStandard.getQuestionsNumber());
        Assert.assertEquals((Integer) 1, issueStandard.getSubjectId());

        Assert.assertNotNull(issueStandard.getIssueStandardTopicPriorities());
        Assert.assertNotNull(issueStandard.getIssueStandardQuestionTypeOptions());

        Assert.assertEquals(2, issueStandard.getIssueStandardTopicPriorities().size());
        Assert.assertEquals(3, issueStandard.getIssueStandardQuestionTypeOptions().size());
    }

    @Test
    public void findBySubjectIdTest() {
        final IssueStandardInterface issueStandard = this.issueStandardMapper.findBySubjectId(3);

        Assert.assertEquals((Integer) 2, issueStandard.getId());
        Assert.assertEquals(null, issueStandard.getTimeLimit());
        Assert.assertEquals((Integer) 15, issueStandard.getQuestionsNumber());
        Assert.assertEquals((Integer) 3, issueStandard.getSubjectId());

        Assert.assertNotNull(issueStandard.getIssueStandardTopicPriorities());
        Assert.assertNotNull(issueStandard.getIssueStandardQuestionTypeOptions());

        Assert.assertEquals(1, issueStandard.getIssueStandardTopicPriorities().size());
        Assert.assertEquals(2, issueStandard.getIssueStandardQuestionTypeOptions().size());
    }

    @Test
    public void insertTest() {
        IssueStandardInterface issueStandard = new IssueStandard();
        issueStandard.setTimeLimit(1200).setQuestionsNumber(30).setSubjectId(2);
        this.issueStandardMapper.insert(issueStandard);

        issueStandard = this.issueStandardMapper.find(3);
        Assert.assertEquals((Integer) 1200, issueStandard.getTimeLimit());
        Assert.assertEquals((Integer) 30, issueStandard.getQuestionsNumber());
        Assert.assertEquals((Integer) 2, issueStandard.getSubjectId());
    }

    @Test
    public void updateTest() {
        IssueStandardInterface issueStandard = this.issueStandardMapper.find(2);
        Assert.assertNotNull(issueStandard);

        issueStandard.setTimeLimit(6000).setQuestionsNumber(50);
        this.issueStandardMapper.update(issueStandard);

        issueStandard = this.issueStandardMapper.find(2);
        Assert.assertEquals((Integer) 6000, issueStandard.getTimeLimit());
        Assert.assertEquals((Integer) 50, issueStandard.getQuestionsNumber());
    }

    // отдельно проходит, валится при прохождении подряд (??)
    @Ignore
    @Test
    public void deleteTest() {
        IssueStandardInterface issueStandard = this.issueStandardMapper.find(1);

        Assert.assertNotNull(issueStandard);
        Assert.assertNotNull(issueStandard.getIssueStandardTopicPriorities());
        Assert.assertNotNull(issueStandard.getIssueStandardQuestionTypeOptions());

        this.issueStandardMapper.delete(issueStandard);
        issueStandard = this.issueStandardMapper.find(1);
        Assert.assertNull(issueStandard);
    }
}
