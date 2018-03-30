package easytests.core.options;

import easytests.core.models.IssueStandardModelInterface;
import easytests.core.models.IssueStandardQuestionTypeOptionModelInterface;
import easytests.core.models.IssueStandardTopicPriorityModelInterface;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.empty.ModelsListEmpty;
import easytests.core.models.empty.SubjectModelEmpty;
import easytests.core.services.IssueStandardQuestionTypeOptionsServiceInterface;
import easytests.core.services.IssueStandardsServiceInterface;
import easytests.core.services.IssueStandardTopicPrioritiesServiceInterface;
import easytests.core.services.SubjectsServiceInterface;
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
public class IssueStandardOptionsTest {

    @Test
    public void testWithRelationsOnNull() throws Exception {

        final IssueStandardsOptionsInterface issueStandardsOptions = new IssueStandardsOptions();

        final IssueStandardTopicPrioritiesServiceInterface topicPrioritiesService
                = Mockito.mock(IssueStandardTopicPrioritiesServiceInterface.class);
        final IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions
                = Mockito.mock(IssueStandardTopicPrioritiesOptionsInterface.class);

        final IssueStandardQuestionTypeOptionsServiceInterface questionTypeOptionsService
                = Mockito.mock(IssueStandardQuestionTypeOptionsServiceInterface.class);
        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions
                = Mockito.mock(IssueStandardQuestionTypeOptionsOptionsInterface.class);

        final SubjectsServiceInterface subjectsService = Mockito.mock(SubjectsServiceInterface.class);
        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);

        issueStandardsOptions.setTopicPrioritiesService(topicPrioritiesService);
        issueStandardsOptions.setQuestionTypeOptionsService(questionTypeOptionsService);
        issueStandardsOptions.setSubjectsService(subjectsService);
        issueStandardsOptions
                .withTopicPriorities(topicPrioritiesOptions)
                .withQuestionTypeOptions(questionTypeOptionsOptions)
                .withSubject(subjectsOptions);

        final IssueStandardModelInterface issueStandardModel = null;

        final IssueStandardModelInterface issueStandardModelWithRelations
                = issueStandardsOptions.withRelations(issueStandardModel);

