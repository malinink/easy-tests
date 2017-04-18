package easytests.options;

import easytests.models.QuestionModelInterface;
import easytests.services.AnswersServiceInterface;
import easytests.services.QuestionTypesServiceInterface;
import easytests.services.QuestionsServiceInterface;
import easytests.services.TopicsServiceInterface;
import java.util.List;

/**
 * @author firkhraag
 */
public interface QuestionsOptionsInterface {

    void setAnswersService(AnswersServiceInterface answersService);

    void setTopicsService(TopicsServiceInterface topicsService);

    void setQuestionTypesService(QuestionTypesServiceInterface questionTypesService);

    void setQuestionsService(QuestionsServiceInterface questionsService);

    QuestionsOptionsInterface withAnswers(AnswersOptionsInterface answerOptions);

    QuestionsOptionsInterface withTopic(TopicsOptionsInterface topicOptions);

    QuestionsOptionsInterface withQuestionType(QuestionTypesOptionsInterface questionTypesOptions);

    QuestionModelInterface withRelations(QuestionModelInterface questionModel);

    List<QuestionModelInterface> withRelations(List<QuestionModelInterface> questionsModels);

    void saveWithRelations(QuestionModelInterface questionModel);

    void deleteWithRelations(QuestionModelInterface questionModel);
}
