package easytests.services;

import easytests.entities.*;
import easytests.mappers.IssueStandardsMapper;
import easytests.models.*;
import easytests.options.IssueStandardsOptionsInterface;
import easytests.services.exceptions.DeleteUnidentifiedModelException;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.junit4.*;

import static org.mockito.BDDMockito.*;

/**
 * @author SingularityA
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IssueStandardsServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private IssueStandardsMapper issueStandardsMapper;

    @InjectMocks
    private IssueStandardsService issueStandardsService;

    private IssueStandardEntity
        createIssueStandardEntityMock(Integer id, Integer timeLimit, Integer questionsNumber, Integer subjectId) {

        final IssueStandardEntity issueStandardEntity = Mockito.mock(IssueStandardEntity.class);
        Mockito.when(issueStandardEntity.getId()).thenReturn(id);
        Mockito.when(issueStandardEntity.getTimeLimit()).thenReturn(timeLimit);
        Mockito.when(issueStandardEntity.getQuestionsNumber()).thenReturn(questionsNumber);
        Mockito.when(issueStandardEntity.getSubjectId()).thenReturn(subjectId);
        return issueStandardEntity;
    }

    private IssueStandardModelInterface
        createIssueStandardModel(Integer id, Integer timeLimit, Integer questionsNumber, Integer subjectId) {

        final IssueStandardModelInterface issueStandardModel = new IssueStandardModel();

        final List<IssueStandardTopicPriorityModelInterface> topicPriorities = new ArrayList<>(0);
        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptions = new ArrayList<>(0);
        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);
        Mockito.when(subjectModel.getId()).thenReturn(subjectId);

        issueStandardModel.setId(id);
        issueStandardModel.setTimeLimit(timeLimit);
        issueStandardModel.setQuestionsNumber(questionsNumber);
        issueStandardModel.setTopicPriorities(topicPriorities);
        issueStandardModel.setQuestionTypeOptions(questionTypeOptions);
        issueStandardModel.setSubject(subjectModel);

        return issueStandardModel;
    }

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

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<IssueStandardEntity> issueStandardEntities = new ArrayList<>(2);
        issueStandardEntities.add(this.createIssueStandardEntityMock(1, 600, 20, 1));
        issueStandardEntities.add(this.createIssueStandardEntityMock(2, 1200, 25, 2));
        given(this.issueStandardsMapper.findAll()).willReturn(issueStandardEntities);

        final List<IssueStandardModelInterface> issueStandardModels = this.issueStandardsService.findAll();

        Assert.assertNotNull(issueStandardModels);
        Assert.assertEquals(issueStandardEntities.size(), issueStandardModels.size());
        for (int i = 0; i < issueStandardModels.size(); i++) {
            Assert.assertEquals(issueStandardModels.get(i), this.mapIssueStandardModel(issueStandardEntities.get(i)));
        }
    }

    @Test
    public void testFindAllAbsentList() throws Exception {
        given(this.issueStandardsMapper.findAll()).willReturn(new ArrayList<>(0));

        final List<IssueStandardModelInterface> issueStandardModels = this.issueStandardsService.findAll();

        Assert.assertNotNull(issueStandardModels);
        Assert.assertEquals(0, issueStandardModels.size());
    }

    @Test
    public void testFindPresentModel() throws Exception {
        final Integer id = 1;
        final IssueStandardEntity issueStandardEntity = this.createIssueStandardEntityMock(id, 1200, 20, 2);
        given(this.issueStandardsMapper.find(id)).willReturn(issueStandardEntity);

        final IssueStandardModelInterface issueStandardModel = this.issueStandardsService.find(id);
        Assert.assertNotNull(issueStandardModel);
        Assert.assertEquals(this.mapIssueStandardModel(issueStandardEntity), issueStandardModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        given(this.issueStandardsMapper.find(id)).willReturn(null);

        final IssueStandardModelInterface issueStandardModel = this.issueStandardsService.find(id);
        Assert.assertNull(issueStandardModel);
    }

    @Test
    public void testFindBySubjectPresentModel() throws Exception {
        final Integer subjectId = 3;
        final IssueStandardEntity issueStandardEntity = this.createIssueStandardEntityMock(3, 600, 10, subjectId);
        given(this.issueStandardsMapper.findBySubjectId(subjectId)).willReturn(issueStandardEntity);

        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);
        Mockito.when(subjectModel.getId()).thenReturn(subjectId);

        final IssueStandardModelInterface issueStandardModel = this.issueStandardsService.findBySubject(subjectModel);
        Assert.assertNotNull(issueStandardModel);
        Assert.assertEquals(this.mapIssueStandardModel(issueStandardEntity), issueStandardModel);
    }

    @Test
    public void testFindBySubjectWithOptions() throws Exception {
        final Integer subjectId = 3;
        final IssueStandardEntity issueStandardEntity = this.createIssueStandardEntityMock(3, 600, 10, subjectId);
        given(this.issueStandardsMapper.findBySubjectId(subjectId)).willReturn(issueStandardEntity);

        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);
        Mockito.when(subjectModel.getId()).thenReturn(subjectId);

        final IssueStandardsOptionsInterface issueStandardsOptions = Mockito.mock(IssueStandardsOptionsInterface.class);

        this.issueStandardsService.findBySubject(subjectModel, issueStandardsOptions);

        verify(issueStandardsOptions).withRelations(Mockito.any());
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

    public void testSaveUpdatesEntity() throws Exception {
        final IssueStandardModelInterface issueStandardModel = this.createIssueStandardModel(1, 600, 10, 1);

        this.issueStandardsService.save(issueStandardModel);

        verify(this.issueStandardsMapper, times(1)).update(this.mapIssueStandardEntity(issueStandardModel));
    }

    @Test
    public void testSaveInsertsEntity() throws Exception {
        final IssueStandardModelInterface issueStandardModel = this.createIssueStandardModel(null, 1200, 20, 5);
        final Integer id = 10;

        doAnswer(invocations -> {
            final IssueStandardEntity issueStandardEntity = (IssueStandardEntity) invocations.getArguments()[0];
            issueStandardEntity.setId(id);
            return null;
        }).when(this.issueStandardsMapper).insert(Mockito.any(IssueStandardEntity.class));

        this.issueStandardsService.save(issueStandardModel);

        verify(this.issueStandardsMapper, times(1)).insert(this.mapIssueStandardEntity(issueStandardModel));
        Assert.assertEquals(id, issueStandardModel.getId());
    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {
        final IssueStandardModelInterface issueStandardModel = this.createIssueStandardModel(1, 600, 10, 2);

        this.issueStandardsService.delete(issueStandardModel);

        verify(issueStandardsMapper, times(1)).delete(this.mapIssueStandardEntity(issueStandardModel));
    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {
        final IssueStandardModelInterface issueStandardModel = this.createIssueStandardModel(null, 600, 10, 1);

        exception.expect(DeleteUnidentifiedModelException.class);
        this.issueStandardsService.delete(issueStandardModel);
    }
}