        Assert.assertEquals(null, issueStandardModelWithRelations);
    }

    @Test
    public void testWithRelationsOnSingleModel() throws Exception {

        final IssueStandardsOptionsInterface issueStandardsOptions = new IssueStandardsOptions();

        final IssueStandardTopicPrioritiesServiceInterface topicPrioritiesService
                = Mockito.mock(IssueStandardTopicPrioritiesServiceInterface.class);
        final IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions
                = Mockito.mock(IssueStandardTopicPrioritiesOptionsInterface.class);

        final IssueStandardQuestionTypeOptionsServiceInterface questionTypeOptionsService
                = Mockito.mock(IssueStandardQuestionTypeOptionsServiceInterface.class);
        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions
                = Mockito.mock(IssueStandardQuestionTypeOptionsOptionsInterface.class);

        final SubjectsServiceInterface subjectsService = Mockito.mock(SubjectsServiceInterface.class);
        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);

        final Integer subjectId = 3;
        // модель в начале
        final IssueStandardModelInterface issueStandardModel = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModel.getTopicPriorities()).thenReturn(new ModelsListEmpty());
        Mockito.when(issueStandardModel.getQuestionTypeOptions()).thenReturn(new ModelsListEmpty());
        Mockito.when(issueStandardModel.getSubject()).thenReturn(new SubjectModelEmpty(subjectId));

        // возвращаемые сервисами
        final List<IssueStandardTopicPriorityModelInterface> topicPriorityModels = new ArrayList<>(1);
        topicPriorityModels.add(Mockito.mock(IssueStandardTopicPriorityModelInterface.class));

        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels = new ArrayList<>(1);
        questionTypeOptionModels.add(Mockito.mock(IssueStandardQuestionTypeOptionModelInterface.class));

        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);
        Mockito.when(subjectModel.getId()).thenReturn(subjectId);

        given(topicPrioritiesService.findByIssueStandard(issueStandardModel, topicPrioritiesOptions))
                .willReturn(topicPriorityModels);
        given(questionTypeOptionsService.findByIssueStandard(issueStandardModel, questionTypeOptionsOptions))
                .willReturn(questionTypeOptionModels);
        given(subjectsService.find(subjectId, subjectsOptions)).willReturn(subjectModel);

        // options не заданы
        final IssueStandardModelInterface issueStandardModelWithoutRelations
                = issueStandardsOptions.withRelations(issueStandardModel);

        verify(topicPrioritiesService, times(0)).findByIssueStandard(issueStandardModel, topicPrioritiesOptions);
        verify(questionTypeOptionsService, times(0)).findByIssueStandard(issueStandardModel, questionTypeOptionsOptions);
        verify(subjectsService, times(0)).find(subjectId, subjectsOptions);

        Assert.assertEquals(issueStandardModel, issueStandardModelWithoutRelations);
        verify(issueStandardModel, times(0)).setTopicPriorities(topicPriorityModels);
        verify(issueStandardModel, times(0)).setQuestionTypeOptions(questionTypeOptionModels);
        verify(issueStandardModel, times(0)).setSubject(subjectModel);

        issueStandardsOptions.setTopicPrioritiesService(topicPrioritiesService);
        issueStandardsOptions.setQuestionTypeOptionsService(questionTypeOptionsService);
        issueStandardsOptions.setSubjectsService(subjectsService);
        issueStandardsOptions
                .withTopicPriorities(topicPrioritiesOptions)
                .withQuestionTypeOptions(questionTypeOptionsOptions)
                .withSubject(subjectsOptions);

        // теперь options заданы
        final IssueStandardModelInterface issueStandardModelWithRelations
                = issueStandardsOptions.withRelations(issueStandardModel);

        verify(topicPrioritiesService, times(1)).findByIssueStandard(issueStandardModel, topicPrioritiesOptions);
        verify(questionTypeOptionsService, times(1)).findByIssueStandard(issueStandardModel, questionTypeOptionsOptions);
        verify(subjectsService, times(1)).find(subjectId, subjectsOptions);

        Assert.assertEquals(issueStandardModel, issueStandardModelWithRelations);
        verify(issueStandardModel, times(1)).setTopicPriorities(topicPriorityModels);
        verify(issueStandardModel, times(1)).setQuestionTypeOptions(questionTypeOptionModels);
        verify(issueStandardModel, times(1)).setSubject(subjectModel);
    }

    @Test
    public void testWithRelationsOnModelsList() throws Exception {

        final IssueStandardsOptionsInterface issueStandardsOptions = new IssueStandardsOptions();

        final IssueStandardTopicPrioritiesServiceInterface topicPrioritiesService
                = Mockito.mock(IssueStandardTopicPrioritiesServiceInterface.class);
        final IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions
                = Mockito.mock(IssueStandardTopicPrioritiesOptionsInterface.class);

        final IssueStandardQuestionTypeOptionsServiceInterface questionTypeOptionsService
                = Mockito.mock(IssueStandardQuestionTypeOptionsServiceInterface.class);
        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions
                = Mockito.mock(IssueStandardQuestionTypeOptionsOptionsInterface.class);

        final SubjectsServiceInterface subjectsService = Mockito.mock(SubjectsServiceInterface.class);
        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);

        final Integer subjectIdFirst = 3;
        final Integer subjectIdSecond = 5;

        // модели в начале
        final IssueStandardModelInterface issueStandardModelFirst = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModelFirst.getTopicPriorities()).thenReturn(new ModelsListEmpty());
        Mockito.when(issueStandardModelFirst.getQuestionTypeOptions()).thenReturn(new ModelsListEmpty());
        Mockito.when(issueStandardModelFirst.getSubject()).thenReturn(new SubjectModelEmpty(subjectIdFirst));

        final IssueStandardModelInterface issueStandardModelSecond = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModelSecond.getTopicPriorities()).thenReturn(new ModelsListEmpty());
        Mockito.when(issueStandardModelSecond.getQuestionTypeOptions()).thenReturn(new ModelsListEmpty());
        Mockito.when(issueStandardModelSecond.getSubject()).thenReturn(new SubjectModelEmpty(subjectIdSecond));

        final List<IssueStandardModelInterface> issueStandardModels = new ArrayList<>(2);
        issueStandardModels.add(issueStandardModelFirst);
        issueStandardModels.add(issueStandardModelSecond);

        final List<IssueStandardTopicPriorityModelInterface> topicPriorityModelsFirst = new ArrayList<>(1);
        topicPriorityModelsFirst.add(Mockito.mock(IssueStandardTopicPriorityModelInterface.class));

        final List<IssueStandardTopicPriorityModelInterface> topicPriorityModelsSecond = new ArrayList<>(1);
        topicPriorityModelsSecond.add(Mockito.mock(IssueStandardTopicPriorityModelInterface.class));

        given(topicPrioritiesService.findByIssueStandard(issueStandardModelFirst, topicPrioritiesOptions))
                .willReturn(topicPriorityModelsFirst);
        given(topicPrioritiesService.findByIssueStandard(issueStandardModelSecond, topicPrioritiesOptions))
                .willReturn(topicPriorityModelsSecond);

        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModelsFirst = new ArrayList<>(1);
        questionTypeOptionModelsFirst.add(Mockito.mock(IssueStandardQuestionTypeOptionModelInterface.class));

        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModelsSecond = new ArrayList<>(1);
        questionTypeOptionModelsSecond.add(Mockito.mock(IssueStandardQuestionTypeOptionModelInterface.class));

        given(questionTypeOptionsService.findByIssueStandard(issueStandardModelFirst, questionTypeOptionsOptions))
                .willReturn(questionTypeOptionModelsFirst);
        given(questionTypeOptionsService.findByIssueStandard(issueStandardModelSecond, questionTypeOptionsOptions))
                .willReturn(questionTypeOptionModelsSecond);

        final SubjectModelInterface subjectModelFirst = Mockito.mock(SubjectModelInterface.class);
        Mockito.when(subjectModelFirst.getId()).thenReturn(subjectIdFirst);

        final SubjectModelInterface subjectModelSecond = Mockito.mock(SubjectModelInterface.class);
        Mockito.when(subjectModelSecond.getId()).thenReturn(subjectIdSecond);

        given(subjectsService.find(subjectIdFirst, subjectsOptions)).willReturn(subjectModelFirst);
        given(subjectsService.find(subjectIdSecond, subjectsOptions)).willReturn(subjectModelSecond);

        // options не заданы
        List<IssueStandardModelInterface> issueStandardModelsWithoutRelations
                = issueStandardsOptions.withRelations(issueStandardModels);

        verify(topicPrioritiesService, times(0)).findByIssueStandard(issueStandardModelFirst, topicPrioritiesOptions);
        verify(topicPrioritiesService, times(0)).findByIssueStandard(issueStandardModelSecond, topicPrioritiesOptions);
        verify(questionTypeOptionsService, times(0)).findByIssueStandard(issueStandardModelFirst, questionTypeOptionsOptions);
        verify(questionTypeOptionsService, times(0)).findByIssueStandard(issueStandardModelSecond, questionTypeOptionsOptions);
        verify(subjectsService, times(0)).find(subjectIdFirst, subjectsOptions);
        verify(subjectsService, times(0)).find(subjectIdSecond, subjectsOptions);

        Assert.assertEquals(issueStandardModels, issueStandardModelsWithoutRelations);
        verify(issueStandardModelFirst, times(0)).setTopicPriorities(topicPriorityModelsFirst);
        verify(issueStandardModelFirst, times(0)).setQuestionTypeOptions(questionTypeOptionModelsFirst);
        verify(issueStandardModelFirst, times(0)).setSubject(subjectModelFirst);
        verify(issueStandardModelSecond, times(0)).setTopicPriorities(topicPriorityModelsSecond);
        verify(issueStandardModelSecond, times(0)).setQuestionTypeOptions(questionTypeOptionModelsSecond);
        verify(issueStandardModelSecond, times(0)).setSubject(subjectModelSecond);

        issueStandardsOptions.setTopicPrioritiesService(topicPrioritiesService);
        issueStandardsOptions.setQuestionTypeOptionsService(questionTypeOptionsService);
        issueStandardsOptions.setSubjectsService(subjectsService);
        issueStandardsOptions
                .withTopicPriorities(topicPrioritiesOptions)
                .withQuestionTypeOptions(questionTypeOptionsOptions)
                .withSubject(subjectsOptions);

        // теперь options заданы
        List<IssueStandardModelInterface> issueStandardModelsWithRelations
                = issueStandardsOptions.withRelations(issueStandardModels);

        verify(topicPrioritiesService, times(1)).findByIssueStandard(issueStandardModelFirst, topicPrioritiesOptions);
        verify(topicPrioritiesService, times(1)).findByIssueStandard(issueStandardModelSecond, topicPrioritiesOptions);
        verify(questionTypeOptionsService, times(1)).findByIssueStandard(issueStandardModelFirst, questionTypeOptionsOptions);
        verify(questionTypeOptionsService, times(1)).findByIssueStandard(issueStandardModelSecond, questionTypeOptionsOptions);
        verify(subjectsService, times(1)).find(subjectIdFirst, subjectsOptions);
        verify(subjectsService, times(1)).find(subjectIdSecond, subjectsOptions);

        Assert.assertEquals(issueStandardModels, issueStandardModelsWithRelations);
        verify(issueStandardModelFirst, times(1)).setTopicPriorities(topicPriorityModelsFirst);
        verify(issueStandardModelFirst, times(1)).setQuestionTypeOptions(questionTypeOptionModelsFirst);
        verify(issueStandardModelFirst, times(1)).setSubject(subjectModelFirst);
        verify(issueStandardModelSecond, times(1)).setTopicPriorities(topicPriorityModelsSecond);
        verify(issueStandardModelSecond, times(1)).setQuestionTypeOptions(questionTypeOptionModelsSecond);
        verify(issueStandardModelSecond, times(1)).setSubject(subjectModelSecond);
    }

    @Test
    public void testSaveWithRelations() throws Exception {

        final IssueStandardsOptionsInterface issueStandardsOptions = new IssueStandardsOptions();
        final IssueStandardsServiceInterface issueStandardsService = Mockito.mock(IssueStandardsServiceInterface.class);
        final IssueStandardTopicPrioritiesServiceInterface topicPrioritiesService
                = Mockito.mock(IssueStandardTopicPrioritiesServiceInterface.class);
        final IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions
                = Mockito.mock(IssueStandardTopicPrioritiesOptionsInterface.class);

        final IssueStandardQuestionTypeOptionsServiceInterface questionTypeOptionsService
                = Mockito.mock(IssueStandardQuestionTypeOptionsServiceInterface.class);
        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions
                = Mockito.mock(IssueStandardQuestionTypeOptionsOptionsInterface.class);

        final SubjectsServiceInterface subjectsService = Mockito.mock(SubjectsServiceInterface.class);
        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);

        final Integer subjectId = 3;
        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);
        Mockito.when(subjectModel.getId()).thenReturn(subjectId);

        final IssueStandardModelInterface issueStandardModel = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModel.getTopicPriorities())
                .thenReturn(new ArrayList<>());
        Mockito.when(issueStandardModel.getQuestionTypeOptions())
                .thenReturn(new ArrayList<>());
        Mockito.when(issueStandardModel.getSubject()).thenReturn(subjectModel);

        issueStandardsOptions.setIssueStandardsService(issueStandardsService);
        issueStandardsOptions.setTopicPrioritiesService(topicPrioritiesService);
        issueStandardsOptions.setQuestionTypeOptionsService(questionTypeOptionsService);
        issueStandardsOptions.setSubjectsService(subjectsService);
        issueStandardsOptions
                .withTopicPriorities(topicPrioritiesOptions)
                .withQuestionTypeOptions(questionTypeOptionsOptions)
                .withSubject(subjectsOptions);

        final InOrder inOrder = Mockito.inOrder(
                subjectsService, issueStandardsService,
                topicPrioritiesService, questionTypeOptionsService);

        issueStandardsOptions.saveWithRelations(issueStandardModel);
        inOrder.verify(subjectsService, times(1)).save(issueStandardModel.getSubject(), subjectsOptions);
        inOrder.verify(issueStandardsService, times(1)).save(issueStandardModel);
        inOrder.verify(topicPrioritiesService, times(1))
                .save(issueStandardModel.getTopicPriorities(), topicPrioritiesOptions);
        inOrder.verify(questionTypeOptionsService, times(1))
                .save(issueStandardModel.getQuestionTypeOptions(), questionTypeOptionsOptions);
    }

    @Test
    public void testDeleteWithRelations() throws Exception {

        final IssueStandardsOptionsInterface issueStandardsOptions = new IssueStandardsOptions();
        final IssueStandardsServiceInterface issueStandardsService = Mockito.mock(IssueStandardsServiceInterface.class);
        final IssueStandardTopicPrioritiesServiceInterface topicPrioritiesService
                = Mockito.mock(IssueStandardTopicPrioritiesServiceInterface.class);
        final IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions
                = Mockito.mock(IssueStandardTopicPrioritiesOptionsInterface.class);

        final IssueStandardQuestionTypeOptionsServiceInterface questionTypeOptionsService
                = Mockito.mock(IssueStandardQuestionTypeOptionsServiceInterface.class);
        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions
                = Mockito.mock(IssueStandardQuestionTypeOptionsOptionsInterface.class);

        final SubjectsServiceInterface subjectsService = Mockito.mock(SubjectsServiceInterface.class);
        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);

        final Integer subjectId = 3;
        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);
        Mockito.when(subjectModel.getId()).thenReturn(subjectId);

        final IssueStandardModelInterface issueStandardModel = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModel.getTopicPriorities())
                .thenReturn(new ArrayList<>());
        Mockito.when(issueStandardModel.getQuestionTypeOptions())
                .thenReturn(new ArrayList<>());
        Mockito.when(issueStandardModel.getSubject()).thenReturn(subjectModel);

        issueStandardsOptions.setIssueStandardsService(issueStandardsService);
        issueStandardsOptions.setTopicPrioritiesService(topicPrioritiesService);
        issueStandardsOptions.setQuestionTypeOptionsService(questionTypeOptionsService);
        issueStandardsOptions.setSubjectsService(subjectsService);
        issueStandardsOptions
                .withTopicPriorities(topicPrioritiesOptions)
                .withQuestionTypeOptions(questionTypeOptionsOptions)
                .withSubject(subjectsOptions);

        final InOrder inOrder = Mockito.inOrder(
                subjectsService, issueStandardsService,
                topicPrioritiesService, questionTypeOptionsService);

        issueStandardsOptions.deleteWithRelations(issueStandardModel);

        inOrder.verify(topicPrioritiesService, times(1))
                .delete(issueStandardModel.getTopicPriorities(), topicPrioritiesOptions);
        inOrder.verify(questionTypeOptionsService, times(1))
                .delete(issueStandardModel.getQuestionTypeOptions(), questionTypeOptionsOptions);
        inOrder.verify(issueStandardsService, times(1)).delete(issueStandardModel);
        inOrder.verify(subjectsService, times(1)).delete(issueStandardModel.getSubject(), subjectsOptions);
    }
}
