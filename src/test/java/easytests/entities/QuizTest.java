package easytests.entities;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author vkpankov
 */
public class QuizTest {
    @Test
    public void testIssueId() throws Exception {

        final Quiz testQuiz = new Quiz();
        testQuiz.setIssueId(1);
        assertEquals((Integer)1, testQuiz.getIssueId());

    }

    @Test
    public void testInviteCode() throws Exception {

        final Quiz testQuiz = new Quiz();
        testQuiz.setInviteCode("TEST");
        assertEquals("TEST", testQuiz.getInviteCode());

    }
}
