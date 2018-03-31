package easytests.core.services;

import easytests.core.entities.TesteeEntity;
import easytests.core.mappers.TesteesMapper;
import easytests.core.models.TesteeModel;
import easytests.core.models.TesteeModelInterface;
import easytests.core.models.QuizModelInterface;
import easytests.core.options.TesteesOptionsInterface;
import easytests.core.services.exceptions.DeleteUnidentifiedModelException;
import easytests.support.QuizzesSupport;
import easytests.support.TesteesSupport;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.mockito.BDDMockito.*;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DoZor-80
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TesteesServiceTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private TesteesMapper testeesMapper;

    @Autowired
    private TesteesService testeesService;

    private TesteesSupport testeesSupport = new TesteesSupport();

    private QuizzesSupport quizzesSupport = new QuizzesSupport();

    private TesteeModelInterface mapTesteeModel(TesteeEntity testeeEntity) {
        final TesteeModelInterface testeeModel = new TesteeModel();
        testeeModel.map(testeeEntity);
        return testeeModel;
    }

    private List<TesteeEntity> getTesteesEntities() {
        final List<TesteeEntity> testeesEntities = new ArrayList<>(2);
        testeesEntities.add(this.testeesSupport.getEntityFixtureMock(0));
        testeesEntities.add(this.testeesSupport.getEntityFixtureMock(1));
        return testeesEntities;
    }

    private List<TesteeModelInterface> getTesteesModels() {
        final List<TesteeModelInterface> testeesModels = new ArrayList<>(2);
        testeesModels.add(this.testeesSupport.getModelFixtureMock(0));
        testeesModels.add(this.testeesSupport.getModelFixtureMock(1));
        return testeesModels;
    }

    private void assertServicesSet(TesteesOptionsInterface testeesOptions) {
        this.assertServicesSet(testeesOptions, 1);
    }

    private void assertServicesSet(TesteesOptionsInterface testeesOptions, Integer times) {
        verify(testeesOptions, times(times)).setQuizzesService(any(QuizzesService.class));
        verify(testeesOptions, times(times)).setTesteesService(any(TesteesService.class));
    }

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<TesteeEntity> testeesEntities = this.getTesteesEntities();
        when(this.testeesMapper.findAll()).thenReturn(testeesEntities);

        final List<TesteeModelInterface> testeesModels = this.testeesService.findAll();

        this.testeesSupport.assertModelsListEquals(this.getTesteesModels(), testeesModels);
    }

    @Test
    public void testFindAllAbsentList() throws Exception {
        when(this.testeesMapper.findAll()).thenReturn(new ArrayList<>(0));

        final List<TesteeModelInterface> testeesModels = this.testeesService.findAll();

        Assert.assertEquals(0, testeesModels.size());
    }

    @Test
    public void testFindAllWithOptions() throws Exception {
        final ArgumentCaptor<List> listCaptor = ArgumentCaptor.forClass(List.class);
        final List<TesteeEntity> testeesEntities = this.getTesteesEntities();
        final List<TesteeModelInterface> testeesModels = this.getTesteesModels();
        final TesteesOptionsInterface testeesOptions = Mockito.mock(TesteesOptionsInterface.class);
        when(this.testeesMapper.findAll()).thenReturn(testeesEntities);
        when(testeesOptions.withRelations(listCaptor.capture())).thenReturn(testeesModels);

        final List<TesteeModelInterface> testeesFoundedModels = this.testeesService.findAll(testeesOptions);

        this.assertServicesSet(testeesOptions);
        this.testeesSupport.assertModelsListEquals(testeesModels, listCaptor.getValue());
        Assert.assertSame(testeesModels, testeesFoundedModels);
    }

    @Test
    public void testFindPresentModel() throws Exception {
        final TesteeEntity testeeEntity = this.testeesSupport.getEntityFixtureMock(0);
        when(this.testeesMapper.find(0)).thenReturn(testeeEntity);

        final TesteeModelInterface testeeFoundedModel = this.testeesService.find(0);

        this.testeesSupport.assertEquals(this.testeesSupport.getModelFixtureMock(0), testeeFoundedModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        when(this.testeesMapper.find(id)).thenReturn(null);

        final TesteeModelInterface testeeModel = this.testeesService.find(id);

        Assert.assertNull(testeeModel);
    }

    @Test
    public void testFindWithOptions() throws Exception {
        final ArgumentCaptor<TesteeModelInterface> testeeModelCaptor = ArgumentCaptor.forClass(TesteeModelInterface.class);
        final TesteeEntity testeeEntity = this.testeesSupport.getEntityFixtureMock(0);
        final TesteeModelInterface testeeModel = this.testeesSupport.getModelFixtureMock(0);
        final TesteesOptionsInterface testeesOptions = Mockito.mock(TesteesOptionsInterface.class);
        when(this.testeesMapper.find(testeeModel.getId())).thenReturn(testeeEntity);
        when(testeesOptions.withRelations(testeeModelCaptor.capture())).thenReturn(testeeModel);

        final TesteeModelInterface testeeFoundedModel = this.testeesService.find(testeeModel.getId(), testeesOptions);

        this.assertServicesSet(testeesOptions);
        this.testeesSupport.assertEquals(testeeModel, testeeModelCaptor.getValue());
        Assert.assertSame(testeeModel, testeeFoundedModel);
    }

    @Test
    public void testFindByQuizPresentModel() throws Exception {
        final QuizModelInterface quizModel = this.quizzesSupport.getModelFixtureMock(0);
        final TesteeEntity testeeEntity = this.testeesSupport.getEntityFixtureMock(0);
        when(this.testeesMapper.findByQuizId(quizModel.getId())).thenReturn(testeeEntity);

        final TesteeModelInterface testeeFoundedModel = this.testeesService.findByQuiz(quizModel);

        this.testeesSupport.assertEquals(this.testeesSupport.getModelFixtureMock(0), testeeFoundedModel);
    }

    @Test
    public void testFindByQuizWithOptions() throws Exception {
        final ArgumentCaptor<TesteeModelInterface> testeeModelCaptor = ArgumentCaptor.forClass(TesteeModelInterface.class);
        final QuizModelInterface quizModel = this.quizzesSupport.getModelFixtureMock(0);
        final TesteeEntity testeeEntity = this.testeesSupport.getEntityFixtureMock(0);
        final TesteeModelInterface testeeModel = this.mapTesteeModel(testeeEntity);

        final TesteesOptionsInterface testeesOptions = Mockito.mock(TesteesOptionsInterface.class);
        when(this.testeesMapper.findByQuizId(quizModel.getId())).thenReturn(testeeEntity);
        when(testeesOptions.withRelations(testeeModelCaptor.capture())).thenReturn(testeeModel);

        final TesteeModelInterface testeeFoundedModel
                = this.testeesService.findByQuiz(testeeModel.getQuiz(), testeesOptions);

        this.assertServicesSet(testeesOptions);
        this.testeesSupport.assertEquals(testeeModel, testeeModelCaptor.getValue());
        Assert.assertSame(testeeModel, testeeFoundedModel);
    }

    @Test
    public void testFindByQuizAbsentModel() throws Exception {
        final QuizModelInterface quizModel = this.quizzesSupport.getModelFixtureMock(0);
        given(this.testeesMapper.findByQuizId(quizModel.getId())).willReturn(null);

        final TesteeModelInterface testeeFoundedModel = this.testeesService.findByQuiz(quizModel);
        Assert.assertNull(testeeFoundedModel);
    }

    @Test
    public void testSaveCreatesEntity() throws Exception {
        final ArgumentCaptor<TesteeEntity> testeeEntityCaptor = ArgumentCaptor.forClass(TesteeEntity.class);
        final TesteeModelInterface testeeModel = this.testeesSupport.getModelAdditionalMock(0);

        this.testeesService.save(testeeModel);

        verify(this.testeesMapper, times(1)).insert(testeeEntityCaptor.capture());
        this.testeesSupport.assertEquals(this.testeesSupport.getEntityAdditionalMock(0), testeeEntityCaptor.getValue());
    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {
        final ArgumentCaptor<TesteeEntity> testeeEntityCaptor = ArgumentCaptor.forClass(TesteeEntity.class);
        final TesteeModelInterface testeeModel = this.testeesSupport.getModelFixtureMock(0);

        this.testeesService.save(testeeModel);

        verify(this.testeesMapper, times(1)).update(testeeEntityCaptor.capture());
        this.testeesSupport.assertEquals(this.testeesSupport.getEntityFixtureMock(0), testeeEntityCaptor.getValue());
    }

    @Test
    public void testSaveWithOptions() throws Exception {
        final TesteeModelInterface testeeModel = this.testeesSupport.getModelFixtureMock(0);
        final TesteesOptionsInterface testeesOptions = Mockito.mock(TesteesOptionsInterface.class);

        this.testeesService.save(testeeModel, testeesOptions);

        this.assertServicesSet(testeesOptions);
        verify(testeesOptions, times(1)).saveWithRelations(testeeModel);
        verifyNoMoreInteractions(this.testeesMapper);
    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {
        final ArgumentCaptor<TesteeEntity> testeeEntityCaptor = ArgumentCaptor.forClass(TesteeEntity.class);

        this.testeesService.delete(this.testeesSupport.getModelFixtureMock(0));

        verify(this.testeesMapper, times(1)).delete(testeeEntityCaptor.capture());
        this.testeesSupport.assertEquals(this.testeesSupport.getEntityFixtureMock(0), testeeEntityCaptor.getValue());
    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {
        final TesteeModelInterface testeeModel = this.testeesSupport.getModelAdditionalMock(0);

        exception.expect(DeleteUnidentifiedModelException.class);
        this.testeesService.delete(testeeModel);
    }

    @Test
    public void testDeleteWithOptions() throws Exception {
        final TesteeModelInterface testeeModel = this.testeesSupport.getModelFixtureMock(0);
        final TesteesOptionsInterface testeesOptions = Mockito.mock(TesteesOptionsInterface.class);

        this.testeesService.delete(testeeModel, testeesOptions);

        this.assertServicesSet(testeesOptions);
        verify(testeesOptions, times(1)).deleteWithRelations(testeeModel);
        verifyNoMoreInteractions(testeesMapper);
    }
}
