package easytests.options;

import easytests.models.QuestionModelInterface;
import java.util.List;


/**
 * @author malinink
 */
public interface QuestionsOptionsInterface extends OptionsInterface {
    QuestionModelInterface withRelations(QuestionModelInterface questionModel);

    List<QuestionModelInterface> withRelations(List<QuestionModelInterface> questionModel);
}
