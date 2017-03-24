package easytests.options;

import easytests.models.IssueStandardModelInterface;
import easytests.models.IssueStandardQuestionTypeOptionModel;
import easytests.models.IssueStandardQuestionTypeOptionModelInterface;
import easytests.models.empty.IssueStandardModelEmpty;
import easytests.services.IssueStandardQuestionTypeOptionsServiceInterface;
import easytests.services.IssueStandardsServiceInterface;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author SingularityA
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IssueStandardQuestionTypeOptionsOptionsTest {

    @Test
    public void testWithRelationsOnSingleModel() throws Exception {

        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions
                = new IssueStandardQuestionTypeOptionsOptions();
        final IssueStandardsServiceInterface issueStandardsService = Mockito.mock(IssueStandardsServiceInterface.class);
        final IssueStandardsOptionsInterface issueStandardsOptions = Mockito.mock(IssueStandardsOptionsInterface.class);

        final Integer issueStandardId = 10;
        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel
                = new IssueStandardQuestionTypeOptionModel();
        questionTypeOptionModel.setIssueStandard(new IssueStandardModelEmpty(issueStandardId));

        final IssueStandardModelInterface issueStandardModel = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModel.getId()).thenReturn(issueStandardId);
        given(issueStandardsService.find(issueStandardId, issueStandardsOptions)).willReturn(issueStandardModel);

        // options не заданы
        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModelWithoutRelations
                = questionTypeOptionsOptions.withRelations(questionTypeOptionModel);

        verify(issueStandardsService, times(0)).find(issueStandardId, issueStandardsOptions);
        Assert.assertEquals(questionTypeOptionModel, questionTypeOptionModelWithoutRelations);
        Assert.assertNotEquals(issueStandardModel, questionTypeOptionModelWithoutRelations.getIssueStandard());

        questionTypeOptionsOptions.setIssueStandardsService(issueStandardsService);
        questionTypeOptionsOptions.withIssueStandard(issueStandardsOptions);

        // теперь options заданы
        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModelWithRelations
                = questionTypeOptionsOptions.withRelations(questionTypeOptionModel);

        verify(issueStandardsService, times(1)).find(issueStandardId, issueStandardsOptions);
        Assert.assertEquals(questionTypeOptionModel, questionTypeOptionModelWithoutRelations);
        Assert.assertEquals(issueStandardModel, questionTypeOptionModelWithoutRelations.getIssueStandard());
    }

    @Test
    public void testWithRelationsOnModelsList() throws Exception {

        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions
                = new IssueStandardQuestionTypeOptionsOptions();
        final IssueStandardsServiceInterface issueStandardsService = Mockito.mock(IssueStandardsServiceInterface.class);
        final IssueStandardsOptionsInterface issueStandardsOptions = Mockito.mock(IssueStandardsOptionsInterface.class);

        final Integer issueStandardIdFirst = 10;
        final Integer issueStandardIdSecond = 20;
        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels = new ArrayList<>(2);

        IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModelFirst
                = new IssueStandardQuestionTypeOptionModel();
        IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModelSecond
                = new IssueStandardQuestionTypeOptionModel();

        questionTypeOptionModelFirst.setIssueStandard(new IssueStandardModelEmpty(issueStandardIdFirst));
        questionTypeOptionModelSecond.setIssueStandard(new IssueStandardModelEmpty(issueStandardIdSecond));
        questionTypeOptionModels.add(questionTypeOptionModelFirst);
        questionTypeOptionModels.add(questionTypeOptionModelSecond);

        final IssueStandardModelInterface issueStandardModelFirst = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModelFirst.getId()).thenReturn(issueStandardIdFirst);
        given(issueStandardsService.find(issueStandardIdFirst, issueStandardsOptions))
                .willReturn(issueStandardModelFirst);

        final IssueStandardModelInterface issueStandardModelSecond = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModelSecond.getId()).thenReturn(issueStandardIdSecond);
        given(issueStandardsService.find(issueStandardIdSecond, issueStandardsOptions))
                .willReturn(issueStandardModelSecond);

        // options не заданы
        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModelsWithoutRelations
                = questionTypeOptionsOptions.withRelations(questionTypeOptionModels);

        verify(issueStandardsService, times(0)).find(issueStandardIdFirst, issueStandardsOptions);
        verify(issueStandardsService, times(0)).find(issueStandardIdSecond, issueStandardsOptions);
        Assert.assertEquals(questionTypeOptionModels, questionTypeOptionModelsWithoutRelations);
        Assert.assertNotEquals(issueStandardModelFirst, questionTypeOptionModelsWithoutRelations.get(0).getIssueStandard());
        Assert.assertNotEquals(issueStandardModelSecond, questionTypeOptionModelsWithoutRelations.get(1).getIssueStandard());
        Assert.assertNotEquals(issueStandardModelFirst, questionTypeOptionModelsWithoutRelations.get(0).getIssueStandard());
        Assert.assertNotEquals(issueStandardModelSecond, questionTypeOptionModelsWithoutRelations.get(1).getIssueStandard());

        questionTypeOptionsOptions.setIssueStandardsService(issueStandardsService);
        questionTypeOptionsOptions.withIssueStandard(issueStandardsOptions);

        // теперь options заданы
        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModelsWithRelations
                = questionTypeOptionsOptions.withRelations(questionTypeOptionModels);

        verify(issueStandardsService, times(1)).find(issueStandardIdFirst, issueStandardsOptions);
        verify(issueStandardsService, times(1)).find(issueStandardIdSecond, issueStandardsOptions);
        Assert.assertEquals(questionTypeOptionModels, questionTypeOptionModelsWithoutRelations);
        Assert.assertEquals(issueStandardModelFirst, questionTypeOptionModelsWithoutRelations.get(0).getIssueStandard());
        Assert.assertEquals(issueStandardModelSecond, questionTypeOptionModelsWithoutRelations.get(1).getIssueStandard());
        Assert.assertEquals(issueStandardModelFirst, questionTypeOptionModelsWithoutRelations.get(0).getIssueStandard());
        Assert.assertEquals(issueStandardModelSecond, questionTypeOptionModelsWithoutRelations.get(1).getIssueStandard());
    }

    @Test
    public void testSaveWithRelations() throws Exception {
        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions
                = new IssueStandardQuestionTypeOptionsOptions();
        final IssueStandardQuestionTypeOptionsServiceInterface questionTypeOptionsService
                = Mockito.mock(IssueStandardQuestionTypeOptionsServiceInterface.class);
        final IssueStandardsServiceInterface issueStandardsService = Mockito.mock(IssueStandardsServiceInterface.class);
        final IssueStandardsOptionsInterface issueStandardsOptions = Mockito.mock(IssueStandardsOptionsInterface.class);

        final Integer issueStandardId = 10;
        final IssueStandardModelInterface issueStandardModel
                = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModel.getId()).thenReturn(issueStandardId);

        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel
                = new IssueStandardQuestionTypeOptionModel();
        questionTypeOptionModel.setIssueStandard(issueStandardModel);

        questionTypeOptionsOptions.setQuestionTypeOptionsService(questionTypeOptionsService);
        questionTypeOptionsOptions.setIssueStandardsService(issueStandardsService);
        questionTypeOptionsOptions.withIssueStandard(issueStandardsOptions);

        final InOrder inOrder = Mockito.inOrder(issueStandardsService, questionTypeOptionsService);

        questionTypeOptionsOptions.saveWithRelations(questionTypeOptionModel);

        inOrder.verify(issueStandardsService).save(questionTypeOptionModel.getIssueStandard(), issueStandardsOptions);
        inOrder.verify(questionTypeOptionsService).save(questionTypeOptionModel);
    }

    @Test
    public void testDeleteWithRelations() throws Exception {
        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions
                = new IssueStandardQuestionTypeOptionsOptions();
        final IssueStandardQuestionTypeOptionsServiceInterface questionTypeOptionsService
                = Mockito.mock(IssueStandardQuestionTypeOptionsServiceInterface.class);
        final IssueStandardsServiceInterface issueStandardsService = Mockito.mock(IssueStandardsServiceInterface.class);
        final IssueStandardsOptionsInterface issueStandardsOptions = Mockito.mock(IssueStandardsOptionsInterface.class);

        final Integer issueStandardId = 10;
        final IssueStandardModelInterface issueStandardModel
                = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModel.getId()).thenReturn(issueStandardId);

        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel
                = new IssueStandardQuestionTypeOptionModel();
        questionTypeOptionModel.setIssueStandard(issueStandardModel);

        questionTypeOptionsOptions.setQuestionTypeOptionsService(questionTypeOptionsService);
        questionTypeOptionsOptions.setIssueStandardsService(issueStandardsService);
        questionTypeOptionsOptions.withIssueStandard(issueStandardsOptions);

        final InOrder inOrder = Mockito.inOrder(issueStandardsService, questionTypeOptionsService);

        questionTypeOptionsOptions.deleteWithRelations(questionTypeOptionModel);

        inOrder.verify(questionTypeOptionsService).delete(questionTypeOptionModel);
        inOrder.verify(issueStandardsService).delete(questionTypeOptionModel.getIssueStandard(), issueStandardsOptions);
    }
}
