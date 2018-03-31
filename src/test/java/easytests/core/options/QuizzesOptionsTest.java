package easytests.core.options;

import easytests.core.models.IssueModelInterface;
import easytests.core.models.PointModelInterface;
import easytests.core.models.QuizModelInterface;
import easytests.core.models.TesteeModelInterface;
import easytests.core.models.empty.IssueModelEmpty;
import easytests.core.services.IssuesServiceInterface;
import easytests.core.services.PointsServiceInterface;
import easytests.core.services.QuizzesServiceInterface;
import easytests.core.services.TesteesServiceInterface;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;

import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;

import static org.mockito.Mockito.verify;

/**
 * @author fortyways
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuizzesOptionsTest {

    @Test
    public void testWithRelationsOnSingleModel() throws Exception {
        final QuizModelInterface quizModel = Mockito.mock(QuizModelInterface.class);

        quizModel.setId(1);
        given(quizModel.getIssue()).willReturn(new IssueModelEmpty(1));

        final QuizzesOptionsInterface quizzesOptions = new QuizzesOptions();

        final PointsServiceInterface pointsService = Mockito.mock(PointsServiceInterface.class);
        final TesteesServiceInterface testeeService = Mockito.mock(TesteesServiceInterface.class);
        final IssuesServiceInterface issuesService = Mockito.mock(IssuesServiceInterface.class);

        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);
        final TesteesOptionsInterface testeeOptions = Mockito.mock(TesteesOptionsInterface.class);
        final IssuesOptionsInterface issuesOptions = Mockito.mock(IssuesOptionsInterface.class);

        quizzesOptions.setPointsService(pointsService);
        quizzesOptions.setIssuesService(issuesService);
        quizzesOptions.setTesteesService(testeeService);

        quizzesOptions.withTestee(testeeOptions);
        quizzesOptions.withPoints(pointsOptions);
        quizzesOptions.withIssue(issuesOptions);

        final List<PointModelInterface> pointsModels = new ArrayList<>();
        pointsModels.add(Mockito.mock(PointModelInterface.class));

        final IssueModelInterface issueModel = Mockito.mock(IssueModelInterface.class);

        final TesteeModelInterface testeeModel = Mockito.mock(TesteeModelInterface.class);

        given(testeeService.findByQuiz(quizModel, testeeOptions)).willReturn(testeeModel);
        given(pointsService.findByQuiz(quizModel, pointsOptions)).willReturn(pointsModels);
        given(issuesService.find(quizModel.getIssue().getId(), issuesOptions)).willReturn(issueModel);

        final QuizModelInterface quizModelWithRelations = quizzesOptions.withRelations(quizModel);

        Assert.assertEquals(quizModel, quizModelWithRelations);

        verify(testeeService).findByQuiz(quizModel, testeeOptions);
        verify(pointsService).findByQuiz(quizModel, pointsOptions);
        verify(issuesService).find(1, issuesOptions);

        verify(quizModel).setTestee(testeeModel);
        verify(quizModel).setPoints(pointsModels);
        verify(quizModel).setIssue(issueModel);
    }

    @Test
    public void testWithRelationsOnNull() throws Exception {

        final QuizzesOptionsInterface quizzesOptions = new QuizzesOptions();

        final TesteesServiceInterface testeesService = Mockito.mock(TesteesServiceInterface.class);
        final PointsServiceInterface pointsService = Mockito.mock(PointsServiceInterface.class);
        final IssuesServiceInterface issuesService = Mockito.mock(IssuesServiceInterface.class);

        final TesteesOptionsInterface testeesOptions = Mockito.mock(TesteesOptionsInterface.class);
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);
        final IssuesOptionsInterface issueOptions = Mockito.mock(IssuesOptionsInterface.class);

        quizzesOptions.setTesteesService(testeesService);
        quizzesOptions.setPointsService(pointsService);
        quizzesOptions.setIssuesService(issuesService);

        quizzesOptions.withPoints(pointsOptions).withIssue(issueOptions).withTestee(testeesOptions);

        final QuizModelInterface nullQuizModel = null;
        final QuizModelInterface quizModelWithRelations = quizzesOptions.withRelations(nullQuizModel);

        Assert.assertNull(quizModelWithRelations);

    }

    @Test
    public void testWithRelationsOnModelsList() throws Exception {

        final QuizModelInterface quizModelFirst = Mockito.mock(QuizModelInterface.class);
        quizModelFirst.setId(1);
        given(quizModelFirst.getIssue()).willReturn(new IssueModelEmpty(1));
        final QuizModelInterface quizModelSecond = Mockito.mock(QuizModelInterface.class);
        quizModelSecond.setId(2);
        given(quizModelSecond.getIssue()).willReturn(new IssueModelEmpty(2));
        final List<QuizModelInterface> quizzesModels = new ArrayList<>(2);
        quizzesModels.add(quizModelFirst);
        quizzesModels.add(quizModelSecond);

        final QuizzesOptionsInterface quizzesOptions = new QuizzesOptions();

        final TesteesServiceInterface testeesService = Mockito.mock(TesteesServiceInterface.class);
        final TesteesOptionsInterface testeesOptions = Mockito.mock(TesteesOptionsInterface.class);

        final PointsServiceInterface pointsService = Mockito.mock(PointsServiceInterface.class);
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);

        final IssuesServiceInterface issuesService = Mockito.mock(IssuesServiceInterface.class);
        final IssuesOptionsInterface issueOptions = Mockito.mock(IssuesOptionsInterface.class);

        quizzesOptions.setTesteesService(testeesService);
        quizzesOptions.setPointsService(pointsService);
        quizzesOptions.setIssuesService(issuesService);

        quizzesOptions.withTestee(testeesOptions);
        quizzesOptions.withPoints(pointsOptions);
        quizzesOptions.withIssue(issueOptions);

        final List<PointModelInterface> pointsModelsFirst = new ArrayList<>();
        pointsModelsFirst.add(Mockito.mock(PointModelInterface.class));

        final List<PointModelInterface> pointsModelsSecond = new ArrayList<>();
        pointsModelsSecond.add(Mockito.mock(PointModelInterface.class));
        pointsModelsSecond.add(Mockito.mock(PointModelInterface.class));

        given(pointsService.findByQuiz(quizModelFirst, pointsOptions)).willReturn(pointsModelsFirst);
        given(pointsService.findByQuiz(quizModelSecond, pointsOptions)).willReturn(pointsModelsSecond);

        final IssueModelInterface issueModelFirst = Mockito.mock(IssueModelInterface.class);
        final IssueModelInterface issueModelSecond = Mockito.mock(IssueModelInterface.class);
        given(issuesService.find(1, issueOptions)).willReturn(issueModelFirst);
        given(issuesService.find(2, issueOptions)).willReturn(issueModelSecond);

        final TesteeModelInterface testeeModelFirst = Mockito.mock(TesteeModelInterface.class);
        final TesteeModelInterface testeeModelSecond = Mockito.mock(TesteeModelInterface.class);

        given(testeesService.findByQuiz(quizModelFirst, testeesOptions)).willReturn(testeeModelFirst);
        given(testeesService.findByQuiz(quizModelSecond, testeesOptions)).willReturn(testeeModelSecond);


        final List<QuizModelInterface> quizzesModelsWithRelations = quizzesOptions.withRelations(quizzesModels);

        Assert.assertEquals(quizzesModelsWithRelations, quizzesModels);
        verify(testeesService).findByQuiz(quizModelFirst, testeesOptions);
        verify(pointsService).findByQuiz(quizModelFirst, pointsOptions);
        verify(issuesService).find(1, issueOptions);

        verify(quizzesModels.get(0)).setPoints(pointsModelsFirst);
        verify(quizzesModels.get(0)).setPoints(Mockito.anyList());
        verify(quizzesModels.get(0)).setIssue(issueModelFirst);
        verify(quizzesModels.get(0)).setTestee(testeeModelFirst);

        verify(testeesService).findByQuiz(quizModelSecond, testeesOptions);
        verify(pointsService).findByQuiz(quizModelSecond, pointsOptions);
        verify(issuesService).find(2, issueOptions);

        verify(quizzesModels.get(1)).setPoints(pointsModelsSecond);
        verify(quizzesModels.get(1)).setPoints(Mockito.anyList());
        verify(quizzesModels.get(1)).setIssue(issueModelSecond);
        verify(quizzesModels.get(1)).setTestee(testeeModelSecond);
    }

    @Test
    public void testSaveWithRelations() throws Exception {
        final QuizModelInterface quizModel = Mockito.mock(QuizModelInterface.class);

        final TesteesServiceInterface testeesService = Mockito.mock(TesteesServiceInterface.class);
        final TesteesOptionsInterface testeesOptions = Mockito.mock(TesteesOptionsInterface.class);

        final QuizzesServiceInterface quizzesService = Mockito.mock(QuizzesServiceInterface.class);
        final QuizzesOptionsInterface quizzesOptions = new QuizzesOptions();

        final PointsServiceInterface pointsService = Mockito.mock(PointsServiceInterface.class);
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);

        quizzesOptions.setTesteesService(testeesService);
        quizzesOptions.setQuizzesService(quizzesService);
        quizzesOptions.setPointsService(pointsService);
        quizzesOptions.withTestee(testeesOptions);
        quizzesOptions.withPoints(pointsOptions);

        final List<PointModelInterface> pointsModels = new ArrayList<>();
        quizModel.setPoints(pointsModels);

        final TesteeModelInterface testeeModel = Mockito.mock(TesteeModelInterface.class);
        quizModel.setTestee(testeeModel);

        final InOrder inOrder = Mockito.inOrder(quizzesService, testeesService, pointsService);

        quizzesOptions.saveWithRelations(quizModel);

        inOrder.verify(quizzesService).save(quizModel);
        inOrder.verify(testeesService).save(quizModel.getTestee(), testeesOptions);
        inOrder.verify(pointsService).save(quizModel.getPoints(), pointsOptions);
    }

    @Test
    public void testDeleteWithRelations() throws Exception {
        final QuizModelInterface quizModel = Mockito.mock(QuizModelInterface.class);

        final TesteesServiceInterface testeesService = Mockito.mock(TesteesServiceInterface.class);
        final TesteesOptionsInterface testeesOptions = Mockito.mock(TesteesOptionsInterface.class);

        final QuizzesServiceInterface quizzesService = Mockito.mock(QuizzesServiceInterface.class);
        final QuizzesOptionsInterface quizzesOptions = new QuizzesOptions();

        final PointsServiceInterface pointsService = Mockito.mock(PointsServiceInterface.class);
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);

        quizzesOptions.setTesteesService(testeesService);
        quizzesOptions.setQuizzesService(quizzesService);
        quizzesOptions.setPointsService(pointsService);
        quizzesOptions.withPoints(pointsOptions);
        quizzesOptions.withTestee(testeesOptions);

        final List<PointModelInterface> pointsModels = new ArrayList<>();
        quizModel.setPoints(pointsModels);

        final TesteeModelInterface testeeModel = Mockito.mock(TesteeModelInterface.class);
        quizModel.setTestee(testeeModel);

        final InOrder inOrder = Mockito.inOrder(pointsService, quizzesService, testeesService);

        quizzesOptions.deleteWithRelations(quizModel);

        inOrder.verify(pointsService).delete(quizModel.getPoints(), pointsOptions);
        inOrder.verify(quizzesService).delete(quizModel);
        inOrder.verify(testeesService).delete(quizModel.getTestee(), testeesOptions);

    }

    @Test
    public void testSaveDeleteWithIssue() {

        final QuizzesOptionsInterface quizzesOptions = new QuizzesOptions();

        final IssuesOptionsInterface issuesOptions = Mockito.mock(IssuesOptionsInterface.class);

        final IssuesServiceInterface issuesService = Mockito.mock(IssuesServiceInterface.class);

        final QuizzesServiceInterface quizzesService = Mockito.mock(QuizzesServiceInterface.class);

        quizzesOptions.setQuizzesService(quizzesService);
        quizzesOptions.setIssuesService(issuesService);
        quizzesOptions.withIssue(issuesOptions);

        final QuizModelInterface quizModel = Mockito.mock(QuizModelInterface.class);

        final QuizzesOptionsInterface quizzesOptionsSpy = Mockito.spy(quizzesOptions);

        quizzesOptionsSpy.deleteWithRelations(quizModel);

        verify(issuesService, times(1)).delete(quizModel.getIssue(), issuesOptions);

        quizzesOptionsSpy.saveWithRelations(quizModel);

        verify(issuesService, times(1)).save(quizModel.getIssue(), issuesOptions);

    }
}
