package easytests.options;

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

    TopicsOptionsInterface withSubjects(SubjectsOptionsInterface subjectOptions);

    TopicsOptionsInterface withQuestions(QuestionsOptionsInterface questionOptions);

    TopicsOptionsInterface withRelations(TopicsOptionsInterface topicModel);

    List<TopicsOptionsInterface> withRelations(List<TopicsOptionsInterface> topicsModels);

    void saveWithRelations(TopicsOptionsInterface topicModel);

    void deleteWithRelations(TopicsOptionsInterface topicModel);
}
