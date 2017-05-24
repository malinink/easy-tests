package easytests.options.builder;

import easytests.options.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;

/**
 * @author nikitalpopov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PointsOptionsBuilderTest {

    @Autowired
    private PointsOptionsBuilder pointsOptionsBuilder;

    @MockBean
    private QuizzesOptionsBuilder quizzesOptionsBuilder;

    @MockBean
    private SolutionsOptionsBuilder solutionsOptionsBuilder;

    @Test
    public void testForDelete() throws Exception {

        final SolutionsOptionsInterface solutionsOptions = new SolutionsOptions();
        given(this.solutionsOptionsBuilder.forDelete()).willReturn(solutionsOptions);

        Assert.assertEquals(new PointsOptions().withSolutions(solutionsOptions), this.pointsOptionsBuilder.forDelete());

    }

    @Test
    public void testForAuth() throws Exception {

        final QuizzesOptionsInterface quizzesOptions = new QuizzesOptions();
        given(this.quizzesOptionsBuilder.forAuth()).willReturn(quizzesOptions);

        Assert.assertEquals(new PointsOptions().withQuiz(quizzesOptions), this.pointsOptionsBuilder.forAuth());

    }

}
