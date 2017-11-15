package easytests.core.services;

import easytests.core.entities.IssueStandardTopicPriorityEntity;
import easytests.core.mappers.IssueStandardTopicPrioritiesMapper;
import easytests.core.models.IssueStandardModelInterface;
import easytests.core.models.IssueStandardTopicPriorityModel;
import easytests.core.models.IssueStandardTopicPriorityModelInterface;
import easytests.core.options.IssueStandardTopicPrioritiesOptionsInterface;
import easytests.core.services.exceptions.DeleteUnidentifiedModelException;
import easytests.support.Entities;
import easytests.support.Models;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author SingularityA
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IssueStandardTopicPrioritiesServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private IssueStandardTopicPrioritiesMapper topicPrioritiesMapper;

    @InjectMocks
    private IssueStandardTopicPrioritiesService topicPrioritiesService;

    private IssueStandardTopicPriorityModelInterface
        mapTopicPriorityModel(IssueStandardTopicPriorityEntity topicPriorityEntity) {

        final IssueStandardTopicPriorityModelInterface topicPriorityModel = new IssueStandardTopicPriorityModel();
        topicPriorityModel.map(topicPriorityEntity);
        return topicPriorityModel;
    }

    private IssueStandardTopicPriorityEntity
        mapTopicPriorityEntity(IssueStandardTopicPriorityModelInterface topicPriorityModel) {

        final IssueStandardTopicPriorityEntity topicPriorityEntity = new IssueStandardTopicPriorityEntity();
        topicPriorityEntity.map(topicPriorityModel);
        return topicPriorityEntity;
    }

    private List<IssueStandardTopicPriorityEntity> getTopicPriorityEntities() {
        List<IssueStandardTopicPriorityEntity> topicPriorityEntities = new ArrayList<>(2);
        topicPriorityEntities.add(Entities.createTopicPriorityEntityMock(1, 11, true, 3));
        topicPriorityEntities.add(Entities.createTopicPriorityEntityMock(2, 12, false, 4));
        return topicPriorityEntities;
    }

    private List<IssueStandardTopicPriorityModelInterface> getTopicPriorityModels() {
        List<IssueStandardTopicPriorityModelInterface> topicPriorityModels = new ArrayList<>(2);
        for (IssueStandardTopicPriorityEntity topicPriorityEntity: this.getTopicPriorityEntities()) {
            topicPriorityModels.add(this.mapTopicPriorityModel(topicPriorityEntity));
        }
        return topicPriorityModels;
    }

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<IssueStandardTopicPriorityEntity> topicPriorityEntities = this.getTopicPriorityEntities();
        given(this.topicPrioritiesMapper.findAll()).willReturn(topicPriorityEntities);

        final List<IssueStandardTopicPriorityModelInterface> topicPriorityModels
                = this.topicPrioritiesService.findAll();

        Assert.assertNotNull(topicPriorityModels);
        Assert.assertEquals(this.getTopicPriorityModels(), topicPriorityModels);
    }

    @Test
    public void testFindAllAbsentList() throws Exception {
        given(this.topicPrioritiesMapper.findAll()).willReturn(new ArrayList<>(0));

        final List<IssueStandardTopicPriorityModelInterface> topicPriorityModels
                = this.topicPrioritiesService.findAll();

        Assert.assertNotNull(topicPriorityModels);
        Assert.assertEquals(0, topicPriorityModels.size());
    }

    @Test
    public void testFindAllWithOptions() throws Exception {
        final List<IssueStandardTopicPriorityEntity> topicPriorityEntities = this.getTopicPriorityEntities();
        final List<IssueStandardTopicPriorityModelInterface> topicPriorityModels = this.getTopicPriorityModels();

        final IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions
                = Mockito.mock(IssueStandardTopicPrioritiesOptionsInterface.class);
        given(this.topicPrioritiesMapper.findAll()).willReturn(topicPriorityEntities);
        given(topicPrioritiesOptions.withRelations(Mockito.anyList())).willReturn(topicPriorityModels);

        final List<IssueStandardTopicPriorityModelInterface> foundedTopicPriorityModels
                = this.topicPrioritiesService.findAll(topicPrioritiesOptions);

        verify(topicPrioritiesOptions).withRelations(topicPriorityModels);
        Assert.assertNotNull(foundedTopicPriorityModels);
        Assert.assertEquals(topicPriorityModels, foundedTopicPriorityModels);
    }

    @Test
    public void testFindPresentModel() throws Exception {
        final Integer id = 1;
        final IssueStandardTopicPriorityEntity topicPriorityEntity
                = Entities.createTopicPriorityEntityMock(id, 2, true, 3);
        given(this.topicPrioritiesMapper.find(id)).willReturn(topicPriorityEntity);

        final IssueStandardTopicPriorityModelInterface topicPriorityModel = this.topicPrioritiesService.find(id);

        Assert.assertNotNull(topicPriorityModel);
        Assert.assertEquals(this.mapTopicPriorityModel(topicPriorityEntity), topicPriorityModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        given(this.topicPrioritiesMapper.find(id)).willReturn(null);

        final IssueStandardTopicPriorityModelInterface topicPriorityModel = this.topicPrioritiesService.find(id);

        Assert.assertNull(topicPriorityModel);
    }

    @Test
    public void testFindWithOptions() throws Exception {
        final Integer id = 1;
        final IssueStandardTopicPriorityEntity topicPriorityEntity
                = Entities.createTopicPriorityEntityMock(id, 2, true, 3);
        final IssueStandardTopicPriorityModelInterface topicPriorityModel = this.mapTopicPriorityModel(topicPriorityEntity);

        final IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions
                = Mockito.mock(IssueStandardTopicPrioritiesOptionsInterface.class);
        given(this.topicPrioritiesMapper.find(id)).willReturn(topicPriorityEntity);
        given(topicPrioritiesOptions.withRelations(topicPriorityModel)).willReturn(topicPriorityModel);

        final IssueStandardTopicPriorityModelInterface foundedTopicPriorityModels
                = this.topicPrioritiesService.find(id, topicPrioritiesOptions);

        verify(topicPrioritiesOptions).withRelations(topicPriorityModel);
        Assert.assertNotNull(foundedTopicPriorityModels);
        Assert.assertEquals(topicPriorityModel, foundedTopicPriorityModels);
    }

    @Test
    public void testFindByIssueStandardPresentList() throws Exception {
        final Integer issueStandardId = 5;
        final List<IssueStandardTopicPriorityEntity> topicPriorityEntities = new ArrayList<>(2);

        topicPriorityEntities.add(Entities.createTopicPriorityEntityMock(1, 2, true, issueStandardId));
        topicPriorityEntities.add(Entities.createTopicPriorityEntityMock(2, 4, false, issueStandardId));
        given(this.topicPrioritiesMapper.findByIssueStandardId(issueStandardId)).willReturn(topicPriorityEntities);

        final IssueStandardModelInterface issueStandardModel = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModel.getId()).thenReturn(issueStandardId);

        final List<IssueStandardTopicPriorityModelInterface> topicPriorityModels
                = this.topicPrioritiesService.findByIssueStandard(issueStandardModel);

        Assert.assertNotNull(topicPriorityModels);
        Assert.assertEquals(topicPriorityEntities.size(), topicPriorityModels.size());
        for (int i = 0; i < topicPriorityModels.size(); i++) {
            Assert.assertEquals(topicPriorityModels.get(i), this.mapTopicPriorityModel(topicPriorityEntities.get(i)));
        }
    }

    @Test
    public void testFindByIssueStandardAbsentList() throws Exception {
        final Integer issueStandardId = 10;
        given(this.topicPrioritiesMapper.findByIssueStandardId(issueStandardId)).willReturn(new ArrayList<>(0));

        final IssueStandardModelInterface issueStandardModel = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModel.getId()).thenReturn(issueStandardId);

        final List<IssueStandardTopicPriorityModelInterface> topicPriorityModels
                = this.topicPrioritiesService.findByIssueStandard(issueStandardModel);

        Assert.assertNotNull(topicPriorityModels);
        Assert.assertEquals(0, topicPriorityModels.size());
    }

    @Test
    public void testFindByIssueStandardWithOptions() throws Exception {
        Integer issueStandardId = 10;
        final List<IssueStandardTopicPriorityEntity> topicPriorityEntities = new ArrayList<>(2);
        topicPriorityEntities.add(Entities.createTopicPriorityEntityMock(1, 2, true, issueStandardId));
        topicPriorityEntities.add(Entities.createTopicPriorityEntityMock(3, 4, true, issueStandardId));

        final List<IssueStandardTopicPriorityModelInterface> topicPriorityModels = new ArrayList<>(2);
        topicPriorityModels.add(this.mapTopicPriorityModel(topicPriorityEntities.get(0)));
        topicPriorityModels.add(this.mapTopicPriorityModel(topicPriorityEntities.get(1)));

        final IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions
                = Mockito.mock(IssueStandardTopicPrioritiesOptionsInterface.class);
        given(this.topicPrioritiesMapper.findByIssueStandardId(issueStandardId)).willReturn(topicPriorityEntities);
        given(topicPrioritiesOptions.withRelations(topicPriorityModels)).willReturn(topicPriorityModels);

        final List<IssueStandardTopicPriorityModelInterface> foundedTopicPriorityModels
                = this.topicPrioritiesService.findByIssueStandard(
                        topicPriorityModels.get(0).getIssueStandard(), topicPrioritiesOptions);

        verify(topicPrioritiesOptions).withRelations(topicPriorityModels);
        Assert.assertNotNull(foundedTopicPriorityModels);
        Assert.assertEquals(topicPriorityModels, foundedTopicPriorityModels);
    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {
        final IssueStandardTopicPriorityModelInterface topicPriorityModel
                = Models.createTopicPriorityModel(1, 2, false, 1);

        this.topicPrioritiesService.save(topicPriorityModel);

        verify(this.topicPrioritiesMapper, times(1)).update(this.mapTopicPriorityEntity(topicPriorityModel));
    }

    @Test
    public void testSaveInsertsEntity() throws Exception {
        final IssueStandardTopicPriorityModelInterface topicPriorityModel
                = Models.createTopicPriorityModel(null, 3, true, 5);

        final Integer id = 10;
        doAnswer(invocations -> {
            final IssueStandardTopicPriorityEntity topicPriorityEntity
                    = (IssueStandardTopicPriorityEntity) invocations.getArguments()[0];
            topicPriorityEntity.setId(id);
            return null;
        }).when(this.topicPrioritiesMapper).insert(Mockito.any(IssueStandardTopicPriorityEntity.class));

        this.topicPrioritiesService.save(topicPriorityModel);

        // TODO verify(this.topicPrioritiesMapper, times(1)).insert(this.mapTopicPriorityEntity(topicPriorityModel));
        Assert.assertEquals(id, topicPriorityModel.getId());
    }

    @Test
    public void testSaveList() throws Exception {
        final List<IssueStandardTopicPriorityModelInterface> topicPriorityModels = new ArrayList<>(2);
        topicPriorityModels.add(Models.createTopicPriorityModel(1, 2, true, 3));
        topicPriorityModels.add(Models.createTopicPriorityModel(null, 3, false, 3));

        final Integer id = 10;
        doAnswer(invocations -> {
            final IssueStandardTopicPriorityEntity topicPriorityEntity
                    = (IssueStandardTopicPriorityEntity) invocations.getArguments()[0];
            topicPriorityEntity.setId(id);
            return null;
        }).when(this.topicPrioritiesMapper).insert(Mockito.any(IssueStandardTopicPriorityEntity.class));

        this.topicPrioritiesService.save(topicPriorityModels);

        verify(this.topicPrioritiesMapper, times(1)).update(this.mapTopicPriorityEntity(topicPriorityModels.get(0)));
        verify(this.topicPrioritiesMapper, times(1)).insert(this.mapTopicPriorityEntity(topicPriorityModels.get(1)));
        Assert.assertEquals(id, topicPriorityModels.get(1).getId());
    }

    @Test
    public void testSaveWithOptions() throws Exception {
        final IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions
                = Mockito.mock(IssueStandardTopicPrioritiesOptionsInterface.class);

        final IssueStandardTopicPriorityModelInterface topicPriorityModel
                = Models.createTopicPriorityModel(1, 2, false, 1);

        this.topicPrioritiesService.save(topicPriorityModel, topicPrioritiesOptions);

        verify(topicPrioritiesOptions).saveWithRelations(topicPriorityModel);
    }

    @Test
    public void testSaveListWithOptions() throws Exception {
        final IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions
                = Mockito.mock(IssueStandardTopicPrioritiesOptionsInterface.class);
        final List<IssueStandardTopicPriorityModelInterface> topicPriorityModels = this.getTopicPriorityModels();

        this.topicPrioritiesService.save(topicPriorityModels, topicPrioritiesOptions);

        for (IssueStandardTopicPriorityModelInterface topicPriorityModel: topicPriorityModels) {
            verify(topicPrioritiesOptions).saveWithRelations(topicPriorityModel);
        }
    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {
        final IssueStandardTopicPriorityModelInterface topicPriorityModel
                = Models.createTopicPriorityModel(1, 3, true, 1);

        this.topicPrioritiesService.delete(topicPriorityModel);

        verify(this.topicPrioritiesMapper, times(1)).delete(this.mapTopicPriorityEntity(topicPriorityModel));
    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {
        final IssueStandardTopicPriorityModelInterface topicPriorityModel
                = Models.createTopicPriorityModel(null, 2, true, 4);

        exception.expect(DeleteUnidentifiedModelException.class);
        this.topicPrioritiesService.delete(topicPriorityModel);

        verify(this.topicPrioritiesMapper, times(0)).delete(this.mapTopicPriorityEntity(topicPriorityModel));
    }

    @Test
    public void testDeleteList() throws Exception {
        final List<IssueStandardTopicPriorityModelInterface> topicPriorityModels = this.getTopicPriorityModels();

        this.topicPrioritiesService.delete(topicPriorityModels);

        for (IssueStandardTopicPriorityModelInterface topicPriorityModel: topicPriorityModels) {
            verify(this.topicPrioritiesMapper, times(1)).delete(this.mapTopicPriorityEntity(topicPriorityModel));
        }
    }

    @Test
    public void testDeleteWithOptions() throws Exception {
        final IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions
                = Mockito.mock(IssueStandardTopicPrioritiesOptionsInterface.class);

        final IssueStandardTopicPriorityModelInterface topicPriorityModel
                = Models.createTopicPriorityModel(1, 2, false, 1);

        this.topicPrioritiesService.delete(topicPriorityModel, topicPrioritiesOptions);

        verify(topicPrioritiesOptions).deleteWithRelations(topicPriorityModel);
    }

    @Test
    public void testDeleteListWithOptions() throws Exception {
        final IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions
                = Mockito.mock(IssueStandardTopicPrioritiesOptionsInterface.class);
        final List<IssueStandardTopicPriorityModelInterface> topicPriorityModels = this.getTopicPriorityModels();

        this.topicPrioritiesService.delete(topicPriorityModels, topicPrioritiesOptions);

        for (IssueStandardTopicPriorityModelInterface topicPriorityModel: topicPriorityModels) {
            verify(topicPrioritiesOptions).deleteWithRelations(topicPriorityModel);
        }
    }
}
