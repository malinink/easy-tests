package easytests.core.options;

import easytests.core.models.TopicModelInterface;
import easytests.core.services.QuestionsServiceInterface;
import easytests.core.services.SubjectsServiceInterface;
import easytests.core.services.TopicsServiceInterface;
import java.util.List;


/**
 * @author malinink
 */
public interface TopicsOptionsInterface extends OptionsInterface {
    void setQuestionsService(QuestionsServiceInterface questionService);

    void setSubjectsService(SubjectsServiceInterface subjectsService);

    void setTopicsService(TopicsServiceInterface topicsService);

    TopicsOptionsInterface withSubject(SubjectsOptionsInterface subjectsOptions);

    TopicsOptionsInterface withQuestions(QuestionsOptionsInterface questionOptions);

    TopicModelInterface withRelations(TopicModelInterface topicModel);

    List<TopicModelInterface> withRelations(List<TopicModelInterface> topicsModels);

    void saveWithRelations(TopicModelInterface topicModel);

    void deleteWithRelations(TopicModelInterface topicModel);
}
