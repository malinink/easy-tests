package easytests.services;

import easytests.models.TesteeModelInterface;
import easytests.options.TesteesOptionsInterface;
import java.util.List;


/**
 * @author DoZor-80
 */
public interface TesteesServiceInterface extends ServiceInterface {
    List<TesteeModelInterface> findAll();

    List<TesteeModelInterface> findAll(TesteesOptionsInterface testeesOptions);

    TesteeModelInterface find(Integer id);

    TesteeModelInterface find(Integer id, TesteesOptionsInterface testeesOptions);

    void save(TesteeModelInterface testeeModel);

    void save(TesteeModelInterface testeeModel, TesteesOptionsInterface testeesOptions);

    void delete(TesteeModelInterface testeeModel);

    void delete(TesteeModelInterface testeeModel, TesteesOptionsInterface testeesOptions);
}
