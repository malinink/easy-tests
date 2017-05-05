package easytests.models;

import easytests.entities.PointEntity;
import easytests.models.empty.QuizModelEmpty;
import org.junit.Assert;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
import org.mockito.Mockito;

/**
 * @author nikitalpopov
 */
public class PointModelTest {

    @Test
    public void testCommon() throws Exception {

        new BeanTester().testBean(PointModel.class);
        new EqualsMethodTester().testEqualsMethod(PointModel.class);
        new HashCodeMethodTester().testHashCodeMethod(PointModel.class);

    }

    @Test
    public void testMap() throws Exception {

        final Integer pointId = 3;
        final String pointType = "Test type";
        final String pointText = "Test text";
        final Integer quizId = 2;

        final PointEntity pointEntity = Mockito.mock(PointEntity.class);

        Mockito.when(pointEntity.getId()).thenReturn(pointId);
        Mockito.when(pointEntity.getType()).thenReturn(pointType);
        Mockito.when(pointEntity.getText()).thenReturn(pointText);
        Mockito.when(pointEntity.getQuizId()).thenReturn(quizId);

        final QuizModelInterface quizModel = Mockito.mock(QuizModelInterface.class);
        Mockito.when(quizModel.getId()).thenReturn(quizId);

        final PointModel pointModel = new PointModel();
        pointModel.map(pointEntity);

        Assert.assertEquals(pointId, pointModel.getId());
        Assert.assertEquals(pointType, pointModel.getType());
        Assert.assertEquals(pointText, pointModel.getText());
        Assert.assertEquals(new QuizModelEmpty(quizId), pointModel.getQuiz());

    }

}
