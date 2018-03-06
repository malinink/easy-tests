package easytests.core.services;

import easytests.core.entities.SolutionEntity;
import easytests.core.mappers.SolutionsMapper;
import easytests.core.models.PointModelInterface;
import easytests.core.models.SolutionModelInterface;
import easytests.core.options.SolutionsOptionsInterface;
import easytests.core.services.exceptions.DeleteUnidentifiedModelException;
import easytests.support.PointsSupport;
import easytests.support.SolutionsSupport;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.*;
import org.mockito.ArgumentCaptor;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;


/**
 * @author SvetlanaTselikova
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SolutionsServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private SolutionsMapper solutionsMapper;

    @Autowired
    private SolutionsService solutionsService;

    private SolutionsSupport solutionsSupport = new SolutionsSupport();

    private PointsSupport pointsSupport = new PointsSupport();

    private List<SolutionEntity> getSolutionsFixturesEntities() {
        final List<SolutionEntity> solutionsEntities = new ArrayList<>(2);
        solutionsEntities.add(this.solutionsSupport.getEntityFixtureMock(0));
        solutionsEntities.add(this.solutionsSupport.getEntityFixtureMock(1));
        return solutionsEntities;
    }

    private List<SolutionModelInterface> getSolutionsFixturesModels() {
        final List<SolutionModelInterface> solutionsModels = new ArrayList<>(2);
        solutionsModels.add(this.solutionsSupport.getModelFixtureMock(0));
        solutionsModels.add(this.solutionsSupport.getModelFixtureMock(1));
        return solutionsModels;
    }

    private void assertServicesSet(SolutionsOptionsInterface solutionsOptions) throws Exception {
        this.assertServicesSet(solutionsOptions,1);
    }

    private void assertServicesSet(SolutionsOptionsInterface solutionsOptions, Integer times) {
        verify(solutionsOptions, times(times)).setPointsService(any(PointsServiceInterface.class));
        verify(solutionsOptions, times(times)).setSolutionsService(this.solutionsService);
    }

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<SolutionEntity> solutionsEntities = this.getSolutionsFixturesEntities();
        when(this.solutionsMapper.findAll()).thenReturn(solutionsEntities);

        final List<SolutionModelInterface> solutionsModels = this.solutionsService.findAll();

        this.solutionsSupport.assertModelsListEquals(this.getSolutionsFixturesModels(), solutionsModels);
    }

    @Test
    public void testFindAllAbsentList() throws Exception {
        when(this.solutionsMapper.findAll()).thenReturn(new ArrayList<>(0));

        final List<SolutionModelInterface> solutionsModels = this.solutionsService.findAll();

        Assert.assertNotNull(solutionsModels);
        Assert.assertEquals(0, solutionsModels.size());
    }

    @Test
    public void testFindAllWithOptions() throws Exception {
        final ArgumentCaptor<List> listCaptor = ArgumentCaptor.forClass(List.class);
        final List<SolutionEntity> solutionsEntities = this.getSolutionsFixturesEntities();
        final List<SolutionModelInterface> solutionsModels = this.getSolutionsFixturesModels();
        final SolutionsOptionsInterface solutionsOptions = Mockito.mock(SolutionsOptionsInterface.class);
        when(this.solutionsMapper.findAll()).thenReturn(solutionsEntities);
        when(solutionsOptions.withRelations(listCaptor.capture())).thenReturn(solutionsModels);

        final List<SolutionModelInterface> solutionsFoundedModels = this.solutionsService.findAll(solutionsOptions);

        this.assertServicesSet(solutionsOptions);
        this.solutionsSupport.assertModelsListEquals(solutionsModels, listCaptor.getValue());
        Assert.assertSame(solutionsModels, solutionsFoundedModels);
    }

    @Test
    public void testFindPresentModel() throws Exception {
        final SolutionEntity solutionEntity = this.solutionsSupport.getEntityFixtureMock(0);
        when(this.solutionsMapper.find(solutionEntity.getId())).thenReturn(solutionEntity);

        final SolutionModelInterface solutionFoundedModel = this.solutionsService.find(solutionEntity.getId());

        this.solutionsSupport.assertEquals(this.solutionsSupport.getModelFixtureMock(0), solutionFoundedModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        when(this.solutionsMapper.find(id)).thenReturn(null);

        final SolutionModelInterface solutionFoundedModel = this.solutionsService.find(id);

        Assert.assertNull(solutionFoundedModel);
    }

    @Test
    public void testFindWithOptions() throws Exception {
        final ArgumentCaptor<SolutionModelInterface> solutionModelCaptor = ArgumentCaptor.forClass(SolutionModelInterface.class);
        final SolutionEntity solutionEntity = this.solutionsSupport.getEntityFixtureMock(0);
        final SolutionModelInterface solutionModel = this.solutionsSupport.getModelFixtureMock(0);
        final SolutionsOptionsInterface solutionsOptions = Mockito.mock(SolutionsOptionsInterface.class);
        when(this.solutionsMapper.find(solutionModel.getId())).thenReturn(solutionEntity);
        when(solutionsOptions.withRelations(solutionModelCaptor.capture())).thenReturn(solutionModel);

        final SolutionModelInterface solutionFoundedModel = this.solutionsService.find(solutionModel.getId(), solutionsOptions);

        this.assertServicesSet(solutionsOptions);
        this.solutionsSupport.assertEquals(solutionModel, solutionModelCaptor.getValue());
        Assert.assertSame(solutionModel, solutionFoundedModel);
    }

    @Test
    public void testFindByPointPresentList() throws Exception {
        final PointModelInterface pointModel = this.pointsSupport.getModelFixtureMock(0);
        final List<SolutionEntity> solutionsEntities = this.getSolutionsFixturesEntities();
        when(this.solutionsMapper.findByPointId(pointModel.getId())).thenReturn(solutionsEntities);

        final List<SolutionModelInterface> solutionsFoundedModels = this.solutionsService.findByPoint(pointModel);

        this.solutionsSupport.assertModelsListEquals(this.getSolutionsFixturesModels(), solutionsFoundedModels);
    }

    @Test
    public void testFindByPointAbsentList() throws Exception {
        final PointModelInterface pointModel = this.pointsSupport.getModelFixtureMock(0);
        when(this.solutionsMapper.findByPointId(pointModel.getId())).thenReturn(new ArrayList<>(0));

        final List<SolutionModelInterface> solutionsFoundedModels = this.solutionsService.findByPoint(pointModel);

        Assert.assertEquals(0, solutionsFoundedModels.size());
    }

    @Test
    public void testFindByPointWithOptions() throws Exception {
        final ArgumentCaptor<List> listCaptor = ArgumentCaptor.forClass(List.class);
        final PointModelInterface pointModel = this.pointsSupport.getModelFixtureMock(0);
        final List<SolutionEntity> solutionsEntities = this.getSolutionsFixturesEntities();
        when(this.solutionsMapper.findByPointId(pointModel.getId())).thenReturn(solutionsEntities);
        final List<SolutionModelInterface> solutionsModels = this.getSolutionsFixturesModels();
        final SolutionsOptionsInterface solutionsOptions = Mockito.mock(SolutionsOptionsInterface.class);
        when(solutionsOptions.withRelations(listCaptor.capture())).thenReturn(solutionsModels);

        final List<SolutionModelInterface> solutionsFoundedModels = this.solutionsService.findByPoint(pointModel, solutionsOptions);

        this.assertServicesSet(solutionsOptions);
        this.solutionsSupport.assertModelsListEquals(solutionsModels, listCaptor.getValue());
        Assert.assertSame(solutionsModels, solutionsFoundedModels);
    }

    @Test
    public void testSaveCreatesEntity() throws Exception {
        final ArgumentCaptor<SolutionEntity> solutionEntityCaptor = ArgumentCaptor.forClass(SolutionEntity.class);
        final SolutionModelInterface solutionModel = this.solutionsSupport.getModelAdditionalMock(0);

        this.solutionsService.save(solutionModel);

        verify(this.solutionsMapper, times(1)).insert(solutionEntityCaptor.capture());
        this.solutionsSupport.assertEquals(this.solutionsSupport.getEntityAdditionalMock(0), solutionEntityCaptor.getValue());
    }

    @Test
    public void testSaveUpdateEntityIdOnCreation() throws Exception {
        final Integer id = 5;
        final SolutionModelInterface solutionModel = this.solutionsSupport.getModelAdditionalMock(0);
        doAnswer(invocation -> {
            final SolutionEntity solutionEntity = (SolutionEntity) invocation.getArguments()[0];
            solutionEntity.setId(id);
            return null;
        }).when(this.solutionsMapper).insert(any());

        this.solutionsService.save(solutionModel);

        verify(solutionModel, times(1)).setId(id);
    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {
        final ArgumentCaptor<SolutionEntity> solutionEntityCaptor = ArgumentCaptor.forClass(SolutionEntity.class);
        final SolutionModelInterface solutionModel = this.solutionsSupport.getModelFixtureMock(0);

        this.solutionsService.save(solutionModel);

        verify(this.solutionsMapper, times(1)).update(solutionEntityCaptor.capture());
        this.solutionsSupport.assertEquals(this.solutionsSupport.getEntityFixtureMock(0), solutionEntityCaptor.getValue());
    }

    @Test
    public void testSaveWithOptions() throws Exception {
        final SolutionModelInterface solutionModel = this.solutionsSupport.getModelFixtureMock(0);
        final SolutionsOptionsInterface solutionsOptions = Mockito.mock(SolutionsOptionsInterface.class);

        this.solutionsService.save(solutionModel, solutionsOptions);

        this.assertServicesSet(solutionsOptions);
        verify(solutionsOptions, times(1)).saveWithRelations(solutionModel);
        verifyNoMoreInteractions(this.solutionsMapper);
    }

    @Test
    public void testSaveModelsList() throws Exception {
        final ArgumentCaptor<SolutionEntity> solutionEntityCaptor = ArgumentCaptor.forClass(SolutionEntity.class);
        final List<SolutionModelInterface> solutionsModels = this.getSolutionsFixturesModels();

        this.solutionsService.save(solutionsModels);

        verify(this.solutionsMapper, times(solutionsModels.size())).update(solutionEntityCaptor.capture());
        this.solutionsSupport.assertEntitiesListEquals(this.getSolutionsFixturesEntities(), solutionEntityCaptor.getAllValues());
    }

    @Test
    public void testSaveModelsListWithOptions() throws Exception {
        final ArgumentCaptor<SolutionModelInterface> solutionModelCaptor = ArgumentCaptor.forClass(SolutionModelInterface.class);
        final List<SolutionModelInterface> solutionsModels = this.getSolutionsFixturesModels();
        final SolutionsOptionsInterface solutionsOptions = Mockito.mock(SolutionsOptionsInterface.class);

        this.solutionsService.save(solutionsModels, solutionsOptions);

        this.assertServicesSet(solutionsOptions, solutionsModels.size());
        verify(solutionsOptions, times(solutionsModels.size())).saveWithRelations(solutionModelCaptor.capture());
        this.solutionsSupport.assertModelsListEquals(solutionsModels, solutionModelCaptor.getAllValues());
        verifyNoMoreInteractions(this.solutionsMapper);
    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {
        final ArgumentCaptor<SolutionEntity> solutionEntityCaptor = ArgumentCaptor.forClass(SolutionEntity.class);

        this.solutionsService.delete(this.solutionsSupport.getModelFixtureMock(0));

        verify(this.solutionsMapper, times(1)).delete(solutionEntityCaptor.capture());
        this.solutionsSupport.assertEquals(this.solutionsSupport.getEntityFixtureMock(0), solutionEntityCaptor.getValue());
    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {
        final SolutionModelInterface solutionModel = this.solutionsSupport.getModelAdditionalMock(0);

        exception.expect(DeleteUnidentifiedModelException.class);
        this.solutionsService.delete(solutionModel);
    }

    @Test
    public void testDeleteWithOptions() throws Exception {
        final SolutionModelInterface solutionModel = this.solutionsSupport.getModelFixtureMock(0);
        final SolutionsOptionsInterface solutionsOptions = Mockito.mock(SolutionsOptionsInterface.class);

        this.solutionsService.delete(solutionModel, solutionsOptions);

        this.assertServicesSet(solutionsOptions);
        verify(solutionsOptions, times(1)).deleteWithRelations(solutionModel);
        verifyNoMoreInteractions(this.solutionsMapper);
    }

    @Test
    public void testDeleteModelsList() throws Exception {
        final ArgumentCaptor<SolutionEntity> solutionEntityCaptor = ArgumentCaptor.forClass(SolutionEntity.class);
        final List<SolutionModelInterface> solutionsModels = this.getSolutionsFixturesModels();

        this.solutionsService.delete(solutionsModels);

        verify(this.solutionsMapper, times(solutionsModels.size())).delete(solutionEntityCaptor.capture());
        this.solutionsSupport.assertEntitiesListEquals(this.getSolutionsFixturesEntities(), solutionEntityCaptor.getAllValues());
    }

    @Test
    public void testDeleteModelsListWithOptions() throws Exception {
        final ArgumentCaptor<SolutionModelInterface> solutionModelCaptor = ArgumentCaptor.forClass(SolutionModelInterface.class);
        final List<SolutionModelInterface> solutionsModels = this.getSolutionsFixturesModels();
        final SolutionsOptionsInterface solutionsOptions = Mockito.mock(SolutionsOptionsInterface.class);

        this.solutionsService.delete(solutionsModels, solutionsOptions);

        this.assertServicesSet(solutionsOptions, solutionsModels.size());
        verify(solutionsOptions, times(solutionsModels.size())).deleteWithRelations(solutionModelCaptor.capture());
        this.solutionsSupport.assertModelsListEquals(solutionsModels, solutionModelCaptor.getAllValues());
        verifyNoMoreInteractions(this.solutionsMapper);
    }
}
