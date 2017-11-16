package easytests.core.entities;

import org.junit.runner.RunWith;
import org.meanbean.test.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author malinink
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractEntityTest {

    protected Configuration getConfiguration() {
        return new ConfigurationBuilder().iterations(10).build();
    }

    public abstract void testCommon() throws Exception;

    protected void testCommon(Class entityClass) {
        final Configuration configuration = this.getConfiguration();
        new BeanTester().testBean(entityClass, configuration);
        new EqualsMethodTester().testEqualsMethod(entityClass, configuration);
        new HashCodeMethodTester().testHashCodeMethod(entityClass);
    }

    public abstract void testMap() throws Exception;

}
