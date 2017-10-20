package easytests.core.options;

import easytests.core.models.QuestionModelInterface;
import easytests.core.models.SubjectModel;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.TopicModelInterface;
import easytests.core.models.empty.SubjectModelEmpty;
import easytests.core.services.QuestionsServiceInterface;
import easytests.core.services.SubjectsServiceInterface;
import easytests.core.services.TopicsServiceInterface;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * @author Yarik2308
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicsOptionsTest {
    @Test
    public void testWithRelationsOnSingleModel() throws Exception {

        final TopicsOptionsInterface topicsOptions = new TopicsOptions();
        final TopicModelInterface topicModel = Mockito.mock(TopicModelInterface.class);

        topicModel.setId(1);
        given(topicModel.getSubject()).willReturn(new SubjectModelEmpty(1));

        final QuestionsServiceInterface questionsService = Mockito.mock(QuestionsServiceInterface.class);
        final SubjectsServiceInterface subjectsService = Mockito.mock(SubjectsServiceInterface.class);
        final TopicsServiceInterface topicsService = Mockito.mock(TopicsServiceInterface.class);

        topicsOptions.setQuestionsService(questionsService);
        topicsOptions.setSubjectsService(subjectsService);
        topicsOptions.setTopicsService(topicsService);

        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);
        final QuestionsOptionsInterface questionsOptions = Mockito.mock(QuestionsOptionsInterface.class);

        topicsOptions.withSubject(subjectsOptions);
        topicsOptions.withQuestions(questionsOptions);

        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);
        final List<QuestionModelInterface> questionModel = new ArrayList<>();
        questionModel.add(Mockito.mock(QuestionModelInterface.class));

        given(subjectsService.find(topicModel.getSubject().getId(), subjectsOptions)).willReturn(subjectModel);
        given(questionsService.findByTopic(topicModel, questionsOptions)).willReturn(questionModel);

        final TopicModelInterface topicModelWithRelations =  topicsOptions.withRelations(topicModel);

        Assert.assertEquals(topicModel,topicModelWithRelations);

        verify(subjectsService).find(1, subjectsOptions);
        verify(questionsService).findByTopic(topicModel, questionsOptions);

        verify(topicModel).setSubject(subjectModel);
        verify(topicModel).setQuestions(questionModel);
    }

    @Test
    public void testWithRelationsOnNull() throws Exception{

        final TopicsOptionsInterface topicsOptions = new TopicsOptions();

        final QuestionsServiceInterface questionsService = Mockito.mock(QuestionsServiceInterface.class);
        final SubjectsServiceInterface subjectsService = Mockito.mock(SubjectsServiceInterface.class);
        final TopicsServiceInterface topicsService = Mockito.mock(TopicsServiceInterface.class);

        topicsOptions.setQuestionsService(questionsService);
        topicsOptions.setSubjectsService(subjectsService);
        topicsOptions.setTopicsService(topicsService);

        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);
        final QuestionsOptionsInterface questionsOptions = Mockito.mock(QuestionsOptionsInterface.class);

        topicsOptions.withSubject(subjectsOptions);
        topicsOptions.withQuestions(questionsOptions);

        final TopicModelInterface nullTopicModel = null;

        final TopicModelInterface topicModelWithRelations =  topicsOptions.withRelations(nullTopicModel);

        Assert.assertNull(topicModelWithRelations);
    }

    @Test
    public void testWithRelationsOnList() throws Exception{

        final TopicModelInterface topicModelFirst = Mockito.mock(TopicModelInterface.class);
        topicModelFirst.setId(1);
        given(topicModelFirst.getSubject()).willReturn(new SubjectModelEmpty(1));

        final TopicModelInterface topicModelSecond = Mockito.mock(TopicModelInterface.class);
        topicModelSecond.setId(2);
        given(topicModelSecond.getSubject()).willReturn(new SubjectModelEmpty(2));

        final List<TopicModelInterface> topicsModels = new ArrayList<>(2);
        topicsModels.add(topicModelFirst);
        topicsModels.add(topicModelSecond);

        final TopicsOptionsInterface topicsOptions = new TopicsOptions();
        final TopicsServiceInterface topicsService = Mockito.mock(TopicsServiceInterface.class);

        final QuestionsServiceInterface questionsService = Mockito.mock(QuestionsServiceInterface.class);
        final QuestionsOptionsInterface questionsOptions = Mockito.mock(QuestionsOptionsInterface.class);

        final SubjectsServiceInterface subjectsService = Mockito.mock(SubjectsServiceInterface.class);
        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);

        topicsOptions.setQuestionsService(questionsService);
        topicsOptions.setSubjectsService(subjectsService);
        topicsOptions.setTopicsService(topicsService);
        topicsOptions.withSubject(subjectsOptions);
        topicsOptions.withQuestions(questionsOptions);

        final SubjectModelInterface subjectModelFirst = Mockito.mock(SubjectModelInterface.class);
        final SubjectModelInterface subjectModelSecond = Mockito.mock(SubjectModelInterface.class);

        final List<QuestionModelInterface> questionsModelsFirst = new ArrayList<>();
        questionsModelsFirst.add(Mockito.mock(QuestionModelInterface.class));

        final List<QuestionModelInterface> questionsModelsSecond = new ArrayList<>();
        questionsModelsSecond.add(Mockito.mock(QuestionModelInterface.class));

        given(subjectsService.find(topicModelFirst.getSubject().getId(), subjectsOptions)).willReturn(subjectModelFirst);
        given(questionsService.findByTopic(topicModelFirst, questionsOptions)).willReturn(questionsModelsFirst);

        given(subjectsService.find(topicModelSecond.getSubject().getId(), subjectsOptions)).willReturn(subjectModelSecond);
        given(questionsService.findByTopic(topicModelSecond, questionsOptions)).willReturn(questionsModelsSecond);

        final List<TopicModelInterface> topicsModelsWithRelations =  topicsOptions.withRelations(topicsModels);

        Assert.assertEquals(topicsModelsWithRelations,topicsModels);

        verify(subjectsService).find(topicModelFirst.getSubject().getId(), subjectsOptions);
        verify(questionsService).findByTopic(topicModelFirst, questionsOptions);

        verify(topicsModels.get(0)).setSubject(subjectModelFirst);
        verify(topicsModels.get(0)).setQuestions(questionsModelsFirst);

        verify(subjectsService).find(topicModelSecond.getSubject().getId(), subjectsOptions);
        verify(questionsService).findByTopic(topicModelSecond, questionsOptions);

        verify(topicsModels.get(1)).setSubject(subjectModelSecond);
        verify(topicsModels.get(1)).setQuestions(questionsModelsSecond);
    }

    @Test
    public void testSaveWithRelations() throws Exception{

        final TopicsOptionsInterface topicsOptions = new TopicsOptions();
        final TopicModelInterface topicModel = Mockito.mock(TopicModelInterface.class);

        topicModel.setId(1);
        given(topicModel.getSubject()).willReturn(new SubjectModelEmpty(1));

        final QuestionsServiceInterface questionsService = Mockito.mock(QuestionsServiceInterface.class);
        final SubjectsServiceInterface subjectsService = Mockito.mock(SubjectsServiceInterface.class);
        final TopicsServiceInterface topicsService = Mockito.mock(TopicsServiceInterface.class);

        topicsOptions.setQuestionsService(questionsService);
        topicsOptions.setSubjectsService(subjectsService);
        topicsOptions.setTopicsService(topicsService);

        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);
        final QuestionsOptionsInterface questionsOptions = Mockito.mock(QuestionsOptionsInterface.class);

        topicsOptions.withSubject(subjectsOptions);
        topicsOptions.withQuestions(questionsOptions);

        final SubjectModelInterface subjectModel = new SubjectModel();
        topicModel.setSubject(subjectModel);

        final List<QuestionModelInterface> questionModel = new ArrayList<>();
        topicModel.setQuestions(questionModel);

        final InOrder inOrder = Mockito.inOrder(subjectsService, questionsService);

        topicsOptions.saveWithRelations(topicModel);

        inOrder.verify(subjectsService).save(topicModel.getSubject(), subjectsOptions);
        inOrder.verify(questionsService).save(topicModel.getQuestions(), questionsOptions);
    }

    @Test
    public void testDeleteWithRelations() throws Exception{

        final TopicsOptionsInterface topicsOptions = new TopicsOptions();
        final TopicModelInterface topicModel = Mockito.mock(TopicModelInterface.class);

        topicModel.setId(1);
        given(topicModel.getSubject()).willReturn(new SubjectModelEmpty(1));

        final QuestionsServiceInterface questionsService = Mockito.mock(QuestionsServiceInterface.class);
        final SubjectsServiceInterface subjectsService = Mockito.mock(SubjectsServiceInterface.class);
        final TopicsServiceInterface topicsService = Mockito.mock(TopicsServiceInterface.class);

        topicsOptions.setQuestionsService(questionsService);
        topicsOptions.setSubjectsService(subjectsService);
        topicsOptions.setTopicsService(topicsService);

        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);
        final QuestionsOptionsInterface questionsOptions = Mockito.mock(QuestionsOptionsInterface.class);

        topicsOptions.withSubject(subjectsOptions);
        topicsOptions.withQuestions(questionsOptions);

        final SubjectModelInterface subjectModel = new SubjectModel();
        topicModel.setSubject(subjectModel);

        final List<QuestionModelInterface> questionModel = new ArrayList<>();
        topicModel.setQuestions(questionModel);

        final InOrder inOrder = Mockito.inOrder(questionsService, subjectsService);

        topicsOptions.deleteWithRelations(topicModel);

        inOrder.verify(questionsService).save(topicModel.getQuestions(), questionsOptions);
        inOrder.verify(subjectsService).save(topicModel.getSubject(), subjectsOptions);
    }
}
