package easytests.options;

import easytests.models.TopicModelInterface;
import easytests.services.QuestionsServiceInterface;
import easytests.services.SubjectsServiceInterface;
import easytests.services.TopicsServiceInterface;
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
