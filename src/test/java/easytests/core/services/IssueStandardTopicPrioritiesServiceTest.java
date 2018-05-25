package easytests.core.services;

import easytests.core.entities.IssueStandardTopicPriorityEntity;
import easytests.core.mappers.IssueStandardTopicPrioritiesMapper;
import easytests.core.models.IssueStandardTopicPriorityModelInterface;
import easytests.core.models.IssueStandardModelInterface;
import easytests.core.options.IssueStandardTopicPrioritiesOptionsInterface;
import easytests.core.services.exceptions.DeleteUnidentifiedModelException;
import easytests.support.IssueStandardTopicPrioritySupport;
import easytests.support.IssueStandardSupport;
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
 * @author VlasovIgor
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IssueStandardTopicPrioritiesServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private IssueStandardTopicPrioritiesMapper issueStandardTopicPrioritiesMapper;

    @Autowired
    private IssueStandardTopicPrioritiesService issueStandardTopicPrioritiesService;

    private IssueStandardTopicPrioritySupport issueStandardTopicPrioritiesSupport = new IssueStandardTopicPrioritySupport();

    private IssueStandardSupport issueStandardsSupport = new IssueStandardSupport();

    private List<IssueStandardTopicPriorityEntity> getIssueStandardTopicPrioritiesFixturesEntities() {
        final List<IssueStandardTopicPriorityEntity> issueStandardTopicPrioritiesEntities = new ArrayList<>(2);
        issueStandardTopicPrioritiesEntities.add(this.issueStandardTopicPrioritiesSupport.getEntityFixtureMock(0));
        issueStandardTopicPrioritiesEntities.add(this.issueStandardTopicPrioritiesSupport.getEntityFixtureMock(1));
        return issueStandardTopicPrioritiesEntities;
    }

    private List<IssueStandardTopicPriorityModelInterface> getIssueStandardTopicPrioritiesFixturesModels() {
        final List<IssueStandardTopicPriorityModelInterface> issueStandardTopicPrioritiesModels = new ArrayList<>(2);
        issueStandardTopicPrioritiesModels.add(this.issueStandardTopicPrioritiesSupport.getModelFixtureMock(0));
        issueStandardTopicPrioritiesModels.add(this.issueStandardTopicPrioritiesSupport.getModelFixtureMock(1));
        return issueStandardTopicPrioritiesModels;
    }

    private void assertServicesSet(IssueStandardTopicPrioritiesOptionsInterface issueStandardTopicPrioritiesOptions) {
        this.assertServicesSet(issueStandardTopicPrioritiesOptions, 1);
    }

    private void assertServicesSet(IssueStandardTopicPrioritiesOptionsInterface issueStandardTopicPrioritiesOptions, Integer times) {
        verify(issueStandardTopicPrioritiesOptions, times(times)).setIssueStandardsService(any(IssueStandardsServiceInterface.class));
        verify(issueStandardTopicPrioritiesOptions, times(times)).setTopicPrioritiesService(this.issueStandardTopicPrioritiesService);
        verify(issueStandardTopicPrioritiesOptions, times(times)).setTopicsService(any(TopicsServiceInterface.class));
    }

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<IssueStandardTopicPriorityEntity> issueStandardTopicPrioritiesEntities = this.getIssueStandardTopicPrioritiesFixturesEntities();
        when(this.issueStandardTopicPrioritiesMapper.findAll()).thenReturn(issueStandardTopicPrioritiesEntities);

        final List<IssueStandardTopicPriorityModelInterface> issueStandardTopicPrioritiesModels = this.issueStandardTopicPrioritiesService.findAll();

        this.issueStandardTopicPrioritiesSupport.assertModelsListEquals(this.getIssueStandardTopicPrioritiesFixturesModels(), issueStandardTopicPrioritiesModels);
    }

    @Test
    public void testFindAllAbsentList() throws Exception {
        when(this.issueStandardTopicPrioritiesMapper.findAll()).thenReturn(new ArrayList<>(0));

        final List<IssueStandardTopicPriorityModelInterface> issueStandardTopicPrioritiesModels = this.issueStandardTopicPrioritiesService.findAll();

        Assert.assertNotNull(issueStandardTopicPrioritiesModels);
        Assert.assertEquals(0, issueStandardTopicPrioritiesModels.size());
    }

    @Test
    public void testFindAllWithOptions() throws Exception {
        final ArgumentCaptor<List> listCaptor = ArgumentCaptor.forClass(List.class);
        final List<IssueStandardTopicPriorityEntity> issueStandardTopicPrioritiesEntities = this.getIssueStandardTopicPrioritiesFixturesEntities();
        final List<IssueStandardTopicPriorityModelInterface> issueStandardTopicPrioritiesModels = this.getIssueStandardTopicPrioritiesFixturesModels();
        final IssueStandardTopicPrioritiesOptionsInterface issueStandardTopicPrioritiesOptions = Mockito.mock(IssueStandardTopicPrioritiesOptionsInterface.class);
        when(this.issueStandardTopicPrioritiesMapper.findAll()).thenReturn(issueStandardTopicPrioritiesEntities);
        when(issueStandardTopicPrioritiesOptions.withRelations(listCaptor.capture())).thenReturn(issueStandardTopicPrioritiesModels);

        final List<IssueStandardTopicPriorityModelInterface> issueStandardTopicPrioritiesFoundedModels = this.issueStandardTopicPrioritiesService.findAll(issueStandardTopicPrioritiesOptions);

        this.assertServicesSet(issueStandardTopicPrioritiesOptions);
        this.issueStandardTopicPrioritiesSupport.assertModelsListEquals(issueStandardTopicPrioritiesModels, listCaptor.getValue());
        Assert.assertSame(issueStandardTopicPrioritiesModels, issueStandardTopicPrioritiesFoundedModels);
    }

    @Test
    public void testFindPresentModel() throws Exception {
        final IssueStandardTopicPriorityEntity issueStandardTopicPriorityEntity = this.issueStandardTopicPrioritiesSupport.getEntityFixtureMock(0);
        when(this.issueStandardTopicPrioritiesMapper.find(issueStandardTopicPriorityEntity.getId())).thenReturn(issueStandardTopicPriorityEntity);

        final IssueStandardTopicPriorityModelInterface issueStandardTopicPriorityFoundedModel = this.issueStandardTopicPrioritiesService.find(issueStandardTopicPriorityEntity.getId());

        this.issueStandardTopicPrioritiesSupport.assertEquals(this.issueStandardTopicPrioritiesSupport.getModelFixtureMock(0), issueStandardTopicPriorityFoundedModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        when(this.issueStandardTopicPrioritiesMapper.find(id)).thenReturn(null);

        final IssueStandardTopicPriorityModelInterface issueStandardTopicPriorityFoundedModel = this.issueStandardTopicPrioritiesService.find(id);

        Assert.assertNull(issueStandardTopicPriorityFoundedModel);
    }

    @Test
    public void testFindWithOptions() throws Exception {
        final ArgumentCaptor<IssueStandardTopicPriorityModelInterface> issueStandardTopicPriorityModelCaptor = ArgumentCaptor.forClass(IssueStandardTopicPriorityModelInterface.class);
        final IssueStandardTopicPriorityEntity issueStandardTopicPriorityEntity = this.issueStandardTopicPrioritiesSupport.getEntityFixtureMock(0);
        final IssueStandardTopicPriorityModelInterface issueStandardTopicPriorityModel = this.issueStandardTopicPrioritiesSupport.getModelFixtureMock(0);
        final IssueStandardTopicPrioritiesOptionsInterface issueStandardTopicPrioritiesOptions = Mockito.mock(IssueStandardTopicPrioritiesOptionsInterface.class);
        when(this.issueStandardTopicPrioritiesMapper.find(issueStandardTopicPriorityModel.getId())).thenReturn(issueStandardTopicPriorityEntity);
        when(issueStandardTopicPrioritiesOptions.withRelations(issueStandardTopicPriorityModelCaptor.capture())).thenReturn(issueStandardTopicPriorityModel);

        final IssueStandardTopicPriorityModelInterface issueStandardTopicPriorityFoundedModel = this.issueStandardTopicPrioritiesService.find(issueStandardTopicPriorityModel.getId(), issueStandardTopicPrioritiesOptions);

        this.assertServicesSet(issueStandardTopicPrioritiesOptions);
        this.issueStandardTopicPrioritiesSupport.assertEquals(issueStandardTopicPriorityModel, issueStandardTopicPriorityModelCaptor.getValue());
        Assert.assertSame(issueStandardTopicPriorityModel, issueStandardTopicPriorityFoundedModel);
    }

    @Test
    public void testFindByIssueStandardPresentList() throws Exception {
        final IssueStandardModelInterface issueStandardModel = this.issueStandardsSupport.getModelFixtureMock(0);
        final List<IssueStandardTopicPriorityEntity> issueStandardTopicPrioritiesEntities = this.getIssueStandardTopicPrioritiesFixturesEntities();
        when(this.issueStandardTopicPrioritiesMapper.findByIssueStandardId(issueStandardModel.getId())).thenReturn(issueStandardTopicPrioritiesEntities);

        final List<IssueStandardTopicPriorityModelInterface> issueStandardTopicPrioritiesFoundedModels = this.issueStandardTopicPrioritiesService.findByIssueStandard(issueStandardModel);

        this.issueStandardTopicPrioritiesSupport.assertModelsListEquals(this.getIssueStandardTopicPrioritiesFixturesModels(), issueStandardTopicPrioritiesFoundedModels);
    }

    @Test
    public void testFindByIssueStandardAbsentList() throws Exception {
        final IssueStandardModelInterface issueStandardModel = this.issueStandardsSupport.getModelFixtureMock(0);
        when(this.issueStandardTopicPrioritiesMapper.findByIssueStandardId(issueStandardModel.getId())).thenReturn(new ArrayList<>(0));

        final List<IssueStandardTopicPriorityModelInterface> issueStandardTopicPrioritiesFoundedModels = this.issueStandardTopicPrioritiesService.findByIssueStandard(issueStandardModel);

        Assert.assertEquals(0, issueStandardTopicPrioritiesFoundedModels.size());
    }

    @Test
    public void testFindByIssueStandardWithOptions() throws Exception {
        final ArgumentCaptor<List> listCaptor = ArgumentCaptor.forClass(List.class);
        final IssueStandardModelInterface issueStandardModel = this.issueStandardsSupport.getModelFixtureMock(0);
        final List<IssueStandardTopicPriorityEntity> issueStandardTopicPrioritiesEntities = this.getIssueStandardTopicPrioritiesFixturesEntities();
        when(this.issueStandardTopicPrioritiesMapper.findByIssueStandardId(issueStandardModel.getId())).thenReturn(issueStandardTopicPrioritiesEntities);
        final List<IssueStandardTopicPriorityModelInterface> issueStandardTopicPrioritiesModels = this.getIssueStandardTopicPrioritiesFixturesModels();
        final IssueStandardTopicPrioritiesOptionsInterface issueStandardTopicPrioritiesOptions = Mockito.mock(IssueStandardTopicPrioritiesOptionsInterface.class);
        when(issueStandardTopicPrioritiesOptions.withRelations(listCaptor.capture())).thenReturn(issueStandardTopicPrioritiesModels);

        final List<IssueStandardTopicPriorityModelInterface> issueStandardTopicPrioritiesFoundedModels = this.issueStandardTopicPrioritiesService.findByIssueStandard(issueStandardModel, issueStandardTopicPrioritiesOptions);

        this.assertServicesSet(issueStandardTopicPrioritiesOptions);
        this.issueStandardTopicPrioritiesSupport.assertModelsListEquals(issueStandardTopicPrioritiesModels, listCaptor.getValue());
        Assert.assertSame(issueStandardTopicPrioritiesModels, issueStandardTopicPrioritiesFoundedModels);
    }

    @Test
    public void testSaveCreatesEntity() throws Exception {
        final ArgumentCaptor<IssueStandardTopicPriorityEntity> issueStandardTopicPriorityEntityCaptor = ArgumentCaptor.forClass(IssueStandardTopicPriorityEntity.class);
        final IssueStandardTopicPriorityModelInterface issueStandardTopicPriorityModel = this.issueStandardTopicPrioritiesSupport.getModelAdditionalMock(0);

        this.issueStandardTopicPrioritiesService.save(issueStandardTopicPriorityModel);

        verify(this.issueStandardTopicPrioritiesMapper, times(1)).insert(issueStandardTopicPriorityEntityCaptor.capture());
        this.issueStandardTopicPrioritiesSupport.assertEquals(this.issueStandardTopicPrioritiesSupport.getEntityAdditionalMock(0), issueStandardTopicPriorityEntityCaptor.getValue());
    }

    @Test
    public void testSaveUpdateEntityIdOnCreation() throws Exception {
        final Integer id = 5;
        final IssueStandardTopicPriorityModelInterface issueStandardTopicPriorityModel = this.issueStandardTopicPrioritiesSupport.getModelAdditionalMock(0);
        doAnswer(invocation -> {
            final IssueStandardTopicPriorityEntity issueStandardTopicPriorityEntity = (IssueStandardTopicPriorityEntity) invocation.getArguments()[0];
            issueStandardTopicPriorityEntity.setId(id);
            return null;
        }).when(this.issueStandardTopicPrioritiesMapper).insert(any());

        this.issueStandardTopicPrioritiesService.save(issueStandardTopicPriorityModel);

        verify(issueStandardTopicPriorityModel, times(1)).setId(id);
    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {
        final ArgumentCaptor<IssueStandardTopicPriorityEntity> issueStandardTopicPriorityEntityCaptor = ArgumentCaptor.forClass(IssueStandardTopicPriorityEntity.class);
        final IssueStandardTopicPriorityModelInterface issueStandardTopicPriorityModel = this.issueStandardTopicPrioritiesSupport.getModelFixtureMock(0);

        this.issueStandardTopicPrioritiesService.save(issueStandardTopicPriorityModel);

        verify(this.issueStandardTopicPrioritiesMapper, times(1)).update(issueStandardTopicPriorityEntityCaptor.capture());
        this.issueStandardTopicPrioritiesSupport.assertEquals(this.issueStandardTopicPrioritiesSupport.getEntityFixtureMock(0), issueStandardTopicPriorityEntityCaptor.getValue());
    }

    @Test
    public void testSaveWithOptions() throws Exception {
        final IssueStandardTopicPriorityModelInterface issueStandardTopicPriorityModel = this.issueStandardTopicPrioritiesSupport.getModelFixtureMock(0);
        final IssueStandardTopicPrioritiesOptionsInterface issueStandardTopicPrioritiesOptions = Mockito.mock(IssueStandardTopicPrioritiesOptionsInterface.class);

        this.issueStandardTopicPrioritiesService.save(issueStandardTopicPriorityModel, issueStandardTopicPrioritiesOptions);

        this.assertServicesSet(issueStandardTopicPrioritiesOptions);
        verify(issueStandardTopicPrioritiesOptions, times(1)).saveWithRelations(issueStandardTopicPriorityModel);
        verifyNoMoreInteractions(this.issueStandardTopicPrioritiesMapper);
    }

    @Test
    public void testSaveModelsList() throws Exception {
        final ArgumentCaptor<IssueStandardTopicPriorityEntity> issueStandardTopicPriorityEntityCaptor = ArgumentCaptor.forClass(IssueStandardTopicPriorityEntity.class);
        final List<IssueStandardTopicPriorityModelInterface> issueStandardTopicPrioritiesModels = this.getIssueStandardTopicPrioritiesFixturesModels();

        this.issueStandardTopicPrioritiesService.save(issueStandardTopicPrioritiesModels);

        verify(this.issueStandardTopicPrioritiesMapper, times(issueStandardTopicPrioritiesModels.size())).update(issueStandardTopicPriorityEntityCaptor.capture());
        this.issueStandardTopicPrioritiesSupport.assertEntitiesListEquals(this.getIssueStandardTopicPrioritiesFixturesEntities(), issueStandardTopicPriorityEntityCaptor.getAllValues());
    }

    @Test
    public void testSaveModelsListWithOptions() throws Exception {
        final ArgumentCaptor<IssueStandardTopicPriorityModelInterface> issueStandardTopicPriorityModelCaptor = ArgumentCaptor.forClass(IssueStandardTopicPriorityModelInterface.class);
        final List<IssueStandardTopicPriorityModelInterface> issueStandardTopicPrioritiesModels = this.getIssueStandardTopicPrioritiesFixturesModels();
        final IssueStandardTopicPrioritiesOptionsInterface issueStandardTopicPrioritiesOptions = Mockito.mock(IssueStandardTopicPrioritiesOptionsInterface.class);

        this.issueStandardTopicPrioritiesService.save(issueStandardTopicPrioritiesModels, issueStandardTopicPrioritiesOptions);

        this.assertServicesSet(issueStandardTopicPrioritiesOptions, issueStandardTopicPrioritiesModels.size());
        verify(issueStandardTopicPrioritiesOptions, times(issueStandardTopicPrioritiesModels.size())).saveWithRelations(issueStandardTopicPriorityModelCaptor.capture());
        this.issueStandardTopicPrioritiesSupport.assertModelsListEquals(issueStandardTopicPrioritiesModels, issueStandardTopicPriorityModelCaptor.getAllValues());
        verifyNoMoreInteractions(this.issueStandardTopicPrioritiesMapper);
    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {
        final ArgumentCaptor<IssueStandardTopicPriorityEntity> issueStandardTopicPriorityEntityCaptor = ArgumentCaptor.forClass(IssueStandardTopicPriorityEntity.class);

        this.issueStandardTopicPrioritiesService.delete(this.issueStandardTopicPrioritiesSupport.getModelFixtureMock(0));

        verify(this.issueStandardTopicPrioritiesMapper, times(1)).delete(issueStandardTopicPriorityEntityCaptor.capture());
        this.issueStandardTopicPrioritiesSupport.assertEquals(this.issueStandardTopicPrioritiesSupport.getEntityFixtureMock(0), issueStandardTopicPriorityEntityCaptor.getValue());
    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {
        final IssueStandardTopicPriorityModelInterface issueStandardTopicPriorityModel = this.issueStandardTopicPrioritiesSupport.getModelAdditionalMock(0);

        exception.expect(DeleteUnidentifiedModelException.class);
        this.issueStandardTopicPrioritiesService.delete(issueStandardTopicPriorityModel);
    }

    @Test
    public void testDeleteWithOptions() throws Exception {
        final IssueStandardTopicPriorityModelInterface issueStandardTopicPriorityModel = this.issueStandardTopicPrioritiesSupport.getModelFixtureMock(0);
        final IssueStandardTopicPrioritiesOptionsInterface issueStandardTopicPrioritiesOptions = Mockito.mock(IssueStandardTopicPrioritiesOptionsInterface.class);

        this.issueStandardTopicPrioritiesService.delete(issueStandardTopicPriorityModel, issueStandardTopicPrioritiesOptions);

        this.assertServicesSet(issueStandardTopicPrioritiesOptions);
        verify(issueStandardTopicPrioritiesOptions, times(1)).deleteWithRelations(issueStandardTopicPriorityModel);
        verifyNoMoreInteractions(this.issueStandardTopicPrioritiesMapper);
    }

    @Test
    public void testDeleteModelsList() throws Exception {
        final ArgumentCaptor<IssueStandardTopicPriorityEntity> issueStandardTopicPriorityEntityCaptor = ArgumentCaptor.forClass(IssueStandardTopicPriorityEntity.class);
        final List<IssueStandardTopicPriorityModelInterface> issueStandardTopicPrioritiesModels = this.getIssueStandardTopicPrioritiesFixturesModels();

        this.issueStandardTopicPrioritiesService.delete(issueStandardTopicPrioritiesModels);

        verify(this.issueStandardTopicPrioritiesMapper, times(issueStandardTopicPrioritiesModels.size())).delete(issueStandardTopicPriorityEntityCaptor.capture());
        this.issueStandardTopicPrioritiesSupport.assertEntitiesListEquals(this.getIssueStandardTopicPrioritiesFixturesEntities(), issueStandardTopicPriorityEntityCaptor.getAllValues());
    }

    @Test
    public void testDeleteModelsListWithOptions() throws Exception {
        final ArgumentCaptor<IssueStandardTopicPriorityModelInterface> issueStandardTopicPriorityModelCaptor = ArgumentCaptor.forClass(IssueStandardTopicPriorityModelInterface.class);
        final List<IssueStandardTopicPriorityModelInterface> issueStandardTopicPrioritiesModels = this.getIssueStandardTopicPrioritiesFixturesModels();
        final IssueStandardTopicPrioritiesOptionsInterface issueStandardTopicPrioritiesOptions = Mockito.mock(IssueStandardTopicPrioritiesOptionsInterface.class);

        this.issueStandardTopicPrioritiesService.delete(issueStandardTopicPrioritiesModels, issueStandardTopicPrioritiesOptions);

        this.assertServicesSet(issueStandardTopicPrioritiesOptions, issueStandardTopicPrioritiesModels.size());
        verify(issueStandardTopicPrioritiesOptions, times(issueStandardTopicPrioritiesModels.size())).deleteWithRelations(issueStandardTopicPriorityModelCaptor.capture());
        this.issueStandardTopicPrioritiesSupport.assertModelsListEquals(issueStandardTopicPrioritiesModels, issueStandardTopicPriorityModelCaptor.getAllValues());
        verifyNoMoreInteractions(this.issueStandardTopicPrioritiesMapper);
    }
}
