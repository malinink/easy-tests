package easytests.options;

import easytests.models.IssueStandardModelInterface;
import easytests.models.IssueStandardQuestionTypeOptionModelInterface;
import easytests.models.QuestionTypeModelInterface;
import easytests.models.empty.IssueStandardModelEmpty;
import easytests.models.empty.QuestionTypeModelEmpty;
import easytests.services.IssueStandardQuestionTypeOptionsServiceInterface;
import easytests.services.IssueStandardsServiceInterface;
import easytests.services.QuestionTypesServiceInterface;
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
    public void testWithRelationsOnNull() throws Exception {

        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions
                = new IssueStandardQuestionTypeOptionsOptions();

        final QuestionTypesServiceInterface questionTypesService = Mockito.mock(QuestionTypesServiceInterface.class);
        final QuestionTypesOptionsInterface questionTypesOptions = Mockito.mock(QuestionTypesOptionsInterface.class);
        final IssueStandardsServiceInterface issueStandardsService = Mockito.mock(IssueStandardsServiceInterface.class);
        final IssueStandardsOptionsInterface issueStandardsOptions = Mockito.mock(IssueStandardsOptionsInterface.class);

        questionTypeOptionsOptions.setQuestionTypesService(questionTypesService);
        questionTypeOptionsOptions.setIssueStandardsService(issueStandardsService);
        questionTypeOptionsOptions
                .withQuestionType(questionTypesOptions)
                .withIssueStandard(issueStandardsOptions);

        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel = null;

        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModelWithRelations
                = questionTypeOptionsOptions.withRelations(questionTypeOptionModel);

        Assert.assertEquals(null, questionTypeOptionModelWithRelations);
    }

    @Test
    public void testWithRelationsOnSingleModel() throws Exception {

        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions
                = new IssueStandardQuestionTypeOptionsOptions();

        final QuestionTypesServiceInterface questionTypesService = Mockito.mock(QuestionTypesServiceInterface.class);
        final QuestionTypesOptionsInterface questionTypesOptions = Mockito.mock(QuestionTypesOptionsInterface.class);
        final IssueStandardsServiceInterface issueStandardsService = Mockito.mock(IssueStandardsServiceInterface.class);
        final IssueStandardsOptionsInterface issueStandardsOptions = Mockito.mock(IssueStandardsOptionsInterface.class);

        final Integer questionTypeId = 3;
        final Integer issueStandardId = 10;

        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel
                = Mockito.mock(IssueStandardQuestionTypeOptionModelInterface.class);
        Mockito.when(questionTypeOptionModel.getQuestionType())
                .thenReturn(new QuestionTypeModelEmpty(questionTypeId));
        Mockito.when(questionTypeOptionModel.getIssueStandard())
                .thenReturn(new IssueStandardModelEmpty(issueStandardId));

        final QuestionTypeModelInterface questionTypeModel = Mockito.mock(QuestionTypeModelInterface.class);
        Mockito.when(questionTypeModel.getId()).thenReturn(questionTypeId);
        given(questionTypesService.find(questionTypeId, questionTypesOptions)).willReturn(questionTypeModel);

        final IssueStandardModelInterface issueStandardModel = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModel.getId()).thenReturn(issueStandardId);
        given(issueStandardsService.find(issueStandardId, issueStandardsOptions)).willReturn(issueStandardModel);

        // options не заданы
        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModelWithoutRelations
                = questionTypeOptionsOptions.withRelations(questionTypeOptionModel);

        verify(questionTypesService, times(0)).find(questionTypeId, questionTypesOptions);
        verify(issueStandardsService, times(0)).find(issueStandardId, issueStandardsOptions);

        Assert.assertEquals(questionTypeOptionModel, questionTypeOptionModelWithoutRelations);
        Mockito.verify(questionTypeOptionModel, times(0)).setQuestionType(questionTypeModel);
        Mockito.verify(questionTypeOptionModel, times(0)).setIssueStandard(issueStandardModel);

        questionTypeOptionsOptions.setQuestionTypesService(questionTypesService);
        questionTypeOptionsOptions.setIssueStandardsService(issueStandardsService);
        questionTypeOptionsOptions
                .withQuestionType(questionTypesOptions)
                .withIssueStandard(issueStandardsOptions);

        // теперь options заданы
        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModelWithRelations
                = questionTypeOptionsOptions.withRelations(questionTypeOptionModel);

        verify(questionTypesService, times(1)).find(questionTypeId, questionTypesOptions);
        verify(issueStandardsService, times(1)).find(issueStandardId, issueStandardsOptions);

        Assert.assertEquals(questionTypeOptionModel, questionTypeOptionModelWithRelations);
        Mockito.verify(questionTypeOptionModel, times(1)).setQuestionType(questionTypeModel);
        Mockito.verify(questionTypeOptionModel, times(1)).setIssueStandard(issueStandardModel);
    }

    @Test
    public void testWithRelationsOnModelsList() throws Exception {

        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions
                = new IssueStandardQuestionTypeOptionsOptions();

        final QuestionTypesServiceInterface questionTypesService = Mockito.mock(QuestionTypesServiceInterface.class);
        final QuestionTypesOptionsInterface questionTypesOptions = Mockito.mock(QuestionTypesOptionsInterface.class);
        final IssueStandardsServiceInterface issueStandardsService = Mockito.mock(IssueStandardsServiceInterface.class);
        final IssueStandardsOptionsInterface issueStandardsOptions = Mockito.mock(IssueStandardsOptionsInterface.class);

        final Integer issueStandardIdFirst = 10;
        final Integer issueStandardIdSecond = 20;
        final Integer questionTypeIdFirst = 2;
        final Integer questionTypeIdSecond = 3;

        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModelFirst
                = Mockito.mock(IssueStandardQuestionTypeOptionModelInterface.class);
        Mockito.when(questionTypeOptionModelFirst.getQuestionType())
                .thenReturn(new QuestionTypeModelEmpty(questionTypeIdFirst));
        Mockito.when(questionTypeOptionModelFirst.getIssueStandard())
                .thenReturn(new IssueStandardModelEmpty(issueStandardIdFirst));

        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModelSecond
                = Mockito.mock(IssueStandardQuestionTypeOptionModelInterface.class);
        Mockito.when(questionTypeOptionModelSecond.getQuestionType())
                .thenReturn(new QuestionTypeModelEmpty(questionTypeIdSecond));
        Mockito.when(questionTypeOptionModelSecond.getIssueStandard())
                .thenReturn(new IssueStandardModelEmpty(issueStandardIdSecond));

        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels = new ArrayList<>(2);
        questionTypeOptionModels.add(questionTypeOptionModelFirst);
        questionTypeOptionModels.add(questionTypeOptionModelSecond);

        final QuestionTypeModelInterface questionTypeModelFirst = Mockito.mock(QuestionTypeModelInterface.class);
        Mockito.when(questionTypeModelFirst.getId()).thenReturn(questionTypeIdFirst);
        given(questionTypesService.find(questionTypeIdFirst, questionTypesOptions))
                .willReturn(questionTypeModelFirst);

        final QuestionTypeModelInterface questionTypeModelSecond = Mockito.mock(QuestionTypeModelInterface.class);
        Mockito.when(questionTypeModelSecond.getId()).thenReturn(questionTypeIdSecond);
        given(questionTypesService.find(questionTypeIdSecond, questionTypesOptions))
                .willReturn(questionTypeModelSecond);

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

        verify(questionTypesService, times(0)).find(questionTypeIdFirst, questionTypesOptions);
        verify(questionTypesService, times(0)).find(questionTypeIdSecond, questionTypesOptions);
        verify(issueStandardsService, times(0)).find(issueStandardIdFirst, issueStandardsOptions);
        verify(issueStandardsService, times(0)).find(issueStandardIdSecond, issueStandardsOptions);

        Assert.assertEquals(questionTypeOptionModels, questionTypeOptionModelsWithoutRelations);
        Mockito.verify(questionTypeOptionModelFirst, times(0)).setQuestionType(questionTypeModelFirst);
        Mockito.verify(questionTypeOptionModelFirst, times(0)).setIssueStandard(issueStandardModelFirst);
        Mockito.verify(questionTypeOptionModelSecond, times(0)).setQuestionType(questionTypeModelSecond);
        Mockito.verify(questionTypeOptionModelSecond, times(0)).setIssueStandard(issueStandardModelSecond);

        questionTypeOptionsOptions.setQuestionTypesService(questionTypesService);
        questionTypeOptionsOptions.setIssueStandardsService(issueStandardsService);
        questionTypeOptionsOptions
                .withQuestionType(questionTypesOptions)
                .withIssueStandard(issueStandardsOptions);

        // теперь options заданы
        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModelsWithRelations
                = questionTypeOptionsOptions.withRelations(questionTypeOptionModels);

        verify(questionTypesService, times(1)).find(questionTypeIdFirst, questionTypesOptions);
        verify(questionTypesService, times(1)).find(questionTypeIdSecond, questionTypesOptions);
        verify(issueStandardsService, times(1)).find(issueStandardIdFirst, issueStandardsOptions);
        verify(issueStandardsService, times(1)).find(issueStandardIdSecond, issueStandardsOptions);

        Assert.assertEquals(questionTypeOptionModels, questionTypeOptionModelsWithRelations);
        Mockito.verify(questionTypeOptionModelFirst, times(1)).setQuestionType(questionTypeModelFirst);
        Mockito.verify(questionTypeOptionModelFirst, times(1)).setIssueStandard(issueStandardModelFirst);
        Mockito.verify(questionTypeOptionModelSecond, times(1)).setQuestionType(questionTypeModelSecond);
        Mockito.verify(questionTypeOptionModelSecond, times(1)).setIssueStandard(issueStandardModelSecond);
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
                = Mockito.mock(IssueStandardQuestionTypeOptionModelInterface.class);
        Mockito.when(questionTypeOptionModel.getIssueStandard()).thenReturn(issueStandardModel);

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
                = Mockito.mock(IssueStandardQuestionTypeOptionModelInterface.class);
        Mockito.when(questionTypeOptionModel.getIssueStandard()).thenReturn(issueStandardModel);

        questionTypeOptionsOptions.setQuestionTypeOptionsService(questionTypeOptionsService);
        questionTypeOptionsOptions.setIssueStandardsService(issueStandardsService);
        questionTypeOptionsOptions.withIssueStandard(issueStandardsOptions);

        final InOrder inOrder = Mockito.inOrder(issueStandardsService, questionTypeOptionsService);

        questionTypeOptionsOptions.deleteWithRelations(questionTypeOptionModel);

        inOrder.verify(questionTypeOptionsService).delete(questionTypeOptionModel);
        inOrder.verify(issueStandardsService).delete(questionTypeOptionModel.getIssueStandard(), issueStandardsOptions);
    }
}
