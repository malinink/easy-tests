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

    @MockBean
    private SolutionsOptions solutionsOptions;

    @Test
    public void testForAuth() throws Exception {
        final SolutionsOptions solutionsOptions = new SolutionsOptions();
        given(this.solutionsOptionsBuilder.forAuth()).willReturn(solutionsOptions);
        Assert.assertEquals(new SolutionsOptions(), this.solutionsOptionsBuilder.forAuth());
    }

    @Test
    public void testForDelete() throws Exception {
        final SolutionsOptions solutionsOptions = new SolutionsOptions();
        given(this.solutionsOptionsBuilder.forDelete()).willReturn(solutionsOptions);
        Assert.assertEquals(new SolutionsOptions(), this.solutionsOptionsBuilder.forDelete());
    }
}
