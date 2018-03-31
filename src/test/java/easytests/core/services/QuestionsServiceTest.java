package easytests.core.services;

import easytests.core.entities.QuestionEntity;
import easytests.core.mappers.QuestionsMapper;
import easytests.core.models.QuestionModelInterface;
import easytests.core.models.TopicModelInterface;
import easytests.core.options.QuestionsOptionsInterface;
import easytests.core.services.exceptions.DeleteUnidentifiedModelException;
import easytests.support.QuestionsSupport;
import easytests.support.TopicsSupport;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.*;
import org.mockito.ArgumentCaptor;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;


/**
 * @author RisaMagpie
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionsServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private QuestionsMapper questionsMapper;

    @Autowired
    private QuestionsService questionsService;

    private QuestionsSupport questionsSupport = new QuestionsSupport();

    private TopicsSupport topicsSupport = new TopicsSupport();

    private List<QuestionEntity> getQuestionsFixturesEntities() {
        final List<QuestionEntity> questionsEntities = new ArrayList<>(2);
        questionsEntities.add(this.questionsSupport.getEntityFixtureMock(0));
        questionsEntities.add(this.questionsSupport.getEntityFixtureMock(1));
        return questionsEntities;
    }

    private List<QuestionModelInterface> getQuestionsFixturesModels() {
        final List<QuestionModelInterface> questionsModels = new ArrayList<>(2);
        questionsModels.add(this.questionsSupport.getModelFixtureMock(0));
        questionsModels.add(this.questionsSupport.getModelFixtureMock(1));
        return questionsModels;
    }

    private void assertServicesSet(QuestionsOptionsInterface questionsOptions) throws Exception {
        this.assertServicesSet(questionsOptions, 1);
    }

    private void assertServicesSet(QuestionsOptionsInterface questionsOptions, Integer times) {
        verify(questionsOptions, times(times)).setQuestionsService(this.questionsService);
        verify(questionsOptions, times(times)).setAnswersService(any(AnswersServiceInterface.class));
        verify(questionsOptions, times(times)).setTopicsService(any(TopicsServiceInterface.class));
        verify(questionsOptions, times(times)).setQuestionTypesService(any(QuestionTypesServiceInterface.class));
    }

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<QuestionEntity> questionsEntities = this.getQuestionsFixturesEntities();
        when(this.questionsMapper.findAll()).thenReturn(questionsEntities);

        final List<QuestionModelInterface> questionsModels = this.questionsService.findAll();

        this.questionsSupport.assertModelsListEquals(this.getQuestionsFixturesModels(), questionsModels);
    }

    @Test
    public void testFindAllAbsentList() throws Exception {
        when(this.questionsMapper.findAll()).thenReturn(new ArrayList<>(0));

        final List<QuestionModelInterface> questionsModels = this.questionsService.findAll();

        Assert.assertEquals(0, questionsModels.size());
    }

    @Test
    public void testFindAllWithOptions() throws Exception {
        final ArgumentCaptor<List> listCaptor = ArgumentCaptor.forClass(List.class);
        final List<QuestionEntity> questionsEntities = this.getQuestionsFixturesEntities();
        final List<QuestionModelInterface> questionsModels = this.getQuestionsFixturesModels();
        final QuestionsOptionsInterface questionsOptions = mock(QuestionsOptionsInterface.class);
        when(this.questionsMapper.findAll()).thenReturn(questionsEntities);
        when(questionsOptions.withRelations(listCaptor.capture())).thenReturn(questionsModels);

        final List<QuestionModelInterface> questionsFoundedModels = this.questionsService.findAll(questionsOptions);

        this.assertServicesSet(questionsOptions);
        this.questionsSupport.assertModelsListEquals(questionsModels, listCaptor.getValue());
        Assert.assertSame(questionsModels, questionsFoundedModels);
    }

    @Test
    public void testFindPresentModel() throws Exception {
        final QuestionEntity questionEntity = this.questionsSupport.getEntityFixtureMock(0);
        when(this.questionsMapper.find(questionEntity.getId())).thenReturn(questionEntity);

        final QuestionModelInterface questionFoundedModel = this.questionsService.find(questionEntity.getId());

        this.questionsSupport.assertEquals(this.questionsSupport.getModelFixtureMock(0), questionFoundedModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        when(this.questionsMapper.find(id)).thenReturn(null);

        final QuestionModelInterface questionFoundedModel = this.questionsService.find(id);

        Assert.assertNull(questionFoundedModel);
    }

    @Test
    public void testFindWithOptions() throws Exception {
        final ArgumentCaptor<QuestionModelInterface> questionModelCaptor = ArgumentCaptor.forClass(QuestionModelInterface.class);
        final QuestionEntity questionEntity = this.questionsSupport.getEntityFixtureMock(0);
        final QuestionModelInterface questionModel = this.questionsSupport.getModelFixtureMock(0);
        final QuestionsOptionsInterface questionsOptions = Mockito.mock(QuestionsOptionsInterface.class);
        when(this.questionsMapper.find(questionModel.getId())).thenReturn(questionEntity);
        when(questionsOptions.withRelations(questionModelCaptor.capture())).thenReturn(questionModel);

        final QuestionModelInterface questionFoundedModel = this.questionsService.find(questionModel.getId(), questionsOptions);

        this.assertServicesSet(questionsOptions);
        this.questionsSupport.assertEquals(questionModel, questionModelCaptor.getValue());
        Assert.assertSame(questionModel, questionFoundedModel);
    }

    @Test
    public void testFindByTopicPresentList() throws Exception {
        final TopicModelInterface topicModel = this.topicsSupport.getModelFixtureMock(0);
        final List<QuestionEntity> questionEntities = this.getQuestionsFixturesEntities();
        when(this.questionsMapper.findByTopicId(topicModel.getId())).thenReturn(questionEntities);

        final List<QuestionModelInterface> questionsFoundedModels = this.questionsService.findByTopic(topicModel);

        this.questionsSupport.assertModelsListEquals(this.getQuestionsFixturesModels(), questionsFoundedModels);
    }

    @Test
    public void testFindByTopicAbsentList() throws Exception {
        final TopicModelInterface topicModel = this.topicsSupport.getModelFixtureMock(0);
        when(this.questionsMapper.findByTopicId(topicModel.getId())).thenReturn(new ArrayList<>(0));

        final List<QuestionModelInterface> questionsFoundedModels = this.questionsService.findByTopic(topicModel);

        Assert.assertEquals(0, questionsFoundedModels.size());
    }

    @Test
    public void testFindByTopicWithOptions() throws Exception {
        final ArgumentCaptor<List> listCaptor = ArgumentCaptor.forClass(List.class);
        final TopicModelInterface topicModel = this.topicsSupport.getModelFixtureMock(0);
        final List<QuestionEntity> questionsEntities = this.getQuestionsFixturesEntities();
        when(this.questionsMapper.findByTopicId(topicModel.getId())).thenReturn(questionsEntities);
        final List<QuestionModelInterface> questionsModels = this.getQuestionsFixturesModels();
        final QuestionsOptionsInterface questionsOptions = Mockito.mock(QuestionsOptionsInterface.class);
        when(questionsOptions.withRelations(listCaptor.capture())).thenReturn(questionsModels);

        final List<QuestionModelInterface> questionsFoundedModels = this.questionsService.findByTopic(topicModel, questionsOptions);

        this.assertServicesSet(questionsOptions);
        this.questionsSupport.assertModelsListEquals(questionsModels, listCaptor.getValue());
        Assert.assertSame(questionsModels, questionsFoundedModels);
    }

    @Test
    public void testSaveCreatesEntity() throws Exception {
        final ArgumentCaptor<QuestionEntity> questionEntityCaptor = ArgumentCaptor.forClass(QuestionEntity.class);
        final QuestionModelInterface questionModel = this.questionsSupport.getModelAdditionalMock(0);

        this.questionsService.save(questionModel);

        verify(this.questionsMapper, times(1)).insert(questionEntityCaptor.capture());
        this.questionsSupport.assertEquals(this.questionsSupport.getEntityAdditionalMock(0), questionEntityCaptor.getValue());
    }

    @Test
    public void testSaveUpdateEntityIdOnCreation() throws Exception {
        final Integer id = 7;
        final QuestionModelInterface questionModel = this.questionsSupport.getModelAdditionalMock(0);
        doAnswer(invocation -> {
            final QuestionEntity questionEntity = (QuestionEntity) invocation.getArguments()[0];
            questionEntity.setId(id);
            return null;
        }).when(this.questionsMapper).insert(any());

        this.questionsService.save(questionModel);

        verify(questionModel, times(1)).setId(id);
    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {
        final ArgumentCaptor<QuestionEntity> questionEntityCaptor = ArgumentCaptor.forClass(QuestionEntity.class);
        final QuestionModelInterface questionModel = this.questionsSupport.getModelFixtureMock(0);

        this.questionsService.save(questionModel);

        verify(this.questionsMapper, times(1)).update(questionEntityCaptor.capture());
        this.questionsSupport.assertEquals(this.questionsSupport.getEntityFixtureMock(0), questionEntityCaptor.getValue());
    }

    @Test
    public void testSaveWithOptions() throws Exception {
        final QuestionModelInterface questionModel = this.questionsSupport.getModelFixtureMock(0);
        final QuestionsOptionsInterface questionsOptions = Mockito.mock(QuestionsOptionsInterface.class);

        this.questionsService.save(questionModel, questionsOptions);

        this.assertServicesSet(questionsOptions);
        verify(questionsOptions, times(1)).saveWithRelations(questionModel);
        verifyNoMoreInteractions(this.questionsMapper);
    }

    @Test
    public void testSaveModelsList() throws Exception {
        final ArgumentCaptor<QuestionEntity> questionEntityCaptor = ArgumentCaptor.forClass(QuestionEntity.class);
        final List<QuestionModelInterface> questionsModels = this.getQuestionsFixturesModels();

        this.questionsService.save(questionsModels);

        verify(this.questionsMapper, times(questionsModels.size())).update(questionEntityCaptor.capture());
        this.questionsSupport.assertEntitiesListEquals(this.getQuestionsFixturesEntities(), questionEntityCaptor.getAllValues());
    }

    @Test
    public void testSaveModelsListWithOptions() throws Exception {
        final ArgumentCaptor<QuestionModelInterface> questionsModelCaptor = ArgumentCaptor.forClass(QuestionModelInterface.class);
        final List<QuestionModelInterface> questionsModels = this.getQuestionsFixturesModels();
        final QuestionsOptionsInterface questionsOptions = Mockito.mock(QuestionsOptionsInterface.class);

        this.questionsService.save(questionsModels, questionsOptions);

        this.assertServicesSet(questionsOptions, questionsModels.size());
        verify(questionsOptions, times(questionsModels.size())).saveWithRelations(questionsModelCaptor.capture());
        this.questionsSupport.assertModelsListEquals(questionsModels, questionsModelCaptor.getAllValues());
        verifyNoMoreInteractions(this.questionsMapper);
    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {
        final ArgumentCaptor<QuestionEntity> questionEntityCaptor = ArgumentCaptor.forClass(QuestionEntity.class);

        this.questionsService.delete(this.questionsSupport.getModelFixtureMock(0));

        verify(this.questionsMapper, times(1)).delete(questionEntityCaptor.capture());
        this.questionsSupport.assertEquals(this.questionsSupport.getEntityFixtureMock(0), questionEntityCaptor.getValue());
    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {
        final QuestionModelInterface questionModel = this.questionsSupport.getModelAdditionalMock(0);

        exception.expect(DeleteUnidentifiedModelException.class);
        this.questionsService.delete(questionModel);
    }

    @Test
    public void testDeleteWithOptions() throws Exception {
        final QuestionModelInterface questionModel = this.questionsSupport.getModelFixtureMock(0);
        final QuestionsOptionsInterface questionsOptions = Mockito.mock(QuestionsOptionsInterface.class);

        this.questionsService.delete(questionModel, questionsOptions);

        this.assertServicesSet(questionsOptions);
        verify(questionsOptions, times(1)).deleteWithRelations(questionModel);
        verifyNoMoreInteractions(this.questionsMapper);
    }

    @Test
    public void testDeleteModelsList() throws Exception {
        final ArgumentCaptor<QuestionEntity> questionEntityCaptor = ArgumentCaptor.forClass(QuestionEntity.class);
        final List<QuestionModelInterface> questionsModels = this.getQuestionsFixturesModels();

        this.questionsService.delete(questionsModels);

        verify(this.questionsMapper, times(questionsModels.size())).delete(questionEntityCaptor.capture());
        this.questionsSupport.assertEntitiesListEquals(this.getQuestionsFixturesEntities(), questionEntityCaptor.getAllValues());
    }

    @Test
    public void testDeleteModelsListWithOptions() throws Exception {
        final ArgumentCaptor<QuestionModelInterface> questionModelCaptor = ArgumentCaptor.forClass(QuestionModelInterface.class);
        final List<QuestionModelInterface> questionsModels = this.getQuestionsFixturesModels();
        final QuestionsOptionsInterface questionsOptions = Mockito.mock(QuestionsOptionsInterface.class);

        this.questionsService.delete(questionsModels, questionsOptions);

        this.assertServicesSet(questionsOptions, questionsModels.size());
        verify(questionsOptions, times(questionsModels.size())).deleteWithRelations(questionModelCaptor.capture());
        this.questionsSupport.assertModelsListEquals(questionsModels, questionModelCaptor.getAllValues());
        verifyNoMoreInteractions(this.questionsMapper);
    }
}
