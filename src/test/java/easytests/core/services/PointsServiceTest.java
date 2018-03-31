package easytests.core.services;

import easytests.core.entities.PointEntity;
import easytests.core.mappers.PointsMapper;
import easytests.core.models.PointModelInterface;
import easytests.core.models.QuizModelInterface;
import easytests.core.options.PointsOptionsInterface;
import easytests.core.services.exceptions.DeleteUnidentifiedModelException;
import easytests.support.PointsSupport;
import easytests.support.QuizzesSupport;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author sakhprace
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PointsServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private PointsMapper pointsMapper;

    @Autowired
    private PointsService pointsService;

    private PointsSupport pointsSupport = new PointsSupport();

    private QuizzesSupport quizzesSupport = new QuizzesSupport();

    private List<PointEntity> getPointsFixturesEntities() {
        final List<PointEntity> pointEntities = new ArrayList<>(3);
        pointEntities.add(this.pointsSupport.getEntityFixtureMock(0));
        pointEntities.add(this.pointsSupport.getEntityFixtureMock(1));
        pointEntities.add(this.pointsSupport.getEntityFixtureMock(2));
        return pointEntities;
    }

    private List<PointModelInterface> getPointsFixturesModels() {
        final List<PointModelInterface> pointsModels = new ArrayList<>(3);
        pointsModels.add(this.pointsSupport.getModelFixtureMock(0));
        pointsModels.add(this.pointsSupport.getModelFixtureMock(1));
        pointsModels.add(this.pointsSupport.getModelFixtureMock(2));
        return pointsModels;
    }

    private List<PointModelInterface> getPointsAdditionalModels() {
        final List<PointModelInterface> pointsModels = new ArrayList<>(2);
        pointsModels.add(this.pointsSupport.getModelAdditionalMock(0));
        pointsModels.add(this.pointsSupport.getModelAdditionalMock(1));
        return pointsModels;
    }

    private void assertServicesSet(PointsOptionsInterface pointsOptions) {
        this.assertServicesSet(pointsOptions, 1);
    }

    private void assertServicesSet(PointsOptionsInterface pointsOptions, Integer times) {
        verify(pointsOptions, times(times)).setQuizzesService(any(QuizzesServiceInterface.class));
        verify(pointsOptions, times(times)).setSolutionsService(any(SolutionsServiceInterface.class));
        verify(pointsOptions, times(times)).setPointsService(this.pointsService);
    }

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<PointEntity> pointEntities = this.getPointsFixturesEntities();
        when(this.pointsMapper.findAll()).thenReturn(pointEntities);

        final List<PointModelInterface> pointsFoundedModels = this.pointsService.findAll();

        this.pointsSupport.assertModelsListEquals(this.getPointsFixturesModels(), pointsFoundedModels);
    }

    @Test
    public void testFindAllAbsentList() throws Exception {
        when(this.pointsMapper.findAll()).thenReturn(new ArrayList<>(0));

        final List<PointModelInterface> pointsModels = this.pointsService.findAll();

        Assert.assertNotNull(pointsModels);
        Assert.assertEquals(0, pointsModels.size());
    }

    @Test
    public void testFindAllWithOptions() throws Exception {
        final ArgumentCaptor<List> listCaptor = ArgumentCaptor.forClass(List.class);
        final List<PointEntity> pointEntities = getPointsFixturesEntities();
        final List<PointModelInterface> pointModels = getPointsFixturesModels();
        final PointsOptionsInterface pointsOptions = mock(PointsOptionsInterface.class);
        when(this.pointsMapper.findAll()).thenReturn(pointEntities);
        when(pointsOptions.withRelations(listCaptor.capture())).thenReturn(pointModels);

        final List<PointModelInterface> pointFoundedModels = this.pointsService.findAll(pointsOptions);

        this.assertServicesSet(pointsOptions);
        this.pointsSupport.assertModelsListEquals(pointFoundedModels, listCaptor.getValue());
        Assert.assertSame(pointModels, pointFoundedModels);
    }

    @Test
    public void testFindPresentModel() throws Exception {
        final PointEntity pointEntity = this.pointsSupport.getEntityFixtureMock(0);
        when(this.pointsMapper.find(pointEntity.getId())).thenReturn(pointEntity);

        final PointModelInterface pointFoundedModel = this.pointsService.find(pointEntity.getId());

        this.pointsSupport.assertEquals(this.pointsSupport.getModelFixtureMock(0), pointFoundedModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer absentId = 10;
        when(this.pointsMapper.find(absentId)).thenReturn(null);

        final PointModelInterface pointFoundedModel = this.pointsService.find(absentId);

        Assert.assertNull(pointFoundedModel);
    }

    @Test
    public void testFindWithOptions() throws Exception {
        final ArgumentCaptor<PointModelInterface> pointModelCaptor = ArgumentCaptor.forClass(PointModelInterface.class);
        final PointEntity pointEntity = this.pointsSupport.getEntityFixtureMock(0);
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);
        final PointModelInterface pointModel = this.pointsSupport.getModelFixtureMock(0);
        when(this.pointsMapper.find(pointEntity.getId())).thenReturn(pointEntity);
        when(pointsOptions.withRelations(pointModelCaptor.capture())).thenReturn(pointModel);

        final PointModelInterface pointFoundedModel = this.pointsService.find(pointEntity.getId(), pointsOptions);

        this.assertServicesSet(pointsOptions);
        this.pointsSupport.assertEquals(pointModel, pointModelCaptor.getValue());
        Assert.assertSame(pointModel, pointFoundedModel);
    }

    @Test
    public void testFindByQuizPresentList() throws Exception {
        final QuizModelInterface quizModel = this.quizzesSupport.getModelFixtureMock(0);
        final List<PointEntity> pointsEntities = this.getPointsFixturesEntities();
        when(this.pointsMapper.findByQuizId(quizModel.getId())).thenReturn(pointsEntities);

        final List<PointModelInterface> pointsFoundedModels = this.pointsService.findByQuiz(quizModel);

        this.pointsSupport.assertModelsListEquals(this.getPointsFixturesModels(), pointsFoundedModels);
    }

    @Test
    public void testFindByQuizAbsentList() throws Exception {
        final QuizModelInterface quizModel = this.quizzesSupport.getModelFixtureMock(0);
        when(this.pointsMapper.findByQuizId(quizModel.getId())).thenReturn(new ArrayList<>(0));

        final List<PointModelInterface> pointFoundedModels = this.pointsService.findByQuiz(quizModel);

        Assert.assertEquals(0, pointFoundedModels.size());
    }

    @Test
    public void testFindByQuizWithOptions() throws Exception {
        final ArgumentCaptor<List> listCaptor = ArgumentCaptor.forClass(List.class);
        final QuizModelInterface quizModel = this.quizzesSupport.getModelFixtureMock(0);
        final List<PointEntity> pointsEntities = this.getPointsFixturesEntities();
        when(this.pointsMapper.findByQuizId(quizModel.getId())).thenReturn(pointsEntities);
        final List<PointModelInterface> pointsModels = this.getPointsFixturesModels();
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);
        when(pointsOptions.withRelations(listCaptor.capture())).thenReturn(pointsModels);

        final List<PointModelInterface> pointsFoundedModels = this.pointsService.findByQuiz(quizModel, pointsOptions);

        this.assertServicesSet(pointsOptions);
        this.pointsSupport.assertModelsListEquals(pointsModels, listCaptor.getValue());
        Assert.assertSame(pointsModels, pointsFoundedModels);
    }

    @Test
    public void testSaveCreatesEntity() throws Exception {
        final ArgumentCaptor<PointEntity> pointEntityCaptor = ArgumentCaptor.forClass(PointEntity.class);
        final PointModelInterface pointModel = this.pointsSupport.getModelAdditionalMock(0);

        this.pointsService.save(pointModel);

        verify(this.pointsMapper, times(1)).insert(pointEntityCaptor.capture());
        this.pointsSupport.assertEquals(this.pointsSupport.getEntityAdditionalMock(0), pointEntityCaptor.getValue());
    }

    @Test
    public void testSaveUpdateEntityIdOnCreation() throws Exception {
        final Integer id = 5;
        final PointModelInterface pointModel = this.pointsSupport.getModelAdditionalMock(0);
        doAnswer(invocation -> {
            final PointEntity answerEntity = (PointEntity) invocation.getArguments()[0];
            answerEntity.setId(id);
            return null;
        }).when(this.pointsMapper).insert(any());

        this.pointsService.save(pointModel);

        verify(pointModel, times(1)).setId(id);
    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {
        final ArgumentCaptor<PointEntity> pointEntityCaptor = ArgumentCaptor.forClass(PointEntity.class);
        final PointModelInterface pointModel = this.pointsSupport.getModelFixtureMock(0);

        this.pointsService.save(pointModel);

        verify(this.pointsMapper, times(1)).update(pointEntityCaptor.capture());
        this.pointsSupport.assertEquals(this.pointsSupport.getEntityFixtureMock(0), pointEntityCaptor.getValue());
    }

    @Test
    public void testSaveWithOptions() throws Exception {
        final PointModelInterface pointModel = this.pointsSupport.getModelFixtureMock(0);
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);

        this.pointsService.save(pointModel, pointsOptions);

        this.assertServicesSet(pointsOptions);
        verify(pointsOptions, times(1)).saveWithRelations(pointModel);
        verifyNoMoreInteractions(this.pointsMapper);
    }

    @Test
    public void testSaveModelsList() throws Exception {
        final ArgumentCaptor<PointEntity> pointEntityCaptor = ArgumentCaptor.forClass(PointEntity.class);
        final List<PointModelInterface> pointModels = this.getPointsFixturesModels();

        this.pointsService.save(pointModels);

        verify(this.pointsMapper, times(pointModels.size())).update(pointEntityCaptor.capture());
        this.pointsSupport.assertEntitiesListEquals(this.getPointsFixturesEntities(), pointEntityCaptor.getAllValues());
    }

    @Test
    public void testSaveModelsListWithOptions() throws Exception {
        final ArgumentCaptor<PointModelInterface> pointModelCaptor = ArgumentCaptor.forClass(PointModelInterface.class);
        final List<PointModelInterface> pointsModels = this.getPointsFixturesModels();
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);

        this.pointsService.save(pointsModels, pointsOptions);

        this.assertServicesSet(pointsOptions, pointsModels.size());
        verify(pointsOptions, times(pointsModels.size())).saveWithRelations(pointModelCaptor.capture());
        this.pointsSupport.assertModelsListEquals(pointsModels, pointModelCaptor.getAllValues());
        verifyNoMoreInteractions(this.pointsMapper);
    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {
        final ArgumentCaptor<PointEntity> pointEntityCaptor = ArgumentCaptor.forClass(PointEntity.class);

        this.pointsService.delete(this.pointsSupport.getModelFixtureMock(0));

        verify(this.pointsMapper, times(1)).delete(pointEntityCaptor.capture());
        this.pointsSupport.assertEquals(this.pointsSupport.getEntityFixtureMock(0), pointEntityCaptor.getValue());
    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {
        final PointModelInterface pointModel = this.pointsSupport.getModelAdditionalMock(0);

        exception.expect(DeleteUnidentifiedModelException.class);
        this.pointsService.delete(pointModel);
    }

    @Test
    public void testDeleteModelWithOptions() throws Exception {
        final PointModelInterface pointModel = this.pointsSupport.getModelFixtureMock(0);
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);

        this.pointsService.delete(pointModel, pointsOptions);

        this.assertServicesSet(pointsOptions);
        verify(pointsOptions, times(1)).deleteWithRelations(pointModel);
        verifyNoMoreInteractions(this.pointsMapper);
    }

    @Test
    public void testDeleteModelsList() throws Exception {
        final ArgumentCaptor<PointEntity> pointEntityCaptor = ArgumentCaptor.forClass(PointEntity.class);
        final List<PointModelInterface> pointsModels = this.getPointsFixturesModels();

        this.pointsService.delete(pointsModels);

        verify(this.pointsMapper, times(pointsModels.size())).delete(pointEntityCaptor.capture());
        this.pointsSupport.assertEntitiesListEquals(this.getPointsFixturesEntities(), pointEntityCaptor.getAllValues());
    }

    @Test
    public void testDeleteModelsListWithOptions() throws Exception {
        final ArgumentCaptor<PointModelInterface> pointModelCaptor = ArgumentCaptor.forClass(PointModelInterface.class);
        final List<PointModelInterface> pointsModels = this.getPointsFixturesModels();
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);

        this.pointsService.delete(pointsModels, pointsOptions);

        this.assertServicesSet(pointsOptions, pointsModels.size());
        verify(pointsOptions, times(pointsModels.size())).deleteWithRelations(pointModelCaptor.capture());
        this.pointsSupport.assertModelsListEquals(pointsModels, pointModelCaptor.getAllValues());
        verifyNoMoreInteractions(this.pointsMapper);
    }
}
