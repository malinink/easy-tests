package easytests.mappers;

import easytests.config.DatabaseConfig;
import easytests.entities.IssueEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

/**
 * @author fortyways
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:database.test.properties"})
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {DatabaseConfig.class})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/mappersTestData.sql")
public class IssuesMapperTest {

    @Autowired
    private IssuesMapper issuesMapper;

    @Test
    public void testFind() throws Exception {
        final IssueEntity issue = this.issuesMapper.find(1);
        Assert.assertEquals((long) 1, (long) issue.getId());
        Assert.assertEquals("Name1", issue.getName());
        Assert.assertEquals((long)11, (long)issue.getAuthorId());
    }

    @Test
    public void testFindAll() throws Exception {
        final List<IssueEntity> issues=this.issuesMapper.findAll();
        Assert.assertEquals("Name1",issues.get(0).getName());
        Assert.assertEquals("Name2",issues.get(1).getName());
        Assert.assertEquals("Name3",issues.get(2).getName());
    }

    @Test
    public void testDelete() throws Exception {
        IssueEntity issue = this.issuesMapper.find(1);
        Assert.assertNotNull(issue);

        this.issuesMapper.delete(issue);
        issue = this.issuesMapper.find(1);
        Assert.assertNull(issue);
    }

    @Test
    public void testInsert() throws Exception {
        final Integer id = this.issuesMapper.findAll().size() + 1;
        final String name = "NewName";
        final Integer authorId=14;

        IssueEntity issueEntity = Mockito.mock(IssueEntity.class);
        Mockito.when(issueEntity.getName()).thenReturn(name);
        Mockito.when(issueEntity.getAuthorId()).thenReturn(authorId);

        this.issuesMapper.insert(issueEntity);

        verify(issueEntity, times(1)) .setId(id);

        issueEntity = this.issuesMapper.find(id);
        Assert.assertEquals(id, issueEntity.getId());
        Assert.assertEquals(name, issueEntity.getName());
        Assert.assertEquals(authorId, issueEntity.getAuthorId());
    }

    @Test
    public void testUpdate() throws Exception {
        final Integer id = 1;
        final String name = "NewName";
        final Integer authorId=14;

        IssueEntity issueEntity = this.issuesMapper.find(id);
        Assert.assertNotNull(issueEntity);
        Assert.assertEquals(id, issueEntity.getId());
        Assert.assertNotEquals(name, issueEntity.getName());
        Assert.assertNotEquals(authorId, issueEntity.getAuthorId());

        issueEntity = Mockito.mock(IssueEntity.class);
        Mockito.when(issueEntity.getId()).thenReturn(id);
        Mockito.when(issueEntity.getName()).thenReturn(name);
        Mockito.when(issueEntity.getAuthorId()).thenReturn(authorId);

        this.issuesMapper.update(issueEntity);

        issueEntity = this.issuesMapper.find(id);
        Assert.assertEquals(id, issueEntity.getId());
        Assert.assertEquals(name, issueEntity.getName());
        Assert.assertEquals(authorId, issueEntity.getAuthorId());
    }

}