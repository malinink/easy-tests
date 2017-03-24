package easytests.services;

import easytests.entities.TopicEntity;
import easytests.models.SubjectModelInterface;
import easytests.models.TopicModel;
import easytests.models.TopicModelInterface;
import easytests.options.TopicsOptionsInterface;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;



/**
 * @author vkpankov
 */
@Service
public class TopicsService implements TopicsServiceInterface {

    public List<TopicModelInterface> findBySubject(SubjectModelInterface subjectModel,
                                                   TopicsOptionsInterface topicsOptions) {
        //TODO vkpankov, need topicsMapper for it
        //return topicsOptions.withRelations(this.map(this.topicsMapper.findBySubjectId(subjectModel.getId())));
        return null;
    }

    public List<TopicModelInterface> findBySubject(SubjectModelInterface subjectModel) {

        //TODO vkpankov, need topicsMapper for it
        //return this.map(this.topicsMapper.findBySubjectId(subjectModel.getId()));
        return null;
    }

    public void save(List<TopicModelInterface> topicModel, TopicsOptionsInterface topicOptions) {
        //TODO: Loriends
    }

    public void delete(List<TopicModelInterface> topicModel, TopicsOptionsInterface topicOptions) {
        //TODO: Loriends
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
