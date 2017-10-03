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
 * @author vkpankov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SubjectsOptionsBuilderTest {
    @Autowired
    private SubjectsOptionsBuilder subjectsOptionsBuilder;

    @MockBean
    private IssueStandardsOptionsBuilder issueStandardsOptionsBuilder;

    @MockBean
    private TopicsOptionsBuilder topicsOptionsBuilder;

    @MockBean
    private UsersOptionsBuilder usersOptionsBuilder;

    @Test
    public void testForAuth() throws Exception {
        final UsersOptionsInterface usersOptions = new UsersOptions();
        given(this.usersOptionsBuilder.forAuth()).willReturn(usersOptions);
        Assert.assertEquals(new SubjectsOptions().withUser(usersOptions), this.subjectsOptionsBuilder.forAuth());

    }

    @Test
    public void testForDelete() throws Exception {
        final IssueStandardsOptionsInterface issueStandardsOptions = new IssueStandardsOptions();
        given(this.issueStandardsOptionsBuilder.forDelete()).willReturn(issueStandardsOptions);

        final TopicsOptionsInterface topicsOptions = new TopicsOptions();
        given(this.topicsOptionsBuilder.forDelete()).willReturn(topicsOptions);

        final SubjectsOptionsInterface subjectsOptions = this.subjectsOptionsBuilder.forDelete();
        Assert.assertEquals(new SubjectsOptions().withTopics(topicsOptions).withIssueStandard(issueStandardsOptions), subjectsOptions);
    }

}
