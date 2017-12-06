package easytests.core.services;

import easytests.core.entities.TopicEntity;
import easytests.core.mappers.TopicsMapper;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.TopicModel;
import easytests.core.models.TopicModelInterface;
import easytests.core.options.TopicsOptionsInterface;
import easytests.core.services.exceptions.DeleteUnidentifiedModelException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author malinink
 */
@Service
public class TopicsService implements TopicsServiceInterface {
    @Autowired
    private TopicsMapper topicsMapper;

    @Autowired
    private SubjectsService subjectsService;

    @Autowired
    private QuestionsService questionsService;

    @Override
    public List<TopicModelInterface> findAll() {
        return this.map(this.topicsMapper.findAll());
    }

    @Override
    public List<TopicModelInterface> findAll(TopicsOptionsInterface topicsOptions) {
        return this.withServices(topicsOptions).withRelations(this.findAll());
    }

    @Override
    public TopicModelInterface find(Integer id) {
        return this.map(this.topicsMapper.find(id));
    }

    @Override
    public TopicModelInterface find(Integer id, TopicsOptionsInterface usersOptions) {
        return this.withServices(usersOptions).withRelations(this.find(id));
    }

    @Override
    public List<TopicModelInterface> findBySubject(
            SubjectModelInterface subjectModel,
            TopicsOptionsInterface topicsOptions) {
        return this.withServices(topicsOptions).withRelations(this.findBySubject(subjectModel));
    }

    @Override
    public List<TopicModelInterface> findBySubject(SubjectModelInterface subjectModel) {
        return this.map(this.topicsMapper.findBySubjectId(subjectModel.getId()));
    }

    @Override
    public void save(TopicModelInterface topicModel) {
        final TopicEntity topicEntity = this.map(topicModel);
        if (topicEntity.getId() == null) {
            this.topicsMapper.insert(topicEntity);
            topicModel.setId(topicEntity.getId());
            return;
        }
        this.topicsMapper.update(topicEntity);
    }

    @Override
    public void save(TopicModelInterface topicModel, TopicsOptionsInterface topicsOptions) {
        this.withServices(topicsOptions).saveWithRelations(topicModel);
    }

    @Override
    public void save(List<TopicModelInterface> topicsModels) {
        for (TopicModelInterface topicModel: topicsModels) {
            this.save(topicModel);
        }
    }

    @Override
    public void save(List<TopicModelInterface> topicsModels, TopicsOptionsInterface topicsOptions) {
        for (TopicModelInterface topicModel: topicsModels) {
            this.save(topicModel, topicsOptions);
        }
    }

    @Override
    public void delete(TopicModelInterface topicModel) {
        final TopicEntity topicEntity = this.map(topicModel);
        if (topicEntity.getId() == null) {
            throw new DeleteUnidentifiedModelException();
        }
        this.topicsMapper.delete(topicEntity);
    }

    @Override
    public void delete(TopicModelInterface topicModel, TopicsOptionsInterface topicsOptions) {
        this.withServices(topicsOptions).deleteWithRelations(topicModel);
    }

    @Override
    public void delete(List<TopicModelInterface> topicsModels) {
        for (TopicModelInterface topicModel: topicsModels) {
            this.delete(topicModel);
        }
    }

    @Override
    public void delete(List<TopicModelInterface> topicsModels, TopicsOptionsInterface topicsOptions) {
        for (TopicModelInterface topicModel: topicsModels) {
            this.delete(topicModel, topicsOptions);
        }
    }

    private TopicsOptionsInterface withServices(TopicsOptionsInterface topicsOptions) {
        topicsOptions.setTopicsService(this);
        topicsOptions.setSubjectsService(this.subjectsService);
        topicsOptions.setQuestionsService(this.questionsService);
        return topicsOptions;
    }

    private TopicModelInterface map(TopicEntity topicEntity) {
        if (topicEntity == null) {
            return null;
        }
        final TopicModelInterface topicModel = new TopicModel();
        topicModel.map(topicEntity);
        return topicModel;
    }

    private TopicEntity map(TopicModelInterface topicModel) {
        final TopicEntity topicEntity = new TopicEntity();
        topicEntity.map(topicModel);
        return topicEntity;
    }

    private List<TopicModelInterface> map(List<TopicEntity> topicsList) {
        final List<TopicModelInterface> resultTopicsList = new ArrayList<>(topicsList.size());
        for (TopicEntity topic: topicsList) {
            resultTopicsList.add(this.map(topic));
        }
        return resultTopicsList;
    }
}
