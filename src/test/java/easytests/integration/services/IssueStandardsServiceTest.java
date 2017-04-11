package easytests.integration.services;

import easytests.models.IssueStandardModelInterface;
import easytests.models.SubjectModelInterface;
import easytests.options.IssueStandardsOptions;
import easytests.options.SubjectsOptions;
import easytests.services.IssueStandardsService;
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
 * @author SingularityA
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:database.test.properties"})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/mappersTestData.sql")
public class IssueStandardsServiceTest {

    @Autowired
    private IssueStandardsService issueStandardsService;

    @Test
    public void testFindPresentModel() throws Exception {
        final Integer id = 1;
        final IssueStandardModelInterface issueStandardModel = Models.createIssueStandardModel(id, 300, 30, 1);

        final IssueStandardModelInterface foundedIssueStandardModel = this.issueStandardsService.find(id);
        Assert.assertEquals(issueStandardModel, foundedIssueStandardModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        final IssueStandardModelInterface foundedIssueStandardModel = this.issueStandardsService.find(id);

        Assert.assertNull(foundedIssueStandardModel);
    }

    @Test
    public void testFindWithOptions() throws Exception {
        final Integer id = 1;
        final Integer subjectId = 1;

        final IssueStandardModelInterface issueStandardModel = Models.createIssueStandardModel(id, 300, 30, subjectId);
        final SubjectModelInterface subjectModel = Models.createSubjectModel(subjectId, "test1", "testdescription1", 2);
        issueStandardModel.setSubject(subjectModel);

        final IssueStandardModelInterface foundedIssueStandardModel
                = this.issueStandardsService.find(id, new IssueStandardsOptions().withSubject(new SubjectsOptions()));

        Assert.assertEquals(issueStandardModel, foundedIssueStandardModel);
        Assert.assertEquals(subjectModel, foundedIssueStandardModel.getSubject());
    }

    @Test
    public void testSaveInsertsModel() throws Exception {
        final Integer id = this.issueStandardsService.findAll().size() + 1;
        final IssueStandardModelInterface issueStandardModel = Models.createIssueStandardModel(null, 200, 20, 2);

        this.issueStandardsService.save(issueStandardModel);
        IssueStandardModelInterface foundedIssueStandardModel = this.issueStandardsService.find(id);

        Assert.assertEquals(issueStandardModel, foundedIssueStandardModel);
    }

    @Test
    public void testSaveUpdatesModel() throws Exception {
        final Integer id = 1;
        final IssueStandardModelInterface issueStandardModel = Models.createIssueStandardModel(id, 200, 10, 2);
        Assert.assertNotEquals(issueStandardModel, this.issueStandardsService.find(id));

        this.issueStandardsService.save(issueStandardModel);

        Assert.assertEquals(issueStandardModel, this.issueStandardsService.find(id));
    }
}
