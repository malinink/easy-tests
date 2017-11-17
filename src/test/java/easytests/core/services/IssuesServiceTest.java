package easytests.core.services;

import easytests.core.entities.IssueEntity;
import easytests.core.mappers.IssuesMapper;
import easytests.core.models.IssueModel;
import easytests.core.models.IssueModelInterface;

import java.util.ArrayList;
import java.util.List;

import easytests.core.models.SubjectModelInterface;
import easytests.core.options.IssuesOptionsInterface;
import easytests.core.services.exceptions.DeleteUnidentifiedModelException;
import easytests.support.Entities;
import easytests.support.Models;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.*;

import static org.mockito.BDDMockito.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;
/**
 * @author fortyways
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IssuesServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private IssuesMapper issuesMapper;

    @Autowired
    private IssuesService issuesService;
    private IssueModelInterface mapIssueModel(IssueEntity issueEntity) {
        final IssueModelInterface issueModel = new IssueModel();
        issueModel.map(issueEntity);
        return issueModel;
    }

    private IssueEntity mapIssueEntity(IssueModelInterface issueModel) {
        final IssueEntity issueEntity = new IssueEntity();
        issueEntity.map(issueModel);
        return issueEntity;
    }

    private List<IssueEntity> getIssuesEntities() {
        final List<IssueEntity> issuesEntities = new ArrayList<>(2);
        final IssueEntity issueEntityFirst = Entities.createIssueEntityMock(
                1,
                "Name1",
                1
        );
        final IssueEntity issueEntitySecond = Entities.createIssueEntityMock(
                2,
                "Name2",
                2
        );
        issuesEntities.add(issueEntityFirst);
        issuesEntities.add(issueEntitySecond);
        return issuesEntities;
    }

    private List<IssueModelInterface> getIssuesModels() {
        final List<IssueModelInterface> issuesModels = new ArrayList<>(2);
        for (IssueEntity issueEntity: this.getIssuesEntities()) {
            issuesModels.add(this.mapIssueModel(issueEntity));
        }
        return issuesModels;
    }

    @Test
    public void testFindBySubject() throws Exception {
        final Integer subjectId = 7;
        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);
        final IssueEntity issueEntityFirst = Entities.createIssueEntityMock(4, "test3", subjectId);
        final IssueEntity issueEntitySecond = Entities.createIssueEntityMock(12, "test12", subjectId);
        final List<IssueEntity> issuesEntities = new ArrayList<>();
        issuesEntities.add(issueEntityFirst);
        issuesEntities.add(issueEntitySecond);
        Mockito.when(subjectModel.getId()).thenReturn(subjectId);
        given(this.issuesMapper.findBySubjectId(subjectId)).willReturn(issuesEntities);
        final List<IssueModelInterface> issuesModels = new ArrayList<>();
        issuesModels.add(this.mapIssueModel(issueEntityFirst));
        issuesModels.add(this.mapIssueModel(issueEntitySecond));

        final List<IssueModelInterface> foundIssuesModels = this.issuesService.findBySubject(subjectModel);

        verify(this.issuesMapper).findBySubjectId(subjectId);
        Assert.assertEquals(issuesModels, foundIssuesModels);

    }

    @Test
    public void testFindBySubjectWithOptions() throws Exception {
        final Integer subjectId = 7;
        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);
        final IssueEntity issueEntityFirst = Entities.createIssueEntityMock(4, "test3", subjectId);
        final IssueEntity issueEntitySecond = Entities.createIssueEntityMock(12, "test12", subjectId);
        final List<IssueEntity> issuesEntities = new ArrayList<>();
        issuesEntities.add(issueEntityFirst);
        issuesEntities.add(issueEntitySecond);
        Mockito.when(subjectModel.getId()).thenReturn(subjectId);
        given(this.issuesMapper.findBySubjectId(subjectId)).willReturn(issuesEntities);
        final List<IssueModelInterface> issuesModels = new ArrayList<>();
        issuesModels.add(this.mapIssueModel(issueEntityFirst));
        issuesModels.add(this.mapIssueModel(issueEntitySecond));

        final IssuesOptionsInterface issuesOptions = Mockito.mock(IssuesOptionsInterface.class);
        given(issuesOptions.withRelations(issuesModels)).willReturn(issuesModels);

        final List<IssueModelInterface> foundIssuesModels =
                this.issuesService.findBySubject(subjectModel,issuesOptions);

        verify(this.issuesMapper).findBySubjectId(subjectId);
        verify(issuesOptions).withRelations(issuesModels);
        Assert.assertEquals(issuesModels, foundIssuesModels);
    }

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<IssueEntity> issuesEntities = this.getIssuesEntities();
        given(this.issuesMapper.findAll()).willReturn(issuesEntities);

        final List<IssueModelInterface> issuesModels = this.issuesService.findAll();

        Assert.assertEquals(this.getIssuesModels(), issuesModels);
    }

    @Test
    public void testFindAllAbsentList() throws Exception {
        given(this.issuesMapper.findAll()).willReturn(new ArrayList<>(0));

        final List<IssueModelInterface> issuesModels = this.issuesService.findAll();

        Assert.assertEquals(0, issuesModels.size());
    }

    @Test
    public void testFindAllWithOptions() throws Exception {
        final List<IssueEntity> issuesEntities = this.getIssuesEntities();
        final List<IssueModelInterface> issuesModels = this.getIssuesModels();
        final IssuesOptionsInterface issuesOptions = Mockito.mock(IssuesOptionsInterface.class);
        given(this.issuesMapper.findAll()).willReturn(issuesEntities);
        given(issuesOptions.withRelations(Mockito.anyList())).willReturn(issuesModels);

        final List<IssueModelInterface> foundedIssuesModels = this.issuesService.findAll(issuesOptions);

        verify(issuesOptions).withRelations(issuesModels);
        Assert.assertEquals(issuesModels, foundedIssuesModels);
    }

    @Test
    public void testFindPresentModel() throws Exception {
        final Integer id = 1;
        final IssueEntity issueEntity = Entities.createIssueEntityMock(
                id,
                "Name",
                1
        );
        given(this.issuesMapper.find(id)).willReturn(issueEntity);

        final IssueModelInterface issueModel = this.issuesService.find(id);

        Assert.assertEquals(this.mapIssueModel(issueEntity), issueModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        given(this.issuesMapper.find(id)).willReturn(null);

        final IssueModelInterface issueModel = this.issuesService.find(id);

        Assert.assertEquals(null, issueModel);
    }

    @Test
    public void testFindWithOptions() throws Exception {
        final Integer id = 1;
        final IssueEntity issueEntity = Entities.createIssueEntityMock(
                id,
                "Name",
                2
        );
        final IssueModelInterface issueModel = this.mapIssueModel(issueEntity);
        final IssuesOptionsInterface issuesOptions = Mockito.mock(IssuesOptionsInterface.class);
        given(this.issuesMapper.find(id)).willReturn(issueEntity);
        given(issuesOptions.withRelations(issueModel)).willReturn(issueModel);

        final IssueModelInterface foundedIssueModel = this.issuesService.find(id, issuesOptions);

        Assert.assertEquals(issueModel, foundedIssueModel);
        verify(issuesOptions).withRelations(issueModel);
    }

    @Test
    public void testSaveCreatesEntity() throws Exception {
        final IssueModelInterface issueModel = Models.createIssueModel(
                null,
                "Name",
                1
        );
        doAnswer(invocation -> {
            final IssueEntity issueEntity = (IssueEntity) invocation.getArguments()[0];
            issueEntity.setId(5);
            return null;
        }).when(this.issuesMapper).insert(Mockito.any(IssueEntity.class));

        this.issuesService.save(issueModel);

        // TODO verify(this.issuesMapper, times(1)).insert(this.mapIssueEntity(issueModel));
        Assert.assertEquals((Integer) 5, issueModel.getId());
    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {
        final IssueModelInterface issueModel = Models.createIssueModel(
                1,
                "Name",
                1
        );

        this.issuesService.save(issueModel);

        verify(this.issuesMapper, times(1)).update(this.mapIssueEntity(issueModel));
    }

    @Test
    public void testSaveWithOptions() throws Exception {
        final IssueModelInterface issueModel = Models.createIssueModel(
                null,
                "Name",
                1
        );
        final IssuesOptionsInterface issuesOptions = Mockito.mock(IssuesOptionsInterface.class);

        this.issuesService.save(issueModel, issuesOptions);

        verify(issuesOptions).saveWithRelations(issueModel);
    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {
        final IssueModelInterface issueModel = Models.createIssueModel(
                1,
                "Name",
                1
        );

        this.issuesService.delete(issueModel);

        verify(this.issuesMapper, times(1)).delete(this.mapIssueEntity(issueModel));
    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {
        final IssueModelInterface issueModel = Models.createIssueModel(
                null,
                "Name",
                1
        );

        exception.expect(DeleteUnidentifiedModelException.class);
        this.issuesService.delete(issueModel);
    }

    @Test
    public void testDeleteWithOptions() throws Exception {
        final IssueModelInterface issueModel = Models.createIssueModel(
                1,
                "Name",
                1
        );
        final IssuesOptionsInterface issuesOptions = Mockito.mock(IssuesOptionsInterface.class);

        this.issuesService.delete(issueModel, issuesOptions);

        verify(issuesOptions).deleteWithRelations(issueModel);
    }

    @Test
    public void testMultipleSaveAndDelete(){
        final IssueModelInterface issueModelFirst = Models.createIssueModel(
                1,
                "Name",
                1
        );
        final IssueModelInterface issueModelSecond = Models.createIssueModel(
                2,
                "Name2",
                2
        );
        final ArrayList<IssueModelInterface> issueModels=new ArrayList<>();
        issueModels.add(issueModelFirst);
        issueModels.add(issueModelSecond);
        this.issuesService.save(issueModels);
        verify(this.issuesMapper, times(1)).update(this.mapIssueEntity(issueModelFirst));
        verify(this.issuesMapper, times(1)).update(this.mapIssueEntity(issueModelSecond));

        this.issuesService.delete(issueModels);

        verify(this.issuesMapper, times(1)).delete(this.mapIssueEntity(issueModelFirst));
        verify(this.issuesMapper, times(1)).delete(this.mapIssueEntity(issueModelSecond));


        final IssuesOptionsInterface issuesOptions = Mockito.mock(IssuesOptionsInterface.class);

        this.issuesService.save(issueModels, issuesOptions);

        verify(issuesOptions).saveWithRelations(issueModelFirst);
        verify(issuesOptions).saveWithRelations(issueModelSecond);

        this.issuesService.delete(issueModels, issuesOptions);

        verify(issuesOptions).deleteWithRelations(issueModelFirst);
        verify(issuesOptions).deleteWithRelations(issueModelSecond);
    }

}
