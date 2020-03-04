package easytests.core.options.builder;

import easytests.core.options.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;

/**
 * @author miron97
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SolutionsOptionsBuilderTest {

    @Autowired
    private SolutionsOptionsBuilder solutionsOptionsBuilder;


    @Test
    public void testForAuth() throws Exception {
        final SolutionsOptionsInterface solutionsOptions = this.solutionsOptionsBuilder.forAuth();
        Assert.assertEquals(new SolutionsOptions().getClass(), solutionsOptions.getClass());
    }

    @Test
    public void testForDelete() throws Exception {
        final SolutionsOptionsInterface solutionsOptions = this.solutionsOptionsBuilder.forDelete();
        Assert.assertEquals(new SolutionsOptions().getClass(), solutionsOptions.getClass());
    }
}
