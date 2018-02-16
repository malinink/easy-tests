package easytests.core.services;

import easytests.core.entities.QuizEntity;
import easytests.core.mappers.QuizzesMapper;
import easytests.core.models.IssueModelInterface;
import easytests.core.models.QuizModelInterface;
import easytests.core.options.QuizzesOptionsInterface;
import easytests.core.services.exceptions.DeleteUnidentifiedModelException;
import easytests.support.QuizzesSupport;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

/**
 * @author miron97
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

    private QuizzesSupport quizzesSupport = new QuizzesSupport();

    private List<QuizEntity> getQuizzesFixturesEntities() {
        final List<QuizEntity> quizzesEntities = new ArrayList<>(2);
        quizzesEntities.add(this.quizzesSupport.getEntityFixtureMock(0));
        quizzesEntities.add(this.quizzesSupport.getEntityFixtureMock(1));
        return quizzesEntities;
    }

    private List<QuizModelInterface> getQuizzesFixturesModels() {
        final List<QuizModelInterface> quizzesModels = new ArrayList<>(2);
        quizzesModels.add(this.quizzesSupport.getModelFixtureMock(0));
        quizzesModels.add(this.quizzesSupport.getModelFixtureMock(1));
        return quizzesModels;
    }

    private void assertServicesSet(QuizzesOptionsInterface quizzesOptions) throws Exception {
        this.assertServicesSet(quizzesOptions, 1);
    }

    private void assertServicesSet(QuizzesOptionsInterface quizzesOptions, Integer times) throws Exception {
        verify(quizzesOptions, times(times)).setIssuesService(any(IssuesServiceInterface.class));
        verify(quizzesOptions, times(times)).setPointsService(any(PointsServiceInterface.class));
        verify(quizzesOptions, times(times)).setTesteesService(any(TesteesServiceInterface.class));
        verify(quizzesOptions, times(times)).setQuizzesService(this.quizzesService);
    }

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<QuizEntity> quizzesEntities = getQuizzesFixturesEntities();
        when(this.quizzesMapper.findAll()).thenReturn(quizzesEntities);

        final List<QuizModelInterface> quizzesFoundedModels = this.quizzesService.findAll();

        this.quizzesSupport.assertModelsListEquals(this.getQuizzesFixturesModels(), quizzesFoundedModels);
    }

    @Test
    public void testFindAllAbsentList() throws Exception {
        when(this.quizzesMapper.findAll()).thenReturn(new ArrayList<>(0));

        final List<QuizModelInterface> quizzesFoundedModels = this.quizzesService.findAll();

        Assert.assertNotNull(quizzesFoundedModels);
        Assert.assertEquals(0, quizzesFoundedModels.size());
    }

    @Test
    public void testFindPresentModel() throws Exception {
        final QuizEntity quizEntity = this.quizzesSupport.getEntityFixtureMock(0);
        when(this.quizzesMapper.find(quizEntity.getId())).thenReturn(quizEntity);

        final QuizModelInterface quizFoundedModel = this.quizzesService.find(quizEntity.getId());

        this.quizzesSupport.assertEquals(this.quizzesSupport.getModelFixtureMock(0), quizFoundedModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        when(this.quizzesMapper.find(id)).thenReturn(null);

        final QuizModelInterface quizFoundedModel = this.quizzesService.find(id);

        Assert.assertNull(quizFoundedModel);
    }

    @Test
    public void testFindWithOptions() throws Exception {
        final ArgumentCaptor<QuizModelInterface> quizModelCaptor = ArgumentCaptor.
                forClass(QuizModelInterface.class);
        final QuizEntity quizEntity = this.quizzesSupport.getEntityFixtureMock(0);
        final QuizModelInterface quizModel = this.quizzesSupport.getModelFixtureMock(0);
        final QuizzesOptionsInterface quizzesOptions = Mockito.mock(QuizzesOptionsInterface.class);
        when(this.quizzesMapper.find(quizModel.getId())).thenReturn(quizEntity);
        when(quizzesOptions.withRelations(quizModelCaptor.capture())).thenReturn(quizModel);

        final QuizModelInterface quizFoundedModel = this.quizzesService.find(quizModel.getId(), quizzesOptions);

        this.assertServicesSet(quizzesOptions);
        this.quizzesSupport.assertEquals(quizModel, quizModelCaptor.getValue());
        Assert.assertSame(quizModel, quizFoundedModel);
    }

    @Test
    public void testFindAllWithOptions() throws Exception {
        final ArgumentCaptor<List> listCaptor = ArgumentCaptor.forClass(List.class);
        final List<QuizEntity> quizzesEntities = this.getQuizzesFixturesEntities();
        final List<QuizModelInterface> quizzesModels = this.getQuizzesFixturesModels();
        final QuizzesOptionsInterface quizzesOptions = Mockito.mock(QuizzesOptionsInterface.class);
        when(this.quizzesMapper.findAll()).thenReturn(quizzesEntities);
        when(quizzesOptions.withRelations(listCaptor.capture())).thenReturn(quizzesModels);

        final List<QuizModelInterface> quizzesFoundedModels = this.quizzesService.findAll(quizzesOptions);

        this.assertServicesSet(quizzesOptions);
        this.quizzesSupport.assertModelsListEquals(quizzesModels, listCaptor.getValue());
        Assert.assertSame(quizzesModels, quizzesFoundedModels);
    }

    @Test
    public void testFindByIssuePresentList() throws Exception {
        final IssueModelInterface issueModel = Mockito.mock(IssueModelInterface.class);
        final List<QuizEntity> quizzesEntities = this.getQuizzesFixturesEntities();
        when(this.quizzesMapper.findByIssueId(issueModel.getId())).thenReturn(quizzesEntities);
        final List<QuizModelInterface> quizzesModels = this.getQuizzesFixturesModels();

        final List<QuizModelInterface> quizzesFoundedModels = this.quizzesService.findByIssue(issueModel);

        this.quizzesSupport.assertModelsListEquals(quizzesModels, quizzesFoundedModels);
    }

    @Test
    public void testFindByIssueAbsentList() throws Exception {
        final IssueModelInterface issueModel = Mockito.mock(IssueModelInterface.class);
        when(this.quizzesMapper.findByIssueId(issueModel.getId())).thenReturn(new ArrayList<>(0));

        final List<QuizModelInterface> quizzesFoundedModels = this.quizzesService.findByIssue(issueModel);

        Assert.assertEquals(0, quizzesFoundedModels.size());
    }

    @Test
    public void testFindByIssueWithOptions() throws Exception {
        final ArgumentCaptor<List> listCaptor = ArgumentCaptor.forClass(List.class);
        final IssueModelInterface issueModel = Mockito.mock(IssueModelInterface.class);
        final List<QuizEntity> quizzesEntities = this.getQuizzesFixturesEntities();
        when(this.quizzesMapper.findByIssueId(issueModel.getId())).thenReturn(quizzesEntities);
        final List<QuizModelInterface> quizzesModels = this.getQuizzesFixturesModels();
        final QuizzesOptionsInterface quizzesOptions = Mockito.mock(QuizzesOptionsInterface.class);
        when(quizzesOptions.withRelations(listCaptor.capture())).thenReturn(quizzesModels);

        final List<QuizModelInterface> quizzesFoundedModels =
                this.quizzesService.findByIssue(issueModel, quizzesOptions);

        this.assertServicesSet(quizzesOptions);
        this.quizzesSupport.assertModelsListEquals(quizzesModels, listCaptor.getValue());
        Assert.assertSame(quizzesModels, quizzesFoundedModels);
    }

    @Test
    public void testSaveCreatesEntity() throws Exception {
        final ArgumentCaptor<QuizEntity> quizEntityCaptor = ArgumentCaptor.forClass(QuizEntity.class);

        this.quizzesService.save(this.quizzesSupport.getModelAdditionalMock(0));

        verify(this.quizzesMapper, times(1)).insert(quizEntityCaptor.capture());
        this.quizzesSupport.assertEquals(this.quizzesSupport.getModelAdditionalMock(0),
                quizEntityCaptor.getValue());
    }

    @Test
    public void testSaveUpdateEntityIdOnCreation() throws Exception {
        final QuizModelInterface quizAdditionalModel = this.quizzesSupport.getModelAdditionalMock(0);
        doAnswer(invocation -> {
            final QuizEntity quizEntity = (QuizEntity) invocation.getArguments()[0];
            quizEntity.setId(5);
            return null;
        }).when(this.quizzesMapper).insert(Mockito.any(QuizEntity.class));

        this.quizzesService.save(quizAdditionalModel);

        verify(quizAdditionalModel, times(1)).setId(5);
    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {
        final ArgumentCaptor<QuizEntity> quizEntityCaptor = ArgumentCaptor.forClass(QuizEntity.class);

        this.quizzesService.save(this.quizzesSupport.getModelFixtureMock(0));

        verify(this.quizzesMapper, times(1)).update(quizEntityCaptor.capture());
        this.quizzesSupport.assertEquals(this.quizzesSupport.getEntityFixtureMock(0),
                quizEntityCaptor.getValue());

    }

    @Test
    public void testSaveWithOptions() throws Exception {
        final QuizModelInterface quizModel = this.quizzesSupport.getModelFixtureMock(0);
        final QuizzesOptionsInterface quizzesOptions = Mockito.mock(QuizzesOptionsInterface.class);

        this.quizzesService.save(quizModel, quizzesOptions);

        this.assertServicesSet(quizzesOptions);
        verify(quizzesOptions, times(1)).saveWithRelations(quizModel);
        verifyNoMoreInteractions(this.quizzesMapper);
    }

    @Test
    public void testSaveModelsList() throws Exception {
        final ArgumentCaptor<QuizEntity> quizEntityCaptor = ArgumentCaptor.forClass(QuizEntity.class);
        final List<QuizModelInterface> quizzesModels = this.getQuizzesFixturesModels();

        this.quizzesService.save(quizzesModels);

        verify(this.quizzesMapper, times(quizzesModels.size())).update(quizEntityCaptor.capture());
        this.quizzesSupport.assertEntitiesListEquals(this.getQuizzesFixturesEntities(),
                quizEntityCaptor.getAllValues());
    }

    @Test
    public void testSaveModelsListWithOptions() throws Exception {
        final ArgumentCaptor<QuizModelInterface> quizModelCaptor =
                ArgumentCaptor.forClass(QuizModelInterface.class);
        final List<QuizModelInterface> quizzesModels = this.getQuizzesFixturesModels();
        final QuizzesOptionsInterface quizzesOptions = Mockito.mock(QuizzesOptionsInterface.class);

        this.quizzesService.save(quizzesModels, quizzesOptions);

        this.assertServicesSet(quizzesOptions, quizzesModels.size());
        verify(quizzesOptions, times(quizzesModels.size())).saveWithRelations(quizModelCaptor.capture());
        this.quizzesSupport.assertModelsListEquals(quizzesModels, quizModelCaptor.getAllValues());
    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {
        final ArgumentCaptor<QuizEntity> quizEntityCaptor = ArgumentCaptor.forClass(QuizEntity.class);

        this.quizzesService.delete(this.quizzesSupport.getModelFixtureMock(0));

        verify(this.quizzesMapper, times(1)).delete(quizEntityCaptor.capture());
        this.quizzesSupport.assertEquals(this.quizzesSupport.getEntityFixtureMock(0), quizEntityCaptor.getValue());
    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {
        final QuizModelInterface quizModel = this.quizzesSupport.getModelAdditionalMock(0);

        exception.expect(DeleteUnidentifiedModelException.class);
        this.quizzesService.delete(quizModel);
    }

    @Test
    public void testDeleteWithOptions() throws Exception {
        final QuizModelInterface quizModel = this.quizzesSupport.getModelFixtureMock(0);
        final QuizzesOptionsInterface quizzesOptions = Mockito.mock(QuizzesOptionsInterface.class);

        this.quizzesService.delete(quizModel, quizzesOptions);

        this.assertServicesSet(quizzesOptions);
        verify(quizzesOptions, times(1)).deleteWithRelations(quizModel);
        verifyNoMoreInteractions(this.quizzesMapper);
    }

    @Test
    public void testDeleteModelsList() throws Exception {
        final ArgumentCaptor<QuizEntity> quizEntityCaptor = ArgumentCaptor.forClass(QuizEntity.class);
        final List<QuizModelInterface> quizzesModels = this.getQuizzesFixturesModels();

        this.quizzesService.delete(quizzesModels);

        verify(this.quizzesMapper, times(quizzesModels.size())).delete(quizEntityCaptor.capture());
        this.quizzesSupport.assertEntitiesListEquals(this.getQuizzesFixturesEntities(), quizEntityCaptor.getAllValues());
    }

    @Test
    public void testDeleteModelsListWithOptions() throws Exception {
        final ArgumentCaptor<QuizModelInterface> quizModelCaptor = ArgumentCaptor.forClass(QuizModelInterface.class);
        final List<QuizModelInterface> quizzesModels = this.getQuizzesFixturesModels();
        final QuizzesOptionsInterface quizzesOptions = Mockito.mock(QuizzesOptionsInterface.class);

        this.quizzesService.delete(quizzesModels, quizzesOptions);

        this.assertServicesSet(quizzesOptions, quizzesModels.size());
        verify(quizzesOptions, times(quizzesModels.size())).deleteWithRelations(quizModelCaptor.capture());
        this.quizzesSupport.assertModelsListEquals(quizzesModels, quizModelCaptor.getAllValues());
        verifyNoMoreInteractions(this.quizzesMapper);
    }
}
