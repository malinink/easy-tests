package easytests.options;

import easytests.models.QuestionModelInterface;
import easytests.services.QuestionsServiceInterface;
import easytests.services.AnswersServiceInterface;
import java.util.List;

/**
 * @author firkhraag
 */
public interface QuestionsOptionsInterface {

    void setAnswersService(AnswersServiceInterface answersService);

    void setQuestionsService(QuestionsServiceInterface questionsService);

    QuestionsOptionsInterface withAnswers(AnswersOptionsInterface answerOptions);

    QuestionModelInterface withRelations(QuestionModelInterface questionModel);

    List<QuestionModelInterface> withRelations(List<QuestionModelInterface> questionsModels);

    void saveWithRelations(QuestionModelInterface questionModel);

    void deleteWithRelations(QuestionModelInterface questionModel);
}
