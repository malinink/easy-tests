package easytests.core.services;

import easytests.core.entities.SubjectEntity;
import easytests.core.mappers.SubjectsMapper;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.UserModelInterface;
import easytests.core.options.SubjectsOptionsInterface;
import easytests.core.services.exceptions.DeleteUnidentifiedModelException;
import easytests.support.SubjectsSupport;
import easytests.support.UsersSupport;
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
 * @author malinink
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SubjectsServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private SubjectsMapper subjectsMapper;

    @Autowired
    private SubjectsService subjectsService;

    private SubjectsSupport subjectsSupport = new SubjectsSupport();

    private UsersSupport usersSupport = new UsersSupport();

    private List<SubjectEntity> getSubjectsFixturesEntities() {
        final List<SubjectEntity> usersEntities = new ArrayList<>(2);
        usersEntities.add(this.subjectsSupport.getEntityFixtureMock(0));
        usersEntities.add(this.subjectsSupport.getEntityFixtureMock(1));
        return usersEntities;
    }

    private List<SubjectModelInterface> getSubjectsFixturesModels() {
        final List<SubjectModelInterface> subjectsModels = new ArrayList<>(3);
        subjectsModels.add(this.subjectsSupport.getModelFixtureMock(0));
        subjectsModels.add(this.subjectsSupport.getModelFixtureMock(1));
        return subjectsModels;
    }

    private void assertServicesSet(SubjectsOptionsInterface subjectsOptions) {
        this.assertServicesSet(subjectsOptions, 1);
    }

    private void assertServicesSet(SubjectsOptionsInterface subjectsOptions, Integer times) {
        verify(subjectsOptions, times(times)).setIssuesService(any(IssuesServiceInterface.class));
        verify(subjectsOptions, times(times)).setSubjectsService(this.subjectsService);
        verify(subjectsOptions, times(times)).setUsersService(any(UsersServiceInterface.class));
        verify(subjectsOptions, times(times)).setIssueStandardsService(any(IssueStandardsServiceInterface.class));
        verify(subjectsOptions, times(times)).setTopicsService(any(TopicsServiceInterface.class));
    }

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<SubjectEntity> subjectsEntities = this.getSubjectsFixturesEntities();
        when(this.subjectsMapper.findAll()).thenReturn(subjectsEntities);

        final List<SubjectModelInterface> subjectsModels = this.subjectsService.findAll();

        this.subjectsSupport.assertModelsListEquals(this.getSubjectsFixturesModels(), subjectsModels);
    }

    @Test
    public void testFindAllAbsentList() throws Exception {
        when(this.subjectsMapper.findAll()).thenReturn(new ArrayList<>(0));

        final List<SubjectModelInterface> subjectsModels = this.subjectsService.findAll();

        Assert.assertEquals(0, subjectsModels.size());
    }

    @Test
    public void testFindAllWithOptions() throws Exception {
        final ArgumentCaptor<List> listCaptor = ArgumentCaptor.forClass(List.class);
        final List<SubjectEntity> subjectsEntities = this.getSubjectsFixturesEntities();
        final List<SubjectModelInterface> subjectsModels = this.getSubjectsFixturesModels();
        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);
        when(this.subjectsMapper.findAll()).thenReturn(subjectsEntities);
        when(subjectsOptions.withRelations(listCaptor.capture())).thenReturn(subjectsModels);

        final List<SubjectModelInterface> subjectsFoundedModels = this.subjectsService.findAll(subjectsOptions);

        this.assertServicesSet(subjectsOptions);
        this.subjectsSupport.assertModelsListEquals(subjectsModels, listCaptor.getValue());
        Assert.assertSame(subjectsModels, subjectsFoundedModels);
    }

    @Test
    public void testFindPresentModel() throws Exception {
        final SubjectEntity subjectEntity = this.subjectsSupport.getEntityFixtureMock(0);
        when(this.subjectsMapper.find(subjectEntity.getId())).thenReturn(subjectEntity);

        final SubjectModelInterface subjectFoundedModel = this.subjectsService.find(subjectEntity.getId());

        this.subjectsSupport.assertEquals(this.subjectsSupport.getModelFixtureMock(0), subjectFoundedModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        when(this.subjectsMapper.find(id)).thenReturn(null);

        final SubjectModelInterface subjectFoundedModel = this.subjectsService.find(id);

        Assert.assertNull(subjectFoundedModel);
    }

    @Test
    public void testFindWithOptions() throws Exception {
        final ArgumentCaptor<SubjectModelInterface> subjectModelCaptor = ArgumentCaptor.forClass(SubjectModelInterface.class);
        final SubjectEntity subjectEntity = this.subjectsSupport.getEntityFixtureMock(0);
        final SubjectModelInterface subjectModel = this.subjectsSupport.getModelFixtureMock(0);
        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);
        when(this.subjectsMapper.find(subjectModel.getId())).thenReturn(subjectEntity);
        when(subjectsOptions.withRelations(subjectModelCaptor.capture())).thenReturn(subjectModel);

        final SubjectModelInterface subjectFoundedModel = this.subjectsService.find(subjectModel.getId(), subjectsOptions);

        this.assertServicesSet(subjectsOptions);
        this.subjectsSupport.assertEquals(subjectModel, subjectModelCaptor.getValue());
        Assert.assertSame(subjectModel, subjectFoundedModel);
    }

    @Test
    public void testFindByUserPresentList() throws Exception {
        final UserModelInterface userModel = this.usersSupport.getModelFixtureMock(0);
        final List<SubjectEntity> subjectsEntities = this.getSubjectsFixturesEntities();
        when(this.subjectsMapper.findByUserId(userModel.getId())).thenReturn(subjectsEntities);

        final List<SubjectModelInterface> subjectsFoundedModels = this.subjectsService.findByUser(userModel);

        this.subjectsSupport.assertModelsListEquals(this.getSubjectsFixturesModels(), subjectsFoundedModels);
    }

    @Test
    public void testFindByUserAbsentList() throws Exception {
        final UserModelInterface userModel = this.usersSupport.getModelFixtureMock(0);
        when(this.subjectsMapper.findByUserId(userModel.getId())).thenReturn(new ArrayList<>(0));

        final List<SubjectModelInterface> subjectsFoundedModels = this.subjectsService.findByUser(userModel);

        Assert.assertEquals(0, subjectsFoundedModels.size());
    }

    @Test
    public void testFindByUserWithOptions() throws Exception {
        final ArgumentCaptor<List> listCaptor = ArgumentCaptor.forClass(List.class);
        final UserModelInterface userModel = this.usersSupport.getModelFixtureMock(0);
        final List<SubjectEntity> subjectsEntities = this.getSubjectsFixturesEntities();
        when(this.subjectsMapper.findByUserId(userModel.getId())).thenReturn(subjectsEntities);
        final List<SubjectModelInterface> subjectsModels = this.getSubjectsFixturesModels();
        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);
        when(subjectsOptions.withRelations(listCaptor.capture())).thenReturn(subjectsModels);

        final List<SubjectModelInterface> subjectsFoundedModels = this.subjectsService.findByUser(userModel, subjectsOptions);

        this.assertServicesSet(subjectsOptions);
        this.subjectsSupport.assertModelsListEquals(subjectsModels, listCaptor.getValue());
        Assert.assertSame(subjectsModels, subjectsFoundedModels);
    }

    @Test
    public void testSaveCreatesEntity() throws Exception {
        final ArgumentCaptor<SubjectEntity> subjectEntityCaptor = ArgumentCaptor.forClass(SubjectEntity.class);
        final SubjectModelInterface subjectModel = this.subjectsSupport.getModelAdditionalMock(0);

        this.subjectsService.save(subjectModel);

        verify(this.subjectsMapper, times(1)).insert(subjectEntityCaptor.capture());
        this.subjectsSupport.assertEquals(this.subjectsSupport.getEntityAdditionalMock(0), subjectEntityCaptor.getValue());
    }

    @Test
    public void testSaveUpdateEntityIdOnCreation() throws Exception {
        final Integer id = 5;
        final SubjectModelInterface subjectModel = this.subjectsSupport.getModelAdditionalMock(0);
        doAnswer(invocation -> {
            final SubjectEntity subjectEntity = (SubjectEntity) invocation.getArguments()[0];
            subjectEntity.setId(id);
            return null;
        }).when(this.subjectsMapper).insert(any());

        this.subjectsService.save(subjectModel);

        verify(subjectModel, times(1)).setId(id);
    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {
        final ArgumentCaptor<SubjectEntity> subjectEntityCaptor = ArgumentCaptor.forClass(SubjectEntity.class);
        final SubjectModelInterface subjectModel = this.subjectsSupport.getModelFixtureMock(0);

        this.subjectsService.save(subjectModel);

        verify(this.subjectsMapper, times(1)).update(subjectEntityCaptor.capture());
        this.subjectsSupport.assertEquals(this.subjectsSupport.getEntityFixtureMock(0), subjectEntityCaptor.getValue());
    }

    @Test
    public void testSaveWithOptions() throws Exception {
        final SubjectModelInterface subjectModel = this.subjectsSupport.getModelFixtureMock(0);
        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);

        this.subjectsService.save(subjectModel, subjectsOptions);

        this.assertServicesSet(subjectsOptions);
        verify(subjectsOptions, times(1)).saveWithRelations(subjectModel);
        verifyNoMoreInteractions(this.subjectsMapper);
    }

    @Test
    public void testSaveModelsList() throws Exception {
        final ArgumentCaptor<SubjectEntity> subjectEntityCaptor = ArgumentCaptor.forClass(SubjectEntity.class);
        final List<SubjectModelInterface> subjectsModels = this.getSubjectsFixturesModels();

        this.subjectsService.save(subjectsModels);

        verify(this.subjectsMapper, times(subjectsModels.size())).update(subjectEntityCaptor.capture());
        this.subjectsSupport.assertEntitiesListEquals(this.getSubjectsFixturesEntities(), subjectEntityCaptor.getAllValues());
    }

    @Test
    public void testSaveModelsListWithOptions() throws Exception {
        final ArgumentCaptor<SubjectModelInterface> subjectModelCaptor = ArgumentCaptor.forClass(SubjectModelInterface.class);
        final List<SubjectModelInterface> subjectsModels = this.getSubjectsFixturesModels();
        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);

        this.subjectsService.save(subjectsModels, subjectsOptions);

        this.assertServicesSet(subjectsOptions, subjectsModels.size());
        verify(subjectsOptions, times(subjectsModels.size())).saveWithRelations(subjectModelCaptor.capture());
        this.subjectsSupport.assertModelsListEquals(subjectsModels, subjectModelCaptor.getAllValues());
        verifyNoMoreInteractions(this.subjectsMapper);
    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {
        final ArgumentCaptor<SubjectEntity> subjectEntityCaptor = ArgumentCaptor.forClass(SubjectEntity.class);

        this.subjectsService.delete(this.subjectsSupport.getModelFixtureMock(0));

        verify(this.subjectsMapper, times(1)).delete(subjectEntityCaptor.capture());
        this.subjectsSupport.assertEquals(this.subjectsSupport.getEntityFixtureMock(0), subjectEntityCaptor.getValue());
    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {
        final SubjectModelInterface subjectModel = this.subjectsSupport.getModelAdditionalMock(0);

        exception.expect(DeleteUnidentifiedModelException.class);
        this.subjectsService.delete(subjectModel);
    }

    @Test
    public void testDeleteWithOptions() throws Exception {
        final SubjectModelInterface subjectModel = this.subjectsSupport.getModelFixtureMock(0);
        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);

        this.subjectsService.delete(subjectModel, subjectsOptions);

        this.assertServicesSet(subjectsOptions);
        verify(subjectsOptions, times(1)).deleteWithRelations(subjectModel);
        verifyNoMoreInteractions(this.subjectsMapper);
    }

    @Test
    public void testDeleteModelsList() throws Exception {
        final ArgumentCaptor<SubjectEntity> subjectEntityCaptor = ArgumentCaptor.forClass(SubjectEntity.class);
        final List<SubjectModelInterface> subjectsModels = this.getSubjectsFixturesModels();

        this.subjectsService.delete(subjectsModels);

        verify(this.subjectsMapper, times(subjectsModels.size())).delete(subjectEntityCaptor.capture());
        this.subjectsSupport.assertEntitiesListEquals(this.getSubjectsFixturesEntities(), subjectEntityCaptor.getAllValues());
    }

    @Test
    public void testDeleteModelsListWithOptions() throws Exception {
        final ArgumentCaptor<SubjectModelInterface> subjectModelCaptor = ArgumentCaptor.forClass(SubjectModelInterface.class);
        final List<SubjectModelInterface> subjectsModels = this.getSubjectsFixturesModels();
        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);

        this.subjectsService.delete(subjectsModels, subjectsOptions);

        this.assertServicesSet(subjectsOptions, subjectsModels.size());
        verify(subjectsOptions, times(subjectsModels.size())).deleteWithRelations(subjectModelCaptor.capture());
        this.subjectsSupport.assertModelsListEquals(subjectsModels, subjectModelCaptor.getAllValues());
        verifyNoMoreInteractions(this.subjectsMapper);
    }

}
