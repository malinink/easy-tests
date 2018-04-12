package easytests.core.options;

import easytests.core.models.QuestionTypeModelInterface;
import java.util.List;

import easytests.core.services.QuestionTypesServiceInterface;
import lombok.EqualsAndHashCode;


/**
 * @author malinink
 */
@EqualsAndHashCode
public class QuestionTypesOptions implements QuestionTypesOptionsInterface {
    @Override
    public QuestionTypeModelInterface withRelations(QuestionTypeModelInterface questionTypeModel) {
        if (questionTypeModel == null) {
            return questionTypeModel;
        }
        return questionTypeModel;
    }

    @Override
    public List<QuestionTypeModelInterface> withRelations(List<QuestionTypeModelInterface> questionTypesModels) {
        for (QuestionTypeModelInterface questionTypeModel: questionTypesModels) {
            this.withRelations(questionTypeModel);
        }
        return questionTypesModels;
    }
}
