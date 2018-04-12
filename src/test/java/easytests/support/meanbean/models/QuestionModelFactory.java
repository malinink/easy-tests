package easytests.support.meanbean.models;

import easytests.core.models.QuestionModel;
import easytests.core.models.QuestionModelInterface;
import org.meanbean.lang.Factory;


/**
 * @author AnyaMaz
 */
public class QuestionModelFactory implements Factory<QuestionModelInterface> {

    @Override
    public QuestionModelInterface create() {return new QuestionModel();}
}
