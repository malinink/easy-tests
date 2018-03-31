package easytests.core.options;

import easytests.core.models.QuestionTypeModelInterface;

import java.util.List;


/**
 * @author malinink
 */
public interface QuestionTypesOptionsInterface extends OptionsInterface {
    QuestionTypeModelInterface withRelations(QuestionTypeModelInterface questionTypeModel);

    List<QuestionTypeModelInterface> withRelations(List<QuestionTypeModelInterface> questionTypesModels);
}
