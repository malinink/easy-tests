package easytests.core.services;

import easytests.core.entities.IssueStandardQuestionTypeOptionEntity;
import easytests.core.mappers.IssueStandardQuestionTypeOptionsMapper;
import easytests.core.models.IssueStandardModelInterface;
import easytests.core.models.IssueStandardQuestionTypeOptionModel;
import easytests.core.models.IssueStandardQuestionTypeOptionModelInterface;
import easytests.core.options.IssueStandardQuestionTypeOptionsOptionsInterface;
import easytests.core.services.exceptions.DeleteUnidentifiedModelException;
import easytests.support.IssueStandardQuestionTypeOptionsSupport;
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
 * @author VeronikaRevjakina
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IssueStandardQuestionTypeOptionsServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private IssueStandardQuestionTypeOptionsMapper questionTypeOptionsMapper;

    @Autowired
    private IssueStandardQuestionTypeOptionsService questionTypeOptionsService;

    private IssueStandardQuestionTypeOptionsSupport questionTypeOptionsSupport=new IssueStandardQuestionTypeOptionsSupport();

    private IssueStandardSupport issueStandardSupport=new IssueStandardSupport();


    private List<IssueStandardQuestionTypeOptionEntity> getQuestionTypeOptionFixturesEntities() {
        final List<IssueStandardQuestionTypeOptionEntity> questionTypeOptionEntities = new ArrayList<>(2);
        questionTypeOptionEntities.add(this.questionTypeOptionsSupport.getEntityFixtureMock(0));
        questionTypeOptionEntities.add(this.questionTypeOptionsSupport.getEntityFixtureMock(1));
        return questionTypeOptionEntities;
    }

    private List<IssueStandardQuestionTypeOptionModelInterface> getQuestionTypeOptionFixturesModels() {
        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels = new ArrayList<>(2);
        questionTypeOptionModels.add(this.questionTypeOptionsSupport.getModelFixtureMock(0));
        questionTypeOptionModels.add(this.questionTypeOptionsSupport.getModelFixtureMock(1));
        return questionTypeOptionModels;
    }

    private void assertServicesSet(IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionOptions) {
        this.assertServicesSet(questionTypeOptionOptions,1) ;
    }

    private void assertServicesSet(IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionOptions, Integer times) {
        verify(questionTypeOptionOptions, times(times)).setQuestionTypesService(any(QuestionTypesServiceInterface.class));
        verify(questionTypeOptionOptions, times(times)).setQuestionTypeOptionsService(any(IssueStandardQuestionTypeOptionsServiceInterface.class));
        verify(questionTypeOptionOptions, times(times)).setIssueStandardsService(any(IssueStandardsServiceInterface.class));
    }

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<IssueStandardQuestionTypeOptionEntity> questionTypeOptionEntities
                = this.getQuestionTypeOptionFixturesEntities();

        when(this.questionTypeOptionsMapper.findAll()).thenReturn(questionTypeOptionEntities);

        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels
                = this.questionTypeOptionsService.findAll();

        this.questionTypeOptionsSupport.assertModelsListEquals(this.getQuestionTypeOptionFixturesModels(),questionTypeOptionModels);
    }

    @Test
    public void testFindAllAbsentList() throws Exception {
        when(this.questionTypeOptionsMapper.findAll()).thenReturn(new ArrayList<>(0));

        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels
                = this.questionTypeOptionsService.findAll();

        Assert.assertEquals(0, questionTypeOptionModels.size());
    }

    @Test
    public void testFindAllWithOptions() throws Exception {
        final ArgumentCaptor<List> listCaptor = ArgumentCaptor.forClass(List.class);
        final List<IssueStandardQuestionTypeOptionEntity> questionTypeOptionEntities
                = this.getQuestionTypeOptionFixturesEntities();
        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels
                = this.getQuestionTypeOptionFixturesModels();

        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions
                = Mockito.mock(IssueStandardQuestionTypeOptionsOptionsInterface.class);
        when(this.questionTypeOptionsMapper.findAll()).thenReturn(questionTypeOptionEntities);
        when(questionTypeOptionsOptions.withRelations(listCaptor.capture())).thenReturn(questionTypeOptionModels);

        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionFoundedModels
                = this.questionTypeOptionsService.findAll(questionTypeOptionsOptions);

        this.assertServicesSet(questionTypeOptionsOptions);
        this.questionTypeOptionsSupport.assertModelsListEquals(questionTypeOptionModels, listCaptor.getValue());
        Assert.assertSame(questionTypeOptionModels, questionTypeOptionFoundedModels);
    }

    @Test
    public void testFindPresentModel() throws Exception {
        final IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity
                = this.questionTypeOptionsSupport.getEntityFixtureMock(0);
        when(this.questionTypeOptionsMapper.find(questionTypeOptionEntity.getId())).thenReturn(questionTypeOptionEntity);

        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionFoundedModel
                = this.questionTypeOptionsService.find(questionTypeOptionEntity.getId());

        this.questionTypeOptionsSupport.assertEquals(this.questionTypeOptionsSupport.getModelFixtureMock(0),questionTypeOptionFoundedModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        when(this.questionTypeOptionsMapper.find(id)).thenReturn(null);

        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionFoundedModel
                = this.questionTypeOptionsService.find(id);

        Assert.assertNull(questionTypeOptionFoundedModel);
    }

    @Test
    public void testFindWithOptions() throws Exception {
        final ArgumentCaptor<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModelCaptor = ArgumentCaptor.forClass(IssueStandardQuestionTypeOptionModelInterface.class);
        final IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity
                = this.questionTypeOptionsSupport.getEntityFixtureMock(0);
        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel
                = this.questionTypeOptionsSupport.getModelFixtureMock(0);
        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions
                = Mockito.mock(IssueStandardQuestionTypeOptionsOptionsInterface.class);
        when(this.questionTypeOptionsMapper.find(questionTypeOptionModel.getId())).thenReturn(questionTypeOptionEntity);
        when(questionTypeOptionsOptions.withRelations(questionTypeOptionModelCaptor.capture())).thenReturn(questionTypeOptionModel);

        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionFoundedModel
                = this.questionTypeOptionsService.find(questionTypeOptionModel.getId(), questionTypeOptionsOptions);

        this.assertServicesSet(questionTypeOptionsOptions);
        this.questionTypeOptionsSupport.assertEquals(questionTypeOptionModel,questionTypeOptionModelCaptor.getValue());
        Assert.assertSame(questionTypeOptionModel,questionTypeOptionFoundedModel);
    }

    @Test
    public void testFindByIssueStandardPresentList() throws Exception {
        final IssueStandardModelInterface issueStandardModel=this.issueStandardSupport.getModelFixtureMock(0);
        final List<IssueStandardQuestionTypeOptionEntity> questionTypeOptionEntities = this.getQuestionTypeOptionFixturesEntities();
        when(this.questionTypeOptionsMapper.findByIssueStandardId(issueStandardModel.getId()))
                .thenReturn(questionTypeOptionEntities);

        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionFoundedModels
                = this.questionTypeOptionsService.findByIssueStandard(issueStandardModel);

        this.questionTypeOptionsSupport.assertModelsListEquals(this.getQuestionTypeOptionFixturesModels(),questionTypeOptionFoundedModels);
    }

    @Test
    public void testFindByIssueStandardAbsentList() throws Exception {
        final IssueStandardModelInterface issueStandardModel=this.issueStandardSupport.getModelFixtureMock(0);
        when(this.questionTypeOptionsMapper.findByIssueStandardId(issueStandardModel.getId())).thenReturn(new ArrayList<>(0));

        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionFoundedModels
                = this.questionTypeOptionsService.findByIssueStandard(issueStandardModel);

        Assert.assertEquals(0, questionTypeOptionFoundedModels.size());
    }

    @Test
    public void testFindByIssueStandardWithOptions() throws Exception {
        final ArgumentCaptor<List> listCaptor = ArgumentCaptor.forClass(List.class);
        final IssueStandardModelInterface issueStandardModel=this.issueStandardSupport.getModelFixtureMock(0);
        final List<IssueStandardQuestionTypeOptionEntity> questionTypeOptionEntities = this.getQuestionTypeOptionFixturesEntities();
        when(this.questionTypeOptionsMapper.findByIssueStandardId(issueStandardModel.getId()))
                .thenReturn(questionTypeOptionEntities);
        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels
                = this.getQuestionTypeOptionFixturesModels();
        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions
                = Mockito.mock(IssueStandardQuestionTypeOptionsOptionsInterface.class);
        when(questionTypeOptionsOptions.withRelations(listCaptor.capture())).thenReturn(questionTypeOptionModels);

        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionFoundedModels
                = this.questionTypeOptionsService.findByIssueStandard(issueStandardModel, questionTypeOptionsOptions);

        this.assertServicesSet(questionTypeOptionsOptions);
        this.questionTypeOptionsSupport.assertModelsListEquals(questionTypeOptionModels,listCaptor.getValue());
        Assert.assertSame(questionTypeOptionModels,questionTypeOptionFoundedModels);
    }

    @Test
    public void testSaveCreatesEntity() throws Exception {
        final ArgumentCaptor<IssueStandardQuestionTypeOptionEntity> questionTypeOptionEntityCaptor = ArgumentCaptor.forClass(IssueStandardQuestionTypeOptionEntity.class);
        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel = this.questionTypeOptionsSupport.getModelAdditionalMock(0);

        this.questionTypeOptionsService.save(questionTypeOptionModel);

        verify(this.questionTypeOptionsMapper, times(1)).insert(questionTypeOptionEntityCaptor.capture());
        this.questionTypeOptionsSupport.assertEquals(this.questionTypeOptionsSupport.getEntityAdditionalMock(0), questionTypeOptionEntityCaptor.getValue());
    }

    @Test
    public void testSaveUpdateEntityIdOnCreation() throws Exception {
        final Integer id = 10;
        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel = this.questionTypeOptionsSupport.getModelAdditionalMock(0);
        doAnswer(invocations -> {
            final IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity
                    = (IssueStandardQuestionTypeOptionEntity) invocations.getArguments()[0];
            questionTypeOptionEntity.setId(id);
            return null;
        }).when(this.questionTypeOptionsMapper).insert(any());

        this.questionTypeOptionsService.save(questionTypeOptionModel);

        verify(questionTypeOptionModel, times(1)).setId(id);
    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {
        final ArgumentCaptor<IssueStandardQuestionTypeOptionEntity> questionTypeOptionEntityCaptor = ArgumentCaptor.forClass(IssueStandardQuestionTypeOptionEntity.class);
        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel = this.questionTypeOptionsSupport.getModelFixtureMock(0);

        this.questionTypeOptionsService.save(questionTypeOptionModel);

        verify(this.questionTypeOptionsMapper, times(1)).update(questionTypeOptionEntityCaptor.capture());
        this.questionTypeOptionsSupport.assertEquals(this.questionTypeOptionsSupport.getEntityFixtureMock(0),questionTypeOptionEntityCaptor.getValue());
    }

    @Test
    public void testSaveWithOptions() throws Exception {
        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel = this.questionTypeOptionsSupport.getModelFixtureMock(0);
        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions = Mockito.mock(IssueStandardQuestionTypeOptionsOptionsInterface.class);

        this.questionTypeOptionsService.save(questionTypeOptionModel,questionTypeOptionsOptions );

        this.assertServicesSet(questionTypeOptionsOptions);
        verify(questionTypeOptionsOptions,times(1)).saveWithRelations(questionTypeOptionModel);
        verifyNoMoreInteractions(this.questionTypeOptionsMapper);
    }


    @Test
    public void testSaveModelsList() throws Exception {
        final ArgumentCaptor<IssueStandardQuestionTypeOptionEntity> questionTypeOptionEntityCaptor = ArgumentCaptor.forClass(IssueStandardQuestionTypeOptionEntity.class);
        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels = this.getQuestionTypeOptionFixturesModels();


        this.questionTypeOptionsService.save(questionTypeOptionModels);

        verify(this.questionTypeOptionsMapper, times(questionTypeOptionModels.size()))
                .update(questionTypeOptionEntityCaptor.capture());
        this.questionTypeOptionsSupport.assertEntitiesListEquals(this.getQuestionTypeOptionFixturesEntities(),questionTypeOptionEntityCaptor.getAllValues());
    }


    @Test
    public void testSaveModelsListWithOptions() throws Exception {
        final ArgumentCaptor<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModelCaptor = ArgumentCaptor.forClass(IssueStandardQuestionTypeOptionModelInterface.class);
        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels = this.getQuestionTypeOptionFixturesModels();
        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions = Mockito.mock(IssueStandardQuestionTypeOptionsOptionsInterface.class);

        this.questionTypeOptionsService.save(questionTypeOptionModels, questionTypeOptionsOptions);

        this.assertServicesSet(questionTypeOptionsOptions, questionTypeOptionModels.size());
        verify(questionTypeOptionsOptions, times(questionTypeOptionModels.size())).saveWithRelations(questionTypeOptionModelCaptor.capture());
        this.questionTypeOptionsSupport.assertModelsListEquals(questionTypeOptionModels, questionTypeOptionModelCaptor.getAllValues());
        verifyNoMoreInteractions(this.questionTypeOptionsMapper);
    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {
        final ArgumentCaptor<IssueStandardQuestionTypeOptionEntity> questionTypeOptionEntityCaptor = ArgumentCaptor.forClass(IssueStandardQuestionTypeOptionEntity.class);

        this.questionTypeOptionsService.delete(this.questionTypeOptionsSupport.getModelFixtureMock(0));

        verify(this.questionTypeOptionsMapper, times(1)).delete(questionTypeOptionEntityCaptor.capture());
        this.questionTypeOptionsSupport.assertEquals(this.questionTypeOptionsSupport.getEntityFixtureMock(0),questionTypeOptionEntityCaptor.getValue());
    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {
        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel = this.questionTypeOptionsSupport.getModelAdditionalMock(0);

        exception.expect(DeleteUnidentifiedModelException.class);
        this.questionTypeOptionsService.delete(questionTypeOptionModel);

    }

    @Test
    public void testDeleteWithOptions() throws Exception {
        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel = this.questionTypeOptionsSupport.getModelFixtureMock(0);
        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions = Mockito.mock(IssueStandardQuestionTypeOptionsOptionsInterface.class);

        this.questionTypeOptionsService.delete(questionTypeOptionModel, questionTypeOptionsOptions);

        this.assertServicesSet(questionTypeOptionsOptions);
        verify(questionTypeOptionsOptions,times(1)).deleteWithRelations(questionTypeOptionModel);
        verifyNoMoreInteractions(this.questionTypeOptionsMapper);
    }

    @Test
    public void testDeleteModelsList() throws Exception {
        final ArgumentCaptor<IssueStandardQuestionTypeOptionEntity> questionTypeOptionEntityCaptor = ArgumentCaptor.forClass(IssueStandardQuestionTypeOptionEntity.class);
        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels = this.getQuestionTypeOptionFixturesModels();

        this.questionTypeOptionsService.delete(questionTypeOptionModels);


        verify(this.questionTypeOptionsMapper, times(questionTypeOptionModels.size())).delete(questionTypeOptionEntityCaptor.capture());
        this.questionTypeOptionsSupport.assertEntitiesListEquals(this.getQuestionTypeOptionFixturesEntities(),questionTypeOptionEntityCaptor.getAllValues());

    }



    @Test
    public void testDeleteModelsListWithOptions() throws Exception {
        final ArgumentCaptor<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModelCaptor = ArgumentCaptor.forClass(IssueStandardQuestionTypeOptionModelInterface.class);
        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels = this.getQuestionTypeOptionFixturesModels();
        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions = Mockito.mock(IssueStandardQuestionTypeOptionsOptionsInterface.class);

        this.questionTypeOptionsService.delete(questionTypeOptionModels, questionTypeOptionsOptions);

        this.assertServicesSet(questionTypeOptionsOptions,questionTypeOptionModels.size());
        verify(questionTypeOptionsOptions,times(questionTypeOptionModels.size())).deleteWithRelations(questionTypeOptionModelCaptor.capture());
        this.questionTypeOptionsSupport.assertModelsListEquals(questionTypeOptionModels,questionTypeOptionModelCaptor.getAllValues());
        verifyNoMoreInteractions(this.questionTypeOptionsMapper);
        }
    }

