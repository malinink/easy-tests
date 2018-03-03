package easytests.support.meanbean.models;

import easytests.core.models.QuestionTypeModel;
import easytests.core.models.QuestionTypeModelInterface;
import org.meanbean.lang.Factory;


/**
 * @author malinink
 */
public class QuestionTypeModelFactory implements Factory<QuestionTypeModelInterface> {

    @Override
    public QuestionTypeModelInterface create()
    {
        return new QuestionTypeModel();
    }
}
