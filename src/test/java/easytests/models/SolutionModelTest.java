package easytests.models;

import easytests.entities.SolutionEntity;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author SingularityA
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SolutionModelTest {
    @Ignore
    @Test
    public void testCommon() throws Exception {
        new BeanTester().testBean(SolutionModel.class);
        new EqualsMethodTester().testEqualsMethod(SolutionModel.class);
        new HashCodeMethodTester().testHashCodeMethod(SolutionModel.class);
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
