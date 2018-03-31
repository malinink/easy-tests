package easytests.support.meanbean.models;

import easytests.core.models.AnswerModel;
import easytests.core.models.AnswerModelInterface;
import org.meanbean.lang.Factory;


/**
 * @author SvetlanaTselikova
 */
public class AnswerModelFactory implements Factory<AnswerModelInterface> {

    @Override
    public AnswerModelInterface create() {
        return new AnswerModel();
    }
}
