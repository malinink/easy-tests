package easytests.options;

import easytests.models.TesteeModelInterface;
import easytests.services.TesteesServiceInterface;
import java.util.List;


/**
 * @author DoZor-80
 */
public interface TesteesOptionsInterface {
    void setTesteesService(TesteesServiceInterface testeesService);

    TesteeModelInterface withRelations(TesteeModelInterface testeeModel);

    List<TesteeModelInterface> withRelations(List<TesteeModelInterface> testeesModels);

    void saveWithRelations(TesteeModelInterface testeeModel);

    void deleteWithRelations(TesteeModelInterface testeeModel);
}
