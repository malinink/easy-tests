package easytests.core.options;

import easytests.core.models.TesteeModelInterface;
import easytests.core.services.QuizzesServiceInterface;
import easytests.core.services.TesteesServiceInterface;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Setter;

/**
 * @author DoZor-80
 */
@EqualsAndHashCode
public class TesteesOptions implements TesteesOptionsInterface {
    @Setter
    private TesteesServiceInterface testeesService;

    @Setter
    private QuizzesServiceInterface quizzesService;

    private QuizzesOptionsInterface quizzesOptions;

    @Override
    public TesteesOptionsInterface withQuiz(QuizzesOptionsInterface quizzesOptions) {
        this.quizzesOptions = quizzesOptions;
        return this;
    }

    @Override
    public TesteeModelInterface withRelations(TesteeModelInterface testeeModel) {
        if (testeeModel == null) {
            return testeeModel;
        }

        if (this.quizzesOptions != null) {
            testeeModel.setQuiz(this.quizzesService
                    .find(testeeModel.getQuiz().getId(), this.quizzesOptions));
        }
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
        if (this.quizzesOptions != null) {
            this.quizzesService.save(testeeModel.getQuiz(), this.quizzesOptions);
        }
        this.testeesService.save(testeeModel);
    }

    @Override
    public void deleteWithRelations(TesteeModelInterface testeeModel) {
        if (this.quizzesOptions != null) {
            this.quizzesService.delete(testeeModel.getQuiz(), quizzesOptions);
        }
        this.testeesService.delete(testeeModel);
    }
}

