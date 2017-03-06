package easytests.entities;

import easytests.models.AnswerModelInterface;
import easytests.models.PointModelInterface;
import easytests.models.SolutionModelInterface;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;

/**
 * @author SingularityA
 */
public class SolutionEntityTest {

    @Test
    public void testCommon() throws Exception {
        new BeanTester().testBean(SolutionEntity.class);
        new EqualsMethodTester().testEqualsMethod(SolutionEntity.class);
        new HashCodeMethodTester().testHashCodeMethod(SolutionEntity.class);
    }

    @Test
    public void testMap() throws Exception {
        final Integer id = 1;
        final Integer answerId = 10;
        final Integer pointId = 3;

        final AnswerModelInterface answerModel = Mockito.mock(AnswerModelInterface.class);
        Mockito.when(answerModel.getId()).thenReturn(answerId);

        final PointModelInterface pointModel = Mockito.mock(PointModelInterface.class);
        Mockito.when(pointModel.getId()).thenReturn(pointId);

        final SolutionModelInterface solutionModel = Mockito.mock(SolutionModelInterface.class);
        Mockito.when(solutionModel.getId()).thenReturn(id);
        Mockito.when(solutionModel.getAnswer()).thenReturn(answerModel);
        Mockito.when(solutionModel.getPoint()).thenReturn(pointModel);

        final SolutionEntity solutionEntity = new SolutionEntity();
        solutionEntity.map(solutionModel);

        assertEquals(id, solutionEntity.getId());
        assertEquals(answerId, solutionEntity.getAnswerId());
        assertEquals(pointId, solutionEntity.getPointId());
    }
}
