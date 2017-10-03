package easytests.core.services;

import easytests.core.entities.QuizEntity;
import easytests.core.mappers.QuizzesMapper;
import easytests.core.models.IssueModelInterface;
import easytests.core.models.QuizModel;
import easytests.core.models.QuizModelInterface;
import easytests.core.options.QuizzesOptionsInterface;
import easytests.core.services.exceptions.DeleteUnidentifiedModelException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vkpankov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuizzesServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Autowired
    private QuizzesService quizzesService;

    @MockBean
    private QuizzesMapper quizzesMapper;

    private QuizModelInterface createQuizModel(Integer id, String inviteCode, Integer issueId) {

        final QuizModelInterface quizModel = new QuizModel();
        quizModel.setId(id);
        quizModel.setInviteCode(inviteCode);

        final IssueModelInterface issueModel = Mockito.mock(IssueModelInterface.class);
        Mockito.when(issueModel.getId()).thenReturn(issueId);

        quizModel.setIssue(issueModel);

        return quizModel;

    }

    private QuizEntity createQuizEntityMock(Integer id, String inviteCode, Integer issueId) {

        final QuizEntity quizEntity = Mockito.mock(QuizEntity.class);

        Mockito.when(quizEntity.getId()).thenReturn(id);
        Mockito.when(quizEntity.getInviteCode()).thenReturn(inviteCode);
        Mockito.when(quizEntity.getIssueId()).thenReturn(issueId);

        return quizEntity;

    }

    private QuizModelInterface mapQuizModel(QuizEntity quizEntity) {

        final QuizModelInterface quizModel = new QuizModel();
        quizModel.map(quizEntity);
        return quizModel;

    }

    private QuizEntity mapQuizEntity(QuizModelInterface quizModel) {

        final QuizEntity quizEntity = new QuizEntity();
        quizEntity.map(quizModel);
        return quizEntity;

    }

    private List<QuizEntity> getQuizzesEntities() {
        final List<QuizEntity> quizzesEntities = new ArrayList<>(2);
        final QuizEntity quizEntityFirst = this.createQuizEntityMock(1, "test1", 1);
        final QuizEntity quizEntitySecond = this.createQuizEntityMock(2,  "test2", 2);
        quizzesEntities.add(quizEntityFirst);
        quizzesEntities.add(quizEntitySecond);
        return quizzesEntities;
    }

    private List<QuizModelInterface> getIssuesModels() {
        final List<QuizModelInterface> quizzesModels = new ArrayList<>(2);
        for (QuizEntity quizEntity : this.getQuizzesEntities()) {
            quizzesModels.add(this.mapQuizModel(quizEntity));
        }
        return quizzesModels;
    }

    @Test
    public void testFindAllPresentList() throws Exception {

        final List<QuizEntity> quizzesEntities = getQuizzesEntities();

        given(this.quizzesMapper.findAll()).willReturn(quizzesEntities);

        final List<QuizModelInterface> quizzesModels = this.quizzesService.findAll();

        Assert.assertEquals(2, quizzesModels.size());
        Assert.assertEquals(quizzesModels.get(0), this.mapQuizModel(quizzesEntities.get(0)));
        Assert.assertEquals(quizzesModels.get(1), this.mapQuizModel(quizzesEntities.get(1)));

    }

    @Test
    public void testFindAllAbsentList() throws Exception {

        given(this.quizzesMapper.findAll()).willReturn(new ArrayList<>(0));

        final List<QuizModelInterface> quizzesModels = this.quizzesService.findAll();

        Assert.assertEquals(0, quizzesModels.size());

    }

    @Test
    public void testFindPresentModel() throws Exception {

        final Integer id = 1;
        final QuizEntity quizEntity = this.createQuizEntityMock(id, "test", 1);
        given(this.quizzesMapper.find(id)).willReturn(quizEntity);

        final QuizModelInterface quizModel = this.quizzesService.find(id);

        Assert.assertEquals(this.mapQuizModel(quizEntity), quizModel);

    }

    @Test
    public void testFindAbsentModel() throws Exception {

        final Integer id = 10;
        given(this.quizzesMapper.find(id)).willReturn(null);

        final QuizModelInterface quizModel = this.quizzesService.find(id);

        Assert.assertEquals(null, quizModel);

    }

    @Test
    public void testFindByIssue() throws Exception {
        final Integer issueId = 7;
        final IssueModelInterface issueModel = Mockito.mock(IssueModelInterface.class);
        final QuizEntity quizEntityFirst = this.createQuizEntityMock(3, "test3", issueId);
        final QuizEntity quizEntitySecond = this.createQuizEntityMock(12, "test12",  issueId);
        final List<QuizEntity> quizzesEntities = new ArrayList<>();
        quizzesEntities.add(quizEntityFirst);
        quizzesEntities.add(quizEntitySecond);
        Mockito.when(issueModel.getId()).thenReturn(issueId);
        given(this.quizzesMapper.findByIssueId(issueId)).willReturn(quizzesEntities);
        final List<QuizModelInterface> quizzesModels = new ArrayList<>();
        quizzesModels.add(this.mapQuizModel(quizEntityFirst));
        quizzesModels.add(this.mapQuizModel(quizEntitySecond));

        final List<QuizModelInterface> foundedQuizzesModels = this.quizzesService.findByIssue(issueModel);

        verify(this.quizzesMapper).findByIssueId(issueId);
        Assert.assertEquals(quizzesModels, foundedQuizzesModels);
    }

    @Test
    public void testFindByIssueWithOptions() throws Exception {
        final Integer issueId = 7;
        final IssueModelInterface issueModel = Mockito.mock(IssueModelInterface.class);
        final QuizEntity quizEntityFirst = this.createQuizEntityMock(3, "test3", issueId);
        final QuizEntity quizEntitySecond = this.createQuizEntityMock(12, "test12", issueId);
        final List<QuizEntity> quizzesEntities = new ArrayList<>();
        quizzesEntities.add(quizEntityFirst);
        quizzesEntities.add(quizEntitySecond);
        given(issueModel.getId()).willReturn(issueId);
        given(this.quizzesMapper.findByIssueId(issueId)).willReturn(quizzesEntities);
        final List<QuizModelInterface> quizzesModels = new ArrayList<>();
        quizzesModels.add(this.mapQuizModel(quizEntityFirst));
        quizzesModels.add(this.mapQuizModel(quizEntitySecond));
        final QuizzesOptionsInterface quizOptions = Mockito.mock(QuizzesOptionsInterface.class);
        given(quizOptions.withRelations(quizzesModels)).willReturn(quizzesModels);

        final List<QuizModelInterface> foundedQuizzesModels =
                this.quizzesService.findByIssue(issueModel, quizOptions);

        verify(this.quizzesMapper).findByIssueId(issueId);
        verify(quizOptions).withRelations(quizzesModels);
        Assert.assertEquals(quizzesModels, foundedQuizzesModels);
    }

    @Test
    public void testSaveCreatesEntity() throws Exception {

        final QuizModelInterface quizModel = this.createQuizModel(null, "test", 1);

        this.quizzesService.save(quizModel);

        verify(this.quizzesMapper, times(1)).insert(this.mapQuizEntity(quizModel));

    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {

        final QuizModelInterface quizModel = this.createQuizModel(1, "test", 1);

        this.quizzesService.save(quizModel);

        verify(this.quizzesMapper, times(1)).update(this.mapQuizEntity(quizModel));

    }

    @Test
    public void testSaveEntitiesList() throws Exception {

        final QuizModelInterface quizModelFirst = this.createQuizModel(null, "test1", 1);
        final QuizModelInterface quizModelSecond = this.createQuizModel(null, "test2", 2);

        final QuizzesOptionsInterface quizzesOptions = Mockito.mock(QuizzesOptionsInterface.class);

        final List<QuizModelInterface> quizzesModels = new ArrayList<>();
        quizzesModels.add(quizModelFirst);
        quizzesModels.add(quizModelSecond);

        final QuizzesServiceInterface quizzesServiceSpy = Mockito.spy(quizzesService);

        quizzesServiceSpy.save(quizzesModels);
        verify(quizzesServiceSpy, times(1)).save(quizModelFirst);
        verify(quizzesServiceSpy, times(1)).save(quizModelSecond);

        quizzesServiceSpy.save(quizzesModels, quizzesOptions);
        verify(quizzesServiceSpy, times(1)).save(quizModelFirst, quizzesOptions);
        verify(quizzesServiceSpy, times(1)).save(quizModelSecond, quizzesOptions);

    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {

        final QuizModelInterface quizModel = this.createQuizModel(1, "test", 1);

        this.quizzesService.delete(quizModel);

        verify(this.quizzesMapper, times(1)).delete(this.mapQuizEntity(quizModel));

    }

    @Test
    public void testDeleteModelsList() throws Exception {

        final QuizModelInterface quizModelFirst = this.createQuizModel(2, "test2", 1);
        final QuizModelInterface quizModelSecond = this.createQuizModel(3, "test3", 2);

        final QuizzesOptionsInterface quizzesOptions = Mockito.mock(QuizzesOptionsInterface.class);

        final List<QuizModelInterface> quizzesModels = new ArrayList<>();
        quizzesModels.add(quizModelFirst);
        quizzesModels.add(quizModelSecond);

        final QuizzesServiceInterface quizzesServiceSpy = Mockito.spy(quizzesService);

        quizzesServiceSpy.delete(quizzesModels);
        verify(quizzesServiceSpy, times(1)).delete(quizModelFirst);
        verify(quizzesServiceSpy, times(1)).delete(quizModelSecond);

        quizzesServiceSpy.delete(quizzesModels, quizzesOptions);
        verify(quizzesServiceSpy, times(1)).delete(quizModelFirst, quizzesOptions);
        verify(quizzesServiceSpy, times(1)).delete(quizModelSecond, quizzesOptions);
    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {

        final QuizModelInterface quizModel = this.createQuizModel(null, "test", 1);

        exception.expect(DeleteUnidentifiedModelException.class);
        this.quizzesService.delete(quizModel);

    }

    @Test
    public void testFindWithOptions() throws Exception {
        final Integer id = 1;
        final Integer issueId = 1;
        final QuizEntity quizEntity = this.createQuizEntityMock(4, "test3", issueId);
        final QuizModelInterface quizModel = this.mapQuizModel(quizEntity);
        final QuizzesOptionsInterface quizzesOptions = Mockito.mock(QuizzesOptionsInterface.class);
        given(this.quizzesMapper.find(id)).willReturn(quizEntity);
        given(quizzesOptions.withRelations(quizModel)).willReturn(quizModel);

        final QuizModelInterface foundedQuizModel = this.quizzesService.find(id, quizzesOptions);

        Assert.assertEquals(quizModel, foundedQuizModel);
        verify(quizzesOptions).withRelations(quizModel);
    }

    @Test
    public void testFindAllWithOptions() throws Exception {
        final List<QuizEntity> quizzesEntities = this.getQuizzesEntities();
        final List<QuizModelInterface> quizzesModels = this.getIssuesModels();
        final QuizzesOptionsInterface quizzesOptions = Mockito.mock(QuizzesOptionsInterface.class);
        given(this.quizzesMapper.findAll()).willReturn(quizzesEntities);
        given(quizzesOptions.withRelations(Mockito.anyList())).willReturn(quizzesModels);

        final List<QuizModelInterface> foundedQuizzesModels = this.quizzesService.findAll(quizzesOptions);

        verify(quizzesOptions).withRelations(foundedQuizzesModels);
        Assert.assertEquals(quizzesModels, foundedQuizzesModels);
    }

    @Test
    public void testSaveWithOptions() throws Exception {
        final QuizModelInterface quizModel = this.createQuizModel(null, "test", 1);
        final QuizzesOptionsInterface quizzesOptions = Mockito.mock(QuizzesOptionsInterface.class);

        this.quizzesService.save(quizModel, quizzesOptions);

        verify(quizzesOptions).saveWithRelations(quizModel);
    }

    @Test
    public void testDeleteWithOptions() throws Exception {
        final QuizModelInterface quizModel = this.createQuizModel(null, "test", 1);
        final QuizzesOptionsInterface quizzesOptions = Mockito.mock(QuizzesOptionsInterface.class);

        this.quizzesService.delete(quizModel, quizzesOptions);

        verify(quizzesOptions).deleteWithRelations(quizModel);
    }
}
