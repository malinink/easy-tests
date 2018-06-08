package easytests.core.options;

import easytests.core.models.*;
import easytests.core.services.AnswersServiceInterface;
import easytests.core.services.TopicsServiceInterface;
import easytests.core.services.QuestionsServiceInterface;
import easytests.core.services.QuestionTypesServiceInterface;
import easytests.support.QuestionsSupport;
import easytests.support.TopicsSupport;
import easytests.support.QuestionTypesSupport;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.BDDMockito.when;
import org.mockito.InOrder;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author RisaMagpie
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionsOptionsTest {

    private QuestionsSupport questionsSupport = new QuestionsSupport();

    private TopicsSupport topicsSupport = new TopicsSupport();

    private QuestionTypesSupport questionTypesSupport = new QuestionTypesSupport();

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

    private TopicsServiceInterface topicsService;

    private TopicsOptionsInterface topicsOptions;

    private TopicModelInterface topicModel;

    private QuestionTypeModelInterface questionTypeModel;

    private ArgumentCaptor<List> listCaptor;

    @Before
    public void before() {
        this.questionModel = Mockito.mock(QuestionModelInterface.class);
        this.answersService = Mockito.mock(AnswersServiceInterface.class);
        this.answersOptions = Mockito.mock(AnswersOptionsInterface.class);
        this.topicsService = Mockito.mock(TopicsServiceInterface.class);
        this.topicsOptions = Mockito.mock(TopicsOptionsInterface.class);
        this.questionTypesService = Mockito.mock(QuestionTypesServiceInterface.class);
        this.questionTypesOptions = Mockito.mock(QuestionTypesOptionsInterface.class);
        this.questionsService = Mockito.mock(QuestionsServiceInterface.class);

        this.questionsOptions = new QuestionsOptions();
        this.questionsOptions.setQuestionsService(this.questionsService);
        this.questionsOptions.setAnswersService(this.answersService);
        this.questionsOptions.setTopicsService(this.topicsService);

        this.listCaptor = ArgumentCaptor.forClass(List.class);
    }

    private QuestionsOptionsTest withQuestionModel() {
        this.questionModel = this.questionsSupport.getModelFixtureMock(0);
        return this;
    }

    private QuestionsOptionsTest withTopicModel() {
        this.topicModel = this.topicsSupport.getModelFixtureMock(0);
        return this;
    }

    private QuestionsOptionsTest withQuestionTypeModel() {
        this.questionTypeModel = this.questionTypesSupport.getModelFixtureMock(0);
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

    private QuestionsOptionsTest withTopic() {
        this.questionsOptions.withTopic(this.topicsOptions);
        return this;
    }

    private QuestionsOptionsTest withQuestionType() {
        this.questionsOptions.withQuestionType(this.questionTypesOptions);
        return this;
    }

    private QuestionsOptionsTest withRelations() {
        this.questionsOptions.withRelations(this.questionModel);
        return this;
    }

    private QuestionsOptionsTest withRelationsInList() {
        this.questionsOptions.withRelations(this.questionsModels);
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

    private QuestionsOptionsTest withTopicModelInjected() {
        when(this.questionModel.getTopic()).thenReturn(this.topicModel);
        return this;
    }

    private QuestionsOptionsTest withQuestionTypeModelInjected() {
        when(this.questionModel.getQuestionType()).thenReturn(this.questionTypeModel);
        return this;
    }


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

    //this method:&&&

    @Test
    public void testWithTopicRelations() throws Exception {
        this.withQuestionModel().withTopicModelInjected().withTopic();
        final ArgumentCaptor<TopicModelInterface> topicModelCaptor = ArgumentCaptor.forClass(TopicModelInterface.class);

        this.questionModel = this.questionsSupport.getModelFixtureMock(0);//почему без этой строки следующая строка хватает null? Ведь это то же самое что и в withQuestionModel() метод.

        final QuestionModelInterface questionModelWithRelations = this.questionsOptions.withRelations(this.questionModel);

        Assert.assertSame(questionModel, questionModelWithRelations);
        verify(this.topicsService, times(1)).find(this.questionModel.getTopic().getId(),this.topicsOptions);
        verify(this.questionModel, times(1)).setTopic(topicModelCaptor.capture());
        Assert.assertSame(this.topicModel, topicModelCaptor.getValue());
    }
/*
    @Test
    public void testWithQuestionTypeRelations() throws Exception {
        this.withQuestionModel().withQuestionTypeModelInjected().withQuestionType();
        final ArgumentCaptor<QuestionTypeModelInterface> questionTypeModelCaptor = ArgumentCaptor.forClass(QuestionTypeModelInterface.class);
        this.questionModel = this.questionsSupport.getModelFixtureMock(0);
        final QuestionModelInterface questionModelWithRelations = this.questionsOptions.withRelations(this.questionModel);

        Assert.assertSame(questionModel, questionModelWithRelations);
        verify(this.questionTypesService, times(1)).find(this.questionModel.getQuestionType().getId(),this.questionTypesOptions);
        verify(this.questionModel, times(1)).setQuestionType(questionTypeModelCaptor.capture());
        Assert.assertSame(this.topicModel, questionTypeModelCaptor.getValue());
    }*/

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
        this.withQuestionModel().withTopicModelInjected();

        this.questionsOptions.saveWithRelations(this.questionModel);

        verify(this.questionsService, times(1)).save(this.questionModel);
        verify(this.answersService, times(0)).save(anyList(), any());
        verify(this.topicsService, times(0)).save(anyList(), any());
    }

    @Test
    public void testSaveWithAnswersRelations() throws Exception {
        this.withQuestionModel().withAnswersModelsInjected().withAnswers();
        final ArgumentCaptor<AnswersOptionsInterface> answersOptionsCaptor = ArgumentCaptor.forClass(AnswersOptionsInterface.class);


        this.questionsOptions.saveWithRelations(this.questionModel);

        verify(this.questionsService, times(1)).save(this.questionModel);
        verify(this.answersService, times(1)).save(this.listCaptor.capture(), answersOptionsCaptor.capture());
        Assert.assertSame(this.answersModels, this.listCaptor.getValue());
        Assert.assertSame(this.answersOptions, answersOptionsCaptor.getValue());
    }

    @Test
    public void testSaveWithTopicRelations() throws Exception {
        this.withQuestionModel().withTopicModelInjected().withTopic();
        final ArgumentCaptor<TopicsOptionsInterface> topicsOptionsCaptor = ArgumentCaptor.forClass(TopicsOptionsInterface.class);
        final ArgumentCaptor<TopicModelInterface> topicModelCaptor = ArgumentCaptor.forClass(TopicModelInterface.class);


        this.questionsOptions.saveWithRelations(this.questionModel);

        verify(this.questionsService, times(1)).save(this.questionModel);
        verify(this.topicsService, times(1)).save(topicModelCaptor.capture(),  topicsOptionsCaptor.capture());
        Assert.assertSame(this.topicModel, topicModelCaptor.getValue());
        Assert.assertSame(this.topicsOptions, topicsOptionsCaptor.getValue());
    }

    @Test
    public void testSaveWithAllRelationsOrder() throws Exception {
        this.withQuestionModel().withTopicModelInjected().withTopic();
        this.withQuestionModel().withAnswersModelsInjected().withAnswers();


        final InOrder inOrder = inOrder(this.topicsService, this.answersService);


        this.questionsOptions.saveWithRelations(this.questionModel);
        inOrder.verify(this.topicsService,times(1)).save(any(TopicModelInterface.class),any(TopicsOptionsInterface.class));
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
    public void testDeleteWithTopicsRelations() throws Exception {
        this.withQuestionModel().withTopicModelInjected().withTopic();
        final ArgumentCaptor<TopicsOptionsInterface> topicsOptionsCaptor = ArgumentCaptor.forClass(TopicsOptionsInterface.class);;
        final ArgumentCaptor<TopicModelInterface> topicModelCaptor = ArgumentCaptor.forClass(TopicModelInterface.class);

        this.questionsOptions.deleteWithRelations(this.questionModel);

        verify(this.topicsService, times(1)).delete(topicModelCaptor.capture(), topicsOptionsCaptor.capture());
        verify(this.questionsService, times(1)).delete(this.questionModel);
        Assert.assertSame(this.topicModel, topicModelCaptor.getValue());
        Assert.assertSame(this.topicsOptions, topicsOptionsCaptor.getValue());
    }

    @Test
    public void testDeleteWithAllRelationsOrder() throws Exception {
        this.withQuestionModel().withAnswersModelsInjected().withAnswers();
        this.withQuestionModel().withTopicModel().withTopicModelInjected().withTopic();
        final InOrder inOrder = inOrder(this.answersService, this.questionsService,  this.topicsService);

        this.questionsOptions.deleteWithRelations(this.questionModel);

        inOrder.verify(this.answersService, times(1)).delete(anyList(), any());
        inOrder.verify(this.questionsService, times(1)).delete(any(QuestionModelInterface.class));
        inOrder.verify(this.topicsService,times(1)).delete(any(TopicModelInterface.class),any(TopicsOptionsInterface.class));
    }
}