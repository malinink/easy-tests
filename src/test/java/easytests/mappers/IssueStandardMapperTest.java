package easytests.mappers;

import easytests.config.DatabaseConfig;
import easytests.entities.IssueStandard;
import easytests.entities.IssueStandardInterface;
import easytests.entities.SubjectInterface;
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
public class IssueStandardMapperTest {

    @Autowired
    private IssueStandardMapper issueStandardMapper;

    @Test
    public void findAllTest() throws Exception {
        List<IssueStandard> issueStandards = this.issueStandardMapper.findAll();

        Assert.assertNotNull(issueStandards);
        Assert.assertEquals(2, issueStandards.size());
    }

    @Test
    public void findTest() throws Exception {
        final IssueStandardInterface issueStandard = this.issueStandardMapper.find(1);

        Assert.assertEquals((Integer) 1, issueStandard.getId());
        Assert.assertEquals((Integer) 300, issueStandard.getTimeLimit());
        Assert.assertEquals((Integer) 30, issueStandard.getQuestionsNumber());
        Assert.assertNull(issueStandard.getIssueStandardTopicPriorities());
        Assert.assertNull(issueStandard.getIssueStandardQuestionTypeOptions());
        Assert.assertNull(issueStandard.getSubject());
    }

    @Test
    public void findBySubjectTest() throws Exception {
        SubjectInterface subject = Mockito.mock(SubjectInterface.class);
        Mockito.when(subject.getId()).thenReturn(3);

        final IssueStandardInterface issueStandard = this.issueStandardMapper.findBySubject(subject);

        Assert.assertEquals((Integer) 2, issueStandard.getId());
        Assert.assertEquals(null, issueStandard.getTimeLimit());
        Assert.assertEquals((Integer) 15, issueStandard.getQuestionsNumber());
        Assert.assertNull(issueStandard.getIssueStandardTopicPriorities());
        Assert.assertNull(issueStandard.getIssueStandardQuestionTypeOptions());
        Assert.assertNull(issueStandard.getSubject());
    }

    @Test
    public void insertTest() throws Exception {
        SubjectInterface subject = Mockito.mock(SubjectInterface.class);
        Mockito.when(subject.getId()).thenReturn(2);

        IssueStandardInterface issueStandard = new IssueStandard();
        issueStandard.setTimeLimit(1200).setQuestionsNumber(30).setSubject(subject);
        this.issueStandardMapper.insert(issueStandard);

        issueStandard = this.issueStandardMapper.findBySubject(subject);
        Assert.assertEquals((Integer) 1200, issueStandard.getTimeLimit());
        Assert.assertEquals((Integer) 30, issueStandard.getQuestionsNumber());
        Assert.assertNull(issueStandard.getIssueStandardTopicPriorities());
        Assert.assertNull(issueStandard.getIssueStandardQuestionTypeOptions());
        Assert.assertNull(issueStandard.getSubject());
    }

    @Test
    public void updateTest() throws Exception {
        IssueStandardInterface issueStandard = this.issueStandardMapper.find(2);
        Assert.assertNotNull(issueStandard);

        issueStandard.setTimeLimit(6000).setQuestionsNumber(50);
        this.issueStandardMapper.update(issueStandard);

        issueStandard = this.issueStandardMapper.find(2);
        Assert.assertEquals((Integer) 6000, issueStandard.getTimeLimit());
        Assert.assertEquals((Integer) 50, issueStandard.getQuestionsNumber());
    }

    @Test
    public void deleteTest() throws Exception {
        IssueStandardInterface issueStandard = this.issueStandardMapper.find(1);

        Assert.assertNotNull(issueStandard);

        this.issueStandardMapper.delete(issueStandard);
        issueStandard = this.issueStandardMapper.find(1);
        Assert.assertNull(issueStandard);
    }
}
