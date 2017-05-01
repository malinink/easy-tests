package easytests.entities;

import easytests.models.PointModelInterface;
import easytests.models.QuizModelInterface;
import org.junit.Assert;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
import org.mockito.Mockito;

/**
 * @author nikitalpopov
 */
public class PointEntityTest {

    @Test
    public void testCommon() throws Exception {

        new BeanTester().testBean(PointEntity.class);
        new EqualsMethodTester().testEqualsMethod(PointEntity.class);
        new HashCodeMethodTester().testHashCodeMethod(PointEntity.class);

    }

    @Test
    public void testMap() throws Exception {

        final Integer pointId = 3;
        final String pointType = "Test type";
        final String pointText = "Test text";
        final Integer quizId = 2;

        final PointModelInterface pointModel = Mockito.mock(PointModelInterface.class);

        final QuizModelInterface quizModel = Mockito.mock(QuizModelInterface.class);
        Mockito.when(quizModel.getId()).thenReturn(quizId);

        Mockito.when(pointModel.getId()).thenReturn(pointId);
        Mockito.when(pointModel.getType()).thenReturn(pointType);
        Mockito.when(pointModel.getText()).thenReturn(pointText);
        Mockito.when(pointModel.getQuiz()).thenReturn(quizModel);

        final PointEntity pointEntity = new PointEntity();
        pointEntity.map(pointModel);

        Assert.assertEquals(pointId, pointEntity.getId());
        Assert.assertEquals(pointType, pointEntity.getType());
        Assert.assertEquals(pointText, pointEntity.getText());
        Assert.assertEquals(quizId, pointEntity.getQuizId());

    }

}
