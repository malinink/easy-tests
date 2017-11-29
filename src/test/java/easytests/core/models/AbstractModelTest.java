package easytests.core.models;

import org.junit.runner.RunWith;
import org.meanbean.test.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author malinink
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractModelTest {

    protected ConfigurationBuilder getConfigurationBuilder() {
        return new ConfigurationBuilder().iterations(10);
    }

    public abstract void testCommon() throws Exception;

    protected void testCommon(Class entityClass) {
        final Configuration configuration = this.getConfigurationBuilder().build();
        new BeanTester().testBean(entityClass, configuration);
    }

    public abstract void testMap() throws Exception;

}
