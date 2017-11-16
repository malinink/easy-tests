package easytests.core.services;

import easytests.core.entities.SolutionEntity;
import easytests.core.mappers.SolutionsMapper;
import easytests.core.models.AnswerModelInterface;
import easytests.core.models.PointModelInterface;
import easytests.core.models.SolutionModel;
import easytests.core.models.SolutionModelInterface;
import java.util.ArrayList;
import java.util.List;

import easytests.core.options.SolutionsOptionsInterface;
import easytests.core.services.exceptions.DeleteUnidentifiedModelException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.BDDMockito.*;

/**
 * @author SingularityA
 * @author Loriens
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SolutionsServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private SolutionsMapper solutionsMapper;

    @InjectMocks
    private SolutionsService solutionsService;

    private SolutionEntity createSolutionEntityMock(Integer id, Integer answerId, Integer pointId) {
        final SolutionEntity solutionEntity = mock(SolutionEntity.class);
        when(solutionEntity.getId()).thenReturn(id);
        when(solutionEntity.getAnswerId()).thenReturn(answerId);
        when(solutionEntity.getPointId()).thenReturn(pointId);
        return solutionEntity;
    }

    private SolutionModelInterface createSolutionModel(Integer id, Integer answerId, Integer pointId) {
        final SolutionModelInterface solutionModel = new SolutionModel();

        final AnswerModelInterface answerModel = mock(AnswerModelInterface.class);
        when(answerModel.getId()).thenReturn(answerId);

        final PointModelInterface pointModel = mock(PointModelInterface.class);
        when(pointModel.getId()).thenReturn(pointId);

        solutionModel.setId(id);
        solutionModel.setAnswer(answerModel);
        solutionModel.setPoint(pointModel);
        return solutionModel;
    }

    private SolutionEntity mapSolutionEntity(SolutionModelInterface solutionModel) {
        final SolutionEntity solutionEntity = new SolutionEntity();
        solutionEntity.map(solutionModel);
        return solutionEntity;
    }

    private SolutionModelInterface mapSolutionModel(SolutionEntity solutionEntity) {
        final SolutionModelInterface solutionModel = new SolutionModel();
        solutionModel.map(solutionEntity);
        return solutionModel;
    }

    private List<SolutionEntity> getSolutionsEntities() {
        final List<SolutionEntity> solutionsEntities = new ArrayList<>(2);
        solutionsEntities.add(this.createSolutionEntityMock(1, 10, 1));
        solutionsEntities.add(this.createSolutionEntityMock(2, 20, 1));
        return solutionsEntities;
    }

    private List<SolutionModelInterface> getSolutionsModels(List<SolutionEntity> solutionsEntities) {
        final List<SolutionModelInterface> usersModels = new ArrayList<>(2);
        for (SolutionEntity solutionEntity: solutionsEntities) {
            usersModels.add(this.mapSolutionModel(solutionEntity));
        }
        return usersModels;
    }

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<SolutionEntity> solutionEntities = new ArrayList<>(5);
        solutionEntities.add(this.createSolutionEntityMock(1, 10, 1));
        solutionEntities.add(this.createSolutionEntityMock(2, 20, 1));
        solutionEntities.add(this.createSolutionEntityMock(3, 11, 2));
        solutionEntities.add(this.createSolutionEntityMock(4, 21, 2));
        solutionEntities.add(this.createSolutionEntityMock(5, 12, 3));
        given(this.solutionsMapper.findAll()).willReturn(solutionEntities);

        final List<SolutionModelInterface> solutionModels = this.solutionsService.findAll();

        Assert.assertNotNull(solutionModels);
        Assert.assertEquals(solutionEntities.size(), solutionModels.size());
        for (int i = 0; i < solutionEntities.size(); i++) {
            Assert.assertEquals(solutionModels.get(i), this.mapSolutionModel(solutionEntities.get(i)));
        }
    }

    @Test
    public void testFindAllAbsentList() throws Exception {
        given(this.solutionsMapper.findAll()).willReturn(new ArrayList<>(0));

        final List<SolutionModelInterface> solutionModels = this.solutionsService.findAll();

        Assert.assertNotNull(solutionModels);
        Assert.assertEquals(0, solutionModels.size());
    }

    @Test
    public void testFindAllWithOptions() throws Exception {
        final List<SolutionEntity> solutionsEntities = this.getSolutionsEntities();

        final List<SolutionModelInterface> solutionsModels = this.getSolutionsModels(solutionsEntities);
        final SolutionsOptionsInterface solutionsOptions = Mockito.mock(SolutionsOptionsInterface.class);
        given(this.solutionsMapper.findAll()).willReturn(solutionsEntities);
        given(solutionsOptions.withRelations(Mockito.anyList())).willReturn(solutionsModels);

        final List<SolutionModelInterface> foundedSolutionsModels = this.solutionsService.findAll(solutionsOptions);

        verify(solutionsOptions).withRelations(solutionsModels);
        Assert.assertEquals(solutionsModels, foundedSolutionsModels);
    }

    @Test
    public void testFindPresentModel() throws Exception {
        Integer id = 1;
        final SolutionEntity solutionEntity = this.createSolutionEntityMock(id, 2, 3);
        given(this.solutionsMapper.find(id)).willReturn(solutionEntity);

        final SolutionModelInterface solutionModel = this.solutionsService.find(id);
        Assert.assertNotNull(solutionModel);
        Assert.assertEquals(solutionModel, this.mapSolutionModel(solutionEntity));
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        Integer id = 20;
        given(this.solutionsMapper.find(id)).willReturn(null);

        final SolutionModelInterface solutionModel = this.solutionsService.find(id);
        Assert.assertNull(solutionModel);
    }

    @Test
    public void testFindByPointPresentModel() throws Exception {
        Integer pointId = 1;
        final List<SolutionEntity> solutionEntities = new ArrayList<>(2);
        solutionEntities.add(this.createSolutionEntityMock(1, 10, pointId));
        solutionEntities.add(this.createSolutionEntityMock(2, 20, pointId));

        given(this.solutionsMapper.findByPointId(pointId)).willReturn(solutionEntities);

        final PointModelInterface pointModel = mock(PointModelInterface.class);
        when(pointModel.getId()).thenReturn(pointId);

        final List<SolutionModelInterface> solutionModels = this.solutionsService.findByPoint(pointModel);

        Assert.assertNotNull(solutionModels);
        Assert.assertEquals(solutionEntities.size(), solutionModels.size());
        for (int i = 0; i < solutionEntities.size(); i++) {
            Assert.assertEquals(solutionModels.get(i), this.mapSolutionModel(solutionEntities.get(i)));
        }
    }

    @Test
    public void testFindByPointAbsentModel() throws Exception {
        Integer pointId = 10;
        given(this.solutionsMapper.findByPointId(pointId)).willReturn(new ArrayList<>(0));

        final PointModelInterface pointModel = mock(PointModelInterface.class);
        when(pointModel.getId()).thenReturn(pointId);

        final List<SolutionModelInterface> solutionModels = this.solutionsService.findByPoint(pointModel);

        Assert.assertNotNull(solutionModels);
        Assert.assertEquals(0, solutionModels.size());
    }

    @Test
    public void testFindWithOptions() throws Exception {
        final Integer id = 1;
        final SolutionEntity solutionEntity = this.createSolutionEntityMock(
                id,
                1,
                1
        );
        final SolutionModelInterface solutionModel = this.mapSolutionModel(solutionEntity);
        final SolutionsOptionsInterface solutionsOptions = Mockito.mock(SolutionsOptionsInterface.class);
        given(this.solutionsMapper.find(id)).willReturn(solutionEntity);
        given(solutionsOptions.withRelations(solutionModel)).willReturn(solutionModel);

        final SolutionModelInterface foundedSolutionModel = this.solutionsService.find(id, solutionsOptions);

        Assert.assertEquals(solutionModel, foundedSolutionModel);
        verify(solutionsOptions).withRelations(solutionModel);
    }

    @Test
    public void testSaveInsertsEntity() throws Exception {
        final SolutionModelInterface solutionModel = this.createSolutionModel(null, 13, 4);
        final Integer id = 5;

        doAnswer(invocations -> {
            final SolutionEntity solutionEntity = (SolutionEntity) invocations.getArguments()[0];
            solutionEntity.setId(id);
            return null;
        }).when(this.solutionsMapper).insert(Mockito.any(SolutionEntity.class));

        this.solutionsService.save(solutionModel);

        // TODO verify(this.solutionsMapper, times(1)).insert(this.mapSolutionEntity(solutionModel));
        Assert.assertEquals(id, solutionModel.getId());
    }

    @Test
    public void testSaveEntitiesList() throws Exception {
        final SolutionModelInterface solutionModelFirst = this.createSolutionModel(1, 13, 4);
        final SolutionModelInterface solutionModelSecond = this.createSolutionModel(2, 10, 3);

        final SolutionsOptionsInterface solutionsOptions = Mockito.mock(SolutionsOptionsInterface.class);

        final List<SolutionModelInterface> solutionModels = new ArrayList<>();
        solutionModels.add(solutionModelFirst);
        solutionModels.add(solutionModelSecond);

        final SolutionsServiceInterface solutionsServiceSpy = Mockito.spy(solutionsService);

        solutionsServiceSpy.save(solutionModels);
        verify(solutionsServiceSpy, times(1)).save(solutionModelFirst);
        verify(solutionsServiceSpy, times(1)).save(solutionModelSecond);

        solutionsServiceSpy.save(solutionModels, solutionsOptions);
        verify(solutionsServiceSpy, times(1)).save(solutionModelFirst, solutionsOptions);
        verify(solutionsServiceSpy, times(1)).save(solutionModelSecond, solutionsOptions);
    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {
        final SolutionModelInterface solutionModel = this.createSolutionModel(1, 10, 1);

        this.solutionsService.save(solutionModel);

        verify(this.solutionsMapper, times(1)).update(this.mapSolutionEntity(solutionModel));
    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {
        final SolutionModelInterface solutionModel = this.createSolutionModel(1, 10, 1);

        this.solutionsService.delete(solutionModel);

        verify(this.solutionsMapper, times(1)).delete(this.mapSolutionEntity(solutionModel));
    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {
        final SolutionModelInterface solutionModel = this.createSolutionModel(null, 13, 4);

        exception.expect(DeleteUnidentifiedModelException.class);

        this.solutionsService.delete(solutionModel);
    }

    @Test
    public void testDeleteWithOptions() throws Exception {
        final SolutionModelInterface solutionModel = this.createSolutionModel(
                1,
                1,
                1
        );
        final SolutionsOptionsInterface solutionsOptions = Mockito.mock(SolutionsOptionsInterface.class);

        this.solutionsService.delete(solutionModel, solutionsOptions);

        verify(solutionsOptions).deleteWithRelations(solutionModel);
    }
}
