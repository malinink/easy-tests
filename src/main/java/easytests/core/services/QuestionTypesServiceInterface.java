package easytests.core.services;

import easytests.core.models.QuestionTypeModelInterface;
import easytests.core.options.QuestionTypesOptionsInterface;

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
