package easytests.entities;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author nikitapopov on 24/02/2017.
 */
public class PointTest {
    @Test
    public void testSetId() throws Exception {
        final Point testPoint = new Point();
        testPoint.setId(1);
        assertEquals((long) 1, (long) testPoint.getId());
    }

    @Test
    public void testSetText() throws Exception {
        final Point testPoint = new Point();
        testPoint.setText("test");
        assertEquals("test", testPoint.getText());
    }

    @Test
    public void testSetType() throws Exception {
        final Point testPoint = new Point();
        testPoint.setType("test");
        assertEquals("test", testPoint.getType());
    }

//    Is this correct?
/*    @Test
    public void testSetQuiz() throws Exception {
        final Point testPoint = new Point();
        Quiz testQuiz = new Quiz();
        testPoint.setQuiz(testQuiz);
        assertEquals(testQuiz, testPoint.getQuiz());
    }

    @Test
    public void testSetSolutions() throws Exception {
        final Point testPoint = new Point();
        List<Solution> testSolutions = new ArrayList<Solution>();
        Solution testSolution = new Solution();
        testSolutions.add(testSolution);
        testPoint.setSolutions(testSolutions);
        assertEquals(testSolutions, testPoint.getSolutions());
    }
    */

}