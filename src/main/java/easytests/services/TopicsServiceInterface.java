package easytests.services;

import easytests.models.SubjectModelInterface;
import easytests.models.TopicModelInterface;
import easytests.options.TopicsOptionsInterface;
import java.util.List;


/**
 * @author malinink
 */
public interface TopicsServiceInterface extends ServiceInterface {
    List<TopicModelInterface> findAll();

    List<TopicModelInterface> findAll(TopicsOptionsInterface topicsOptions);

    TopicModelInterface find(Integer id);

    TopicModelInterface find(Integer id, TopicsOptionsInterface topicsOptions);

    List<TopicModelInterface> findBySubject(SubjectModelInterface subjectModel, TopicsOptionsInterface topicsOptions);

    List<TopicModelInterface> findBySubject(SubjectModelInterface subjectModel);

    void save(TopicModelInterface topicModel);

    void save(TopicModelInterface topicModel, TopicsOptionsInterface topicsOptions);

    void save(List<TopicModelInterface> topicsModels);

    void save(List<TopicModelInterface> topicsModels, TopicsOptionsInterface topicsOptions);

    void delete(TopicModelInterface topicModel);

    void delete(TopicModelInterface topicModel, TopicsOptionsInterface topicsOptions);

    void delete(List<TopicModelInterface> topicsModels);

    void delete(List<TopicModelInterface> topicsModels, TopicsOptionsInterface topicsOptions);
}
