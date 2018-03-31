package easytests.core.models;

import easytests.core.entities.TesteeEntity;
import easytests.support.TesteesSupport;
import org.junit.Test;
import org.meanbean.test.ConfigurationBuilder;


/**
 * @author Yarik2308
 */
public class TesteeModelTest extends AbstractModelTest {

    protected TesteesSupport testeesSupport = new TesteesSupport();

    @Override
    protected ConfigurationBuilder getConfigurationBuilder() {
        return super.getConfigurationBuilder();
    }

    @Test
    public void testCommon() throws Exception {
        this.testCommon(TesteeModel.class);
    }

    @Test
    public void testMap() throws Exception {
        final TesteeEntity testeeEntity = this.testeesSupport.getEntityFixtureMock(0);
        final TesteeModelInterface testeeModel = new TesteeModel();

        testeeModel.map(testeeEntity);

        this.testeesSupport.assertEquals(testeeEntity, testeeModel);
    }

}
