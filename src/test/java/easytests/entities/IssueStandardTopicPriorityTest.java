package easytests.entities;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author SingularityA
 */
public class IssueStandardTopicPriorityTest {

    @Test
    public void idTest() throws Exception {
        final IssueStandardTopicPriorityInterface issueStandardTopicPriority = new IssueStandardTopicPriority();
        issueStandardTopicPriority.setId(1);
        assertEquals((Integer) 1, issueStandardTopicPriority.getId());
    }

    @Test
    public void topicIdTest() throws Exception {
        final IssueStandardTopicPriorityInterface issueStandardTopicPriority = new IssueStandardTopicPriority();
        issueStandardTopicPriority.setTopicId(1);
        assertEquals((Integer) 1, issueStandardTopicPriority.getTopicId());
    }

    @Test
    public void priorityTest() throws Exception {
        final IssueStandardTopicPriorityInterface issueStandardTopicPriority = new IssueStandardTopicPriority();
        issueStandardTopicPriority.setPriority(Priority.HIGH);
        assertEquals(Priority.HIGH, issueStandardTopicPriority.getPriority());
    }

    @Test
    public void issueStandardIdTest() throws Exception {
        final IssueStandardTopicPriorityInterface issueStandardTopicPriority = new IssueStandardTopicPriority();
        issueStandardTopicPriority.setIssueStandardId(1);
        assertEquals((Integer) 1, issueStandardTopicPriority.getIssueStandardId());
    }
}
