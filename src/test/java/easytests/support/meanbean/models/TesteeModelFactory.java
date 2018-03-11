package easytests.support.meanbean.models;

import easytests.core.models.TesteeModel;
import easytests.core.models.TesteeModelInterface;
import org.meanbean.lang.Factory;


/**
 * @author miron97
 */
public class TesteeModelFactory implements Factory<TesteeModelInterface> {

    @Override
    public TesteeModelInterface create() {
        return new TesteeModel();
    }
}
