package easytests.mappers;

import easytests.config.DatabaseConfig;
import easytests.entities.IssueStandardEntity;
import java.util.List;
import org.junit.Assert;
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

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author SingularityA
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:database.test.properties"})
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {DatabaseConfig.class})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/mappersTestData.sql")
public class IssueStandardsMapperTest {

    @Autowired
    private IssueStandardsMapper issueStandardsMapper;

    @Test
    public void testFindAll() throws Exception {
        List<IssueStandardEntity> issueStandardEntities = this.issueStandardsMapper.findAll();

        Assert.assertNotNull(issueStandardEntities);
        Assert.assertEquals(2, issueStandardEntities.size());
    }

    @Test
    public void testFind() throws Exception {
        final IssueStandardEntity issueStandardEntity = this.issueStandardsMapper.find(1);

        Assert.assertEquals((Integer) 1, issueStandardEntity.getId());
        Assert.assertEquals((Integer) 300, issueStandardEntity.getTimeLimit());
        Assert.assertEquals((Integer) 30, issueStandardEntity.getQuestionsNumber());
        Assert.assertEquals((Integer) 1, issueStandardEntity.getSubjectId());
    }

    @Test
    public void testFindBySubjectId() throws Exception {
        final IssueStandardEntity issueStandardEntity = this.issueStandardsMapper.findBySubjectId(3);

        Assert.assertEquals((Integer) 2, issueStandardEntity.getId());
        Assert.assertEquals(null, issueStandardEntity.getTimeLimit());
        Assert.assertEquals((Integer) 15, issueStandardEntity.getQuestionsNumber());
        Assert.assertEquals((Integer) 3, issueStandardEntity.getSubjectId());
    }

    @Test
    public void testInsert() throws Exception {
        final Integer id = this.issueStandardsMapper.findAll().size() + 1;
        final Integer timeLimit = 3600;
        final Integer questionsNumber = 20;
        final Integer subjectId = 2;

        IssueStandardEntity issueStandardEntity = Mockito.mock(IssueStandardEntity.class);
        Mockito.when(issueStandardEntity.getTimeLimit()).thenReturn(timeLimit);
        Mockito.when(issueStandardEntity.getQuestionsNumber()).thenReturn(questionsNumber);
        Mockito.when(issueStandardEntity.getSubjectId()).thenReturn(subjectId);

        this.issueStandardsMapper.insert(issueStandardEntity);

        verify(issueStandardEntity, times(1)).setId(id);

        issueStandardEntity = this.issueStandardsMapper.find(id);
        Assert.assertEquals(id, issueStandardEntity.getId());
        Assert.assertEquals(timeLimit, issueStandardEntity.getTimeLimit());
        Assert.assertEquals(questionsNumber, issueStandardEntity.getQuestionsNumber());
        Assert.assertEquals(subjectId, issueStandardEntity.getSubjectId());
    }

    @Test
    public void testUpdate() throws Exception {
        final Integer id = 1;
        final Integer timeLimit = 3600;
        final Integer questionsNumber = 20;
        final Integer subjectId = 2;

        IssueStandardEntity issueStandardEntity = this.issueStandardsMapper.find(id);
        Assert.assertNotNull(issueStandardEntity);
        Assert.assertEquals(id, issueStandardEntity.getId());
        Assert.assertNotEquals(timeLimit, issueStandardEntity.getTimeLimit());
        Assert.assertNotEquals(questionsNumber, issueStandardEntity.getQuestionsNumber());
        Assert.assertNotEquals(subjectId, issueStandardEntity.getSubjectId());

        issueStandardEntity = Mockito.mock(IssueStandardEntity.class);
        Mockito.when(issueStandardEntity.getId()).thenReturn(id);
        Mockito.when(issueStandardEntity.getTimeLimit()).thenReturn(timeLimit);
        Mockito.when(issueStandardEntity.getQuestionsNumber()).thenReturn(questionsNumber);
        Mockito.when(issueStandardEntity.getSubjectId()).thenReturn(subjectId);

        this.issueStandardsMapper.update(issueStandardEntity);

        issueStandardEntity = this.issueStandardsMapper.find(id);
        Assert.assertEquals(id, issueStandardEntity.getId());
        Assert.assertEquals(timeLimit, issueStandardEntity.getTimeLimit());
        Assert.assertEquals(questionsNumber, issueStandardEntity.getQuestionsNumber());
        Assert.assertEquals(subjectId, issueStandardEntity.getSubjectId());
    }

    @Test
    public void deleteTest() throws Exception {
        IssueStandardEntity issueStandardEntity = this.issueStandardsMapper.find(1);
        Assert.assertNotNull(issueStandardEntity);

        this.issueStandardsMapper.delete(issueStandardEntity);
        issueStandardEntity = this.issueStandardsMapper.find(1);
        Assert.assertNull(issueStandardEntity);
    }
}
