package easytests.services;

import easytests.entities.IssueStandardTopicPriorityEntity;
import easytests.mappers.IssueStandardTopicPrioritiesMapper;
import easytests.models.IssueStandardModelInterface;
import easytests.models.IssueStandardTopicPriorityModel;
import easytests.models.IssueStandardTopicPriorityModelInterface;
import easytests.services.exceptions.DeleteUnidentifiedModelException;
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

    private IssueStandardTopicPriorityEntity
        createTopicPriorityEntityMock(Integer id, Integer topicId, Boolean isPreferable, Integer issueStandardId) {

        final IssueStandardTopicPriorityEntity topicPriorityEntity
                = Mockito.mock(IssueStandardTopicPriorityEntity.class);
        Mockito.when(topicPriorityEntity.getId()).thenReturn(id);
        Mockito.when(topicPriorityEntity.getTopicId()).thenReturn(topicId);
        Mockito.when(topicPriorityEntity.getIsPreferable()).thenReturn(isPreferable);
        Mockito.when(topicPriorityEntity.getIssueStandardId()).thenReturn(issueStandardId);
        return topicPriorityEntity;
    }

    private IssueStandardTopicPriorityModelInterface
        createTopicPriorityModel(Integer id, Integer topicId, Boolean isPreferable, Integer issueStandardId) {

        final IssueStandardTopicPriorityModelInterface topicPriorityModel = new IssueStandardTopicPriorityModel();
        final IssueStandardModelInterface issueStandardModel = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModel.getId()).thenReturn(issueStandardId);

        topicPriorityModel.setId(id);
        topicPriorityModel.setTopicId(topicId);
        topicPriorityModel.setIsPreferable(isPreferable);
        topicPriorityModel.setIssueStandard(issueStandardModel);
        return topicPriorityModel;
    }

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

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<IssueStandardTopicPriorityEntity> topicPriorityEntities = new ArrayList<>(2);
        topicPriorityEntities.add(this.createTopicPriorityEntityMock(1, 1, true, 1));
        topicPriorityEntities.add(this.createTopicPriorityEntityMock(2, 3, false, 2));
        given(this.topicPrioritiesMapper.findAll()).willReturn(topicPriorityEntities);

        final List<IssueStandardTopicPriorityModelInterface> topicPriorityModels
                = this.topicPrioritiesService.findAll();

        Assert.assertNotNull(topicPriorityModels);
        Assert.assertEquals(topicPriorityEntities.size(), topicPriorityModels.size());
        for (int i = 0; i < topicPriorityModels.size(); i++) {
            Assert.assertEquals(topicPriorityModels.get(i), this.mapTopicPriorityModel(topicPriorityEntities.get(i)));
        }
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
    public void testFindPresentModel() throws Exception {
        final Integer id = 1;
        final IssueStandardTopicPriorityEntity topicPriorityEntity = this.createTopicPriorityEntityMock(id, 2, true, 3);
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
    public void testFindByIssueStandardPresentList() throws Exception {
        final Integer issueStandardId = 5;
        final List<IssueStandardTopicPriorityEntity> topicPriorityEntities = new ArrayList<>(2);

        topicPriorityEntities.add(this.createTopicPriorityEntityMock(1, 2, true, issueStandardId));
        topicPriorityEntities.add(this.createTopicPriorityEntityMock(2, 4, false, issueStandardId));
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
    public void testSaveUpdatesEntity() throws Exception {
        final IssueStandardTopicPriorityModelInterface topicPriorityModel
                = this.createTopicPriorityModel(1, 2, false, 1);

        this.topicPrioritiesService.save(topicPriorityModel);
        verify(this.topicPrioritiesMapper, times(1)).update(this.mapTopicPriorityEntity(topicPriorityModel));
    }

    @Test
    public void testSaveInsertsEntity() throws Exception {
        final IssueStandardTopicPriorityModelInterface topicPriorityModel
                = this.createTopicPriorityModel(null, 3, true, 5);
        final Integer id = 10;

        doAnswer(invocations -> {
            final IssueStandardTopicPriorityEntity topicPriorityEntity
                    = (IssueStandardTopicPriorityEntity) invocations.getArguments()[0];
            topicPriorityEntity.setId(id);
            return null;
        }).when(this.topicPrioritiesMapper).insert(Mockito.any(IssueStandardTopicPriorityEntity.class));

        this.topicPrioritiesService.save(topicPriorityModel);

        verify(this.topicPrioritiesMapper, times(1)).insert(this.mapTopicPriorityEntity(topicPriorityModel));
        Assert.assertEquals(id, topicPriorityModel.getId());
    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {
        final IssueStandardTopicPriorityModelInterface topicPriorityModel
                = this.createTopicPriorityModel(1, 3, true, 1);

        this.topicPrioritiesService.delete(topicPriorityModel);
        verify(this.topicPrioritiesMapper, times(1)).delete(this.mapTopicPriorityEntity(topicPriorityModel));
    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {
        final IssueStandardTopicPriorityModelInterface topicPriorityModel
                = this.createTopicPriorityModel(null, 2, true, 4);

        exception.expect(DeleteUnidentifiedModelException.class);
        this.topicPrioritiesService.delete(topicPriorityModel);
    }
}
