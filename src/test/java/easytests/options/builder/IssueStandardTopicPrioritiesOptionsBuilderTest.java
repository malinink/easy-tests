package easytests.options.builder;

import easytests.options.IssueStandardTopicPrioritiesOptions;
import easytests.options.IssueStandardTopicPrioritiesOptionsInterface;
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
public class IssueStandardTopicPrioritiesOptionsBuilderTest {
    @Autowired
    private IssueStandardTopicPrioritiesOptionsBuilder topicPrioritiesOptionsBuilder;

    @MockBean
    private IssueStandardsOptionsBuilder issueStandardsOptionsBuilder;

    @Test
    public void testForAuth() throws Exception {
        final IssueStandardsOptionsInterface issueStandardsOptions = new IssueStandardsOptions();
        given(this.issueStandardsOptionsBuilder.forAuth()).willReturn(issueStandardsOptions);

        final IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions
                = this.topicPrioritiesOptionsBuilder.forAuth();

        Assert.assertEquals(new IssueStandardTopicPrioritiesOptions()
                .withIssueStandard(issueStandardsOptions),
                topicPrioritiesOptions);
    }

    @Test
    public void testForDelete() throws Exception {
        final IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions
                = this.topicPrioritiesOptionsBuilder.forDelete();

        Assert.assertEquals(new IssueStandardTopicPrioritiesOptions(), topicPrioritiesOptions);
    }
}
