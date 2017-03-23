package easytests.options;

import easytests.models.TopicModelInterface;

import java.util.List;

/**
 * @author vkpankov
 */
public interface TopicsOptionsInterface {

    TopicModelInterface withRelations(TopicModelInterface topicModel);

    List<TopicModelInterface> withRelations(List<TopicModelInterface> topicModel);

    void saveWithRelations(TopicModelInterface topicModel);

    void saveWithRelations(List<TopicModelInterface> topicsModels);

    void deleteWithRelations(TopicModelInterface topicModel);

    void deleteWithRelations(List<TopicModelInterface> topicsModels);

}
