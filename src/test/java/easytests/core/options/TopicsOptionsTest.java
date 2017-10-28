package easytests.core.options;

import easytests.core.models.QuestionModelInterface;
import easytests.core.models.SubjectModel;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.TopicModelInterface;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Yarik2308
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicsOptionsTest {
    @Test
    public void testWithRelationsSubjectOnSingleModel() throws Exception {

        final TopicsOptionsInterface topicsOptions = new TopicsOptions();
        final TopicModelInterface topicModel = Mockito.mock(TopicModelInterface.class);

        final SubjectsServiceInterface subjectsService = Mockito.mock(SubjectsServiceInterface.class);
        final TopicsServiceInterface topicsService = Mockito.mock(TopicsServiceInterface.class);
        topicsOptions.setSubjectsService(subjectsService);
        topicsOptions.setTopicsService(topicsService);

        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);
        topicsOptions.withSubject(subjectsOptions);

        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);
        final SubjectModelInterface subjectModelWithId = Mockito.mock(SubjectModelInterface.class);

        given(topicModel.getSubject()).willReturn(subjectModelWithId);
        given(subjectsService.find(topicModel.getSubject().getId(), subjectsOptions)).willReturn(subjectModel);

        final TopicModelInterface topicModelWithRelations =  topicsOptions.withRelations(topicModel);

        Assert.assertEquals(topicModel,topicModelWithRelations);
        subjectsService.find(1, subjectsOptions);
        verify(topicModel).setSubject(subjectModel);
    }

    @Test
    public void testWithRelationsQuestionsOnSingleModel() throws Exception {

        final TopicsOptionsInterface topicsOptions = new TopicsOptions();
        final TopicModelInterface topicModel = Mockito.mock(TopicModelInterface.class);
        final QuestionsServiceInterface questionsService = Mockito.mock(QuestionsServiceInterface.class);
        final TopicsServiceInterface topicsService = Mockito.mock(TopicsServiceInterface.class);

        topicsOptions.setQuestionsService(questionsService);
        topicsOptions.setTopicsService(topicsService);

        final QuestionsOptionsInterface questionsOptions = Mockito.mock(QuestionsOptionsInterface.class);
        topicsOptions.withQuestions(questionsOptions);

        final List<QuestionModelInterface> questionsModels = new ArrayList<>();
        questionsModels.add(Mockito.mock(QuestionModelInterface.class));

        given(questionsService.findByTopic(topicModel, questionsOptions)).willReturn(questionsModels);

        final TopicModelInterface topicModelWithRelations =  topicsOptions.withRelations(topicModel);

        Assert.assertEquals(topicModel,topicModelWithRelations);
        verify(questionsService).findByTopic(topicModel, questionsOptions);
        verify(topicModel).setQuestions(questionsModels);
    }

    @Test
    public void testWithRelationsOnNull() throws Exception{

        final TopicsOptionsInterface topicsOptions = new TopicsOptions();
        final TopicModelInterface nullTopicModel = null;

        final TopicModelInterface topicModelWithRelations =  topicsOptions.withRelations(nullTopicModel);

        Assert.assertNull(topicModelWithRelations);
    }

    @Test
    public void testWithRelationsSubjectOnList() throws Exception{

        final TopicModelInterface topicModelFirst = Mockito.mock(TopicModelInterface.class);
        final TopicModelInterface topicModelSecond = Mockito.mock(TopicModelInterface.class);
        final List<TopicModelInterface> topicsModels = new ArrayList<>(2);
        final TopicsOptionsInterface topicsOptions = new TopicsOptions();
        final TopicsServiceInterface topicsService = Mockito.mock(TopicsServiceInterface.class);
        final SubjectsServiceInterface subjectsService = Mockito.mock(SubjectsServiceInterface.class);
        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);

        topicsOptions.setTopicsService(topicsService);
        topicsOptions.setSubjectsService(subjectsService);
        topicsOptions.withSubject(subjectsOptions);
        topicsModels.add(topicModelFirst);
        topicsModels.add(topicModelSecond);

        final SubjectModelInterface subjectModelFirst = Mockito.mock(SubjectModelInterface.class);
        final SubjectModelInterface subjectModelWithIdFirst = Mockito.mock(SubjectModelInterface.class);
        final SubjectModelInterface subjectModelSecond = Mockito.mock(SubjectModelInterface.class);
        final SubjectModelInterface subjectModelWithIdSecond = Mockito.mock(SubjectModelInterface.class);

        given(topicModelFirst.getSubject()).willReturn(subjectModelWithIdFirst);
        given(topicModelSecond.getSubject()).willReturn(subjectModelWithIdSecond);
        given(topicModelFirst.getSubject().getId()).willReturn(1);
        given(topicModelSecond.getSubject().getId()).willReturn(2);
        given(subjectsService.find(topicModelFirst.getSubject().getId(), subjectsOptions)).willReturn(subjectModelFirst);
        given(subjectsService.find(topicModelSecond.getSubject().getId(), subjectsOptions)).willReturn(subjectModelSecond);

        final List<TopicModelInterface> topicsModelsWithRelations =  topicsOptions.withRelations(topicsModels);

        Assert.assertEquals(topicsModelsWithRelations,topicsModels);
        subjectsService.find(1, subjectsOptions);
        subjectsService.find(2, subjectsOptions);
        verify(topicsModels.get(0)).setSubject(subjectModelFirst);
        verify(topicsModels.get(1)).setSubject(subjectModelSecond);
    }


    @Test
    public void testWithRelationsQuestionsOnList() throws Exception{

        final TopicModelInterface topicModelFirst = Mockito.mock(TopicModelInterface.class);

        final TopicModelInterface topicModelSecond = Mockito.mock(TopicModelInterface.class);

        final List<TopicModelInterface> topicsModels = new ArrayList<>(2);
        topicsModels.add(topicModelFirst);
        topicsModels.add(topicModelSecond);

        final TopicsOptionsInterface topicsOptions = new TopicsOptions();
        final TopicsServiceInterface topicsService = Mockito.mock(TopicsServiceInterface.class);

        final QuestionsServiceInterface questionsService = Mockito.mock(QuestionsServiceInterface.class);
        final QuestionsOptionsInterface questionsOptions = Mockito.mock(QuestionsOptionsInterface.class);

        topicsOptions.setTopicsService(topicsService);
        topicsOptions.setQuestionsService(questionsService);
        topicsOptions.withQuestions(questionsOptions);


        final List<QuestionModelInterface> questionsModelsFirst = new ArrayList<>();
        questionsModelsFirst.add(Mockito.mock(QuestionModelInterface.class));

        final List<QuestionModelInterface> questionsModelsSecond = new ArrayList<>();
        questionsModelsSecond.add(Mockito.mock(QuestionModelInterface.class));

        given(questionsService.findByTopic(topicModelFirst, questionsOptions)).willReturn(questionsModelsFirst);
        given(questionsService.findByTopic(topicModelSecond, questionsOptions)).willReturn(questionsModelsSecond);

        final List<TopicModelInterface> topicsModelsWithRelations =  topicsOptions.withRelations(topicsModels);

        Assert.assertEquals(topicsModelsWithRelations,topicsModels);
        verify(questionsService).findByTopic(topicModelFirst, questionsOptions);
        verify(topicsModels.get(0)).setQuestions(questionsModelsFirst);
        verify(questionsService).findByTopic(topicModelSecond, questionsOptions);
        verify(topicsModels.get(1)).setQuestions(questionsModelsSecond);
    }

    @Test
    public void testSaveWithRelationsSubject() throws Exception{

        final TopicsOptionsInterface topicsOptions = new TopicsOptions();
        final TopicModelInterface topicModel = Mockito.mock(TopicModelInterface.class);
        final SubjectModelInterface subjectModelId = Mockito.mock(SubjectModelInterface.class);
        given(topicModel.getSubject()).willReturn(subjectModelId);

        final SubjectsServiceInterface subjectsService = Mockito.mock(SubjectsServiceInterface.class);
        final TopicsServiceInterface topicsService = Mockito.mock(TopicsServiceInterface.class);
        topicsOptions.setSubjectsService(subjectsService);
        topicsOptions.setTopicsService(topicsService);

        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);
        topicsOptions.withSubject(subjectsOptions);

        final SubjectModelInterface subjectModel = new SubjectModel();
        topicModel.setSubject(subjectModel);

        final InOrder inOrder = Mockito.inOrder(subjectsService, topicsService);

        topicsOptions.saveWithRelations(topicModel);

        inOrder.verify(subjectsService).save(topicModel.getSubject(), subjectsOptions);
        inOrder.verify(topicsService).save(topicModel);
    }

    @Test
    public void testSaveWithRelationsQuestions() throws Exception{

        final TopicsOptionsInterface topicsOptions = new TopicsOptions();
        final TopicModelInterface topicModel = Mockito.mock(TopicModelInterface.class);
        final QuestionsServiceInterface questionsService = Mockito.mock(QuestionsServiceInterface.class);
        final TopicsServiceInterface topicsService = Mockito.mock(TopicsServiceInterface.class);
        topicsOptions.setQuestionsService(questionsService);
        topicsOptions.setTopicsService(topicsService);

        final QuestionsOptionsInterface questionsOptions = Mockito.mock(QuestionsOptionsInterface.class);
        topicsOptions.withQuestions(questionsOptions);

        final List<QuestionModelInterface> questionsModels = new ArrayList<>();
        topicModel.setQuestions(questionsModels);

        final InOrder inOrder = Mockito.inOrder(topicsService, questionsService);

        topicsOptions.saveWithRelations(topicModel);

        inOrder.verify(topicsService).save(topicModel);
        inOrder.verify(questionsService).save(topicModel.getQuestions(), questionsOptions);
    }

    @Test
    public void testDeleteWithRelationsSubject() throws Exception{

        final TopicsOptionsInterface topicsOptions = new TopicsOptions();
        final TopicModelInterface topicModel = Mockito.mock(TopicModelInterface.class);
        final SubjectModelInterface subjectModelId = Mockito.mock(SubjectModelInterface.class);
        given(topicModel.getSubject()).willReturn(subjectModelId);

        final SubjectsServiceInterface subjectsService = Mockito.mock(SubjectsServiceInterface.class);
        final TopicsServiceInterface topicsService = Mockito.mock(TopicsServiceInterface.class);
        topicsOptions.setSubjectsService(subjectsService);
        topicsOptions.setTopicsService(topicsService);

        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);
        topicsOptions.withSubject(subjectsOptions);

        final SubjectModelInterface subjectModel = new SubjectModel();
        topicModel.setSubject(subjectModel);

        final InOrder inOrder = Mockito.inOrder(topicsService, subjectsService);

        topicsOptions.deleteWithRelations(topicModel);

        inOrder.verify(topicsService).delete(topicModel);
        inOrder.verify(subjectsService).save(topicModel.getSubject(), subjectsOptions);
    }

    @Test
    public void testDeleteWithRelationsQuestion() throws Exception{

        final TopicsOptionsInterface topicsOptions = new TopicsOptions();
        final TopicModelInterface topicModel = Mockito.mock(TopicModelInterface.class);
        final QuestionsServiceInterface questionsService = Mockito.mock(QuestionsServiceInterface.class);
        final TopicsServiceInterface topicsService = Mockito.mock(TopicsServiceInterface.class);
        topicsOptions.setQuestionsService(questionsService);
        topicsOptions.setTopicsService(topicsService);

        final QuestionsOptionsInterface questionsOptions = Mockito.mock(QuestionsOptionsInterface.class);
        topicsOptions.withQuestions(questionsOptions);

        final List<QuestionModelInterface> questionsModels = new ArrayList<>();
        topicModel.setQuestions(questionsModels);

        final InOrder inOrder = Mockito.inOrder(questionsService, topicsService);

        topicsOptions.deleteWithRelations(topicModel);

        inOrder.verify(questionsService).save(topicModel.getQuestions(), questionsOptions);
        inOrder.verify(topicsService).delete(topicModel);
    }

    @Test
    public void testWithSubject() throws Exception {

        final TopicsOptionsInterface topicsOptions = new TopicsOptions();
        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);

        final TopicsOptionsInterface topicsOptionsWithSubject = topicsOptions.withSubject(subjectsOptions);
        Assert.assertEquals(topicsOptionsWithSubject, topicsOptions);
    }

    @Test
    public void testWithQuestions() throws Exception {

        final TopicsOptionsInterface topicsOptions = new TopicsOptions();
        final QuestionsOptionsInterface questionsOptions = Mockito.mock(QuestionsOptionsInterface.class);

        final TopicsOptionsInterface topicsOptionsWithQuestions = topicsOptions.withQuestions(questionsOptions);
        Assert.assertEquals(topicsOptionsWithQuestions, topicsOptions);
    }

    @Test
    public void testSaveDeleteWithRelationsSubject() {

        final TopicsOptionsInterface topicsOptions = new TopicsOptions();
        final TopicModelInterface topicModel = Mockito.mock(TopicModelInterface.class);
        final SubjectModelInterface subjectModelId = Mockito.mock(SubjectModelInterface.class);
        given(topicModel.getSubject()).willReturn(subjectModelId);

        final SubjectsServiceInterface subjectsService = Mockito.mock(SubjectsServiceInterface.class);
        final TopicsServiceInterface topicsService = Mockito.mock(TopicsServiceInterface.class);
        topicsOptions.setSubjectsService(subjectsService);
        topicsOptions.setTopicsService(topicsService);

        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);
        topicsOptions.withSubject(subjectsOptions);

        final SubjectModelInterface subjectModel = new SubjectModel();
        topicModel.setSubject(subjectModel);

        topicsOptions.saveWithRelations(topicModel);
        verify(topicsService).save(topicModel);

        topicsOptions.deleteWithRelations(topicModel);
        verify(topicsService).delete(topicModel);

        verify(subjectsService, times(2)).save(topicModel.getSubject(), subjectsOptions);
    }

    @Test
    public void testSaveDeleteWithRelationsQuestion() throws Exception{

        final TopicsOptionsInterface topicsOptions = new TopicsOptions();
        final TopicModelInterface topicModel = Mockito.mock(TopicModelInterface.class);
        final QuestionsServiceInterface questionsService = Mockito.mock(QuestionsServiceInterface.class);
        final TopicsServiceInterface topicsService = Mockito.mock(TopicsServiceInterface.class);
        topicsOptions.setQuestionsService(questionsService);
        topicsOptions.setTopicsService(topicsService);

        final QuestionsOptionsInterface questionsOptions = Mockito.mock(QuestionsOptionsInterface.class);
        topicsOptions.withQuestions(questionsOptions);

        final List<QuestionModelInterface> questionsModels = new ArrayList<>();
        topicModel.setQuestions(questionsModels);

        topicsOptions.saveWithRelations((topicModel));
        verify(topicsService).save(topicModel);

        topicsOptions.deleteWithRelations(topicModel);
        verify(topicsService).delete(topicModel);
        
        verify(questionsService, times(2)).save(topicModel.getQuestions(), questionsOptions);
    }

}
