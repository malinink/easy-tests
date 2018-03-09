package easytests.core.services;

import easytests.core.entities.TopicEntity;
import easytests.core.mappers.TopicsMapper;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.TopicModelInterface;
import easytests.core.options.TopicsOptionsInterface;
import easytests.core.services.exceptions.DeleteUnidentifiedModelException;
import easytests.support.SubjectsSupport;
import easytests.support.TopicsSupport;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author lelay
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicsServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private TopicsMapper topicsMapper;

    @Autowired
    private TopicsService topicsService;

    private SubjectsSupport subjectsSupport = new SubjectsSupport();

    private TopicsSupport topicsSupport = new TopicsSupport();

    private List<TopicEntity> getTopicsFixturesEntities() {
        final List<TopicEntity> topicsEntities = new ArrayList<>(2);
        topicsEntities.add(this.topicsSupport.getEntityFixtureMock(1));
        topicsEntities.add(this.topicsSupport.getEntityFixtureMock(2));
        return topicsEntities;
    }

    private List<TopicModelInterface> getTopicsFixturesModels() {
        final List<TopicModelInterface> topicsModels = new ArrayList<>(2);
        topicsModels.add(this.topicsSupport.getModelFixtureMock(1));
        topicsModels.add(this.topicsSupport.getModelFixtureMock(2));
        return topicsModels;
    }

    private void assertServicesSet(TopicsOptionsInterface topicsOptions) throws Exception{
        this.assertServicesSet(topicsOptions, 1);
    }

    private void assertServicesSet(TopicsOptionsInterface topicsOptions, Integer times) throws Exception {
        verify(topicsOptions, times(times)).setTopicsService(any(TopicsServiceInterface.class));
        verify(topicsOptions, times(times)).setSubjectsService(any(SubjectsServiceInterface.class));
        verify(topicsOptions, times(times)).setQuestionsService(any(QuestionsServiceInterface.class));
    }

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<TopicEntity> topicsEntities = this.getTopicsFixturesEntities();
        when(this.topicsMapper.findAll()).thenReturn(topicsEntities);

        final List<TopicModelInterface> topicsFoundedModels = this.topicsService.findAll();

        this.topicsSupport.assertModelsListEquals(this.getTopicsFixturesModels(), topicsFoundedModels);
    }

    @Test
    public void testFindAllAbsentList() throws Exception {
        when(this.topicsMapper.findAll()).thenReturn(new ArrayList<>(0));

        final List<TopicModelInterface> topicsFoundedModels = this.topicsService.findAll();

        Assert.assertNotNull(topicsFoundedModels);
        Assert.assertEquals(0, topicsFoundedModels.size());
    }

    @Test
    public void testFindAllWithOptions() throws Exception {
        final ArgumentCaptor<List> listCaptor = ArgumentCaptor.forClass(List.class);
        final List<TopicEntity> topicsEntities = this.getTopicsFixturesEntities();
        final List<TopicModelInterface> topicsModels = this.getTopicsFixturesModels();
        final TopicsOptionsInterface topicsOptions = mock(TopicsOptionsInterface.class);

        when(this.topicsMapper.findAll()).thenReturn(topicsEntities);
        when(topicsOptions.withRelations(listCaptor.capture())).thenReturn(topicsModels);

        final List<TopicModelInterface> topicsFoundedModels = this.topicsService.findAll(topicsOptions);

        this.topicsSupport.assertModelsListEquals(topicsModels, listCaptor.getValue());
        Assert.assertSame(topicsModels, topicsFoundedModels);
        this.assertServicesSet(topicsOptions);
    }

    @Test
    public void testFindPresentModel() throws Exception {
        final TopicEntity topicExistentEntity = this.topicsSupport.getEntityFixtureMock(0);
        when(this.topicsMapper.find(topicExistentEntity.getId())).thenReturn(topicExistentEntity);

        final TopicModelInterface topicFoundedModel = this.topicsService.find(topicExistentEntity.getId());

        this.topicsSupport.assertEquals(this.topicsSupport.getModelFixtureMock(0), topicFoundedModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer absentId = 7;
        when(this.topicsMapper.find(absentId)).thenReturn(null);

        final TopicModelInterface topicFoundedModel = this.topicsService.find(absentId);

        Assert.assertNull(topicFoundedModel);
    }

    @Test
    public void testFindWithOptions() throws Exception {
        final ArgumentCaptor<TopicModelInterface> topicModelCaptor = ArgumentCaptor.forClass(TopicModelInterface.class);
        final TopicEntity topicEntity = this.topicsSupport.getEntityFixtureMock(0);
        final TopicModelInterface topicModel = this.topicsSupport.getModelFixtureMock(0);
        final TopicsOptionsInterface topicOptions = mock(TopicsOptionsInterface.class);

        when(this.topicsMapper.find(topicModel.getId())).thenReturn(topicEntity);
        when(topicOptions.withRelations(topicModelCaptor.capture())).thenReturn(topicModel);

        final TopicModelInterface topicFoundedModel = this.topicsService.find(topicModel.getId(), topicOptions);

        this.topicsSupport.assertEquals(topicModel, topicModelCaptor.getValue());
        Assert.assertSame(topicModel, topicFoundedModel);
        this.assertServicesSet(topicOptions);
    }

    @Test
    public void testFindBySubjectPresentList() throws Exception {
        final SubjectModelInterface subjectModel = this.subjectsSupport.getModelFixtureMock(0);
        final List<TopicEntity> topicsEntities = this.getTopicsFixturesEntities();

        when(this.topicsMapper.findBySubjectId(subjectModel.getId())).thenReturn(topicsEntities);

        final List<TopicModelInterface> topicsFoundedModels = this.topicsService.findBySubject(subjectModel);

        this.topicsSupport.assertModelsListEquals(this.getTopicsFixturesModels(), topicsFoundedModels);
    }

    @Test
    public void testFindBySubjectAbsentList() throws Exception {
        final SubjectModelInterface subjectModel = this.subjectsSupport.getModelFixtureMock(0);

        when(this.topicsMapper.findBySubjectId(subjectModel.getId())).thenReturn(new ArrayList<>(0));

        final List<TopicModelInterface> topicFoundedModels =
                this.topicsService.findBySubject(subjectModel);

        Assert.assertNotNull(topicFoundedModels);
        Assert.assertEquals(0, topicFoundedModels.size());
    }

    @Test
    public void testFindBySubjectWithOptions() throws Exception {
        final ArgumentCaptor<List> listCaptor = ArgumentCaptor.forClass(List.class);
        final List<TopicEntity> topicsEntities = this.getTopicsFixturesEntities();
        final List<TopicModelInterface> topicsModels = this.getTopicsFixturesModels();
        final TopicsOptionsInterface topicOptions = mock(TopicsOptionsInterface.class);

        final SubjectModelInterface subjectModel = this.subjectsSupport.getModelFixtureMock(0);

        when(this.topicsMapper.findBySubjectId(subjectModel.getId())).thenReturn(topicsEntities);
        when(topicOptions.withRelations(listCaptor.capture())).thenReturn(topicsModels);

        final List<TopicModelInterface> topicsFoundedModels =
                this.topicsService.findBySubject(subjectModel, topicOptions);

        this.topicsSupport.assertModelsListEquals(topicsModels, listCaptor.getValue());
        Assert.assertSame(topicsModels, topicsFoundedModels);
        this.assertServicesSet(topicOptions);
    }

    @Test
    public void testSaveCreatesEntity() throws Exception {
        final ArgumentCaptor<TopicEntity> topicEntityCaptor = ArgumentCaptor.forClass(TopicEntity.class);

        this.topicsService.save(this.topicsSupport.getModelAdditionalMock(0));

        verify(this.topicsMapper, times(1)).insert(topicEntityCaptor.capture());
        this.topicsSupport.assertEquals(this.topicsSupport.getEntityAdditionalMock(0), topicEntityCaptor.getValue());
    }

    @Test
    public void testSaveUpdatesEntityIdOnCreation() throws Exception {
        final TopicModelInterface topicAdditionalModel = this.topicsSupport.getModelAdditionalMock(0);
        final Integer updatedId = 10;
        doAnswer(invocation -> {
            final TopicEntity topicEntity = invocation.getArgument(0);
            topicEntity.setId(updatedId);
            return null;
        }).when(this.topicsMapper).insert(any(TopicEntity.class));

        this.topicsService.save(topicAdditionalModel);

        verify(topicAdditionalModel, times(1)).setId(updatedId);
    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {
        final ArgumentCaptor<TopicEntity> topicEntityCaptor = ArgumentCaptor.forClass(TopicEntity.class);

        this.topicsService.save(this.topicsSupport.getModelFixtureMock(0));

        verify(this.topicsMapper, times(1)).update(topicEntityCaptor.capture());
        this.topicsSupport.assertEquals(this.topicsSupport.getEntityFixtureMock(0), topicEntityCaptor.getValue());
    }

    @Test
    public void testSaveWithOptions() throws Exception {
        final TopicModelInterface topicModel = this.topicsSupport.getModelFixtureMock(0);
        final TopicsOptionsInterface topicOptions = mock(TopicsOptionsInterface.class);

        this.topicsService.save(topicModel, topicOptions);

        verify(topicOptions, times(1)).saveWithRelations(topicModel);
        this.assertServicesSet(topicOptions);
        verifyNoMoreInteractions(this.topicsMapper);
    }

    @Test
    public void testSaveModelsList() throws Exception {
        final ArgumentCaptor<TopicEntity> topicEntityCaptor = ArgumentCaptor.forClass(TopicEntity.class);
        final List<TopicModelInterface> topicsModels = this.getTopicsFixturesModels();

        this.topicsService.save(topicsModels);

        verify(this.topicsMapper, times(topicsModels.size())).update(topicEntityCaptor.capture());
        this.topicsSupport.assertEntitiesListEquals(this.getTopicsFixturesEntities(), topicEntityCaptor.getAllValues());
    }

    @Test
    public void testSaveModelsListWithOptions() throws Exception {
        final ArgumentCaptor<TopicModelInterface> topicModelCaptor = ArgumentCaptor.forClass(TopicModelInterface.class);
        final List<TopicModelInterface> topicsModels = this.getTopicsFixturesModels();
        final TopicsOptionsInterface topicsOptions = mock(TopicsOptionsInterface.class);

        this.topicsService.save(topicsModels, topicsOptions);

        this.assertServicesSet(topicsOptions, topicsModels.size());
        verify(topicsOptions, times(topicsModels.size())).saveWithRelations(topicModelCaptor.capture());
        this.topicsSupport.assertModelsListEquals(topicsModels, topicModelCaptor.getAllValues());
        verifyNoMoreInteractions(this.topicsMapper);
    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {
        final ArgumentCaptor<TopicEntity> topicEntityCaptor = ArgumentCaptor.forClass(TopicEntity.class);

        this.topicsService.delete(this.topicsSupport.getModelFixtureMock(0));

        verify(this.topicsMapper, times(1)).delete(topicEntityCaptor.capture());
        this.topicsSupport.assertEquals(this.topicsSupport.getModelFixtureMock(0), topicEntityCaptor.getValue());
    }

    @Test
    public void testDeleteUnidentifiedModelThrowsException() throws Exception {
        final TopicModelInterface topicModel = this.topicsSupport.getModelAdditionalMock(0);

        exception.expect(DeleteUnidentifiedModelException.class);
        this.topicsService.delete(topicModel);
    }

    @Test
    public void testDeleteWithOptions() throws Exception {
        final TopicModelInterface topicModel = this.topicsSupport.getModelFixtureMock(0);
        final TopicsOptionsInterface topicOptions = mock(TopicsOptionsInterface.class);

        this.topicsService.delete(topicModel, topicOptions);

        verify(topicOptions, times(1)).deleteWithRelations(topicModel);
        this.assertServicesSet(topicOptions);
        verifyNoMoreInteractions(this.topicsMapper);
    }

    @Test
    public void testDeleteModelsList() throws Exception {
        final ArgumentCaptor<TopicEntity> topicEntityCaptor = ArgumentCaptor.forClass(TopicEntity.class);
        final List<TopicModelInterface> topicsModels = this.getTopicsFixturesModels();

        this.topicsService.delete(topicsModels);

        verify(this.topicsMapper, times(topicsModels.size())).delete(topicEntityCaptor.capture());
        this.topicsSupport.assertEntitiesListEquals(this.getTopicsFixturesEntities(), topicEntityCaptor.getAllValues());
    }

    @Test
    public void testDeleteModelsListWithOptions() throws Exception {
        final ArgumentCaptor<TopicModelInterface> topicModelCaptor = ArgumentCaptor.forClass(TopicModelInterface.class);
        final List<TopicModelInterface> topicsModels = this.getTopicsFixturesModels();
        final TopicsOptionsInterface topicsOptions = mock(TopicsOptionsInterface.class);

        this.topicsService.delete(topicsModels, topicsOptions);

        this.assertServicesSet(topicsOptions, topicsModels.size());
        verify(topicsOptions, times(topicsModels.size())).deleteWithRelations(topicModelCaptor.capture());
        this.topicsSupport.assertModelsListEquals(topicsModels, topicModelCaptor.getAllValues());
        verifyNoMoreInteractions(this.topicsMapper);
    }


}
