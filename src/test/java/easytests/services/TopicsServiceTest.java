package easytests.services;

import easytests.entities.TopicEntity;
import easytests.mappers.TopicsMapper;
import easytests.models.SubjectModelInterface;
import easytests.models.TopicModel;
import easytests.models.TopicModelInterface;
import easytests.options.TopicsOptionsInterface;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.mockito.BDDMockito.given;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author vkpankov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicsServiceTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Autowired
    private TopicsService topicsService;

    @MockBean
    private TopicsMapper topicsMapper;

    private TopicEntity createTopicEntityMock(Integer id, Integer subjectId, String name) {

        final TopicEntity topicEntity = Mockito.mock(TopicEntity.class);

        Mockito.when(topicEntity.getId()).thenReturn(id);
        Mockito.when(topicEntity.getSubjectId()).thenReturn(subjectId);
        Mockito.when(topicEntity.getName()).thenReturn(name);
        return topicEntity;

    }

    private TopicModelInterface mapTopicModel(TopicEntity topicEntity) {

        final TopicModelInterface topicModel = new TopicModel();
        topicModel.map(topicEntity);
        return topicModel;

    }

    @Test
    public void testFindBySubject() throws Exception {
        final Integer subjectId = 3;
        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);
        final TopicEntity topicEntityFirst = this.createTopicEntityMock(4, subjectId, "testfirst");
        final TopicEntity topicEntitySecond = this.createTopicEntityMock(5, subjectId, "testsecond");
        final List<TopicEntity> topicsEntities = new ArrayList<>();
        topicsEntities.add(topicEntityFirst);
        topicsEntities.add(topicEntitySecond);
        Mockito.when(subjectModel.getId()).thenReturn(subjectId);
        given(this.topicsMapper.findBySubjectId(subjectId)).willReturn(topicsEntities);
        final List<TopicModelInterface> topicsModels = new ArrayList<>();
        topicsModels.add(this.mapTopicModel(topicEntityFirst));
        topicsModels.add(this.mapTopicModel(topicEntitySecond));

        final List<TopicModelInterface> foundedTopicsModels = this.topicsService.findBySubject(subjectModel);

        verify(this.topicsMapper).findBySubjectId(subjectId);
        Assert.assertEquals(topicsModels, foundedTopicsModels);
    }

    @Test
    public void testFindByUserWithOptions() throws Exception {
        final Integer subjectId = 3;
        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);
        final TopicEntity topicEntityFirst = this.createTopicEntityMock(4, subjectId, "testfirst");
        final TopicEntity topicEntitySecond = this.createTopicEntityMock(5, subjectId, "testsecond");
        final List<TopicEntity> topicsEntities = new ArrayList<>();
        topicsEntities.add(topicEntityFirst);
        topicsEntities.add(topicEntitySecond);

        given(subjectModel.getId()).willReturn(subjectId);
        given(this.topicsMapper.findBySubjectId(subjectId)).willReturn(topicsEntities);
        final List<TopicModelInterface> topicsModels = new ArrayList<>();
        topicsModels.add(this.mapTopicModel(topicEntityFirst));
        topicsModels.add(this.mapTopicModel(topicEntitySecond));
        final TopicsOptionsInterface topicOptions = Mockito.mock(TopicsOptionsInterface.class);
        given(topicOptions.withRelations(topicsModels)).willReturn(topicsModels);

        final List<TopicModelInterface> foundedTopicsModels =
                this.topicsService.findBySubject(subjectModel, topicOptions);

        verify(this.topicsMapper).findBySubjectId(subjectId);
        verify(topicOptions).withRelations(topicsModels);
        Assert.assertEquals(topicsModels, foundedTopicsModels);
    }

}
