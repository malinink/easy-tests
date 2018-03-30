package easytests.core.services;

import easytests.core.entities.*;
import easytests.core.mappers.IssueStandardsMapper;
import easytests.core.models.*;
import easytests.core.options.IssueStandardsOptionsInterface;
import easytests.core.services.exceptions.DeleteUnidentifiedModelException;
import easytests.support.IssueStandardSupport;
import easytests.support.Models;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.*;

import static org.mockito.BDDMockito.*;

/**
 * @author janchk
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IssueStandardsServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private IssueStandardsMapper issueStandardsMapper;

    @Autowired
    private IssueStandardsService issueStandardsService;

    private IssueStandardSupport issueStandardSupport = new IssueStandardSupport();

    private IssueStandardEntity mapIssueStandardEntity(IssueStandardModelInterface issueStandardModel) {
        final IssueStandardEntity issueStandardEntity = new IssueStandardEntity();
        issueStandardEntity.map(issueStandardModel);
        return issueStandardEntity;
    }

    private IssueStandardModelInterface mapIssueStandardModel(IssueStandardEntity issueStandardEntity) {
        final IssueStandardModelInterface issueStandardModel = new IssueStandardModel();
        issueStandardModel.map(issueStandardEntity);
        return issueStandardModel;
    }
    private List<IssueStandardEntity> getIssueStandardFixturesEntities(){
        final List<IssueStandardEntity> issueStandardEntities = new ArrayList<>(2);
        issueStandardEntities.add(this.issueStandardSupport.getEntityFixtureMock(0));
        issueStandardEntities.add(this.issueStandardSupport.getEntityFixtureMock(1));
        return issueStandardEntities;
    }


    private List<IssueStandardModelInterface> getIssueStandardFixturesModels() {
        final List<IssueStandardModelInterface> issueStandardModels = new ArrayList<>(2);
        issueStandardModels.add(this.issueStandardSupport.getModelFixtureMock(0));
        issueStandardModels.add(this.issueStandardSupport.getModelFixtureMock(1));
        return issueStandardModels;
    }

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<IssueStandardEntity> issueStandardEntities = this.getIssueStandardFixturesEntities();
        given(this.issueStandardsMapper.findAll()).willReturn(issueStandardEntities);

        final List<IssueStandardModelInterface> issueStandardModels = this.issueStandardsService.findAll();

        Assert.assertNotNull(issueStandardModels);
        this.issueStandardSupport.assertModelsListEquals(this.getIssueStandardFixturesModels(), issueStandardModels);
    }

    @Test
    public void testFindAllAbsentList() throws Exception {
        given(this.issueStandardsMapper.findAll()).willReturn(new ArrayList<>(0));

        final List<IssueStandardModelInterface> issueStandardModels = this.issueStandardsService.findAll();

        Assert.assertNotNull(issueStandardModels);
        Assert.assertEquals(0, issueStandardModels.size());
    }


    @Test
    public void testFindAllWithOptions() throws Exception {
        final ArgumentCaptor<List> listCaptor = ArgumentCaptor.forClass(List.class);

        final List<IssueStandardModelInterface> issueStandardModels = this.getIssueStandardFixturesModels();
        final List<IssueStandardEntity> issueStandardEntities = this.getIssueStandardFixturesEntities();

        final IssueStandardsOptionsInterface issueStandardsOptions = Mockito.mock(IssueStandardsOptionsInterface.class);
        when(this.issueStandardsMapper.findAll()).thenReturn(issueStandardEntities);
        when(issueStandardsOptions.withRelations(listCaptor.capture())).thenReturn(issueStandardModels);

        final List<IssueStandardModelInterface> foundedIssueStandardModels
                = this.issueStandardsService.findAll(issueStandardsOptions);

        this.assertServicesSet(issueStandardsOptions);
        this.issueStandardSupport.assertModelsListEquals(issueStandardModels, listCaptor.getValue());
        Assert.assertSame(issueStandardModels, foundedIssueStandardModels);
    }

    private void assertServicesSet(IssueStandardsOptionsInterface issueStandardsOptions) throws Exception {
        this.assertServicesSet(issueStandardsOptions, 1);
    }

    private void assertServicesSet(IssueStandardsOptionsInterface issueStandardsOptions, Integer times)  throws Exception{
        verify(issueStandardsOptions, times(times)).setIssueStandardsService(this.issueStandardsService);

    }

    @Test
    public void testFindPresentModel() throws Exception {
        final IssueStandardEntity issueStandardEntity = this.issueStandardSupport.getEntityFixtureMock(1);
        when(this.issueStandardsMapper.find(issueStandardEntity.getId())).thenReturn(issueStandardEntity);

        final IssueStandardModelInterface foundedIssieStandardModel = this.issueStandardsService.find(issueStandardEntity.getId());

        this.issueStandardSupport.assertEquals(this.issueStandardSupport.getModelFixtureMock(1), foundedIssieStandardModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        given(this.issueStandardsMapper.find(id)).willReturn(null);

        final IssueStandardModelInterface issueStandardModel = this.issueStandardsService.find(id);
        Assert.assertNull(issueStandardModel);
    }

    @Test
    public void testFindWithOptions() throws Exception {
        final ArgumentCaptor<IssueStandardModelInterface> issueStandardModelCaptor =
                ArgumentCaptor.forClass(IssueStandardModelInterface.class);
        final IssueStandardModelInterface issueStandardModel = this.issueStandardSupport.getModelFixtureMock(1);
        final IssueStandardEntity issueStandardEntity = this.issueStandardSupport.getEntityFixtureMock(1);
        final IssueStandardsOptionsInterface issueStandardsOptions = mock(IssueStandardsOptionsInterface.class);
        when(this.issueStandardsMapper.find(issueStandardModel.getId())).thenReturn(issueStandardEntity);
        when(issueStandardsOptions.withRelations(issueStandardModelCaptor.capture())).thenReturn(issueStandardModel);

        final IssueStandardModelInterface foundedIssueStandardModel
                = this.issueStandardsService.find(issueStandardModel.getId(), issueStandardsOptions);

        this.assertServicesSet(issueStandardsOptions);
        this.issueStandardSupport.assertEquals(issueStandardModel, issueStandardModelCaptor.getValue());
        Assert.assertSame(issueStandardModel, foundedIssueStandardModel);
    }

    @Test
    public void testFindBySubjectPresentModel() throws Exception {
        final IssueStandardEntity issueStandardEntity = this.issueStandardSupport.getEntityFixtureMock(3);
        when(this.issueStandardsMapper.findBySubjectId(issueStandardEntity.getSubjectId())).thenReturn(issueStandardEntity);

        final IssueStandardModelInterface foundedIssueStandardModel = this.issueStandardsService.findBySubject(issueStandardEntity.getSubject());

        this.issueStandardSupport.assertEquals(this.issueStandardSupport.getModelFixtureMock(3), foundedIssueStandardModel);
    }

    @Test
    public void testFindBySubjectWithOptions() throws Exception {
        final ArgumentCaptor<IssueStandardModelInterface> issueStandardModelInterfaceCaptor  = ArgumentCaptor.forClass(IssueStandardModelInterface.class);
        final IssueStandardModelInterface issueStandardModel = this.issueStandardSupport.getModelFixtureMock(3);
        final IssueStandardEntity issueStandardEntity = this.issueStandardSupport.getEntityFixtureMock(3);
        final IssueStandardsOptionsInterface issueStandardsOptions = mock(IssueStandardsOptionsInterface.class);
        when(this.issueStandardsMapper.find(issueStandardModel.getId())).thenReturn(issueStandardEntity);
        when(issueStandardsOptions.withRelations(issueStandardModelInterfaceCaptor.capture())).thenReturn(issueStandardModel);

        final IssueStandardModelInterface issueStandardFoundedModel  = this.issueStandardsService.find(issueStandardModel.getId(), issueStandardsOptions);

        this.assertServicesSet(issueStandardsOptions);
        this.issueStandardSupport.assertEquals(issueStandardModel, issueStandardModelInterfaceCaptor.getValue());
        Assert.assertSame(issueStandardModel, issueStandardFoundedModel);
    }

    @Test
    public void testFindBySubjectAbsentModel() throws Exception {
        final Integer subjectId = 5;
        given(this.issueStandardsMapper.findBySubjectId(subjectId)).willReturn(null);

        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);
        Mockito.when(subjectModel.getId()).thenReturn(subjectId);

        final IssueStandardModelInterface issueStandardModel = this.issueStandardsService.findBySubject(subjectModel);
        Assert.assertNull(issueStandardModel);
    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {
        final ArgumentCaptor<IssueStandardEntity> issueStandardsEntityCaptor = ArgumentCaptor.forClass(IssueStandardEntity.class);

        this.issueStandardsService.save(this.issueStandardSupport.getModelFixtureMock(0));

        verify(this.issueStandardsMapper, times(1)).update(issueStandardsEntityCaptor.capture());

        this.issueStandardSupport.assertEquals(this.issueStandardSupport.getEntityFixtureMock(0), issueStandardsEntityCaptor.getValue());

    }

    @Test
    public void testSaveInsertsEntity() throws Exception {
        final IssueStandardModelInterface issueStandardModel = Models.createIssueStandardModel(null, 1200, 20, 5);
        final Integer id = 10;

        doAnswer(invocations -> {
            final IssueStandardEntity issueStandardEntity = (IssueStandardEntity) invocations.getArguments()[0];
            issueStandardEntity.setId(id);
            return null;
        }).when(this.issueStandardsMapper).insert(Mockito.any(IssueStandardEntity.class));

        this.issueStandardsService.save(issueStandardModel);

        // TODO verify(this.issueStandardsMapper, times(1)).insert(this.mapIssueStandardEntity(issueStandardModel));
        Assert.assertEquals(id, issueStandardModel.getId());
    }

    @Test
    public void testSaveList() throws  Exception {
        final List<IssueStandardModelInterface> issueStandardModels = new ArrayList<>(2);
        issueStandardModels.add(Models.createIssueStandardModel(1, 600, 10, 1));
        issueStandardModels.add(Models.createIssueStandardModel(null, 1200, 20, 5));

        final Integer id = 10;
        doAnswer(invocations -> {
            final IssueStandardEntity issueStandardEntity = (IssueStandardEntity) invocations.getArguments()[0];
            issueStandardEntity.setId(id);
            return null;
        }).when(this.issueStandardsMapper).insert(Mockito.any(IssueStandardEntity.class));

        this.issueStandardsService.save(issueStandardModels);

        verify(this.issueStandardsMapper, times(1)).update(this.mapIssueStandardEntity(issueStandardModels.get(0)));
        verify(this.issueStandardsMapper, times(1)).insert(this.mapIssueStandardEntity(issueStandardModels.get(1)));
        Assert.assertEquals(id, issueStandardModels.get(1).getId());
    }

    @Test
    public void testSaveWithOptions() throws Exception {
        final IssueStandardModelInterface issueStandardModel = this.issueStandardSupport.getModelAdditionalMock(0);
        final IssueStandardsOptionsInterface issueStandardsOptions = Mockito.mock(IssueStandardsOptionsInterface.class);

        this.issueStandardsService.save(issueStandardModel, issueStandardsOptions);

        this.assertServicesSet(issueStandardsOptions);
        verify(issueStandardsOptions).saveWithRelations(issueStandardModel);
    }

    @Test
    public void testSaveListWithOptions() throws Exception {
        final IssueStandardsOptionsInterface issueStandardsOptions = Mockito.mock(IssueStandardsOptionsInterface.class);
        final List<IssueStandardModelInterface> issueStandardModels = this.getIssueStandardFixturesModels();

        this.issueStandardsService.save(issueStandardModels, issueStandardsOptions);

        for(IssueStandardModelInterface issueStandardModel: issueStandardModels) {
            verify(issueStandardsOptions).saveWithRelations(issueStandardModel);
        }
    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {
        final IssueStandardModelInterface issueStandardModel = this.issueStandardSupport.getModelFixtureMock(0);

        this.issueStandardsService.delete(issueStandardModel);

        verify(issueStandardsMapper, times(1)).delete(this.mapIssueStandardEntity(issueStandardModel));
    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {
        final IssueStandardModelInterface issueStandardModel = this.issueStandardSupport.getModelAdditionalMock(1);

        exception.expect(DeleteUnidentifiedModelException.class);
        this.issueStandardsService.delete(issueStandardModel);

        verify(this.issueStandardsMapper, times(0)).delete(this.mapIssueStandardEntity(issueStandardModel));
    }

    @Test
    public void testDeleteList() throws Exception {
        final List<IssueStandardModelInterface> issueStandardModels = this.getIssueStandardFixturesModels();

        this.issueStandardsService.delete(issueStandardModels);

        for (IssueStandardModelInterface issueStandardModel: issueStandardModels) {
            verify(this.issueStandardsMapper, times(1)).delete(this.mapIssueStandardEntity(issueStandardModel));
        }
    }

    @Test
    public void testDeleteWithOptions() throws Exception {
        final IssueStandardModelInterface issueStandardModel = this.issueStandardSupport.getModelFixtureMock(0);
        final IssueStandardsOptionsInterface issueStandardsOptions = Mockito.mock(IssueStandardsOptionsInterface.class);

        this.issueStandardsService.delete(issueStandardModel, issueStandardsOptions);

        verify(issueStandardsOptions).deleteWithRelations(issueStandardModel);
    }

    @Test
    public void testDeleteListWithOptions() throws Exception {
        final IssueStandardsOptionsInterface issueStandardsOptions = Mockito.mock(IssueStandardsOptionsInterface.class);
        final List<IssueStandardModelInterface> issueStandardModels = this.getIssueStandardFixturesModels();

        this.issueStandardsService.delete(issueStandardModels, issueStandardsOptions);

        for (IssueStandardModelInterface issueStandardModel: issueStandardModels) {
            verify(issueStandardsOptions).deleteWithRelations(issueStandardModel);
        }
    }
}
