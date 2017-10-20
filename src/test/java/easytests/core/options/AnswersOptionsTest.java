package easytests.core.options;

import easytests.core.models.*;
import easytests.core.models.empty.QuestionModelEmpty;
import easytests.core.services.AnswersServiceInterface;
import easytests.core.services.QuestionsServiceInterface;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.times;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;




/**
 * @author rezenbekk
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AnswersOptionsTest {
    @Test
    public void testWithRelationsOnSingleModel() throws Exception {
        final AnswerModelInterface answerModel = Mockito.mock(AnswerModelInterface.class);

        answerModel.setId(1);
        given(answerModel.getQuestion()).willReturn(new QuestionModelEmpty(1));

        final AnswersOptionsInterface answersOptions = new AnswersOptions();

        final QuestionsServiceInterface questionsService = Mockito.mock(QuestionsServiceInterface.class);

        final QuestionsOptionsInterface questionsOptions = Mockito.mock(QuestionsOptionsInterface.class);

        answersOptions.setQuestionsService(questionsService);

        answersOptions.withQuestion(questionsOptions);

        final QuestionModelInterface questionModel = Mockito.mock(QuestionModelInterface.class);

        given(questionsService.find(answerModel.getQuestion().getId(), questionsOptions)).willReturn(questionModel);

        final AnswerModelInterface answerModelWithRelations = answersOptions.withRelations(answerModel);

        Assert.assertEquals(answerModel, answerModelWithRelations);

        verify(questionsService).find(1, questionsOptions);

        verify(answerModel).setQuestion(questionModel);
    }

    @Test
    public void testWithRelationsOnNull() throws Exception {

        final AnswersOptionsInterface answersOptions = new AnswersOptions();

        final QuestionsServiceInterface questionsService = Mockito.mock(QuestionsServiceInterface.class);

        final QuestionsOptionsInterface questionOptions = Mockito.mock(QuestionsOptionsInterface.class);

        answersOptions.setQuestionsService(questionsService);

        answersOptions.withQuestion(questionOptions);

        final AnswerModelInterface nullAnswerModel = null;
        final AnswerModelInterface answerModelWithRelations = answersOptions.withRelations(nullAnswerModel);

        Assert.assertNull(answerModelWithRelations);

    }

    @Test
    public void testWithRelationsOnModelsList() throws Exception {

        final AnswerModelInterface answerModelFirst = Mockito.mock(AnswerModelInterface.class);
        answerModelFirst.setId(1);
        given(answerModelFirst.getQuestion()).willReturn(new QuestionModelEmpty(1));
        final AnswerModelInterface answerModelSecond = Mockito.mock(AnswerModelInterface.class);
        answerModelSecond.setId(2);
        given(answerModelSecond.getQuestion()).willReturn(new QuestionModelEmpty(2));
        final List<AnswerModelInterface> answersModels = new ArrayList<>(2);
        answersModels.add(answerModelFirst);
        answersModels.add(answerModelSecond);

        final AnswersOptionsInterface answersOptions = new AnswersOptions();

        final QuestionsServiceInterface questionsService = Mockito.mock(QuestionsServiceInterface.class);
        final QuestionsOptionsInterface questionOptions = Mockito.mock(QuestionsOptionsInterface.class);

        answersOptions.setQuestionsService(questionsService);

        answersOptions.withQuestion(questionOptions);

        final QuestionModelInterface questionModelFirst = Mockito.mock(QuestionModelInterface.class);
        final QuestionModelInterface questionModelSecond = Mockito.mock(QuestionModelInterface.class);
        given(questionsService.find(1, questionOptions)).willReturn(questionModelFirst);
        given(questionsService.find(2, questionOptions)).willReturn(questionModelSecond);

        final List<AnswerModelInterface> answersModelsWithRelations = answersOptions.withRelations(answersModels);

        Assert.assertEquals(answersModelsWithRelations, answersModels);

        verify(questionsService).find(1, questionOptions);

        verify(answersModels.get(0)).setQuestion(questionModelFirst);

        verify(questionsService).find(2, questionOptions);

        verify(answersModels.get(1)).setQuestion(questionModelSecond);
    }
    @Test
    public void testSaveWithRelations() throws Exception {

        final AnswerModelInterface answerModel = Mockito.mock(AnswerModelInterface.class);

        final AnswersOptionsInterface answersOptions = new AnswersOptions();

        answersOptions.saveWithRelations(answerModel);


    }

    @Test
    public void testDeleteWithRelations() throws Exception {

        final AnswerModelInterface answerModel = Mockito.mock(AnswerModelInterface.class);

        final AnswersServiceInterface answersService = Mockito.mock(AnswersServiceInterface.class);
        final AnswersOptionsInterface answersOptions = new AnswersOptions();
        answersOptions.setAnswersService(answersService);
        answersOptions.deleteWithRelations(answerModel);




    }
    @Test
    public void testSaveDeleteWithQuestion() {

        final AnswersOptionsInterface answersOptions = new AnswersOptions();

        final QuestionsOptionsInterface questionsOptions = Mockito.mock(QuestionsOptionsInterface.class);

        final QuestionsServiceInterface questionsService = Mockito.mock(QuestionsServiceInterface.class);
        answersOptions.setQuestionsService(questionsService);
        answersOptions.withQuestion(questionsOptions);

        final AnswerModelInterface answerModel = Mockito.mock(AnswerModelInterface.class);

        final AnswersOptionsInterface answersOptionsSpy = Mockito.spy(answersOptions);

        answersOptionsSpy.deleteWithRelations(answerModel);

        verify(questionsOptions, times(1)).deleteWithRelations(answerModel.getQuestion());

        answersOptionsSpy.saveWithRelations(answerModel);

        verify(questionsOptions, times(1)).saveWithRelations(answerModel.getQuestion());

    }
}
