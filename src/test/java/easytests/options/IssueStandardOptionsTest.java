package easytests.options;

import easytests.models.*;
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

        final List<IssueStandardModelInterface> issueStandardModels = new ArrayList<>(2);
        IssueStandardModelInterface issueStandardModelFirst = Mockito.mock(IssueStandardModelInterface.class);
        IssueStandardModelInterface issueStandardModelSecond = Mockito.mock(IssueStandardModelInterface.class);

        issueStandardModels.add(issueStandardModelFirst);
        issueStandardModels.add(issueStandardModelSecond);

        final IssueStandardsOptionsInterface issueStandardsOptions = new IssueStandardsOptions();
        final IssueStandardsOptionsInterface issueStandardsOptionsSpy = Mockito.spy(issueStandardsOptions);

        issueStandardsOptionsSpy.withRelations(issueStandardModels);

        verify(issueStandardsOptionsSpy, times(1)).withRelations(issueStandardModelFirst);
        verify(issueStandardsOptionsSpy, times(1)).withRelations(issueStandardModelSecond);
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
