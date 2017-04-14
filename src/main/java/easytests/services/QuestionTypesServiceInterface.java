package easytests.services;

import easytests.models.QuestionTypeModelInterface;
import easytests.options.QuestionTypesOptionsInterface;
import java.util.List;


/**
 * @author malinink
 */
public interface QuestionTypesServiceInterface extends ServiceInterface {
    List<QuestionTypeModelInterface> findAll();

    List<QuestionTypeModelInterface> findAll(QuestionTypesOptionsInterface questionTypesOptions);

    QuestionTypeModelInterface find(Integer id);

    QuestionTypeModelInterface find(Integer id, QuestionTypesOptionsInterface questionTypesOptions);
}
