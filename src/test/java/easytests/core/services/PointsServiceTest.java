package easytests.core.services;

import easytests.core.entities.PointEntity;
import easytests.core.mappers.PointsMapper;
import easytests.core.models.*;
import easytests.core.options.PointsOptions;
import easytests.core.options.PointsOptionsInterface;
import easytests.core.services.exceptions.DeleteUnidentifiedModelException;
import easytests.support.Entities;
import easytests.support.Models;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
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
 * @author nikitalpopov
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

    private PointModelInterface mapPointModel(PointEntity pointEntity) {

        final PointModelInterface pointModel = new PointModel();

        pointModel.map(pointEntity);

        return pointModel;

    }

    private PointEntity mapPointEntity(PointModelInterface pointModel) {

        final PointEntity pointEntity = new PointEntity();

        pointEntity.map(pointModel);

        return pointEntity;

    }

    private List<PointEntity> getPointsEntities() {

        final List<PointEntity> pointEntities = new ArrayList<>(2);
        final PointEntity pointEntityFirst = Entities.createPointEntityMock(1, 1, 1);
        final PointEntity pointEntitySecond = Entities.createPointEntityMock(2, 2, 2);

        pointEntities.add(pointEntityFirst);
        pointEntities.add(pointEntitySecond);

        return pointEntities;

    }

    private List<PointModelInterface> getPointsModels() {

        final List<PointModelInterface> pointsModels = new ArrayList<>(2);

        for (PointEntity pointEntity : this.getPointsEntities()) {
            pointsModels.add(this.mapPointModel(pointEntity));
        }

        return pointsModels;

    }

    @Test
    public void testFindAllPresentList() throws Exception {

        final List<PointEntity> pointsEntities = getPointsEntities();

        given(this.pointsMapper.findAll()).willReturn(pointsEntities);

        final List<PointModelInterface> pointsModels = this.pointsService.findAll();

        Assert.assertEquals(2, pointsModels.size());
        Assert.assertEquals(this.mapPointModel(pointsEntities.get(0)), pointsModels.get(0));
        Assert.assertEquals(this.mapPointModel(pointsEntities.get(1)), pointsModels.get(1));

    }

    @Test
    public void testFindAllAbsentList() throws Exception {

        given(this.pointsMapper.findAll()).willReturn(new ArrayList<>(0));

        final List<PointModelInterface> pointsModels = this.pointsService.findAll();

        Assert.assertEquals(0, pointsModels.size());

    }

    @Test
    public void testFindPresentModel() throws Exception {

        final Integer id = 1;
        final PointEntity pointEntity = Entities.createPointEntityMock(id, 1, 1);

        given(this.pointsMapper.find(id)).willReturn(pointEntity);

        final PointModelInterface pointModel = this.pointsService.find(id);

        Assert.assertEquals(this.mapPointModel(pointEntity), pointModel);

    }

    @Test
    public void testFindAbsentModel() throws Exception {

        final Integer id = 5;

        given(this.pointsMapper.find(id)).willReturn(null);

        final PointModelInterface pointModel = this.pointsService.find(id);

        Assert.assertNull(pointModel);

    }

    @Test
    public void testFindByQuiz() throws Exception {

        final Integer quizId = 5;
        final QuizModelInterface quizModel = Mockito.mock(QuizModelInterface.class);
        Mockito.when(quizModel.getId()).thenReturn(quizId);

        final PointEntity pointEntityFirst = Entities.createPointEntityMock(1, 1, 1);
        final PointEntity pointEntitySecond = Entities.createPointEntityMock(4, 4, 4);
        final List<PointEntity> pointsEntities = new ArrayList<>();
        pointsEntities.add(pointEntityFirst);
        pointsEntities.add(pointEntitySecond);
        given(this.pointsMapper.findByQuizId(quizId)).willReturn(pointsEntities);

        final List<PointModelInterface> pointsModels = new ArrayList<>();
        pointsModels.add(this.mapPointModel(pointEntityFirst));
        pointsModels.add(this.mapPointModel(pointEntitySecond));

        final List<PointModelInterface> foundPointsModels = this.pointsService.findByQuiz(quizModel);

        verify(this.pointsMapper).findByQuizId(quizId);
        Assert.assertEquals(pointsModels, foundPointsModels);

    }

    @Test
    @Ignore
    public void testFindByQuizWithOptions() throws Exception {

        final Integer quizId = 5;
        final QuizModelInterface quizModel = Mockito.mock(QuizModelInterface.class);
        Mockito.when(quizModel.getId()).thenReturn(quizId);

        final PointEntity pointEntityFirst = Entities.createPointEntityMock(1, 1, 1);
        final PointEntity pointEntitySecond = Entities.createPointEntityMock(4, 4, 4);
        final List<PointEntity> pointsEntities = new ArrayList<>();
        pointsEntities.add(pointEntityFirst);
        pointsEntities.add(pointEntitySecond);
        given(this.pointsMapper.findByQuizId(quizId)).willReturn(pointsEntities);

        final List<PointModelInterface> pointsModels = new ArrayList<>();
        pointsModels.add(this.mapPointModel(pointEntityFirst));
        pointsModels.add(this.mapPointModel(pointEntitySecond));

        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptions.class);
        given(pointsOptions.withRelations(pointsModels)).willReturn(pointsModels);

        final List<PointModelInterface> foundPointsModels = this.pointsService.findByQuiz(quizModel);

        verify(this.pointsMapper).findByQuizId(quizId);
        verify(pointsOptions).withRelations(pointsModels);
        Assert.assertEquals(pointsModels, foundPointsModels);

    }

    @Test
    public void testFindWithOptions() throws Exception {

        final Integer id = 1;
        final Integer questionId = 1;
        final Integer quizId = 2;
        final PointEntity pointEntity = Entities.createPointEntityMock(4, questionId, quizId);
        final PointModelInterface pointModel = this.mapPointModel(pointEntity);
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);

        given(this.pointsMapper.find(id)).willReturn(pointEntity);
        given(pointsOptions.withRelations(pointModel)).willReturn(pointModel);

        final PointModelInterface foundPointModel = this.pointsService.find(id, pointsOptions);

        verify(pointsOptions).withRelations(pointModel);
        Assert.assertEquals(pointModel, foundPointModel);

    }

    @Test
    public void testFindAllWithOptions() throws Exception {

        final List<PointEntity> pointsEntities = this.getPointsEntities();
        final List<PointModelInterface> pointsModels = this.getPointsModels();
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);

        given(this.pointsMapper.findAll()).willReturn(pointsEntities);
        given(pointsOptions.withRelations(Mockito.anyList())).willReturn(pointsModels);

        final List<PointModelInterface> foundPointsModels = this.pointsService.findAll(pointsOptions);

        verify(pointsOptions).withRelations(foundPointsModels);
        Assert.assertEquals(pointsModels, foundPointsModels);

    }

    @Test
    public void testSaveCreatesEntity() throws Exception {

        final PointModelInterface pointModel = Models.createPointModel(null, 1, 1);
        doAnswer(invocation -> {
            final PointEntity pointEntity = (PointEntity) invocation.getArguments()[0];
            pointEntity.setId(5);
            return null;
        }).when(this.pointsMapper).insert(Mockito.any(PointEntity.class));

        this.pointsService.save(pointModel);

        // TODO verify(this.pointsMapper, times(1)).insert(this.mapPointEntity(pointModel));

        Assert.assertEquals((Integer) 5, pointModel.getId());

    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {

        final PointModelInterface pointModel = Models.createPointModel(1, 1, 1);

        this.pointsService.save(pointModel);

        verify(this.pointsMapper, times(1)).update(this.mapPointEntity(pointModel));

    }

    @Test
    public void testSaveWithOptions() throws Exception {

        final PointModelInterface pointModel = Models.createPointModel(null, 1,1);
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);

        this.pointsService.save(pointModel, pointsOptions);

        verify(pointsOptions).saveWithRelations(pointModel);

    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {

        final PointModelInterface pointModel = Models.createPointModel(1, 1, 1);

        this.pointsService.delete(pointModel);

        verify(this.pointsMapper, times(1)).delete(this.mapPointEntity(pointModel));

    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {

        final PointModelInterface pointModel = Models.createPointModel(null, 1, 1);

        exception.expect(DeleteUnidentifiedModelException.class);
        this.pointsService.delete(pointModel);

    }

    @Test
    @Ignore
    public void testDeleteModelsList() throws Exception {

        final PointModelInterface pointModelFirst = Models.createPointModel(2, 2, 2);
        final PointModelInterface pointModelSecond = Models.createPointModel(3, 3, 3);
        final List<PointModelInterface> pointsModels = new ArrayList<>();
        pointsModels.add(pointModelFirst);
        pointsModels.add(pointModelSecond);

        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);

        final PointsServiceInterface pointsServiceSpy = Mockito.spy(pointsService);

        pointsServiceSpy.delete(pointsModels);
        verify(pointsServiceSpy, times(1)).delete(pointModelFirst);
        verify(pointsServiceSpy, times(1)).delete(pointModelSecond);

        pointsServiceSpy.delete(pointsModels);
        verify(pointsServiceSpy, times(1)).delete(pointModelFirst, pointsOptions);
        verify(pointsServiceSpy, times(1)).delete(pointModelSecond, pointsOptions);

    }

    @Test
    public void testDeleteWithOptions() throws Exception {

        final PointModelInterface pointModel = Models.createPointModel(null, 1, 1);
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);

        this.pointsService.delete(pointModel, pointsOptions);

        verify(pointsOptions).deleteWithRelations(pointModel);

    }

}
