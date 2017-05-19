package easytests.options;

import easytests.models.TesteeModelInterface;
import easytests.services.QuizzesServiceInterface;
import easytests.services.TesteesServiceInterface;
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
