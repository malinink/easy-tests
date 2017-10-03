package easytests.core.entities;

import easytests.core.models.PointModelInterface;
import easytests.core.models.QuestionModelInterface;
import easytests.core.models.QuizModelInterface;
import org.junit.Assert;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.mockito.Mockito;

/**
 * @author nikitalpopov
 */
public class PointEntityTest {

    @Test
    public void testCommon() throws Exception {
        new BeanTester().testBean(PointEntity.class);
    }

    @Test
    public void testMap() throws Exception {

        final Integer pointId = 3;
        final Integer questionId = 1;
        final Integer quizId = 2;

        final PointModelInterface pointModel = Mockito.mock(PointModelInterface.class);

        final QuestionModelInterface questionModel = Mockito.mock(QuestionModelInterface.class);
        Mockito.when(questionModel.getId()).thenReturn(questionId);
        final QuizModelInterface quizModel = Mockito.mock(QuizModelInterface.class);
        Mockito.when(quizModel.getId()).thenReturn(quizId);

        Mockito.when(pointModel.getId()).thenReturn(pointId);
        Mockito.when(pointModel.getQuestion()).thenReturn(questionModel);
        Mockito.when(pointModel.getQuiz()).thenReturn(quizModel);

        final PointEntity pointEntity = new PointEntity();
        pointEntity.map(pointModel);

        Assert.assertEquals(pointId, pointEntity.getId());
        Assert.assertEquals(questionId, pointEntity.getQuestionId());
        Assert.assertEquals(quizId, pointEntity.getQuizId());

    }

}
