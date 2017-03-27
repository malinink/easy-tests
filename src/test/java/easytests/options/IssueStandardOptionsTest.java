package easytests.options;

import easytests.models.*;
import easytests.models.empty.IssueStandardModelEmpty;
import easytests.models.empty.ModelsListEmpty;
import easytests.models.empty.SubjectModelEmpty;
import easytests.models.exceptions.CallMethodOnEmptyModelsListException;
import easytests.services.IssueStandardQuestionTypeOptionsServiceInterface;
import easytests.services.IssueStandardsServiceInterface;
import easytests.services.IssueStandardTopicPrioritiesServiceInterface;
import easytests.services.SubjectsServiceInterface;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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

    @Rule
    public final ExpectedException exception = ExpectedException.none();

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
        final IssueStandardModelInterface issueStandardModel = new IssueStandardModel();
        issueStandardModel.setTopicPriorities(new ModelsListEmpty());
        issueStandardModel.setQuestionTypeOptions(new ModelsListEmpty());
        issueStandardModel.setSubject(new SubjectModelEmpty(subjectId));

        // возвращаемые сервисами
        final List<IssueStandardTopicPriorityModelInterface> topicPriorityModels = new ArrayList<>();
        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels = new ArrayList<>();
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

        exception.expect(CallMethodOnEmptyModelsListException.class);
        Assert.assertNotEquals(topicPriorityModels, issueStandardModelWithoutRelations.getTopicPriorities());

        exception.expect(CallMethodOnEmptyModelsListException.class);
        Assert.assertNotEquals(questionTypeOptionModels, issueStandardModelWithoutRelations.getQuestionTypeOptions());

        Assert.assertNotEquals(subjectModel, issueStandardModelWithoutRelations.getSubject());

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
        Assert.assertEquals(topicPriorityModels, issueStandardModelWithRelations.getTopicPriorities());
        Assert.assertEquals(questionTypeOptionModels, issueStandardModelWithRelations.getQuestionTypeOptions());
        Assert.assertEquals(subjectModel, issueStandardModelWithRelations.getSubject());
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
        final List<IssueStandardModelInterface> issueStandardModels = new ArrayList<>(2);

        final IssueStandardModelInterface issueStandardModelFirst = new IssueStandardModel();
        final IssueStandardModelInterface issueStandardModelSecond = new IssueStandardModel();
        issueStandardModelFirst.setTopicPriorities(new ModelsListEmpty());
        issueStandardModelFirst.setQuestionTypeOptions(new ModelsListEmpty());
        issueStandardModelFirst.setSubject(new SubjectModelEmpty(subjectIdFirst));
        issueStandardModelSecond.setTopicPriorities(new ModelsListEmpty());
        issueStandardModelSecond.setQuestionTypeOptions(new ModelsListEmpty());
        issueStandardModelSecond.setSubject(new SubjectModelEmpty(subjectIdFirst));

        issueStandardModels.add(issueStandardModelFirst);
        issueStandardModels.add(issueStandardModelSecond);

        final List<IssueStandardTopicPriorityModelInterface> topicPriorityModelsFirst = new ArrayList<>(1);
        topicPriorityModelsFirst.add(Mockito.mock(IssueStandardTopicPriorityModelInterface.class));
        final List<IssueStandardTopicPriorityModelInterface> topicPriorityModelsSecond = new ArrayList<>(1);
        topicPriorityModelsSecond.add(Mockito.mock(IssueStandardTopicPriorityModelInterface.class));
        given(topicPrioritiesService.findByIssueStandard(issueStandardModelFirst))
                .willReturn(topicPriorityModelsFirst);
        given(topicPrioritiesService.findByIssueStandard(issueStandardModelSecond))
                .willReturn(topicPriorityModelsSecond);

        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModelsFirst = new ArrayList<>(1);
        questionTypeOptionModelsFirst.add(Mockito.mock(IssueStandardQuestionTypeOptionModelInterface.class));
        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModelsSecond = new ArrayList<>(1);
        questionTypeOptionModelsSecond.add(Mockito.mock(IssueStandardQuestionTypeOptionModelInterface.class));
        given(questionTypeOptionsService.findByIssueStandard(issueStandardModelFirst))
                .willReturn(questionTypeOptionModelsFirst);
        given(questionTypeOptionsService.findByIssueStandard(issueStandardModelSecond))
                .willReturn(questionTypeOptionModelsSecond);

        final SubjectModelInterface subjectModelFirst = Mockito.mock(SubjectModelInterface.class);
        Mockito.when(subjectModelFirst.getId()).thenReturn(subjectIdFirst);
        final SubjectModelInterface subjectModelSecond = Mockito.mock(SubjectModelInterface.class);
        Mockito.when(subjectModelSecond.getId()).thenReturn(subjectIdSecond);
        given(subjectsService.find(subjectIdFirst, subjectsOptions)).willReturn(subjectModelFirst);
        given(subjectsService.find(subjectIdSecond, subjectsOptions)).willReturn(subjectModelSecond);

        List<IssueStandardModelInterface> issueStandardModelsWithoutRelations
                = issueStandardsOptions.withRelations(issueStandardModels);

        verify(topicPrioritiesService, times(0)).findByIssueStandard(issueStandardModelFirst);
        verify(topicPrioritiesService, times(0)).findByIssueStandard(issueStandardModelSecond);
        verify(questionTypeOptionsService, times(0)).findByIssueStandard(issueStandardModelFirst);
        verify(questionTypeOptionsService, times(0)).findByIssueStandard(issueStandardModelSecond);
        verify(subjectsService, times(0)).find(subjectIdFirst);
        verify(subjectsService, times(0)).find(subjectIdSecond);

        Assert.assertEquals(issueStandardModels, issueStandardModelsWithoutRelations);
        exception.expect(CallMethodOnEmptyModelsListException.class);
        Assert.assertNotEquals(topicPriorityModelsFirst,
                issueStandardModelsWithoutRelations.get(0).getTopicPriorities());

        exception.expect(CallMethodOnEmptyModelsListException.class);
        Assert.assertNotEquals(topicPriorityModelsSecond,
                issueStandardModelsWithoutRelations.get(1).getTopicPriorities());

        exception.expect(CallMethodOnEmptyModelsListException.class);
        Assert.assertNotEquals(questionTypeOptionModelsFirst,
                issueStandardModelsWithoutRelations.get(0).getQuestionTypeOptions());

        exception.expect(CallMethodOnEmptyModelsListException.class);
        Assert.assertNotEquals(questionTypeOptionModelsSecond,
                issueStandardModelsWithoutRelations.get(1).getQuestionTypeOptions());

        Assert.assertNotEquals(subjectModelFirst,
                issueStandardModelsWithoutRelations.get(0).getSubject());
        Assert.assertNotEquals(subjectModelSecond,
                issueStandardModelsWithoutRelations.get(1).getSubject());

        issueStandardsOptions.setTopicPrioritiesService(topicPrioritiesService);
        issueStandardsOptions.setQuestionTypeOptionsService(questionTypeOptionsService);
        issueStandardsOptions.setSubjectsService(subjectsService);
        issueStandardsOptions
                .withTopicPriorities(topicPrioritiesOptions)
                .withQuestionTypeOptions(questionTypeOptionsOptions)
                .withSubject(subjectsOptions);

        List<IssueStandardModelInterface> issueStandardModelsWithRelations
                = issueStandardsOptions.withRelations(issueStandardModels);

        verify(topicPrioritiesService, times(1)).findByIssueStandard(issueStandardModelFirst);
        verify(topicPrioritiesService, times(1)).findByIssueStandard(issueStandardModelSecond);
        verify(questionTypeOptionsService, times(1)).findByIssueStandard(issueStandardModelFirst);
        verify(questionTypeOptionsService, times(1)).findByIssueStandard(issueStandardModelSecond);
        verify(subjectsService, times(1)).find(subjectIdFirst);
        verify(subjectsService, times(1)).find(subjectIdSecond);

        Assert.assertEquals(issueStandardModels, issueStandardModelsWithRelations);
        Assert.assertEquals(topicPriorityModelsFirst,
                issueStandardModelsWithRelations.get(0).getTopicPriorities());
        Assert.assertEquals(topicPriorityModelsSecond,
                issueStandardModelsWithRelations.get(1).getTopicPriorities());
        Assert.assertEquals(questionTypeOptionModelsFirst,
                issueStandardModelsWithRelations.get(0).getQuestionTypeOptions());
        Assert.assertEquals(questionTypeOptionModelsSecond,
                issueStandardModelsWithRelations.get(1).getQuestionTypeOptions());
        Assert.assertEquals(subjectModelFirst,
                issueStandardModelsWithRelations.get(0).getSubject());
        Assert.assertEquals(subjectModelSecond,
                issueStandardModelsWithRelations.get(1).getSubject());
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

        final IssueStandardModelInterface issueStandardModel = new IssueStandardModel();
        issueStandardModel.setTopicPriorities(new ArrayList<IssueStandardTopicPriorityModelInterface>());
        issueStandardModel.setQuestionTypeOptions(new ArrayList<IssueStandardQuestionTypeOptionModelInterface>());
        issueStandardModel.setSubject(subjectModel);

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

        final IssueStandardModelInterface issueStandardModel = new IssueStandardModel();
        issueStandardModel.setTopicPriorities(new ArrayList<IssueStandardTopicPriorityModelInterface>());
        issueStandardModel.setQuestionTypeOptions(new ArrayList<IssueStandardQuestionTypeOptionModelInterface>());
        issueStandardModel.setSubject(subjectModel);

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
