package easytests.core.models;

import org.junit.runner.RunWith;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author malinink
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractModelTest {

    protected Configuration getConfiguration() {
        return new ConfigurationBuilder().iterations(10).build();
    }

    public abstract void testCommon() throws Exception;

    public abstract void testMap() throws Exception;

}
