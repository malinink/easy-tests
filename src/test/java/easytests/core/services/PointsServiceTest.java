package easytests.core.services;

import easytests.core.entities.PointEntity;
import easytests.core.mappers.PointsMapper;
import easytests.core.models.*;
import easytests.core.options.PointsOptions;
import easytests.core.options.PointsOptionsInterface;
import easytests.core.services.exceptions.DeleteUnidentifiedModelException;
import easytests.support.Entities;
import easytests.support.Models;
import easytests.support.PointsSupport;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;


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

        this.pointsSupport.assertModelsListEquals(pointFoundedModels, listCaptor.getValue());
        Assert.assertSame(pointModels, pointFoundedModels);
        verify(this.pointsMapper, times(1)).findAll();
        verifyNoMoreInteractions(this.pointsMapper);
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

        final PointModelInterface pointModel = this.pointsService.find(absentId);

        Assert.assertNull(pointModel);

    }

    @Test
    public void testFindByQuiz() throws Exception {
        final Integer quizId = 2;
        final QuizModelInterface quizModel = mock(QuizModelInterface.class);
        when(quizModel.getId()).thenReturn(quizId);

        final List<PointEntity> pointsEntities = getPointsFixturesEntities();
        when(this.pointsMapper.findByQuizId(quizId)).thenReturn(pointsEntities);

        final List<PointModelInterface> pointsModels = getPointsFixturesModels();

        final List<PointModelInterface> pointsFoundedModels = this.pointsService.findByQuiz(quizModel);

        verify(this.pointsMapper, times(1)).findByQuizId(quizId);
        this.pointsSupport.assertModelsListEquals(pointsModels, pointsFoundedModels);

    }

    @Test
    public void testFindByAbsentQuiz() throws Exception {
        final PointModelInterface pointModel = this.pointsSupport.getModelAdditionalMock(2);

        final List<PointModelInterface> pointFoundedModels = this.pointsService.findByQuiz(pointModel.getQuiz());

        Assert.assertEquals(0, pointFoundedModels.size());
    }

    @Test
    public void testFindByQuizWithOptions() throws Exception {
        final ArgumentCaptor<List> listCaptor = ArgumentCaptor.forClass(List.class);

        final Integer quizId = 2;
        final QuizModelInterface quizModel = mock(QuizModelInterface.class);
        when(quizModel.getId()).thenReturn(quizId);

        final List<PointEntity> pointsEntities = getPointsFixturesEntities();
        when(this.pointsMapper.findByQuizId(quizId)).thenReturn(pointsEntities);

        final List<PointModelInterface> pointsModels = getPointsFixturesModels();

        final PointsOptionsInterface pointsOptions = mock(PointsOptions.class);
        when(pointsOptions.withRelations(listCaptor.capture())).thenReturn(pointsModels);

        final List<PointModelInterface> pointsFoundedModels = this.pointsService.findByQuiz(quizModel, pointsOptions);

        this.pointsSupport.assertModelsListEquals(pointsFoundedModels, listCaptor.getValue());
        Assert.assertSame(pointsModels, pointsFoundedModels);
        verify(this.pointsMapper).findByQuizId(quizId);
        verifyZeroInteractions(this.pointsMapper);
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

        this.pointsSupport.assertEquals(pointModel, pointModelCaptor.getValue());
        Assert.assertSame(pointModel, pointFoundedModel);
        verify(this.pointsMapper, times(1)).find(pointEntity.getId());
        verifyNoMoreInteractions(this.pointsMapper);
    }

    @Test
    public void testSaveCreatesEntity() throws Exception {

        final ArgumentCaptor<PointEntity> pointEntityCaptor = ArgumentCaptor.forClass(PointEntity.class);

        this.pointsService.save(this.pointsSupport.getModelAdditionalMock(0));

        verify(this.pointsMapper, times(1)).insert(pointEntityCaptor.capture());
        this.pointsSupport.assertEquals(this.pointsSupport.getEntityAdditionalMock(0), pointEntityCaptor.getValue());
    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {
        final ArgumentCaptor<PointEntity> pointEntityCaptor = ArgumentCaptor.forClass(PointEntity.class);

        this.pointsService.save(this.pointsSupport.getModelFixtureMock(0));

        verify(this.pointsMapper, times(1)).update(pointEntityCaptor.capture());
        this.pointsSupport.assertEquals(this.pointsSupport.getEntityFixtureMock(0), pointEntityCaptor.getValue());
    }

    @Test
    public void testSaveUpdateEntityIdOnCreation() throws Exception {
        final PointModelInterface pointAdditionalModel = this.pointsSupport.getModelAdditionalMock(0);
        doAnswer(invocation -> {
            final PointEntity answerEntity = (PointEntity) invocation.getArguments()[0];
            answerEntity.setId(5);
            return null;
        }).when(this.pointsMapper).insert(Mockito.any(PointEntity.class));

        this.pointsService.save(pointAdditionalModel);

        verify(pointAdditionalModel, times(1)).setId(5);
    }

    @Test
    public void testSaveCreatesListEntities() throws Exception {
        final ArgumentCaptor<PointEntity> pointEntityCaptor = ArgumentCaptor.forClass(PointEntity.class);
        final List<PointModelInterface> pointModels = getPointsAdditionalModels();

        this.pointsService.save(pointModels);

        verify(this.pointsMapper, times(2)).insert(pointEntityCaptor.capture());
        final List<PointEntity> capturedEntities = pointEntityCaptor.getAllValues();

        Integer index = 0;
        for (PointModelInterface pointModel: pointModels) {
            this.pointsSupport.assertEquals(pointModels.get(index), capturedEntities.get(index));
            index++;
        }
    }

    @Test
    public void testSaveWithOptions() throws Exception {
        final PointModelInterface pointModel = this.pointsSupport.getModelFixtureMock(0);
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);

        this.pointsService.save(pointModel, pointsOptions);

        verify(pointsOptions, times(1)).saveWithRelations(pointModel);
        verifyNoMoreInteractions(this.pointsMapper);
    }

    @Test
    public void testSaveCreatesListEntitiesWithOptions() throws Exception {
        final List<PointModelInterface> pointsModels = getPointsAdditionalModels();
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);

        this.pointsService.save(pointsModels, pointsOptions);
        Integer index = 0;
        for (PointModelInterface pointsModel: pointsModels) {
            verify(pointsOptions, times(1)).saveWithRelations(pointsModels.get(index));
            index++;
        }
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
    public void testDeleteModelsList() throws Exception {
        final ArgumentCaptor<PointEntity> pointEntityCaptor = ArgumentCaptor.forClass(PointEntity.class);
        final List<PointModelInterface> pointModels = getPointsFixturesModels();

        this.pointsService.delete(pointModels);
        verify(this.pointsMapper, times(3)).delete(pointEntityCaptor.capture());
        final List<PointEntity> capturedEntities = pointEntityCaptor.getAllValues();

        Integer index = 0;
        for (PointModelInterface pointModel: pointModels) {
            this.pointsSupport.assertEquals(pointModel, capturedEntities.get(index));
            index++;
        }
    }

    @Test
    public void testDeleteModelWithOptions() throws Exception {
        final ArgumentCaptor<PointModel> pointModelCaptor = ArgumentCaptor.forClass(PointModel.class);
        final PointsOptionsInterface pointsOptions = mock(PointsOptionsInterface.class);

        this.pointsService.delete(this.pointsSupport.getModelFixtureMock(0), pointsOptions);

        verify(pointsOptions, times(1)).deleteWithRelations(pointModelCaptor.capture());
        this.pointsSupport.assertEquals(this.pointsSupport.getModelFixtureMock(0), pointModelCaptor.getValue());
        verifyNoMoreInteractions(this.pointsMapper);
    }

    @Test
    public void testDeleteModelListWithOptions() throws Exception {
        final ArgumentCaptor<PointModel> pointModelCaptor = ArgumentCaptor.forClass(PointModel.class);
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);
        final List<PointModelInterface> pointsModels = getPointsFixturesModels();

        this.pointsService.delete(pointsModels, pointsOptions);

        verify(pointsOptions, times(3)).deleteWithRelations(pointModelCaptor.capture());
        final List<PointModel> capturedModels = pointModelCaptor.getAllValues();
        Integer index = 0;
        for (PointModelInterface pointModel: pointsModels) {
            this.pointsSupport.assertEquals(pointModel, capturedModels.get(index));
            index++;
        }
        verifyNoMoreInteractions(this.pointsMapper);
    }
}
