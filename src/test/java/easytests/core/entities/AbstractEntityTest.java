package easytests.core.entities;

import easytests.core.AbstractMeanBeanTest;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author malinink
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractEntityTest extends AbstractMeanBeanTest {

    public abstract void testCommon() throws Exception;

    protected void testCommon(Class entityClass) {
        this.getBeanTester().testBean(entityClass, this.getConfiguration());
        this.getEqualsMethodTester().testEqualsMethod(entityClass, this.getConfiguration());
        this.getHashCodeMethodTester().testHashCodeMethod(entityClass);
    }

    public abstract void testMap() throws Exception;
}