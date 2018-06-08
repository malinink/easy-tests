package easytests.core.options;

import easytests.core.models.QuestionModelInterface;
import easytests.core.services.AnswersServiceInterface;
import easytests.core.services.QuestionTypesServiceInterface;
import easytests.core.services.QuestionsServiceInterface;
import easytests.core.services.TopicsServiceInterface;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Setter;


/**
 * @author firkhraag
 */
@EqualsAndHashCode
public class QuestionsOptions implements QuestionsOptionsInterface {

    @Setter
    private QuestionsServiceInterface questionsService;

    @Setter
    private TopicsServiceInterface topicsService;

    @Setter
    private AnswersServiceInterface answersService;

    @Setter
    private QuestionTypesServiceInterface questionTypesService;

    private TopicsOptionsInterface topicsOptions;

    private AnswersOptionsInterface answersOptions;

    private QuestionTypesOptionsInterface questionTypesOptions;

    @Override
    public QuestionsOptionsInterface withAnswers(AnswersOptionsInterface answerOptions) {
        this.answersOptions = answerOptions;
        return this;
    }

    @Override
    public QuestionsOptionsInterface withTopic(TopicsOptionsInterface topicOptions) {
        this.topicsOptions = topicOptions;
        return this;
    }

    @Override
    public QuestionsOptionsInterface withQuestionType(QuestionTypesOptionsInterface questionTypesOptions) {
        this.questionTypesOptions = questionTypesOptions;
        return this;
    }

    @Override
    public QuestionModelInterface withRelations(QuestionModelInterface questionModel) {
        if (questionModel == null) {
            return questionModel;
        }
        if (this.answersOptions != null) {
            questionModel.setAnswers(this.answersService.findByQuestion(questionModel, this.answersOptions));
        }
        if (this.topicsOptions != null) {
            questionModel.setTopic(this.topicsService.find(questionModel.getTopic().getId(), this.topicsOptions));
        }
        if (this.questionTypesOptions != null) {
            questionModel.setQuestionType(
                    this.questionTypesService.find(questionModel.getQuestionType().getId(), this.questionTypesOptions));
        }
        return questionModel;
    }

    @Override
    public List<QuestionModelInterface> withRelations(List<QuestionModelInterface> questionsModels) {
        for (QuestionModelInterface questionModel: questionsModels) {
            this.withRelations(questionModel);
        }
        return questionsModels;
    }

    @Override
    public void saveWithRelations(QuestionModelInterface questionModel) {
        if (this.topicsOptions != null) {
            this.topicsService.save(questionModel.getTopic(), this.topicsOptions);
        }
        this.questionsService.save(questionModel);
        if (this.answersOptions != null) {
            this.answersService.save(questionModel.getAnswers(), this.answersOptions);
        }
    }

    @Override
    public void deleteWithRelations(QuestionModelInterface questionModel) {
        if (this.answersOptions != null) {
            this.answersService.delete(questionModel.getAnswers(), this.answersOptions);
        }
        this.questionsService.delete(questionModel);
        if (this.topicsOptions != null) {
            this.topicsService.delete(questionModel.getTopic(), this.topicsOptions);
        }
    }
}
