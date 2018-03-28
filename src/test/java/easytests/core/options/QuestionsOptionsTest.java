package easytests.core.options;

import easytests.core.models.AnswerModelInterface;
import easytests.core.models.QuestionTypeModelInterface;
import easytests.core.models.TopicModelInterface;
import easytests.core.models.QuestionModelInterface;
import easytests.core.models.empty.QuestionTypeModelEmpty;
import easytests.core.models.empty.TopicModelEmpty;
import easytests.core.services.AnswersServiceInterface;
import easytests.core.services.QuestionTypesServiceInterface;
import easytests.core.services.TopicsServiceInterface;
import easytests.core.services.QuestionsServiceInterface;
import easytests.support.QuestionsSupport;
import easytests.support.QuestionTypesSupport;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.BDDMockito.when;
import static org.mockito.BDDMockito.given;
import org.mockito.InOrder;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author Risa_Magpie
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionsOptionsTest {

    private QuestionsSupport questionsSupport = new QuestionsSupport();

    private QuestionsOptionsInterface questionsOptions;

    private QuestionsServiceInterface questionsService;

    private QuestionModelInterface questionModel;

    private List<QuestionModelInterface> questionsModels;

    private AnswersServiceInterface answersService;

    private AnswersOptionsInterface answersOptions;

    private List<AnswerModelInterface> answersModels;

    private List<List<AnswerModelInterface>> answersModelsLists;

    private QuestionTypesServiceInterface questionTypesService;

    private QuestionTypesOptionsInterface questionTypesOptions;

    private QuestionTypeModelInterface questionTypesModel;



    private TopicsServiceInterface topicsService;

    private TopicsOptionsInterface topicsOptions;

    private TopicModelInterface topicsModels;

    private List<List<TopicModelInterface>> topicsModelsLists;

    private ArgumentCaptor<List> listCaptor;

    @Before
    public void before() {
        this.answersService = Mockito.mock(AnswersServiceInterface.class);
        this.answersOptions = Mockito.mock(AnswersOptionsInterface.class);
        this.questionTypesService = Mockito.mock(QuestionTypesServiceInterface.class);
        this.questionTypesOptions = Mockito.mock(QuestionTypesOptionsInterface.class);
        this.topicsService = Mockito.mock(TopicsServiceInterface.class);
        this.topicsOptions = Mockito.mock(TopicsOptionsInterface.class);
        this.questionsService = Mockito.mock(QuestionsServiceInterface.class);

        this.questionsOptions = new QuestionsOptions();
        this.questionsOptions.setQuestionsService(this.questionsService);
        this.questionsOptions.setAnswersService(this.answersService);
        this.questionsOptions.setQuestionTypesService(this.questionTypesService);
        this.questionsOptions.setTopicsService(this.topicsService);

        this.listCaptor = ArgumentCaptor.forClass(List.class);
    }

    private QuestionsOptionsTest withQuestionModel() {
        this.questionModel = this.questionsSupport.getModelFixtureMock(0);
        return this;
    }

    private QuestionsOptionsTest withAnswersModelsFounded() {
        this.answersModels = new ArrayList<>();
        when(this.answersService.findByQuestion(this.questionModel, this.answersOptions)).thenReturn(this.answersModels);
        return this;
    }

    private QuestionsOptionsTest withAnswersModelsInjected() {
        this.answersModels = new ArrayList<>();
        when(this.questionModel.getAnswers()).thenReturn(this.answersModels);
        return this;
    }


    private QuestionsOptionsTest withAnswers() {
        this.questionsOptions.withAnswers(this.answersOptions);
        return this;
    }


    private QuestionsOptionsTest withQuestionType() {
        this.questionsOptions.withQuestionType(this.questionTypesOptions);
        return this;
    }


    private QuestionsOptionsTest withTopic() {
        this.questionsOptions.withTopic(this.topicsOptions);
        return this;
    }


    private QuestionsOptionsTest withQuestionsList() {
        this.questionsModels = new ArrayList<>(2);
        this.questionsModels.add(this.questionsSupport.getModelFixtureMock(0));
        this.questionsModels.add(this.questionsSupport.getModelFixtureMock(1));

        return this;
    }

    private QuestionsOptionsTest withAnswersModelsListsFounded() {
        this.answersModelsLists = new ArrayList<>(2);
        this.answersModelsLists.add(new ArrayList<>());
        this.answersModelsLists.add(new ArrayList<>());
        when(this.answersService.findByQuestion(this.questionsModels.get(0), this.answersOptions)).thenReturn(answersModelsLists.get(0));
        when(this.answersService.findByQuestion(this.questionsModels.get(1), this.answersOptions)).thenReturn(answersModelsLists.get(1));

        return this;
    }
    /**/

    private QuestionsOptionsTest withQuestionTypesModelInjected() {
        when(this.questionModel.getQuestionType()).thenReturn(this.questionTypesModel);
        return this;
    }

    private QuestionsOptionsTest withTopicsModelsInjected() {
        this.topicsModelsLists = new ArrayList<>(2);
        this.topicsModelsLists.add(new ArrayList<>());
        this.topicsModelsLists.add(new ArrayList<>());
        when(this.questionModel.getTopic()).thenReturn(this.topicsModels);
        return this;
    }
/**/
    @Test
    public void testWithNoRelations() throws Exception {
        this.withQuestionModel().withAnswersModelsFounded();

        final QuestionModelInterface questionModelWithRelations = this.questionsOptions.withRelations(this.questionModel);

        Assert.assertSame(questionModel, questionModelWithRelations);
        verify(this.answersService, times(0)).findByQuestion(any(), any());
        verify(this.questionModel, times(0)).setAnswers(anyList());
        verify(this.questionModel, times(0)).setQuestionType(any());
        verify(this.questionModel, times(0)).setTopic(any());
    }

    @Test
    public void testWithAnswersRelations() throws Exception {
        this.withQuestionModel().withAnswersModelsFounded().withAnswers();

        final QuestionModelInterface questionModelWithRelations = this.questionsOptions.withRelations(this.questionModel);

        Assert.assertSame(questionModel, questionModelWithRelations);
        verify(this.answersService, times(1)).findByQuestion(this.questionModel, this.answersOptions);
        verify(this.questionModel, times(1)).setAnswers(this.listCaptor.capture());
        Assert.assertSame(this.answersModels, this.listCaptor.getValue());
    }

    @Test
    public void testWithRelationsOnNull() throws Exception {
        final QuestionModelInterface questionModel = null;

        final QuestionModelInterface questionModelWithRelations = this.questionsOptions.withRelations(questionModel);

        Assert.assertSame(null, questionModelWithRelations);
    }

    @Test
    public void testWithNoRelationsOnModelsList() throws Exception {
        this.withQuestionsList().withAnswersModelsListsFounded();

        final List<QuestionModelInterface> questionsModelsWithRelations = this.questionsOptions.withRelations(this.questionsModels);

        Assert.assertSame(questionsModelsWithRelations, this.questionsModels);
        for (Integer i = 0; i < 2; i++) {
            verify(this.answersService, times(0)).findByQuestion(any(), any());
            verify(this.questionsModels.get(i), times(0)).setAnswers(anyList());
        }
    }

    @Test
    public void testWithAnswersRelationsOnModelsList() throws Exception {
        this.withQuestionsList().withAnswersModelsListsFounded().withAnswers();

        final List<QuestionModelInterface> questionsModelsWithRelations = this.questionsOptions.withRelations(this.questionsModels);

        Assert.assertSame(questionsModelsWithRelations, this.questionsModels);
        for (Integer i = 0; i < 2; i++) {
            verify(this.answersService, times(1)).findByQuestion(this.questionsModels.get(i), this.answersOptions);
            verify(this.questionsModels.get(i), times(1)).setAnswers(this.listCaptor.capture());
            Assert.assertSame(this.answersModelsLists.get(i), this.listCaptor.getValue());
        }
    }

    @Test
    public void testSaveWithNoRelations() throws Exception {
        this.withQuestionModel().withAnswersModelsInjected();

        this.questionsOptions.saveWithRelations(this.questionModel);

        verify(this.questionsService, times(1)).save(this.questionModel);
        verify(this.answersService, times(0)).save(anyList(), any());
    }

    @Test
    public void testSaveWithAnswersRelations() throws Exception {
        this.withQuestionModel().withAnswersModelsInjected().withAnswers();
        final ArgumentCaptor<AnswersOptionsInterface> answersOptionsCaptor = ArgumentCaptor.forClass(AnswersOptionsInterface.class);;


        this.questionsOptions.saveWithRelations(this.questionModel);

        verify(this.questionsService, times(1)).save(this.questionModel);
        verify(this.answersService, times(1)).save(this.listCaptor.capture(), answersOptionsCaptor.capture());
        Assert.assertSame(this.answersModels, this.listCaptor.getValue());
        Assert.assertSame(this.answersOptions, answersOptionsCaptor.getValue());
    }

    @Test
    public void testSaveWithTopicRelations() throws Exception {
        this.withQuestionModel().withTopicsModelsInjected().withTopic();
        final ArgumentCaptor<TopicsOptionsInterface> topicsOptionsCaptor = ArgumentCaptor.forClass(TopicsOptionsInterface.class);;


        this.questionsOptions.saveWithRelations(this.questionModel);

        verify(this.questionsService, times(1)).save(this.questionModel);
        verify(this.topicsService, times(1)).save(this.listCaptor.capture(), topicsOptionsCaptor.capture());
        Assert.assertSame(this.topicsModels, this.listCaptor.getValue());
        Assert.assertSame(this.topicsOptions, topicsOptionsCaptor.getValue());
    }

    @Test
    public void testSaveWithAllRelationsOrder() throws Exception {
        //this.withQuestionModel().withTopicsModelInjected().withTopic();
        this.withQuestionModel().withAnswersModelsInjected().withAnswers();
        //this.withQuestionModel().withQuestionTypesModelInjected().withQuestionType();

        final InOrder inOrder = inOrder(this.answersService);
//, this.questionTypesService, this.topicsService

        this.questionsOptions.saveWithRelations(this.questionModel);

        inOrder.verify(this.questionsService, times(1)).save(any(QuestionModelInterface.class));
        inOrder.verify(this.topicsService,times(1)).save(any(TopicModelInterface.class));
        inOrder.verify(this.answersService, times(1)).save(anyList(),any());

    }

    @Test
    public void testDeleteWithNoRelations() throws Exception {
        this.withQuestionModel().withAnswersModelsInjected();

        this.questionsOptions.deleteWithRelations(this.questionModel);

        verify(this.questionsService, times(1)).delete(this.questionModel);
        verify(this.answersService, times(0)).delete(this.answersModels, this.answersOptions);
    }

    @Test
    public void testDeleteWithAnswersRelations() throws Exception {
        this.withQuestionModel().withAnswersModelsInjected().withAnswers();
        final ArgumentCaptor<AnswersOptionsInterface> answersOptionsCaptor = ArgumentCaptor.forClass(AnswersOptionsInterface.class);;

        this.questionsOptions.deleteWithRelations(this.questionModel);

        verify(this.questionsService, times(1)).delete(this.questionModel);
        verify(this.answersService, times(1)).delete(this.listCaptor.capture(), answersOptionsCaptor.capture());
        Assert.assertSame(this.answersModels, this.listCaptor.getValue());
        Assert.assertSame(this.answersOptions, answersOptionsCaptor.getValue());
    }

    @Test
    public void testDeleteWithAllRelationsOrder() throws Exception {
        this.withQuestionModel().withAnswersModelsInjected().withAnswers();
        final InOrder inOrder = inOrder(this.answersService, this.answersService);

        this.questionsOptions.deleteWithRelations(this.questionModel);

        inOrder.verify(this.answersService, times(1)).delete(anyList(), any());
        inOrder.verify(this.questionsService, times(1)).delete(anyList(),any());
    }
}

