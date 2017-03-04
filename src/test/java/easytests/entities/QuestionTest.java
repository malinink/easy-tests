package easytests.entities;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author firkhraag
 */
public class QuestionTest {

    @Test
    public void testText() throws Exception {
        final Question testQuestion = new Question();
        testQuestion.setText("Question1");
        assertEquals("Question1", testQuestion.getText());
    }

    @Test
    public void testType() throws Exception {
        final Question testQuestion = new Question();
        testQuestion.setType(1);
        assertEquals((long) 1, (long) testQuestion.getType());
    }

    @Test
    public void testTopicId() throws Exception {
        final Question testQuestion = new Question();
        testQuestion.setTopicId(1);
        assertEquals((long) 1, (long) testQuestion.getTopicId());
    }
}
