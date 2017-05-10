package easytests.options.builder;

import easytests.options.IssueStandardQuestionTypeOptionsOptions;
import easytests.options.IssueStandardQuestionTypeOptionsOptionsInterface;
import easytests.options.IssueStandardsOptions;
import easytests.options.IssueStandardsOptionsInterface;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;

/**
 * @author SingularityA
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IssueStandardQuestionTypeOptionsOptionsBuilderTest {
    @Autowired
    private IssueStandardQuestionTypeOptionsOptionsBuilder questionTypeOptionsOptionsBuilder;

    @MockBean
    private IssueStandardsOptionsBuilder issueStandardsOptionsBuilder;

    @Test
    public void testForAuth() throws Exception {
        final IssueStandardsOptionsInterface issueStandardsOptions = new IssueStandardsOptions();
        given(this.issueStandardsOptionsBuilder.forAuth()).willReturn(issueStandardsOptions);

        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions
                = this.questionTypeOptionsOptionsBuilder.forAuth();

        Assert.assertEquals(new IssueStandardQuestionTypeOptionsOptions()
                .withIssueStandard(issueStandardsOptions),
                questionTypeOptionsOptions);
    }

    @Test
    public void testForDelete() throws Exception {
        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions
                = this.questionTypeOptionsOptionsBuilder.forDelete();

        Assert.assertEquals(new IssueStandardQuestionTypeOptionsOptions(), questionTypeOptionsOptions);
    }
}
