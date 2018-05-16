package easytests.core.services;

import easytests.core.entities.IssueEntity;
import easytests.core.mappers.IssuesMapper;
import easytests.core.models.IssueModelInterface;
import easytests.core.models.SubjectModelInterface;
import easytests.core.options.IssuesOptionsInterface;
import easytests.core.services.exceptions.DeleteUnidentifiedModelException;
import easytests.support.IssueSupport;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
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


/**
 * @author greenbarrow
 */

@RunWith(SpringRunner.class)
@SpringBootTest

public class IssuesServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Autowired
    private IssuesService issuesService;

    @MockBean
    private IssuesMapper issuesMapper;

    private IssueSupport issueSupport = new IssueSupport();

    private List<IssueEntity> getIssuesFixturesEntities() {
        final List<IssueEntity> issuesEntities = new ArrayList<>(2);
        issuesEntities.add(this.issueSupport.getEntityFixtureMock(0));
        issuesEntities.add(this.issueSupport.getEntityFixtureMock(1));
        return issuesEntities;
    }

    private List<IssueModelInterface> getIssuesFixturesModels() {
        final List<IssueModelInterface> issuesModels = new ArrayList<>(2);
        issuesModels.add(this.issueSupport.getModelFixtureMock(0));
        issuesModels.add(this.issueSupport.getModelFixtureMock(1));
        return issuesModels;
    }

    private void assertServicesSet(IssuesOptionsInterface issuesOptions) throws Exception {
        this.assertServicesSet(issuesOptions, 1);
    }

    private void assertServicesSet(IssuesOptionsInterface issuesOptions, Integer times) throws Exception {
        verify(issuesOptions, times(times)).setSubjectsService(any(SubjectsServiceInterface.class));
        verify(issuesOptions, times(times)).setQuizzesService(any(QuizzesServiceInterface.class));
        verify(issuesOptions, times(times)).setIssuesService(this.issuesService);
    }

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<IssueEntity> issuesEntities = getIssuesFixturesEntities();
        when(this.issuesMapper.findAll()).thenReturn(issuesEntities);

        final List<IssueModelInterface> issuesFoundedModels = this.issuesService.findAll();

        this.issueSupport.assertModelsListEquals(this.getIssuesFixturesModels(), issuesFoundedModels);
    }

    @Test
    public void testFindAllAbsentList() throws Exception {
        when(this.issuesMapper.findAll()).thenReturn(new ArrayList<>(0));

        final List<IssueModelInterface> issuesFoundedModels = this.issuesService.findAll();

        Assert.assertNotNull(issuesFoundedModels);
        Assert.assertEquals(0, issuesFoundedModels.size());
    }

    @Test
    public void testFindAllWithOptions() throws Exception {
        final ArgumentCaptor<List> listCaptor = ArgumentCaptor.forClass(List.class);
        final List<IssueModelInterface> issuesModels = this.getIssuesFixturesModels();
        final List<IssueEntity> issuesEntities = this.getIssuesFixturesEntities();
        final IssuesOptionsInterface issuesOptions = mock(IssuesOptionsInterface.class);
        when(this.issuesMapper.findAll()).thenReturn(issuesEntities);
        when(issuesOptions.withRelations(listCaptor.capture())).thenReturn(issuesModels);

        final List<IssueModelInterface> issuesFoundedModels = this.issuesService.findAll(issuesOptions);

        this.assertServicesSet(issuesOptions);
        this.issueSupport.assertModelsListEquals(issuesModels, listCaptor.getValue());
        Assert.assertSame(issuesModels, issuesFoundedModels);
    }

    @Test
    public void testFindPresentModel() throws Exception {
        final IssueEntity issueEntity = this.issueSupport.getEntityFixtureMock(0);
        when(this.issuesMapper.find(issueEntity.getId())).thenReturn(issueEntity);

        final IssueModelInterface issueFoundedModel = this.issuesService.find(issueEntity.getId());

        this.issueSupport.assertEquals(this.issueSupport.getModelFixtureMock(0), issueFoundedModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer absentId = 10;
        when(this.issuesMapper.find(absentId)).thenReturn(null);

        final IssueModelInterface issueFoundedModel = this.issuesService.find(absentId);

        Assert.assertNull(issueFoundedModel);
    }

    @Test
    public void testFindWithOptions() throws Exception {
        final ArgumentCaptor<IssueModelInterface> issueModelCaptor = ArgumentCaptor.
                forClass(IssueModelInterface.class);
        final IssueEntity issueEntity = this.issueSupport.getEntityFixtureMock(0);
        final IssueModelInterface issueModel = this.issueSupport.getModelFixtureMock(0);
        final IssuesOptionsInterface issuesOptions = Mockito.mock(IssuesOptionsInterface.class);
        when(this.issuesMapper.find(issueModel.getId())).thenReturn(issueEntity);
        when(issuesOptions.withRelations(issueModelCaptor.capture())).thenReturn(issueModel);

        final IssueModelInterface issueFoundedModel = this.issuesService.find(issueModel.getId(), issuesOptions);

        this.assertServicesSet(issuesOptions);
        this.issueSupport.assertEquals(issueModel, issueModelCaptor.getValue());
        Assert.assertSame(issueModel, issueFoundedModel);
    }

    @Test
    public void testFindBySubject() throws Exception {
        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);
        final List<IssueEntity> issuesEntities = this.getIssuesFixturesEntities();
        when(this.issuesMapper.findBySubjectId(subjectModel.getId())).thenReturn(issuesEntities);
        final List<IssueModelInterface> issuesModels = this.getIssuesFixturesModels();

        final List<IssueModelInterface> issuesFoundedModels = this.issuesService.findBySubject(subjectModel);

        this.issueSupport.assertModelsListEquals(issuesModels, issuesFoundedModels);
    }

    @Test
    public void testFindBySubjectAbsentList() throws Exception {
        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);
        when(this.issuesMapper.findBySubjectId(subjectModel.getId())).thenReturn(new ArrayList<>(0));

        final List<IssueModelInterface> issuesFoundedModels = this.issuesService.findBySubject(subjectModel);

        Assert.assertEquals(0, issuesFoundedModels.size());
    }

    @Test
    public void testFindBySubjectWithOptions() throws Exception {
        final ArgumentCaptor<List> listCaptor = ArgumentCaptor.forClass(List.class);
        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);
        final List<IssueEntity> issuesEntities = this.getIssuesFixturesEntities();
        when(this.issuesMapper.findBySubjectId(subjectModel.getId())).thenReturn(issuesEntities);
        final List<IssueModelInterface> issuesModels = this.getIssuesFixturesModels();
        final IssuesOptionsInterface issuesOptions = Mockito.mock(IssuesOptionsInterface.class);
        when(issuesOptions.withRelations(listCaptor.capture())).thenReturn(issuesModels);

        final List<IssueModelInterface> issuesFoundedModels =
                this.issuesService.findBySubject(subjectModel, issuesOptions);

        this.assertServicesSet(issuesOptions);
        this.issueSupport.assertModelsListEquals(issuesModels, listCaptor.getValue());
        Assert.assertSame(issuesModels, issuesFoundedModels);
    }

    @Test
    public void testSaveCreatesEntity() throws Exception {
        final ArgumentCaptor<IssueEntity> issueEntityCaptor = ArgumentCaptor.forClass(IssueEntity.class);

        this.issuesService.save(this.issueSupport.getModelAdditionalMock(0));

        verify(this.issuesMapper, times(1)).insert(issueEntityCaptor.capture());
        this.issueSupport.assertEquals(
                this.issueSupport.getModelAdditionalMock(0),
                issueEntityCaptor.getValue()
        );
    }

    @Test
    public void testSaveUpdateEntityIdOnCreation() throws Exception {
        final IssueModelInterface issueAdditionalModel = this.issueSupport.getModelAdditionalMock(0);
        doAnswer(invocation -> {
            final IssueEntity issueEntity = (IssueEntity) invocation.getArguments()[0];
            issueEntity.setId(5);
            return null;
        }).when(this.issuesMapper).insert(Mockito.any(IssueEntity.class));

        this.issuesService.save(issueAdditionalModel);

        verify(issueAdditionalModel, times(1)).setId(5);
    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {
        final ArgumentCaptor<IssueEntity> issueEntityCaptor = ArgumentCaptor.forClass(IssueEntity.class);

        this.issuesService.save(this.issueSupport.getModelFixtureMock(0));

        verify(this.issuesMapper, times(1)).update(issueEntityCaptor.capture());
        this.issueSupport.assertEquals(
                this.issueSupport.getEntityFixtureMock(0),
                issueEntityCaptor.getValue()
        );

    }

    @Test
    public void testSaveWithOptions() throws Exception {
        final IssueModelInterface issueModel = this.issueSupport.getModelFixtureMock(0);
        final IssuesOptionsInterface issuesOptions = Mockito.mock(IssuesOptionsInterface.class);

        this.issuesService.save(issueModel, issuesOptions);

        this.assertServicesSet(issuesOptions);
        verify(issuesOptions, times(1)).saveWithRelations(issueModel);
        verifyNoMoreInteractions(this.issuesMapper);
    }

    @Test
    public void testSaveModelsList() throws Exception {
        final ArgumentCaptor<IssueEntity> issueEntityCaptor = ArgumentCaptor.forClass(IssueEntity.class);
        final List<IssueModelInterface> issuesModels = this.getIssuesFixturesModels();

        this.issuesService.save(issuesModels);

        verify(this.issuesMapper, times(issuesModels.size())).update(issueEntityCaptor.capture());
        this.issueSupport.assertEntitiesListEquals(
                this.getIssuesFixturesEntities(),
                issueEntityCaptor.getAllValues()
        );
    }

    @Test
    public void testSaveModelsListWithOptions() throws Exception {
        final ArgumentCaptor<IssueModelInterface> issueModelCaptor =
                ArgumentCaptor.forClass(IssueModelInterface.class);
        final List<IssueModelInterface> issuesModels = this.getIssuesFixturesModels();
        final IssuesOptionsInterface issuesOptions = Mockito.mock(IssuesOptionsInterface.class);

        this.issuesService.save(issuesModels, issuesOptions);

        this.assertServicesSet(issuesOptions, issuesModels.size());
        verify(issuesOptions, times(issuesModels.size())).saveWithRelations(issueModelCaptor.capture());
        this.issueSupport.assertModelsListEquals(issuesModels, issueModelCaptor.getAllValues());
    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {
        final ArgumentCaptor<IssueEntity> issueEntityCaptor = ArgumentCaptor.forClass(IssueEntity.class);

        this.issuesService.delete(this.issueSupport.getModelFixtureMock(0));

        verify(this.issuesMapper, times(1)).delete(issueEntityCaptor.capture());
        this.issueSupport.assertEquals(this.issueSupport.getEntityFixtureMock(0), issueEntityCaptor.getValue());
    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {
        final IssueModelInterface issueModel = this.issueSupport.getModelAdditionalMock(0);

        exception.expect(DeleteUnidentifiedModelException.class);
        this.issuesService.delete(issueModel);
    }

    @Test
    public void testDeleteWithOptions() throws Exception {
        final IssueModelInterface issueModel = this.issueSupport.getModelFixtureMock(0);
        final IssuesOptionsInterface issuesOptions = Mockito.mock(IssuesOptionsInterface.class);

        this.issuesService.delete(issueModel, issuesOptions);

        this.assertServicesSet(issuesOptions);
        verify(issuesOptions, times(1)).deleteWithRelations(issueModel);
        verifyNoMoreInteractions(this.issuesMapper);
    }

    @Test
    public void testDeleteModelsList() throws Exception {
        final ArgumentCaptor<IssueEntity> issueEntityCaptor = ArgumentCaptor.forClass(IssueEntity.class);
        final List<IssueModelInterface> issuesModels = this.getIssuesFixturesModels();

        this.issuesService.delete(issuesModels);

        verify(this.issuesMapper, times(issuesModels.size())).delete(issueEntityCaptor.capture());
        this.issueSupport.assertEntitiesListEquals(this.getIssuesFixturesEntities(), issueEntityCaptor.getAllValues());
    }

    @Test
    public void testDeleteModelsListWithOptions() throws Exception {
        final ArgumentCaptor<IssueModelInterface> issueModelCaptor = ArgumentCaptor.forClass(IssueModelInterface.class);
        final List<IssueModelInterface> issuesModels = this.getIssuesFixturesModels();
        final IssuesOptionsInterface issuesOptions = Mockito.mock(IssuesOptionsInterface.class);

        this.issuesService.delete(issuesModels, issuesOptions);

        this.assertServicesSet(issuesOptions, issuesModels.size());
        verify(issuesOptions, times(issuesModels.size())).deleteWithRelations(issueModelCaptor.capture());
        this.issueSupport.assertModelsListEquals(issuesModels, issueModelCaptor.getAllValues());
        verifyNoMoreInteractions(this.issuesMapper);
    }

}
