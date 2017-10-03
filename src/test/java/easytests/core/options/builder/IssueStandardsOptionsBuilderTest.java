package easytests.core.options.builder;

import easytests.core.options.*;
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
public class IssueStandardsOptionsBuilderTest {
    @Autowired
    private IssueStandardsOptionsBuilder issueStandardsOptionsBuilder;

    @MockBean
    private SubjectsOptionsBuilder subjectsOptionsBuilder;

    @MockBean
    private IssueStandardTopicPrioritiesOptionsBuilder topicPrioritiesOptionsBuilder;

    @MockBean
    private IssueStandardQuestionTypeOptionsOptionsBuilder questionTypeOptionsOptionsBuilder;

    @Test
    public void testForAuth() throws Exception {
        final SubjectsOptionsInterface subjectsOptions = new SubjectsOptions();
        given(this.subjectsOptionsBuilder.forAuth()).willReturn(subjectsOptions);

        final IssueStandardsOptionsInterface issueStandardsOptions = this.issueStandardsOptionsBuilder.forAuth();

        Assert.assertEquals(new IssueStandardsOptions().withSubject(subjectsOptions), issueStandardsOptions);
    }

    @Test
    public void testForDelete() throws Exception {
        final IssueStandardTopicPrioritiesOptionsInterface topicPrioritiesOptions = new IssueStandardTopicPrioritiesOptions();
        given(this.topicPrioritiesOptionsBuilder.forDelete()).willReturn(topicPrioritiesOptions);

        final IssueStandardQuestionTypeOptionsOptionsInterface questionTypeOptionsOptions = new IssueStandardQuestionTypeOptionsOptions();
        given(this.questionTypeOptionsOptionsBuilder.forDelete()).willReturn(questionTypeOptionsOptions);

        final IssueStandardsOptionsInterface issueStandardsOptions = this.issueStandardsOptionsBuilder.forDelete();

        Assert.assertEquals(new IssueStandardsOptions()
                .withTopicPriorities(topicPrioritiesOptions)
                .withQuestionTypeOptions(questionTypeOptionsOptions),
                issueStandardsOptions);
    }
}
