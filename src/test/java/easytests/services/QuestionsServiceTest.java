package easytests.services;

import easytests.entities.QuestionEntity;
import easytests.mappers.QuestionsMapper;
import easytests.models.QuestionModel;
import easytests.models.QuestionModelInterface;
import easytests.models.TopicModelInterface;
import easytests.models.empty.ModelsListEmpty;
import easytests.options.QuestionsOptionsInterface;
import easytests.services.exceptions.DeleteUnidentifiedModelException;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.*;
import static org.mockito.BDDMockito.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

/**
 * @author firkhraag
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

    private QuestionModelInterface createQuestionModel(Integer id, String text, Integer type,  Integer topicId) {
        final QuestionModelInterface questionModel = new QuestionModel();
        final TopicModelInterface topicModel = Mockito.mock(TopicModelInterface.class);
        Mockito.when(topicModel.getId()).thenReturn(topicId);
        questionModel.setId(id);
        questionModel.setText(text);
        questionModel.setType(type);
        questionModel.setTopic(topicModel);
        questionModel.setAnswers(new ModelsListEmpty());
        return questionModel;
    }

    private QuestionEntity createQuestionEntityMock(Integer id, String text, Integer type, Integer topicId) {
        final QuestionEntity questionEntity = Mockito.mock(QuestionEntity.class);
        Mockito.when(questionEntity.getId()).thenReturn(id);
        Mockito.when(questionEntity.getText()).thenReturn(text);
        Mockito.when(questionEntity.getType()).thenReturn(type);
        Mockito.when(questionEntity.getTopicId()).thenReturn(topicId);
        return questionEntity;
    }

    private QuestionModelInterface mapQuestionModel(QuestionEntity questionEntity) {
        final QuestionModelInterface questionModel = new QuestionModel();
        questionModel.map(questionEntity);
        return questionModel;
    }

    private QuestionEntity mapQuestionEntity(QuestionModelInterface questionModel) {
        final QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.map(questionModel);
        return questionEntity;
    }

    private List<QuestionEntity> getQuestionsEntities() {
        final List<QuestionEntity> questionsEntities = new ArrayList<>(2);
        final QuestionEntity questionEntityFirst = this.createQuestionEntityMock(1, "test1", 1, 1);
        final QuestionEntity questionEntitySecond = this.createQuestionEntityMock(2, "test2", 2, 1);
        questionsEntities.add(questionEntityFirst);
        questionsEntities.add(questionEntitySecond);
        return questionsEntities;
    }

    private List<QuestionModelInterface> getQuestionsModels() {
        final List<QuestionModelInterface> questionsModels = new ArrayList<>(2);
        for (QuestionEntity questionEntity: this.getQuestionsEntities()) {
            questionsModels.add(this.mapQuestionModel(questionEntity));
        }
        return questionsModels;
    }

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<QuestionEntity> questionsEntities = this.getQuestionsEntities();
        given(this.questionsMapper.findAll()).willReturn(questionsEntities);

        final List<QuestionModelInterface> questionsModels = this.questionsService.findAll();

        Assert.assertEquals(this.getQuestionsModels(), questionsModels);
    }

    @Test
    public void testFindAllAbsentList() throws Exception {
        given(this.questionsMapper.findAll()).willReturn(new ArrayList<>(0));

        final List<QuestionModelInterface> questionsModels = this.questionsService.findAll();

        Assert.assertEquals(0, questionsModels.size());
    }

    @Test
    public void testFindAllWithOptions() throws Exception {
        final List<QuestionEntity> questionsEntities = this.getQuestionsEntities();
        final List<QuestionModelInterface> questionsModels = this.getQuestionsModels();
        final QuestionsOptionsInterface questionsOptions = Mockito.mock(QuestionsOptionsInterface.class);
        given(this.questionsMapper.findAll()).willReturn(questionsEntities);
        given(questionsOptions.withRelations(Mockito.anyList())).willReturn(questionsModels);

        final List<QuestionModelInterface> foundedQuestionsModels = this.questionsService.findAll(questionsOptions);

        verify(questionsOptions).withRelations(questionsModels);
        Assert.assertEquals(questionsModels, foundedQuestionsModels);
    }

    @Test
    public void testFindPresentModel() throws Exception {
        final Integer id = 1;
        final QuestionEntity questionEntity = this.createQuestionEntityMock(id, "NewText", 1, 1);
        given(this.questionsMapper.find(id)).willReturn(questionEntity);

        final QuestionModelInterface questionModel = this.questionsService.find(id);

        Assert.assertEquals(this.mapQuestionModel(questionEntity), questionModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        given(this.questionsMapper.find(id)).willReturn(null);

        final QuestionModelInterface questionModel = this.questionsService.find(id);

        Assert.assertEquals(null, questionModel);
    }

    @Test
    public void testFindWithOptions() throws Exception {
        final Integer id = 1;
        final QuestionEntity questionEntity = this.createQuestionEntityMock(id, "NewText", 1, 1);
        final QuestionModelInterface questionModel = this.mapQuestionModel(questionEntity);
        final QuestionsOptionsInterface questionsOptions = Mockito.mock(QuestionsOptionsInterface.class);
        given(this.questionsMapper.find(id)).willReturn(questionEntity);
        given(questionsOptions.withRelations(questionModel)).willReturn(questionModel);

        final QuestionModelInterface foundedQuestionModel = this.questionsService.find(id, questionsOptions);

        Assert.assertEquals(questionModel, foundedQuestionModel);
        verify(questionsOptions).withRelations(questionModel);
    }

    @Test
    public void testFindByTopic() throws Exception {
        final Integer topicId = 7;
        final TopicModelInterface topicModel = Mockito.mock(TopicModelInterface.class);
        final QuestionEntity questionEntityFirst = this.createQuestionEntityMock(3, "test3", 1, topicId);
        final QuestionEntity questionEntitySecond = this.createQuestionEntityMock(12, "test12", 1, topicId);
        final List<QuestionEntity> questionsEntities = new ArrayList<>();
        questionsEntities.add(questionEntityFirst);
        questionsEntities.add(questionEntitySecond);
        Mockito.when(topicModel.getId()).thenReturn(topicId);
        given(this.questionsMapper.findByTopicId(topicId)).willReturn(questionsEntities);
        final List<QuestionModelInterface> questionsModels = new ArrayList<>();
        questionsModels.add(this.mapQuestionModel(questionEntityFirst));
        questionsModels.add(this.mapQuestionModel(questionEntitySecond));

        final List<QuestionModelInterface> foundedQuestionsModels = this.questionsService.findByTopic(topicModel);

        verify(this.questionsMapper).findByTopicId(topicId);
        Assert.assertEquals(questionsModels, foundedQuestionsModels);
    }

    @Test
    public void testFindByTopicWithOptions() throws Exception {
        final Integer topicId = 7;
        final TopicModelInterface topicModel = Mockito.mock(TopicModelInterface.class);
        final QuestionEntity questionEntityFirst = this.createQuestionEntityMock(3, "test3", 1, topicId);
        final QuestionEntity questionEntitySecond = this.createQuestionEntityMock(12, "test12", 1, topicId);
        final List<QuestionEntity> questionsEntities = new ArrayList<>();
        questionsEntities.add(questionEntityFirst);
        questionsEntities.add(questionEntitySecond);
        given(topicModel.getId()).willReturn(topicId);
        given(this.questionsMapper.findByTopicId(topicId)).willReturn(questionsEntities);
        final List<QuestionModelInterface> questionsModels = new ArrayList<>();
        questionsModels.add(this.mapQuestionModel(questionEntityFirst));
        questionsModels.add(this.mapQuestionModel(questionEntitySecond));
        final QuestionsOptionsInterface questionOptions = Mockito.mock(QuestionsOptionsInterface.class);
        given(questionOptions.withRelations(questionsModels)).willReturn(questionsModels);

        final List<QuestionModelInterface> foundedQuestionsModels = this.questionsService.findByTopic(topicModel, questionOptions);

        verify(this.questionsMapper).findByTopicId(topicId);
        verify(questionOptions).withRelations(questionsModels);
        Assert.assertEquals(questionsModels, foundedQuestionsModels);
    }

    @Test
    public void testSaveCreatesEntity() throws Exception {
        final QuestionModelInterface questionModel = this.createQuestionModel(null, "Text", 1, 1);
        doAnswer(invocation -> {
            final QuestionEntity questionEntity = (QuestionEntity) invocation.getArguments()[0];
            questionEntity.setId(5);
            return null;
        }).when(this.questionsMapper).insert(Mockito.any(QuestionEntity.class));

        this.questionsService.save(questionModel);

        verify(this.questionsMapper, times(1)).insert(this.mapQuestionEntity(questionModel));
        Assert.assertEquals((Integer) 5, questionModel.getId());
    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {
        final QuestionModelInterface questionModel = this.createQuestionModel(1, "Text", 1, 1);

        this.questionsService.save(questionModel);

        verify(this.questionsMapper, times(1)).update(this.mapQuestionEntity(questionModel));
    }

    @Test
    public void testSaveEntitiesList() throws Exception {

        final QuestionModelInterface questionModelFirst = this.createQuestionModel(null, "Text1", 1, 1);
        final QuestionModelInterface questionModelSecond = this.createQuestionModel(null, "Text2", 2, 1);
        final QuestionsOptionsInterface questionsOptions = Mockito.mock(QuestionsOptionsInterface.class);

        final List<QuestionModelInterface> questionsModels = new ArrayList<>();
        questionsModels.add(questionModelFirst);
        questionsModels.add(questionModelSecond);

        final QuestionsServiceInterface questionsServiceSpy = Mockito.spy(questionsService);

        questionsServiceSpy.save(questionsModels);
        verify(questionsServiceSpy, times(1)).save(questionModelFirst);
        verify(questionsServiceSpy, times(1)).save(questionModelSecond);

        questionsServiceSpy.save(questionsModels, questionsOptions);
        verify(questionsServiceSpy, times(1)).save(questionModelFirst, questionsOptions);
        verify(questionsServiceSpy, times(1)).save(questionModelSecond, questionsOptions);
    }

    @Test
    public void testSaveWithOptions() throws Exception {
        final QuestionModelInterface questionModel = this.createQuestionModel(null, "Text", 1, 1);
        final QuestionsOptionsInterface questionsOptions = Mockito.mock(QuestionsOptionsInterface.class);

        this.questionsService.save(questionModel, questionsOptions);

        verify(questionsOptions).saveWithRelations(questionModel);
    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {
        final QuestionModelInterface questionModel = this.createQuestionModel(1, "Text", 1, 1);

        this.questionsService.delete(questionModel);

        verify(this.questionsMapper, times(1)).delete(this.mapQuestionEntity(questionModel));
    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {
        final QuestionModelInterface questionModel = this.createQuestionModel(null, "Text", 1, 1);

        exception.expect(DeleteUnidentifiedModelException.class);
        this.questionsService.delete(questionModel);
    }

    @Test
    public void testDeleteModelsList() throws Exception {

        final QuestionModelInterface questionModelFirst = this.createQuestionModel(1, "test1", 1, 1);
        final QuestionModelInterface questionModelSecond = this.createQuestionModel(2, "test2", 2, 1);
        final QuestionsOptionsInterface questionsOptions = Mockito.mock(QuestionsOptionsInterface.class);

        final List<QuestionModelInterface> questionsModels = new ArrayList<>();
        questionsModels.add(questionModelFirst);
        questionsModels.add(questionModelSecond);

        final QuestionsServiceInterface questionsServiceSpy = Mockito.spy(questionsService);

        questionsServiceSpy.delete(questionsModels);
        verify(questionsServiceSpy, times(1)).delete(questionModelFirst);
        verify(questionsServiceSpy, times(1)).delete(questionModelSecond);

        questionsServiceSpy.delete(questionsModels, questionsOptions);
        verify(questionsServiceSpy, times(1)).delete(questionModelFirst, questionsOptions);
        verify(questionsServiceSpy, times(1)).delete(questionModelSecond, questionsOptions);
    }

    @Test
    public void testDeleteWithOptions() throws Exception {
        final QuestionModelInterface questionModel = this.createQuestionModel(1, "Text", 1, 1);
        final QuestionsOptionsInterface questionsOptions = Mockito.mock(QuestionsOptionsInterface.class);

        this.questionsService.delete(questionModel, questionsOptions);

        verify(questionsOptions).deleteWithRelations(questionModel);
    }
}
