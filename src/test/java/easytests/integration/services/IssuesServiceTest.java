package easytests.integration.services;

import easytests.models.IssueModel;
import easytests.models.IssueModelInterface;
import easytests.models.empty.ModelsListEmpty;
import easytests.models.empty.SubjectModelEmpty;
import easytests.services.IssuesService;
import easytests.support.Models;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author fortyways
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:database.test.properties"})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/mappersTestData.sql")
public class IssuesServiceTest {
    @Autowired
    private IssuesService issuesService;

    @Test
    public void testSaveModel() throws Exception {

        final IssueModelInterface issueModel = new IssueModel();
        issueModel.setName("test111");
        issueModel.setQuizzes(new ModelsListEmpty());
        issueModel.setSubject(new SubjectModelEmpty(1));
        this.issuesService.save(issueModel);

        final IssueModelInterface foundIssueModel = this.issuesService.find(issueModel.getId());

        Assert.assertEquals(issueModel, foundIssueModel);

    }

    @Test
    public void testFindPresentModel() throws Exception {
        final Integer id = 1;
        final IssueModelInterface issueModel = Models.createIssueModel(id, "Name1", 1);

        final IssueModelInterface foundIssueModel = this.issuesService.find(id);

        Assert.assertEquals(issueModel.getId(), foundIssueModel.getId());
        Assert.assertEquals(issueModel.getName(), foundIssueModel.getName());
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        final IssueModelInterface issueModel = this.issuesService.find(id);

        Assert.assertEquals(null, issueModel);
    }
}
