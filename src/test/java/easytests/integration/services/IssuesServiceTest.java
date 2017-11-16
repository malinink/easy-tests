package easytests.integration.services;

import easytests.core.models.IssueModel;
import easytests.core.models.IssueModelInterface;
import easytests.core.models.empty.ModelsListEmpty;
import easytests.core.models.empty.SubjectModelEmpty;
import easytests.core.services.IssuesService;
import easytests.support.Models;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author fortyways
 */
public class IssuesServiceTest extends AbstractServiceTest {

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
