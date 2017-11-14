package easytests.integration.services;

import easytests.core.models.IssueStandardModelInterface;
import easytests.core.models.SubjectModelInterface;
import easytests.core.options.IssueStandardsOptions;
import easytests.core.options.SubjectsOptions;
import easytests.core.services.IssueStandardsService;
import easytests.support.Models;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author SingularityA
 */
public class IssueStandardsServiceTest extends AbstractServiceTest {

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
        final SubjectModelInterface subjectModel = Models.createSubjectModel(subjectId, "Subject1", "Subject Description 1", 2);
        issueStandardModel.setSubject(subjectModel);

        final IssueStandardModelInterface foundedIssueStandardModel
                = this.issueStandardsService.find(id, new IssueStandardsOptions().withSubject(new SubjectsOptions()));

        Assert.assertEquals(issueStandardModel, foundedIssueStandardModel);
        Assert.assertEquals(subjectModel, foundedIssueStandardModel.getSubject());
    }

    @Test
    public void testSaveInsertsModel() throws Exception {
        final IssueStandardModelInterface issueStandardModel = Models.createIssueStandardModel(null, 200, 20, 2);

        this.issueStandardsService.save(issueStandardModel);

        Assert.assertNotNull(issueStandardModel.getId());

        final IssueStandardModelInterface foundedIssueStandardModel = this.issueStandardsService.find(issueStandardModel.getId());

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
