package easytests.core.services;

import easytests.core.entities.TopicEntity;
import easytests.core.mappers.TopicsMapper;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.TopicModel;
import easytests.core.models.TopicModelInterface;
import easytests.core.options.TopicsOptionsInterface;
import easytests.support.Entities;
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

    private TopicModelInterface mapTopicModel(TopicEntity topicEntity) {
        final TopicModelInterface topicModel = new TopicModel();
        topicModel.map(topicEntity);
        return topicModel;
    }

    private TopicEntity getTopicEntity() {
        final TopicEntity topicEntity = Entities.createTopicEntityMock(6,3, "testentity");
        return topicEntity;

    }

    private TopicModelInterface getTopicModel() {
        final TopicModelInterface topicModel = mapTopicModel(this.getTopicEntity());
        return topicModel;
    }

    private List<TopicEntity> getTopicsEntities() {
        final TopicEntity topicEntityFirst = Entities.createTopicEntityMock(4, 3, "testfirst");
        final TopicEntity topicEntitySecond = Entities.createTopicEntityMock(5, 3, "testsecond");
        final List<TopicEntity> topicsEntities = new ArrayList<>();
        topicsEntities.add(topicEntityFirst);
        topicsEntities.add(topicEntitySecond);
        return topicsEntities;
    }

    private List<TopicModelInterface> getTopicsModels() {
        final List<TopicModelInterface> topicsModels = new ArrayList<>(2);
        for (TopicEntity topicEntity: this.getTopicsEntities()) {
            topicsModels.add(this.mapTopicModel(topicEntity));
        }
        return topicsModels;
    }

//    private
    @Test
    public void testFind() throws Exception{
        final Integer id = 6;
//        final TopicsOptionsInterface topicOptions = Mockito.mock(TopicsOptionsInterface.class);
//        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);
        final TopicModelInterface topicModel = getTopicModel();
        final TopicEntity topicEntity = this.getTopicEntity();


        given(this.topicsMapper.find(id)).willReturn(topicEntity);
        final TopicModelInterface foundedTopicModel = this.topicsService.find(id);

        verify(this.topicsMapper).find(id);
        Assert.assertEquals(topicModel,foundedTopicModel);

    }

    @Test
    public void testFindBySubject() throws Exception {
        final Integer subjectId = 3;
        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);
        final List<TopicEntity> topicsEntities = this.getTopicsEntities();
        final List<TopicModelInterface> topicsModels = getTopicsModels();

        Mockito.when(subjectModel.getId()).thenReturn(subjectId);
        given(this.topicsMapper.findBySubjectId(subjectId)).willReturn(topicsEntities);

        final List<TopicModelInterface> foundedTopicsModels = this.topicsService.findBySubject(subjectModel);

        verify(this.topicsMapper).findBySubjectId(subjectId);
        Assert.assertEquals(topicsModels, foundedTopicsModels);
    }

    @Test
    public void testFindByUserWithOptions() throws Exception {
        final Integer subjectId = 3;
        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);
        final List<TopicEntity> topicsEntities = this.getTopicsEntities();
        final List<TopicModelInterface> topicsModels = this.getTopicsModels();

        given(subjectModel.getId()).willReturn(subjectId);
        given(this.topicsMapper.findBySubjectId(subjectId)).willReturn(topicsEntities);
        final TopicsOptionsInterface topicOptions = Mockito.mock(TopicsOptionsInterface.class);
        given(topicOptions.withRelations(topicsModels)).willReturn(topicsModels);

        final List<TopicModelInterface> foundedTopicsModels =
                this.topicsService.findBySubject(subjectModel, topicOptions);

        verify(this.topicsMapper).findBySubjectId(subjectId);
        verify(topicOptions).withRelations(topicsModels);
        Assert.assertEquals(topicsModels, foundedTopicsModels);
    }

}
