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
 * @author fortyways
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IssuesOptionsBuilderTest {

    @Autowired
    private IssuesOptionsBuilder issuesOptionsBuilder;

    @MockBean
    private QuizzesOptionsBuilder quizzesOptionsBuilder;

    @MockBean
    private SubjectsOptionsBuilder subjectsOptionsBuilder;

    @Test
    public void testForAuth() throws Exception {
        final SubjectsOptionsInterface subjectsOptions = new SubjectsOptions();
        given(this.subjectsOptionsBuilder.forAuth()).willReturn(subjectsOptions);
        Assert.assertEquals(new IssuesOptions().withSubject(subjectsOptions), this.issuesOptionsBuilder.forAuth());

    }

    @Test
    public void testForDelete() throws Exception {
        final QuizzesOptionsInterface quizzesOptions = new QuizzesOptions();
        given(this.quizzesOptionsBuilder.forDelete()).willReturn(quizzesOptions);
        final IssuesOptionsInterface issuesOptions = this.issuesOptionsBuilder.forDelete();
        Assert.assertEquals(new IssuesOptions().withQuizzes(quizzesOptions), issuesOptions);
    }

}
