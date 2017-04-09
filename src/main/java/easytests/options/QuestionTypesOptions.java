package easytests.options;

import easytests.models.QuestionTypeModelInterface;
import java.util.List;


/**
 * @author malinink
 */
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
