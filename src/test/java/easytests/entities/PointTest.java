package easytests.entities;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author nikitalpopov
 */
public class PointTest {
    @Test
    public void testSetId() throws Exception {
        final Point testPoint = new Point();
        testPoint.setId(1);
        Assert.assertEquals((Integer) 1, testPoint.getId());
    }

    @Test
    public void testSetType() throws Exception {
        final Point testPoint = new Point();
        testPoint.setType("test");
        Assert.assertEquals("test", testPoint.getType());
    }

    @Test
    public void testSetText() throws Exception {
        final Point testPoint = new Point();
        testPoint.setText("test");
        Assert.assertEquals("test", testPoint.getText());
    }

    @Test
    public void testSetQuizId() throws Exception {
        final Point testPoint = new Point();
        testPoint.setQuizId(1);
        Assert.assertEquals((Integer) 1, testPoint.getQuizId());
    }

    @Test
    public void testSetSolutions() throws Exception {
        final Point testPoint = new Point();
        SolutionInterface testSolution = Mockito.mock(SolutionInterface.class);
        List<SolutionInterface> testList = new ArrayList<>();
        testList.add(testSolution);
        testPoint.setSolutions(testList);
        assertEquals(testList, testPoint.getSolutions());
    }

}