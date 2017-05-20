package easytests.options;

import easytests.models.TopicModelInterface;
import easytests.services.QuestionsServiceInterface;
import easytests.services.SubjectsServiceInterface;
import easytests.services.TopicsServiceInterface;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Setter;


/**
 * @author malinink
 */
@EqualsAndHashCode
public class TopicsOptions implements TopicsOptionsInterface {
    @Setter
    private TopicsServiceInterface topicsService;

    @Setter
    private SubjectsServiceInterface subjectsService;

    @Setter
    private QuestionsServiceInterface questionsService;

    private SubjectsOptionsInterface subjectsOptions;

    private QuestionsOptionsInterface questionsOptions;

    @Override
    public TopicsOptionsInterface withSubject(SubjectsOptionsInterface subjectsOptions) {
        this.subjectsOptions = subjectsOptions;
        return this;
    }

    @Override
    public TopicsOptionsInterface withQuestions(QuestionsOptionsInterface questionsOptions) {
        this.questionsOptions = questionsOptions;
        return this;
    }

    @Override
    public TopicModelInterface withRelations(TopicModelInterface topicModel) {
        if (topicModel == null) {
            return topicModel;
        }
        if (this.subjectsOptions != null) {
            topicModel.setSubject(this.subjectsService.find(topicModel.getSubject().getId(), this.subjectsOptions));
        }
        if (this.questionsOptions != null) {
            topicModel.setQuestions(this.questionsService.findByTopic(topicModel, this.questionsOptions));
        }
        return topicModel;
    }

    @Override
    public List<TopicModelInterface> withRelations(List<TopicModelInterface> topicsModels) {
        for (TopicModelInterface topicModel: topicsModels) {
            this.withRelations(topicModel);
        }
        return topicsModels;
    }

    @Override
    public void saveWithRelations(TopicModelInterface topicModel) {
        if (this.subjectsOptions != null) {
            this.subjectsService.save(topicModel.getSubject(), this.subjectsOptions);
        }
        this.topicsService.save(topicModel);
        if (this.questionsOptions != null) {
            this.questionsService.save(topicModel.getQuestions(), this.questionsOptions);
        }
    }

    @Override
    public void deleteWithRelations(TopicModelInterface topicModel) {
        if (this.questionsOptions != null) {
            this.questionsService.save(topicModel.getQuestions(), this.questionsOptions);
        }
        this.topicsService.delete(topicModel);
        if (this.subjectsOptions != null) {
            this.subjectsService.save(topicModel.getSubject(), this.subjectsOptions);
        }
    }
}
