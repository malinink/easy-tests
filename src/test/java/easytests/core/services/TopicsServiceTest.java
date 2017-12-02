package easytests.core.services;

import easytests.core.entities.TopicEntity;
import easytests.core.mappers.TopicsMapper;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.TopicModelInterface;
import easytests.core.options.QuestionsOptionsInterface;
import easytests.core.options.SubjectsOptionsInterface;
import easytests.core.options.TopicsOptionsInterface;
import easytests.core.services.exceptions.DeleteUnidentifiedModelException;
import easytests.support.SubjectsSupport;
import easytests.support.TopicsSupport;
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

import static org.mockito.Mockito.*;

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

    private void assertServiceSet(TopicsOptionsInterface topicOptions) throws Exception {
        verify(topicOptions, times(1)).setTopicsService(any(TopicsServiceInterface.class));
        verify(topicOptions, times(1)).setSubjectsService(any(SubjectsServiceInterface.class));
        verify(topicOptions, times(1)).setQuestionsService(any(QuestionsServiceInterface.class));
    }

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<TopicEntity> topicsEntities = this.getTopicsFixturesEntities();
        when(this.topicsMapper.findAll()).thenReturn(topicsEntities);

        final List<TopicModelInterface> topicsFoundedModels = this.topicsService.findAll();

        this.topicsSupport.assertModelsListEquals(topicsFoundedModels, this.getTopicsFixturesModels());
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
        // Why mockito's 'when' crashing if I want to pass 'this.get...Entitites()' as a parameter
        // instead of passing predetermined 'topicEntities' ?
        final List<TopicEntity> topicEntities = this.getTopicsFixturesEntities();
        final List<TopicModelInterface> topicModels = this.getTopicsFixturesModels();
        final TopicsOptionsInterface topicsOptions = mock(TopicsOptionsInterface.class);

        when(this.topicsMapper.findAll()).thenReturn(topicEntities);
        when(topicsOptions.withRelations(listCaptor.capture())).thenReturn(topicModels);

        final List<TopicModelInterface> topicFoundedModels = this.topicsService.findAll(topicsOptions);

        this.topicsSupport.assertModelsListEquals(topicModels, listCaptor.getValue());
        Assert.assertSame(topicModels, topicFoundedModels);
        this.assertServiceSet(topicsOptions);
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
        this.assertServiceSet(topicOptions);
    }

    @Test
    public void testFindBySubjectPresentList() throws Exception {
        final SubjectModelInterface subjectModel = this.subjectsSupport.getModelFixtureMock(0);
        final List<TopicEntity> topicEntities = this.getTopicsFixturesEntities();

        when(this.topicsMapper.findBySubjectId(subjectModel.getId())).thenReturn(topicEntities);

        final List<TopicModelInterface> topicFoundedModels = this.topicsService.findBySubject(subjectModel);

        this.topicsSupport.assertModelsListEquals(this.getTopicsFixturesModels(), topicFoundedModels);
    }

    @Test
    public void testFindBySubjectAbsentList() throws Exception {
        final SubjectModelInterface absentSubject = this.subjectsSupport.getModelFixtureMock(0);

        when(this.topicsMapper.findBySubjectId(absentSubject.getId())).thenReturn(new ArrayList<>(0));

        final List<TopicModelInterface> topicFoundedModels =
                this.topicsService.findBySubject(absentSubject);

        Assert.assertNotNull(topicFoundedModels);
        Assert.assertEquals(0, topicFoundedModels.size());
    }

    @Test
    public void testFindBySubjectWithOptions() throws Exception {
        final ArgumentCaptor<List> listCaptor = ArgumentCaptor.forClass(List.class);
        final List<TopicEntity> topicEntities = this.getTopicsFixturesEntities();
        final List<TopicModelInterface> topicModels = this.getTopicsFixturesModels();
        final TopicsOptionsInterface topicOptions = mock(TopicsOptionsInterface.class);

        final SubjectModelInterface subjectModel = this.subjectsSupport.getModelFixtureMock(0);

        when(this.topicsMapper.findBySubjectId(subjectModel.getId())).thenReturn(topicEntities);
        when(topicOptions.withRelations(listCaptor.capture())).thenReturn(topicModels);

        final List<TopicModelInterface> topicFoundedModels =
                this.topicsService.findBySubject(subjectModel, topicOptions);

        this.topicsSupport.assertModelsListEquals(topicModels, listCaptor.getValue());
        Assert.assertSame(topicModels, topicFoundedModels);
        this.assertServiceSet(topicOptions);
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
        }).when(this.topicsMapper).insert(Mockito.any(TopicEntity.class));

        this.topicsService.save(topicAdditionalModel);

        verify(topicAdditionalModel, times(1)).setId(updatedId);
    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {
        final ArgumentCaptor<TopicEntity> topicEntityCaptor = ArgumentCaptor.forClass(TopicEntity.class);

        this.topicsService.save(this.topicsSupport.getModelFixtureMock(0));

        verify(this.topicsMapper, times(1)).update(topicEntityCaptor.capture());
        this.topicsSupport.assertEquals(this.topicsSupport.getModelFixtureMock(0), topicEntityCaptor.getValue());
    }

    @Test
    public void testSaveWithOptions() throws Exception {
        final TopicModelInterface topicModel = this.topicsSupport.getModelFixtureMock(0);
        final TopicsOptionsInterface topicOptions = mock(TopicsOptionsInterface.class);

        this.topicsService.save(topicModel, topicOptions);

        verify(topicOptions, times(1)).saveWithRelations(topicModel);
        this.assertServiceSet(topicOptions);
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
        this.assertServiceSet(topicOptions);
    }
}
