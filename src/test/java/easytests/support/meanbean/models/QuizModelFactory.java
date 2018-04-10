package easytests.support.meanbean.models;

import easytests.core.models.QuizModel;
import easytests.core.models.QuizModelInterface;
import org.meanbean.lang.Factory;


/**
 * @author Yarik2308
 */
public class QuizModelFactory implements Factory<QuizModelInterface> {
    @Override
    public QuizModelInterface create() {
        return new QuizModel();
    }
}
