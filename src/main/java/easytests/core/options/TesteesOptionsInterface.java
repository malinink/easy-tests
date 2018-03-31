package easytests.core.options;

import easytests.core.models.TesteeModelInterface;
import easytests.core.services.QuizzesServiceInterface;
import easytests.core.services.TesteesServiceInterface;

import java.util.List;


/**
 * @author DoZor-80
 */
public interface TesteesOptionsInterface {
    void setTesteesService(TesteesServiceInterface testeesService);

    void setQuizzesService(QuizzesServiceInterface quizzesService);

    TesteesOptionsInterface withQuiz(QuizzesOptionsInterface quizzesOptions);

    TesteeModelInterface withRelations(TesteeModelInterface testeeModel);

    List<TesteeModelInterface> withRelations(List<TesteeModelInterface> testeesModels);

    void saveWithRelations(TesteeModelInterface testeeModel);

    void deleteWithRelations(TesteeModelInterface testeeModel);
}
