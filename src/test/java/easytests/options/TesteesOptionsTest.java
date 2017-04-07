package easytests.options;

import easytests.models.TesteeModelInterface;
import easytests.services.TesteesServiceInterface;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;


/**
 * @author DoZor-80
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TesteesOptionsTest {
    @Test
    public void testWithRelationsOnSingleModel() throws Exception {
        final TesteeModelInterface testeeModel = Mockito.mock(TesteeModelInterface.class);
        final TesteesOptionsInterface testeesOptions = new TesteesOptions();
        final TesteeModelInterface testeeModelWithRelations = testeesOptions.withRelations(testeeModel);

        Assert.assertEquals(testeeModel, testeeModelWithRelations);
    }

    @Test
    public void testWithRelationsOnNull() throws Exception {
        final TesteeModelInterface testeeModel = null;
        final TesteesOptionsInterface testeesOptions = new TesteesOptions();
        final TesteeModelInterface testeeModelWithRelations = testeesOptions.withRelations(testeeModel);

        Assert.assertEquals(null, testeeModelWithRelations);
    }

    @Test
    public void testWithRelationsOnModelsList() throws Exception {
        final TesteeModelInterface testeeModelFirst = Mockito.mock(TesteeModelInterface.class);
        testeeModelFirst.setId(1);
        final TesteeModelInterface testeeModelSecond = Mockito.mock(TesteeModelInterface.class);
        testeeModelSecond.setId(2);
        final List<TesteeModelInterface> testeesModels = new ArrayList<>(2);
        testeesModels.add(testeeModelFirst);
        testeesModels.add(testeeModelSecond);

        final TesteesOptionsInterface testeesOptions = new TesteesOptions();

        final List<TesteeModelInterface> testeesModelsWithRelations = testeesOptions.withRelations(testeesModels);

        Assert.assertEquals(testeesModelsWithRelations, testeesModels);
    }

    @Test
    public void testSaveWithRelations() throws Exception {
        final TesteeModelInterface testeeModel = Mockito.mock(TesteeModelInterface.class);
        final TesteesOptionsInterface testeesOptions = new TesteesOptions();
        final TesteesServiceInterface testeesService = Mockito.mock(TesteesServiceInterface.class);
        testeesOptions.setTesteesService(testeesService);
        testeesOptions.saveWithRelations(testeeModel);
        verify(testeesService).save(testeeModel);
    }

    @Test
    public void testDeleteWithRelations() throws Exception {
        final TesteeModelInterface testeeModel = Mockito.mock(TesteeModelInterface.class);
        final TesteesOptionsInterface testeesOptions = new TesteesOptions();
        final TesteesServiceInterface testeesService = Mockito.mock(TesteesServiceInterface.class);
        testeesOptions.setTesteesService(testeesService);
        testeesOptions.deleteWithRelations(testeeModel);
        verify(testeesService).delete(testeeModel);
    }
}
