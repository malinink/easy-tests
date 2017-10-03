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
 * @author fortyways
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuizzesOptionsBuilderTest {

    @Autowired
    private QuizzesOptionsBuilder quizzesOptionsBuilder;

    @MockBean
    private PointsOptionsBuilder pointsOptionsBuilder;

    @MockBean
    private IssuesOptionsBuilder issuesOptionsBuilder;

    @Test
    public void testForAuth() throws Exception {
        final IssuesOptionsInterface issuesOptions = new IssuesOptions();
        given(this.issuesOptionsBuilder.forAuth()).willReturn(issuesOptions);
        Assert.assertEquals(new QuizzesOptions().withIssue(issuesOptions), this.quizzesOptionsBuilder.forAuth());
    }

    @Test
    public void testForDelete() throws Exception {
        final PointsOptionsInterface pointsOptions = Mockito.mock(PointsOptionsInterface.class);
        given(this.pointsOptionsBuilder.forDelete()).willReturn(pointsOptions);
        final QuizzesOptionsInterface quizzesOptions = this.quizzesOptionsBuilder.forDelete();
        Assert.assertEquals(new QuizzesOptions().withPoints(pointsOptions).withTestee(new TesteesOptions()), quizzesOptions);
    }

}
