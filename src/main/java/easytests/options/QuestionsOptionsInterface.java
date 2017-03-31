package easytests.options;

import easytests.models.QuestionModelInterface;
import easytests.services.QuestionsServiceInterface;
import easytests.services.AnswersServiceInterface;
import easytests.services.TopicsServiceInterface;
import java.util.List;

/**
 * @author firkhraag
 */
public interface QuestionsOptionsInterface {

    void setAnswersService(AnswersServiceInterface answersService);

    void setTopicsService(TopicsServiceInterface topicsService);

    void setQuestionsService(QuestionsServiceInterface questionsService);

    QuestionsOptionsInterface withAnswers(AnswersOptionsInterface answerOptions);

    QuestionsOptionsInterface withTopic(TopicsOptionsInterface topicOptions);

    QuestionModelInterface withRelations(QuestionModelInterface questionModel);

    List<QuestionModelInterface> withRelations(List<QuestionModelInterface> questionsModels);

    void saveWithRelations(QuestionModelInterface questionModel);

    void deleteWithRelations(QuestionModelInterface questionModel);
}