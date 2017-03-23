package easytests.services;

import easytests.entities.TopicEntity;
import easytests.mappers.TopicsMapper;
import easytests.models.SubjectModelInterface;
import easytests.models.TopicModel;
import easytests.models.TopicModelInterface;
import easytests.options.TopicsOptionsInterface;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @author vkpankov
 */
@Service
public class TopicsService implements TopicsServiceInterface {

    @Autowired
    private TopicsMapper topicsMapper;

    public List<TopicModelInterface> findBySubject(SubjectModelInterface subjectModel,
                                                   TopicsOptionsInterface topicsOptions) {

        return topicsOptions.withRelations(this.map(this.topicsMapper.findBySubjectId(subjectModel.getId())));

    }

    public List<TopicModelInterface> findBySubject(SubjectModelInterface subjectModel) {

        return this.map(this.topicsMapper.findBySubjectId(subjectModel.getId()));

    }

    private TopicModelInterface map(TopicEntity topicEntity) {
        final TopicModel topicModel = new TopicModel();
        topicModel.map(topicEntity);
        return topicModel;
    }

    private List<TopicModelInterface> map(List<TopicEntity> topicList) {
        final List<TopicModelInterface> resultTopicList = new ArrayList(topicList.size());
        for (TopicEntity topic: topicList) {
            resultTopicList.add(this.map(topic));
        }
        return resultTopicList;
    }
}
