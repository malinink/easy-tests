package easytests.entities;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author SingularityA
 */
public class SolutionTest {

    @Test
    public void idTest() throws Exception {
        final SolutionInterface solution = new Solution();
        solution.setId(1);
        Assert.assertEquals((Integer) 1, solution.getId());
    }

    @Test
    public void answerTest() throws Exception {
        final SolutionInterface solution = new Solution();
        AnswerInterface answer = Mockito.mock(AnswerInterface.class);
        solution.setAnswer(answer);
        Assert.assertEquals(answer, solution.getAnswer());
    }

    @Test
    public void pointTest() throws Exception {
        final SolutionInterface solution = new Solution();
        PointInterface point = Mockito.mock(PointInterface.class);
        solution.setPoint(point);
        Assert.assertEquals(point, solution.getPoint());
    }
}
