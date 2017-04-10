package easytests.options;

import easytests.models.TesteeModelInterface;
import easytests.services.TesteesServiceInterface;
import java.util.List;
import lombok.Setter;


/**
 * @author DoZor-80
 */
public class TesteesOptions implements TesteesOptionsInterface {
    @Setter
    private TesteesServiceInterface testeesService;

    @Override
    public TesteeModelInterface withRelations(TesteeModelInterface testeeModel) {
        return testeeModel;
    }

    @Override
    public List<TesteeModelInterface> withRelations(List<TesteeModelInterface> testeesModels) {
        for (TesteeModelInterface testeeModel: testeesModels) {
            this.withRelations(testeeModel);
        }
        return testeesModels;
    }

    @Override
    public void saveWithRelations(TesteeModelInterface testeeModel) {
        this.testeesService.save(testeeModel);
    }

    @Override
    public void deleteWithRelations(TesteeModelInterface testeeModel) {
        this.testeesService.delete(testeeModel);
    }
}

