package easytests.integration.services;

import easytests.core.models.IssueStandardModelInterface;
import easytests.core.models.IssueStandardQuestionTypeOptionModelInterface;
import easytests.core.options.IssueStandardQuestionTypeOptionsOptions;
import easytests.core.options.IssueStandardsOptions;
import easytests.core.services.IssueStandardQuestionTypeOptionsService;
import easytests.support.Models;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author SingularityA
 */
public class IssueStandardQuestionTypeOptionsServiceTest extends AbstractServiceTest {

    @Autowired
    private IssueStandardQuestionTypeOptionsService questionTypeOptionsService;

    @Test
    public void testFindPresentModel() throws Exception {
        final Integer id = 1;
        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel
                = Models.createQuestionTypeOptionModel(id, 1, 1, null, null, 1);

        final IssueStandardQuestionTypeOptionModelInterface foundedQuestionTypeOptionModel
                = this.questionTypeOptionsService.find(id);
        Assert.assertEquals(questionTypeOptionModel, foundedQuestionTypeOptionModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        final IssueStandardQuestionTypeOptionModelInterface foundedQuestionTypeOptionModel
                = this.questionTypeOptionsService.find(id);

        Assert.assertNull(foundedQuestionTypeOptionModel);
    }

    @Test
    public void testFindWithOptions() throws Exception {
        final Integer id = 1;
        final Integer issueStandardId = 1;

        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel
                = Models.createQuestionTypeOptionModel(id, 1, 1, null, null, issueStandardId);
        final IssueStandardModelInterface issueStandardModel
                = Models.createIssueStandardModel(issueStandardId, 300, 30, 1);
        questionTypeOptionModel.setIssueStandard(issueStandardModel);

        final IssueStandardQuestionTypeOptionModelInterface foundedQuestionTypeOptionModel
                = this.questionTypeOptionsService
                .find(id, new IssueStandardQuestionTypeOptionsOptions().withIssueStandard(new IssueStandardsOptions()));

        Assert.assertEquals(questionTypeOptionModel, foundedQuestionTypeOptionModel);
        Assert.assertEquals(issueStandardModel, foundedQuestionTypeOptionModel.getIssueStandard());
    }

    @Test
    public void testSaveInsertsModel() throws Exception {
        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel
                = Models.createQuestionTypeOptionModel(null, 3, 10, 20, 300, 4);

        this.questionTypeOptionsService.save(questionTypeOptionModel);

        Assert.assertNotNull(questionTypeOptionModel.getId());

        final IssueStandardQuestionTypeOptionModelInterface foundedQuestionTypeOptionModel
                = this.questionTypeOptionsService.find(questionTypeOptionModel.getId());

        Assert.assertEquals(questionTypeOptionModel, foundedQuestionTypeOptionModel);
    }

    @Test
    public void testSaveUpdatesModel() throws Exception {
        final Integer id = 1;
        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel
                = Models.createQuestionTypeOptionModel(id, 1, 10, 20, 300, 1);
        Assert.assertNotEquals(questionTypeOptionModel, this.questionTypeOptionsService.find(id));

        this.questionTypeOptionsService.save(questionTypeOptionModel);

        Assert.assertEquals(questionTypeOptionModel, this.questionTypeOptionsService.find(id));
    }
}
