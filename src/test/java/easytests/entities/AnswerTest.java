package easytests.entities;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * @author rezenbekk
 */
public class AnswerTest {
    @Test
    public void testTxt() throws Exception {
        final Answer testAnswer = new Answer();
        final String testString = "Test";
        testAnswer.setTxt(testString);
        assertEquals(testString, testAnswer.getTxt());
    }

    @Test
    public void testQuestionId() throws Exception {
        final Answer testAnswer = new Answer();
        testAnswer.setQuestionId(1337);
        assertEquals((long) 1337, (long) testAnswer.getQuestionId());
    }

    @Test
    public void testIsRight() throws Exception {
        final Answer testAnswer = new Answer();
        testAnswer.setIsRight(true);
        assertEquals(true, testAnswer.getIsRight());
    }

}
