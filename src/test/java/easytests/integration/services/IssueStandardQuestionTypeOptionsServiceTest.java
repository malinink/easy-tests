package easytests.integration.services;

import easytests.models.IssueStandardModelInterface;
import easytests.models.IssueStandardQuestionTypeOptionModelInterface;
import easytests.options.IssueStandardQuestionTypeOptionsOptions;
import easytests.options.IssueStandardsOptions;
import easytests.services.IssueStandardQuestionTypeOptionsService;
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
public class IssueStandardQuestionTypeOptionsServiceTest {

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
        final Integer id = this.questionTypeOptionsService.findAll().size() + 1;
        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel
                = Models.createQuestionTypeOptionModel(null, 3, 10, 20, 300, 4);

        this.questionTypeOptionsService.save(questionTypeOptionModel);
        final IssueStandardQuestionTypeOptionModelInterface foundedQuestionTypeOptionModel
                = this.questionTypeOptionsService.find(id);

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
