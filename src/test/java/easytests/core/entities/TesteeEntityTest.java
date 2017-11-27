package easytests.core.entities;

import easytests.core.models.TesteeModelInterface;

import easytests.support.TesteesSupport;
import org.junit.Test;

/**
 * @author DoZor-80
 */
public class TesteeEntityTest extends AbstractEntityTest{

    protected TesteesSupport testeesSupport = new TesteesSupport();

    @Test
    public void testCommon() throws Exception {
       this.testCommon(TesteeEntity.class);
    }

    @Test
    public void testMap() throws Exception {
        final TesteeModelInterface testeeModel = this.testeesSupport.getModelFixtureMock(0);
        final TesteeEntity testeeEntity = new TesteeEntity();

        testeeEntity.map(testeeModel);

        this.testeesSupport.assertEquals(testeeModel,testeeEntity);

    }
}
