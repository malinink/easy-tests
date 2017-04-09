package easytests.options;

import easytests.models.QuestionTypeModelInterface;
import easytests.services.UsersServiceInterface;
import java.util.List;


/**
 * @author malinink
 */
public interface QuestionTypesOptionsInterface extends OptionsInterface {
    void setQuestionTypesService(UsersServiceInterface usersService);

    QuestionTypeModelInterface withRelations(QuestionTypeModelInterface questionTypeModel);

    List<QuestionTypeModelInterface> withRelations(List<QuestionTypeModelInterface> questionTypesModels);
}
