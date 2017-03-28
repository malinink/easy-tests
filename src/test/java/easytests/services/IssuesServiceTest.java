package easytests.services;

import easytests.entities.IssueEntity;
import easytests.mappers.IssuesMapper;
import easytests.models.IssueModel;
import easytests.models.IssueModelInterface;

import java.util.ArrayList;
import java.util.List;

import easytests.services.exceptions.DeleteUnidentifiedModelException;
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

    private IssueModelInterface createIssueModel(Integer id, String name,Integer authorId) {
        final IssueModelInterface issueModel = new IssueModel();
        issueModel.setId(id);
        issueModel.setName(name);
        issueModel.setAuthorId(authorId);
        return issueModel;
    }

    private IssueEntity createIssueEntityMock(Integer id, String name,Integer authorId) {
        final IssueEntity issueEntity = Mockito.mock(IssueEntity.class);
        Mockito.when(issueEntity.getId()).thenReturn(id);
        Mockito.when(issueEntity.getName()).thenReturn(name);
        Mockito.when(issueEntity.getAuthorId()).thenReturn(authorId);
        return issueEntity;
    }

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

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<IssueEntity> issueEntities = new ArrayList<>(2);
        final IssueEntity issueEntityFirst = this.createIssueEntityMock(1, "Name1",11);
        final IssueEntity issueEntitySecond = this.createIssueEntityMock(2, "Name2", 12);
        issueEntities.add(issueEntityFirst);
        issueEntities.add(issueEntitySecond);
        given(this.issuesMapper.findAll()).willReturn(issueEntities);

        final List<IssueModelInterface> issueModels = this.issuesService.findAll();

        Assert.assertEquals(2, issueModels.size());
        Assert.assertEquals(issueModels.get(0), this.mapIssueModel(issueEntityFirst));
        Assert.assertEquals(issueModels.get(1), this.mapIssueModel(issueEntitySecond));
    }

    @Test
    public void testFindAllAbsentList() throws Exception {
        given(this.issuesMapper.findAll()).willReturn(new ArrayList<>(0));

        final List<IssueModelInterface> issuesModels = this.issuesService.findAll();

        Assert.assertEquals(0, issuesModels.size());
    }

    @Test
    public void testFindPresentModel() throws Exception {
        final Integer id = 1;
        final IssueEntity issueEntity = this.createIssueEntityMock(id, "Name", 11);
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
    public void testSaveCreatesEntity() throws Exception {
        final IssueModelInterface issueModel = this.createIssueModel(null, "Name", 11);

        this.issuesService.save(issueModel);

        verify(this.issuesMapper, times(1)).insert(this.mapIssueEntity(issueModel));
    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {
        final IssueModelInterface issueModel = this.createIssueModel(1, "Name",11);

        this.issuesService.save(issueModel);

        verify(this.issuesMapper, times(1)).update(this.mapIssueEntity(issueModel));
    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {
        final IssueModelInterface issueModel = this.createIssueModel(1, "Name", 11);

        this.issuesService.delete(issueModel);

        verify(this.issuesMapper, times(1)).delete(this.mapIssueEntity(issueModel));
    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {
        final IssueModelInterface issueModel = this.createIssueModel(null, "Name", 11);

        exception.expect(DeleteUnidentifiedModelException.class);
        this.issuesService.delete(issueModel);
    }

}
