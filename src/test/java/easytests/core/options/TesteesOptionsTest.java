package easytests.core.options;

import easytests.core.models.TesteeModelInterface;
import easytests.core.models.QuizModelInterface;
import easytests.core.models.empty.QuizModelEmpty;
import easytests.core.services.QuizzesServiceInterface;
import easytests.core.services.TesteesServiceInterface;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.mockito.InOrder;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;


/**
 * @author DoZor-80
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TesteesOptionsTest {
    @Test
    public void testWithRelationsOnSingleModel() throws Exception {
        final TesteeModelInterface testeeModel = Mockito.mock(TesteeModelInterface.class);
        final TesteesOptionsInterface testeesOptions = new TesteesOptions();

        final QuizzesServiceInterface quizzesService = Mockito.mock(QuizzesServiceInterface.class);
        final QuizzesOptionsInterface quizzesOptions = Mockito.mock(QuizzesOptionsInterface.class);

        final Integer quizId = 3;

        Mockito.when(testeeModel.getQuiz()).thenReturn(new QuizModelEmpty(quizId));

        final QuizModelInterface quizModel = Mockito.mock(QuizModelInterface.class);
        Mockito.when(quizModel.getId()).thenReturn(quizId);

        given(quizzesService.find(quizId, quizzesOptions)).willReturn(quizModel);

        final TesteeModelInterface testeeModelWithoutRelations
                = testeesOptions.withRelations(testeeModel);

        verify(quizzesService, times(0)).find(quizId, quizzesOptions);

        Assert.assertEquals(testeeModel, testeeModelWithoutRelations);

        verify(testeeModel, times(0)).setQuiz(quizModel);

        testeesOptions.setQuizzesService(quizzesService);
        testeesOptions.withQuiz(quizzesOptions);

        final TesteeModelInterface testeeModelWithRelations = testeesOptions.withRelations(testeeModel);

        verify(quizzesService, times(1)).find(quizId, quizzesOptions);
        Assert.assertEquals(testeeModel, testeeModelWithRelations);
        verify(testeeModel, times(1)).setQuiz(quizModel);
    }

    @Test
    public void testWithRelationsOnNull() throws Exception {
        final TesteeModelInterface testeeModel = null;
        final TesteesOptionsInterface testeesOptions = new TesteesOptions();

        final QuizzesServiceInterface quizzesService = Mockito.mock(QuizzesServiceInterface.class);
        final QuizzesOptionsInterface quizzesOptions = Mockito.mock(QuizzesOptionsInterface.class);
        testeesOptions.setQuizzesService(quizzesService);
        testeesOptions.withQuiz(quizzesOptions);

        final TesteeModelInterface testeeModelWithRelations = testeesOptions.withRelations(testeeModel);

        Assert.assertEquals(null, testeeModelWithRelations);
    }

    @Test
    public void testWithRelationsOnModelsList() throws Exception {
        final TesteeModelInterface testeeModelFirst = Mockito.mock(TesteeModelInterface.class);
        testeeModelFirst.setId(1);
        final TesteeModelInterface testeeModelSecond = Mockito.mock(TesteeModelInterface.class);
        testeeModelSecond.setId(2);

        final QuizzesServiceInterface quizzesService = Mockito.mock(QuizzesServiceInterface.class);
        final QuizzesOptionsInterface quizzesOptions = Mockito.mock(QuizzesOptionsInterface.class);

        final Integer quizIdFirst = 3;
        final Integer quizIdSecond = 5;

        Mockito.when(testeeModelFirst.getQuiz()).thenReturn(new QuizModelEmpty(quizIdFirst));
        Mockito.when(testeeModelSecond.getQuiz()).thenReturn(new QuizModelEmpty(quizIdSecond));

        final List<TesteeModelInterface> testeesModels = new ArrayList<>(2);
        testeesModels.add(testeeModelFirst);
        testeesModels.add(testeeModelSecond);

        final TesteesOptionsInterface testeesOptions = new TesteesOptions();

        final QuizModelInterface quizModelFirst = Mockito.mock(QuizModelInterface.class);
        Mockito.when(quizModelFirst.getId()).thenReturn(quizIdFirst);

        final QuizModelInterface quizModelSecond = Mockito.mock(QuizModelInterface.class);
        Mockito.when(quizModelSecond.getId()).thenReturn(quizIdSecond);

        given(quizzesService.find(quizIdFirst, quizzesOptions)).willReturn(quizModelFirst);
        given(quizzesService.find(quizIdSecond, quizzesOptions)).willReturn(quizModelSecond);

        // options не заданы
        List<TesteeModelInterface> testeeModelsWithoutRelations
                = testeesOptions.withRelations(testeesModels);

        verify(quizzesService, times(0)).find(quizIdFirst, quizzesOptions);
        verify(quizzesService, times(0)).find(quizIdSecond, quizzesOptions);

        Assert.assertEquals(testeesModels, testeeModelsWithoutRelations);
        verify(testeeModelFirst, times(0)).setQuiz(quizModelFirst);
        verify(testeeModelSecond, times(0)).setQuiz(quizModelSecond);

        testeesOptions.setQuizzesService(quizzesService);
        testeesOptions.withQuiz(quizzesOptions);

        final List<TesteeModelInterface> testeesModelsWithRelations = testeesOptions.withRelations(testeesModels);

        verify(quizzesService, times(1)).find(quizIdFirst, quizzesOptions);
        verify(quizzesService, times(1)).find(quizIdSecond, quizzesOptions);

        Assert.assertEquals(testeesModelsWithRelations, testeesModels);
        verify(testeeModelFirst, times(1)).setQuiz(quizModelFirst);
        verify(testeeModelSecond, times(1)).setQuiz(quizModelSecond);
    }

    @Test
    public void testSaveWithRelations() throws Exception {
        final TesteeModelInterface testeeModel = Mockito.mock(TesteeModelInterface.class);
        final TesteesOptionsInterface testeesOptions = new TesteesOptions();
        final TesteesServiceInterface testeesService = Mockito.mock(TesteesServiceInterface.class);

        final QuizzesServiceInterface quizzesService = Mockito.mock(QuizzesServiceInterface.class);
        final QuizzesOptionsInterface quizzesOptions = Mockito.mock(QuizzesOptionsInterface.class);

        final Integer quizId = 3;
        final QuizModelInterface quizModel = Mockito.mock(QuizModelInterface.class);
        Mockito.when(quizModel.getId()).thenReturn(quizId);

        Mockito.when(testeeModel.getQuiz()).thenReturn(quizModel);

        testeesOptions.setTesteesService(testeesService);
        testeesOptions.setQuizzesService(quizzesService);
        testeesOptions.withQuiz(quizzesOptions);

        final InOrder inOrder = Mockito.inOrder(
                quizzesService, testeesService);

        testeesOptions.saveWithRelations(testeeModel);
        inOrder.verify(quizzesService, times(1)).save(testeeModel.getQuiz(), quizzesOptions);
        inOrder.verify(testeesService, times(1)).save(testeeModel);
    }

    @Test
    public void testDeleteWithRelations() throws Exception {
        final TesteeModelInterface testeeModel = Mockito.mock(TesteeModelInterface.class);
        final TesteesOptionsInterface testeesOptions = new TesteesOptions();
        final TesteesServiceInterface testeesService = Mockito.mock(TesteesServiceInterface.class);

        final QuizzesServiceInterface quizzesService = Mockito.mock(QuizzesServiceInterface.class);
        final QuizzesOptionsInterface quizzesOptions = Mockito.mock(QuizzesOptionsInterface.class);

        final Integer quizId = 3;
        final QuizModelInterface quizModel = Mockito.mock(QuizModelInterface.class);
        Mockito.when(quizModel.getId()).thenReturn(quizId);

        Mockito.when(testeeModel.getQuiz()).thenReturn(quizModel);

        testeesOptions.setTesteesService(testeesService);
        testeesOptions.setQuizzesService(quizzesService);
        testeesOptions.withQuiz(quizzesOptions);

        final InOrder inOrder = Mockito.inOrder(
                quizzesService, testeesService);

        testeesOptions.deleteWithRelations(testeeModel);
        inOrder.verify(quizzesService, times(1)).delete(testeeModel.getQuiz(), quizzesOptions);
        inOrder.verify(testeesService, times(1)).delete(testeeModel);
    }
}
