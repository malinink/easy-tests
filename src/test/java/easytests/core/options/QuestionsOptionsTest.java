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
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.mockito.InOrder;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author firkhraag
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionsOptionsTest {

    @Test
    public void testWithRelationsOnSingleModel() throws Exception {
        final QuestionModelInterface questionModel = Mockito.mock(QuestionModelInterface.class);
        questionModel.setId(1);
        given(questionModel.getTopic()).willReturn(new TopicModelEmpty(1));
        given(questionModel.getQuestionType()).willReturn(new QuestionTypeModelEmpty(1));
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
        final List<AnswerModelInterface> answersModels = new ArrayList<>();
        answersModels.add(Mockito.mock(AnswerModelInterface.class));
        TopicModelInterface topicModel = Mockito.mock(TopicModelInterface.class);
        QuestionTypeModelInterface questionTypeModel = Mockito.mock(QuestionTypeModelInterface.class);
        given(answersService.findByQuestion(questionModel, answersOptions)).willReturn(answersModels);
        given(topicsService.find(questionModel.getTopic().getId(), topicsOptions)).willReturn(topicModel);
        given(questionTypesService.find(questionModel.getQuestionType().getId(), questionTypesOptions)).willReturn(questionTypeModel);

        final QuestionModelInterface questionModelWithRelations = questionsOptions.withRelations(questionModel);

        Assert.assertEquals(questionModel, questionModelWithRelations);
        verify(answersService).findByQuestion(questionModel, answersOptions);
        verify(topicsService).find(questionModel.getTopic().getId(), topicsOptions);
        verify(questionTypesService).find(questionModel.getQuestionType().getId(), questionTypesOptions);
        verify(questionModel).setAnswers(answersModels);
        verify(questionModel).setTopic(topicModel);
        verify(questionModel).setQuestionType(questionTypeModel);
        verify(questionModel).setAnswers(Mockito.anyList());
    }

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
