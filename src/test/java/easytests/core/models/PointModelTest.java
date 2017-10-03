package easytests.core.models;

import easytests.core.entities.PointEntity;
import easytests.core.models.empty.QuestionModelEmpty;
import easytests.core.models.empty.QuizModelEmpty;
import org.junit.Assert;
import org.junit.Test;
import org.meanbean.test.*;
import org.mockito.Mockito;

/**
 * @author nikitalpopov
 */
public class PointModelTest {

    @Test
    public void testCommon() throws Exception {

        Configuration configuration = new ConfigurationBuilder()
                .ignoreProperty("question")
                .ignoreProperty("quiz")
                .ignoreProperty("solutions")
                .build();
        new BeanTester().testBean(PointModel.class, configuration);

    }

    @Test
    public void testMap() throws Exception {

        final Integer pointId = 3;
        final Integer questionId = 2;
        final Integer quizId = 2;

        final PointEntity pointEntity = Mockito.mock(PointEntity.class);

        Mockito.when(pointEntity.getId()).thenReturn(pointId);
        Mockito.when(pointEntity.getQuestionId()).thenReturn(questionId);
        Mockito.when(pointEntity.getQuizId()).thenReturn(quizId);

        final QuizModelInterface quizModel = Mockito.mock(QuizModelInterface.class);
        Mockito.when(quizModel.getId()).thenReturn(quizId);
        final QuestionModelInterface questionModel = Mockito.mock(QuestionModelInterface.class);
        Mockito.when(questionModel.getId()).thenReturn(questionId);

        final PointModel pointModel = new PointModel();
        pointModel.map(pointEntity);

        Assert.assertEquals(pointId, pointModel.getId());
        Assert.assertEquals(new QuestionModelEmpty(questionId), pointModel.getQuestion());
        Assert.assertEquals(new QuizModelEmpty(quizId), pointModel.getQuiz());

    }

}
