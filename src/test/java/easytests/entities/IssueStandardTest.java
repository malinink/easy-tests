package easytests.entities;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

/**
 * @author SingularityA
 */
public class IssueStandardTest {

    @Test
    public void idTest() throws Exception {
        final IssueStandardInterface issueStandard = new IssueStandard();
        issueStandard.setId(1);
        assertEquals((Integer) 1, issueStandard.getId());
    }

    @Test
    public void timeLimitTest() throws Exception {
        final IssueStandardInterface issueStandard = new IssueStandard();
        issueStandard.setTimeLimit(600);
        assertEquals((Integer) 600, issueStandard.getTimeLimit());
    }

    @Test
    public void questionsNumberTest() throws Exception {
        final IssueStandardInterface issueStandard = new IssueStandard();
        issueStandard.setQuestionsNumber(10);
        assertEquals((Integer) 10, issueStandard.getQuestionsNumber());
    }

    @Test
    public void issueStandardTopicPrioritiesTest() throws Exception {
        final IssueStandardInterface issueStandard = new IssueStandard();
        List<IssueStandardTopicPriorityInterface> issueStandardTopicPriorities = new ArrayList<>();
        issueStandardTopicPriorities.add(new IssueStandardTopicPriority().setId(1));
        issueStandardTopicPriorities.add(new IssueStandardTopicPriority().setId(2));

        issueStandard.setIssueStandardTopicPriorities(issueStandardTopicPriorities);
        assertEquals(issueStandardTopicPriorities, issueStandard.getIssueStandardTopicPriorities());
    }

    @Test
    public void issueStandardQuestionTypeOptionsTest() throws Exception {
        final IssueStandardInterface issueStandard = new IssueStandard();
        List<IssueStandardQuestionTypeOptionInterface> issueStandardQuestionTypeOptions = new ArrayList<>();
        issueStandardQuestionTypeOptions.add(new IssueStandardQuestionTypeOption().setId(1));
        issueStandardQuestionTypeOptions.add(new IssueStandardQuestionTypeOption().setId(2));

        issueStandard.setIssueStandardQuestionTypeOptions(issueStandardQuestionTypeOptions);
        assertEquals(issueStandardQuestionTypeOptions, issueStandard.getIssueStandardQuestionTypeOptions());
    }

    @Test
    public void subjectTest() throws Exception {
        SubjectInterface subject = Mockito.mock(SubjectInterface.class);
        Mockito.when(subject.getId()).thenReturn(1);

        final IssueStandardInterface issueStandard = new IssueStandard();
        issueStandard.setSubject(subject);
        assertEquals((Integer) 1, issueStandard.getSubject().getId());
    }
}
