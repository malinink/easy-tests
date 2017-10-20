package easytests.core.models.empty;

import easytests.core.models.exceptions.CallMethodOnEmptyModelsListException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author malinink
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ModelsListEmptyTest extends AbstractEmptyTest {
    public ModelsListEmptyTest() {
        super(ModelsListEmpty.class);
    }

    @Test
    public void testAllOtherMethodsFailsInModelsListEmpty() throws Exception {
        final Object model = new ModelsListEmpty();
        this.testModelTrowsExpectedExceptions(model, CallMethodOnEmptyModelsListException.class);
    }
    @Test
    public void testGetHashCode(){
        final Object model = new ModelsListEmpty();
        Assert.assertNotNull(model.hashCode());
    }
}