/*
    ////////////////////////

    @Test
    public void testWithRelationsOnNull() throws Exception {
        final QuestionModelInterface questionModel = null;
        final QuestionsOptionsInterface questionsOptions = new QuestionsOptions();
        final AnswersServiceInterface answersService = Mockito.mock(AnswersServiceInterface.class);
        final AnswersOptionsInterface answersOptions = Mockito.mock(AnswersOptionsInterface.class);
        final TopicsServiceInterface topicsService = Mockito.mock(TopicsServiceInterface.class);
        final TopicsOptionsInterface topicsOptions = Mockito.mock(TopicsOptionsInterface.class);
        final QuestionTypesServiceInterface questionTypesService = Mockito.mock(QuestionTypesServiceInterface.class);
        final QuestionTypesOptionsInterface questionTypesOptions = Mockito.mock(QuestionTypesOptionsInterface.class);
        questionsOptions.setAnswersService(answersService);
        questionsOptions.withAnswers(answersOptions);
        questionsOptions.setTopicsService(topicsService);
        questionsOptions.withTopic(topicsOptions);
        questionsOptions.setQuestionTypesService(questionTypesService);
        questionsOptions.withQuestionType(questionTypesOptions);

        final QuestionModelInterface questionModelWithRelations = questionsOptions.withRelations(questionModel);

        Assert.assertEquals(null, questionModelWithRelations);
    }

    @Test
    public void testWithRelationsOnModelsList() throws Exception {
        final QuestionModelInterface questionModelFirst = Mockito.mock(QuestionModelInterface.class);
        questionModelFirst.setId(1);
        given(questionModelFirst.getTopic()).willReturn(new TopicModelEmpty(1));
        given(questionModelFirst.getQuestionType()).willReturn(new QuestionTypeModelEmpty(1));
        final QuestionModelInterface questionModelSecond = Mockito.mock(QuestionModelInterface.class);
        questionModelSecond.setId(2);
        given(questionModelSecond.getTopic()).willReturn(new TopicModelEmpty(2));
        given(questionModelSecond.getQuestionType()).willReturn(new QuestionTypeModelEmpty(2));
        final List<QuestionModelInterface> questionsModels = new ArrayList<>(2);
        questionsModels.add(questionModelFirst);
        questionsModels.add(questionModelSecond);

        final QuestionsOptionsInterface questionsOptions = new QuestionsOptions();
        final AnswersServiceInterface answersService = Mockito.mock(AnswersServiceInterface.class);
        final AnswersOptionsInterface answersOptions = Mockito.mock(AnswersOptionsInterface.class);
        final TopicsServiceInterface topicsService = Mockito.mock(TopicsServiceInterface.class);
        final TopicsOptionsInterface topicsOptions = Mockito.mock(TopicsOptionsInterface.class);
        final QuestionTypesServiceInterface questionTypesService = Mockito.mock(QuestionTypesServiceInterface.class);
        final QuestionTypesOptionsInterface questionTypesOptions = Mockito.mock(QuestionTypesOptionsInterface.class);
        questionsOptions.setAnswersService(answersService);
        questionsOptions.withAnswers(answersOptions);
        questionsOptions.setTopicsService(topicsService);
        questionsOptions.withTopic(topicsOptions);
        questionsOptions.setQuestionTypesService(questionTypesService);
        questionsOptions.withQuestionType(questionTypesOptions);
        final List<AnswerModelInterface> answersModelsFirst = new ArrayList<>();
        answersModelsFirst.add(Mockito.mock(AnswerModelInterface.class));
        final List<AnswerModelInterface> answersModelsSecond = new ArrayList<>();
        answersModelsSecond.add(Mockito.mock(AnswerModelInterface.class));
        TopicModelInterface topicModelFirst = Mockito.mock(TopicModelInterface.class);
        TopicModelInterface topicModelSecond = Mockito.mock(TopicModelInterface.class);
        QuestionTypeModelInterface questionTypeModelFirst = Mockito.mock(QuestionTypeModelInterface.class);
        QuestionTypeModelInterface questionTypeModelSecond = Mockito.mock(QuestionTypeModelInterface.class);
        given(answersService.findByQuestion(questionModelFirst, answersOptions)).willReturn(answersModelsFirst);
        given(answersService.findByQuestion(questionModelSecond, answersOptions)).willReturn(answersModelsSecond);
        given(topicsService.find(questionModelFirst.getTopic().getId(), topicsOptions)).willReturn(topicModelFirst);
        given(topicsService.find(questionModelSecond.getTopic().getId(), topicsOptions)).willReturn(topicModelSecond);
        given(questionTypesService.find(questionModelFirst.getQuestionType().getId(), questionTypesOptions)).willReturn(questionTypeModelFirst);
        given(questionTypesService.find(questionModelSecond.getQuestionType().getId(), questionTypesOptions)).willReturn(questionTypeModelSecond);

        final List<QuestionModelInterface> questionsModelsWithRelations = questionsOptions.withRelations(questionsModels);

        Assert.assertEquals(questionsModelsWithRelations, questionsModels);
        verify(answersService).findByQuestion(questionModelFirst, answersOptions);
        verify(questionsModels.get(0)).setAnswers(answersModelsFirst);
        verify(questionsModels.get(0)).setAnswers(Mockito.anyList());
        verify(answersService).findByQuestion(questionModelSecond, answersOptions);
        verify(questionsModels.get(1)).setAnswers(answersModelsSecond);
        verify(questionsModels.get(1)).setAnswers(Mockito.anyList());
        verify(topicsService).find(questionModelFirst.getTopic().getId(), topicsOptions);
        verify(topicsService).find(questionModelSecond.getTopic().getId(), topicsOptions);
        verify(questionsModels.get(0)).setTopic(topicModelFirst);
        verify(questionsModels.get(1)).setTopic(topicModelSecond);
        verify(questionTypesService).find(questionModelFirst.getQuestionType().getId(), questionTypesOptions);
        verify(questionTypesService).find(questionModelSecond.getQuestionType().getId(), questionTypesOptions);
        verify(questionModelFirst).setQuestionType(questionTypeModelFirst);
        verify(questionModelSecond).setQuestionType(questionTypeModelSecond);
    }

    @Test
    public void testSaveWithRelations() throws Exception {
        final QuestionModelInterface questionModel = Mockito.mock(QuestionModelInterface.class);
        final QuestionsOptionsInterface questionsOptions = new QuestionsOptions();
        final QuestionsServiceInterface questionsService = Mockito.mock(QuestionsServiceInterface.class);
        final AnswersServiceInterface answersService = Mockito.mock(AnswersServiceInterface.class);
        final AnswersOptionsInterface answersOptions = Mockito.mock(AnswersOptionsInterface.class);
        final TopicsServiceInterface topicsService = Mockito.mock(TopicsServiceInterface.class);
        final TopicsOptionsInterface topicsOptions = Mockito.mock(TopicsOptionsInterface.class);
        questionsOptions.setQuestionsService(questionsService);
        questionsOptions.setAnswersService(answersService);
        questionsOptions.withAnswers(answersOptions);
        questionsOptions.setTopicsService(topicsService);
        questionsOptions.withTopic(topicsOptions);
        final List<AnswerModelInterface> answersModels = new ArrayList<>();
        answersModels.add(Mockito.mock(AnswerModelInterface.class));
        TopicModelInterface topicModel = Mockito.mock(TopicModelInterface.class);
        given(questionModel.getAnswers()).willReturn(answersModels);
        given(questionModel.getTopic()).willReturn(topicModel);
        final InOrder inOrder = Mockito.inOrder(topicsService, questionsService, answersService);

        questionsOptions.saveWithRelations(questionModel);

        inOrder.verify(topicsService).save(topicModel, topicsOptions);
        inOrder.verify(questionsService).save(questionModel);
        inOrder.verify(answersService).save(answersModels, answersOptions);
    }

    @Test
    public void testDeleteWithRelations() throws Exception {
        final QuestionModelInterface questionModel = Mockito.mock(QuestionModelInterface.class);
        final QuestionsOptionsInterface questionsOptions = new QuestionsOptions();
        final QuestionsServiceInterface questionsService = Mockito.mock(QuestionsServiceInterface.class);
        final AnswersServiceInterface answersService = Mockito.mock(AnswersServiceInterface.class);
        final AnswersOptionsInterface answersOptions = Mockito.mock(AnswersOptionsInterface.class);
        final TopicsServiceInterface topicsService = Mockito.mock(TopicsServiceInterface.class);
        final TopicsOptionsInterface topicsOptions = Mockito.mock(TopicsOptionsInterface.class);
        questionsOptions.setQuestionsService(questionsService);
        questionsOptions.setAnswersService(answersService);
        questionsOptions.withAnswers(answersOptions);
        questionsOptions.setTopicsService(topicsService);
        questionsOptions.withTopic(topicsOptions);
        final List<AnswerModelInterface> answersModels = new ArrayList<>();
        answersModels.add(Mockito.mock(AnswerModelInterface.class));
        TopicModelInterface topicModel = Mockito.mock(TopicModelInterface.class);
        given(questionModel.getAnswers()).willReturn(answersModels);
        given(questionModel.getTopic()).willReturn(topicModel);
        final InOrder inOrder = Mockito.inOrder(answersService, questionsService, topicsService);

        questionsOptions.deleteWithRelations(questionModel);

        inOrder.verify(answersService).delete(answersModels, answersOptions);
        inOrder.verify(questionsService).delete(questionModel);
        inOrder.verify(topicsService).delete(topicModel, topicsOptions);
    }
}
*/