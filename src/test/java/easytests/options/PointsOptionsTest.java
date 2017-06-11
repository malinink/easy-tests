package easytests.options;

import easytests.models.PointModelInterface;
import easytests.models.QuestionModelInterface;
import easytests.models.QuizModelInterface;
import easytests.models.SolutionModelInterface;
import easytests.models.empty.QuestionModelEmpty;
import easytests.models.empty.QuizModelEmpty;
import easytests.services.PointsServiceInterface;
import easytests.services.QuestionsServiceInterface;
import easytests.services.QuizzesServiceInterface;
import easytests.services.SolutionsServiceInterface;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

/**
 * @author nikitalpopov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PointsOptionsTest {

    @Test
    public void testWithRelationsOnSingleModel() throws Exception {

        final PointModelInterface pointModel = Mockito.mock(PointModelInterface.class);
        final PointsOptionsInterface pointsOptions = new PointsOptions();

        pointModel.setId(1);
        given(pointModel.getQuiz()).willReturn(new QuizModelEmpty(1));
        given(pointModel.getQuestion()).willReturn(new QuestionModelEmpty(1));

        final QuizzesServiceInterface quizzesService = Mockito.mock(QuizzesServiceInterface.class);
        final QuestionsServiceInterface questionsService = Mockito.mock(QuestionsServiceInterface.class);
        final SolutionsServiceInterface solutionsService = Mockito.mock(SolutionsServiceInterface.class);

        pointsOptions.setQuizzesService(quizzesService);
        pointsOptions.setQuestionsService(questionsService);
        pointsOptions.setSolutionsService(solutionsService);

        final QuizzesOptionsInterface quizOptions = Mockito.mock(QuizzesOptionsInterface.class);
        final QuestionsOptionsInterface questionOptions = Mockito.mock(QuestionsOptionsInterface.class);
        final SolutionsOptionsInterface solutionsOptions = Mockito.mock(SolutionsOptionsInterface.class);

        pointsOptions.withQuiz(quizOptions);
        pointsOptions.withQuestion(questionOptions);
        pointsOptions.withSolutions(solutionsOptions);

        final QuizModelInterface quizModel = Mockito.mock(QuizModelInterface.class);
        final QuestionModelInterface questionModel = Mockito.mock(QuestionModelInterface.class);
        final List<SolutionModelInterface> solutionsModels = new ArrayList<>();
        solutionsModels.add(Mockito.mock(SolutionModelInterface.class));

        given(quizzesService.find(pointModel.getQuiz().getId(), quizOptions)).willReturn(quizModel);
        given(questionsService.find(pointModel.getQuestion().getId(), questionOptions)).willReturn(questionModel);
        given(solutionsService.findByPoint(pointModel, solutionsOptions)).willReturn(solutionsModels);

        final PointModelInterface pointModelWithRelations = pointsOptions.withRelations(pointModel);

        Assert.assertEquals(pointModel, pointModelWithRelations);

        verify(quizzesService).find(1, quizOptions);
        verify(questionsService).find(1, questionOptions);
        verify(solutionsService).findByPoint(pointModel, solutionsOptions);

        verify(pointModel).setQuiz(quizModel);
        verify(pointModel).setQuestion(questionModel);
        verify(pointModel).setSolutions(solutionsModels);

    }

    @Test
    public void testWithRelationsOnNull() throws Exception {

        final PointsOptionsInterface pointsOptions = new PointsOptions();

        final QuizzesServiceInterface quizzesService = Mockito.mock(QuizzesServiceInterface.class);
        final QuestionsServiceInterface questionsService = Mockito.mock(QuestionsServiceInterface.class);
        final SolutionsServiceInterface solutionsService = Mockito.mock(SolutionsServiceInterface.class);

        pointsOptions.setQuizzesService(quizzesService);
        pointsOptions.setQuestionsService(questionsService);
        pointsOptions.setSolutionsService(solutionsService);

        final QuizzesOptionsInterface quizOptions = Mockito.mock(QuizzesOptionsInterface.class);
        final QuestionsOptionsInterface questionOptions = Mockito.mock(QuestionsOptionsInterface.class);
        final SolutionsOptionsInterface solutionsOptions = Mockito.mock(SolutionsOptionsInterface.class);

        pointsOptions.withQuiz(quizOptions).withQuestion(questionOptions).withSolutions(solutionsOptions);

        final PointModelInterface nullPointModel = null;
        final PointModelInterface pointModelWithRelations = pointsOptions.withRelations(nullPointModel);

        Assert.assertNull(pointModelWithRelations);

    }

    @Test
    public void testWithRelationsOnModelsList() throws Exception {

        final PointModelInterface pointModelFirst = Mockito.mock(PointModelInterface.class);
        final PointModelInterface pointModelSecond = Mockito.mock(PointModelInterface.class);
        pointModelFirst.setId(1);
        pointModelSecond.setId(2);
        given(pointModelFirst.getQuiz()).willReturn(new QuizModelEmpty(1));
        given(pointModelSecond.getQuiz()).willReturn(new QuizModelEmpty(2));
        given(pointModelFirst.getQuestion()).willReturn(new QuestionModelEmpty(1));
        given(pointModelSecond.getQuestion()).willReturn(new QuestionModelEmpty(2));

        final List<PointModelInterface> pointsModels = new ArrayList<>();
        pointsModels.add(pointModelFirst);
        pointsModels.add(pointModelSecond);

        final PointsOptionsInterface pointsOptions = new PointsOptions();

        final QuizzesServiceInterface quizzesService = Mockito.mock(QuizzesServiceInterface.class);
        final QuestionsServiceInterface questionsService = Mockito.mock(QuestionsServiceInterface.class);
        final SolutionsServiceInterface solutionsService = Mockito.mock(SolutionsServiceInterface.class);

        pointsOptions.setQuizzesService(quizzesService);
        pointsOptions.setQuestionsService(questionsService);
        pointsOptions.setSolutionsService(solutionsService);

        final QuizzesOptionsInterface quizOptions = Mockito.mock(QuizzesOptionsInterface.class);
        final QuestionsOptionsInterface questionOptions = Mockito.mock(QuestionsOptionsInterface.class);
        final SolutionsOptionsInterface solutionsOptions = Mockito.mock(SolutionsOptionsInterface.class);

        pointsOptions.withQuiz(quizOptions);
        pointsOptions.withQuestion(questionOptions);
        pointsOptions.withSolutions(solutionsOptions);

        final QuizModelInterface quizModelFirst = Mockito.mock(QuizModelInterface.class);
        final QuizModelInterface quizModelSecond = Mockito.mock(QuizModelInterface.class);
        given(quizzesService.find(1, quizOptions)).willReturn(quizModelFirst);
        given(quizzesService.find(2, quizOptions)).willReturn(quizModelSecond);

        final QuestionModelInterface questionModelFirst = Mockito.mock(QuestionModelInterface.class);
        final QuestionModelInterface questionModelSecond = Mockito.mock(QuestionModelInterface.class);
        given(questionsService.find(1, questionOptions)).willReturn(questionModelFirst);
        given(questionsService.find(2, questionOptions)).willReturn(questionModelSecond);

        final List<SolutionModelInterface> solutionsModelsFirst = new ArrayList<>();
        solutionsModelsFirst.add(Mockito.mock(SolutionModelInterface.class));
        final List<SolutionModelInterface> solutionsModelsSecond = new ArrayList<>();
        solutionsModelsSecond.add(Mockito.mock(SolutionModelInterface.class));
        solutionsModelsSecond.add(Mockito.mock(SolutionModelInterface.class));

        given(solutionsService.findByPoint(pointModelFirst, solutionsOptions)).willReturn(solutionsModelsFirst);
        given(solutionsService.findByPoint(pointModelSecond, solutionsOptions)).willReturn(solutionsModelsSecond);

        final List<PointModelInterface> pointsModelsWithRelations = pointsOptions.withRelations(pointsModels);

        Assert.assertEquals(pointsModels, pointsModelsWithRelations);

        verify(quizzesService).find(1, quizOptions);
        verify(questionsService).find(1, questionOptions);
        verify(solutionsService).findByPoint(pointModelFirst, solutionsOptions);

        verify(pointsModels.get(0)).setQuiz(quizModelFirst);
        verify(pointsModels.get(0)).setQuestion(questionModelFirst);
        verify(pointsModels.get(0)).setSolutions(solutionsModelsFirst);
        verify(pointsModels.get(0)).setSolutions(Mockito.anyList());

        verify(quizzesService).find(2, quizOptions);
        verify(questionsService).find(2, questionOptions);
        verify(solutionsService).findByPoint(pointModelSecond, solutionsOptions);

        verify(pointsModels.get(1)).setQuiz(quizModelSecond);
        verify(pointsModels.get(1)).setQuestion(questionModelSecond);
        verify(pointsModels.get(1)).setSolutions(solutionsModelsSecond);
        verify(pointsModels.get(1)).setSolutions(Mockito.anyList());

    }

    @Test
    @Ignore
    public void testSaveWithRelations() throws Exception {

        final PointModelInterface pointModel = Mockito.mock(PointModelInterface.class);
        final PointsServiceInterface pointsService = Mockito.mock(PointsServiceInterface.class);
        final PointsOptionsInterface pointsOptions = new PointsOptions();

        final QuizzesServiceInterface quizzesService = Mockito.mock(QuizzesServiceInterface.class);
        final SolutionsServiceInterface solutionsService = Mockito.mock(SolutionsServiceInterface.class);

        pointsOptions.setPointsService(pointsService);
        pointsOptions.setQuizzesService(quizzesService);
        pointsOptions.setSolutionsService(solutionsService);

        final QuizzesOptionsInterface quizOptions = Mockito.mock(QuizzesOptionsInterface.class);
        final SolutionsOptionsInterface solutionsOptions = Mockito.mock(SolutionsOptionsInterface.class);

        pointsOptions.withQuiz(quizOptions);
        pointsOptions.withSolutions(solutionsOptions);

        final QuizModelInterface quizModel = Mockito.mock(QuizModelInterface.class);
        final List<SolutionModelInterface> solutionsModels = new ArrayList<>();

        pointModel.setQuiz(quizModel);
        pointModel.setSolutions(solutionsModels);

        final InOrder inOrder = Mockito.inOrder(quizzesService, pointsService, solutionsService);

        pointsOptions.saveWithRelations(pointModel);

        inOrder.verify(quizzesService).save(quizModel, quizOptions);
        inOrder.verify(pointsService).save(pointModel);
        inOrder.verify(solutionsService).save(solutionsModels, solutionsOptions);

    }

    @Test
    @Ignore
    public void testDeleteWithRelations() throws Exception {

        final PointModelInterface pointModel = Mockito.mock(PointModelInterface.class);
        final PointsServiceInterface pointsService = Mockito.mock(PointsServiceInterface.class);
        final PointsOptionsInterface pointsOptions = new PointsOptions();

        final QuizzesServiceInterface quizzesService = Mockito.mock(QuizzesServiceInterface.class);
        final SolutionsServiceInterface solutionsService = Mockito.mock(SolutionsServiceInterface.class);

        pointsOptions.setPointsService(pointsService);
        pointsOptions.setQuizzesService(quizzesService);
        pointsOptions.setSolutionsService(solutionsService);

        final QuizzesOptionsInterface quizOptions = Mockito.mock(QuizzesOptionsInterface.class);
        final SolutionsOptionsInterface solutionsOptions = Mockito.mock(SolutionsOptionsInterface.class);

        pointsOptions.withQuiz(quizOptions);
        pointsOptions.withSolutions(solutionsOptions);

        final QuizModelInterface quizModel = Mockito.mock(QuizModelInterface.class);
        final List<SolutionModelInterface> solutionsModels = new ArrayList<>();

        pointModel.setQuiz(quizModel);
        pointModel.setSolutions(solutionsModels);

        final InOrder inOrder = Mockito.inOrder(solutionsService, pointsService, quizzesService);

        pointsOptions.deleteWithRelations(pointModel);

        inOrder.verify(solutionsService).delete(solutionsModels, solutionsOptions);
        inOrder.verify(pointsService).delete(pointModel);
        inOrder.verify(quizzesService).delete(quizModel, quizOptions);

    }

    @Test
    public void testSaveDeleteWithQuiz() {

        final PointsOptionsInterface pointsOptions = new PointsOptions();

        final PointsServiceInterface pointsService = Mockito.mock(PointsServiceInterface.class);
        pointsOptions.setPointsService(pointsService);

        final QuizzesServiceInterface quizzesService = Mockito.mock(QuizzesServiceInterface.class);
        pointsOptions.setQuizzesService(quizzesService);

        final QuizzesOptionsInterface quizzesOptions = Mockito.mock(QuizzesOptionsInterface.class);
        pointsOptions.withQuiz(quizzesOptions);

        final PointModelInterface pointModel = Mockito.mock(PointModelInterface.class);

        final PointsOptionsInterface pointsOptionsSpy = Mockito.spy(pointsOptions);

        pointsOptionsSpy.deleteWithRelations(pointModel);

        verify(quizzesService, times(1)).delete(pointModel.getQuiz(), quizzesOptions);

        pointsOptionsSpy.saveWithRelations(pointModel);

        verify(quizzesService, times(1)).save(pointModel.getQuiz(), quizzesOptions);

    }

}
