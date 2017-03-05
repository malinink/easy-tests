package easytests.models;

import easytests.entities.SolutionEntity;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
import org.mockito.Mockito;

/**
 * @author SingularityA
 */
public class SolutionModelTest {
    @Ignore
    @Test
    public void testCommon() throws Exception {
        new BeanTester().testBean(SolutionModel.class);
        new EqualsMethodTester().testEqualsMethod(SolutionModel.class);
        new HashCodeMethodTester().testHashCodeMethod(SolutionModel.class);
    }

    @Test
    public void testId() throws Exception {
        final SolutionModelInterface solution = new SolutionModel();
        solution.setId(1);
        Assert.assertEquals((Integer) 1, solution.getId());
    }

    @Test
    public void testAnswer() throws Exception {
        final SolutionModelInterface solution = new SolutionModel();
        AnswerModelInterface answer = Mockito.mock(AnswerModelInterface.class);
        solution.setAnswer(answer);
        Assert.assertEquals(answer, solution.getAnswer());
    }

    @Test
    public void testPoint() throws Exception {
        final SolutionModelInterface solution = new SolutionModel();
        PointModelInterface point = Mockito.mock(PointModelInterface.class);
        solution.setPoint(point);
        Assert.assertEquals(point, solution.getPoint());
    }

    @Test
    public void testMap() throws Exception {
        final Integer id = 1;
        final Integer answerId = 2;
        final Integer pointId = 3;

        SolutionEntity solutionEntity = Mockito.mock(SolutionEntity.class);
        Mockito.when(solutionEntity.getId()).thenReturn(id);
        Mockito.when(solutionEntity.getAnswerId()).thenReturn(answerId);
        Mockito.when(solutionEntity.getPointId()).thenReturn(pointId);

        final SolutionModelInterface solutionModel = new SolutionModel();
        solutionModel.map(solutionEntity);

        Assert.assertEquals(id, solutionModel.getId());
        Assert.assertNull(solutionModel.getPoint());
        Assert.assertNull(solutionModel.getAnswer());
    }
}
