package easytests.mappers;

import easytests.config.DatabaseConfig;
import easytests.entities.Issue;
import easytests.entities.IssueInterface;
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

import java.util.List;

/**
 * Created by fortyways
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
        final IssueInterface issue = this.issuesMapper.find(1);

        Assert.assertEquals((long) 1, (long) issue.getId());
        Assert.assertEquals("Name1", issue.getName());
        Assert.assertEquals((long)11, (long)issue.getAuthorId());

    }

    @Test
    public void testFindAll() throws Exception {
        final List<Issue> issues=this.issuesMapper.findAll();

        Assert.assertEquals("Name1",issues.get(0).getName());
        Assert.assertEquals("Name2",issues.get(1).getName());
        Assert.assertEquals("Name3",issues.get(2).getName());
    }

    @Test
    public void testDelete() throws Exception {
        Issue issue = this.issuesMapper.find(1);
        Assert.assertNotNull(issue);
        this.issuesMapper.delete(issue);
        issue = this.issuesMapper.find(1);
        Assert.assertNull(issue);
    }
    @Test
    public void testInsert() throws Exception {
        Issue testissue=new Issue();
        testissue.setName("Name4").setAuthorId(14);
        this.issuesMapper.insert(testissue);
        Issue issue=this.issuesMapper.find(4);
        Assert.assertEquals((long) 4, (long) issue.getId());
        Assert.assertEquals("Name4", issue.getName());
        Assert.assertEquals((long)14, (long)issue.getAuthorId());
    }
    @Test
    public void testUpdate() throws Exception {
        Issue testissue=new Issue();
        testissue.setId(2).setName("Name4").setAuthorId(14);
        this.issuesMapper.update(testissue);
        Issue issue=this.issuesMapper.find(2);
        Assert.assertEquals((long) 2, (long) issue.getId());
        Assert.assertEquals("Name4", issue.getName());
        Assert.assertEquals((long)14, (long)issue.getAuthorId());
    }

}
