package easytests.services;

import easytests.entities.PointEntity;
import easytests.mappers.PointsMapper;
import easytests.models.PointModel;
import easytests.models.PointModelInterface;
import easytests.models.QuizModelInterface;
import easytests.services.exceptions.DeleteUnidentifiedModelException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
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

    private PointModelInterface createPointModel(Integer id, String type, String text, Integer quizId) {

        final QuizModelInterface quizModel = Mockito.mock(QuizModelInterface.class);
        Mockito.when(quizModel.getId()).thenReturn(quizId);

        final PointModelInterface pointModel = new PointModel();

        pointModel.setId(id);
        pointModel.setType(type);
        pointModel.setText(text);
        pointModel.setQuiz(quizModel);

        return pointModel;

    }

    private PointEntity createPointEntityMock(Integer id, String type, String text, Integer quizId) {

        final PointEntity pointEntity = Mockito.mock(PointEntity.class);

        Mockito.when(pointEntity.getId()).thenReturn(id);
        Mockito.when(pointEntity.getType()).thenReturn(type);
        Mockito.when(pointEntity.getText()).thenReturn(text);
        Mockito.when(pointEntity.getQuizId()).thenReturn(quizId);

        return pointEntity;

    }

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
        final PointEntity pointEntityFirst = this.createPointEntityMock(1, "type1", "text1", 1);
        final PointEntity pointEntitySecond = this.createPointEntityMock(2, "type2", "text2", 2);

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
    public void testFindAllAbsebtList() throws Exception {

        given(this.pointsMapper.findAll()).willReturn(new ArrayList<>(0));

        final List<PointModelInterface> pointsModels = this.pointsService.findAll();

        Assert.assertEquals(0, pointsModels.size());

    }

    @Test
    public void testFindPresentModel() throws Exception {

        final Integer id = 1;
        final PointEntity pointEntity = this.createPointEntityMock(id, "type1", "text1", 1);

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
    public void testSaveCreatesEntity() throws Exception {

        final PointModelInterface pointModel = this.createPointModel(null, "type1", "text1", 1);

        this.pointsService.save(pointModel);

        verify(this.pointsMapper, times(1)).insert(this.mapPointEntity(pointModel));

    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {

        final PointModelInterface pointModel = this.createPointModel(1, "type1", "text1", 1);

        this.pointsService.save(pointModel);

        verify(this.pointsMapper, times(1)).update(this.mapPointEntity(pointModel));

    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {

        final PointModelInterface pointModel = this.createPointModel(1, "type1", "text1", 1);

        this.pointsService.delete(pointModel);

        verify(this.pointsMapper, times(1)).delete(this.mapPointEntity(pointModel));

    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {

        final PointModelInterface pointModel = this.createPointModel(null, "type1", "text1", 1);

        exception.expect(DeleteUnidentifiedModelException.class);
        this.pointsService.delete(pointModel);

    }

}