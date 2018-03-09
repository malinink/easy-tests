package easytests.support;

import easytests.core.entities.PointEntity;
import easytests.core.models.PointModelInterface;
import easytests.core.models.empty.ModelsListEmpty;
import easytests.core.models.empty.QuestionModelEmpty;
import easytests.core.models.empty.QuizModelEmpty;
import java.util.List;
import org.junit.Assert;
import org.mockito.Mockito;


/**
 * @author SvetlanaTselikova
 */
public class PointsSupport {

    protected static Object[][] fixtures = new Object[][]{
            {
                    1,
                    1,
                    1
            },
            {
                    2,
                    2,
                    2
            },
            {
                    3,
                    2,
                    3
            },
    };

    protected static Object[][] additional = new Object[][]{
            {
                    // for insert entity
                    null,
                    1,
                    2
            },
            {
                    // for insert entity
                    null,
                    3,
                    3
            },
            {
                    // for update entity with id = 1
                    1,
                    2,
                    2
            },
    };

    public PointEntity getEntityFixtureMock(Integer index) {
        return this.getEntityMock(fixtures[index]);
    }

    public PointEntity getEntityAdditionalMock(Integer index) {
        return this.getEntityMock(additional[index]);
    }

    private PointEntity getEntityMock(Object[] data) {
        return this.getEntityMock(
                (Integer) data[0],
                (Integer) data[1],
                (Integer) data[2]
        );
    }

    private PointEntity getEntityMock(
            Integer id,
            Integer quizId,
            Integer questionId
    ) {
        final PointEntity pointEntity = Mockito.mock(PointEntity.class);
        Mockito.when(pointEntity.getId()).thenReturn(id);
        Mockito.when(pointEntity.getQuizId()).thenReturn(quizId);
        Mockito.when(pointEntity.getQuestionId()).thenReturn(questionId);
        return pointEntity;
    }

    public PointModelInterface getModelFixtureMock(Integer index) {
        return this.getModelMock(fixtures[index]);
    }

    public PointModelInterface getModelAdditionalMock(Integer index) {
        return this.getModelMock(additional[index]);
    }

    private PointModelInterface getModelMock(Object[] data) {
        return this.getModelMock(
                (Integer) data[0],
                (Integer) data[1],
                (Integer) data[2]
        );
    }

    private PointModelInterface getModelMock(
            Integer id,
            Integer quizId,
            Integer questionId
    ) {
        final PointModelInterface pointModel = Mockito.mock(PointModelInterface.class);
        Mockito.when(pointModel.getId()).thenReturn(id);
        Mockito.when(pointModel.getQuestion()).thenReturn(new QuestionModelEmpty(questionId));
        Mockito.when(pointModel.getQuiz()).thenReturn(new QuizModelEmpty(quizId));
        Mockito.when(pointModel.getSolutions()).thenReturn(new ModelsListEmpty());
        return pointModel;
    }

    public void assertEquals(PointEntity expected, PointEntity actual) {
        assertEquals(expected, actual, false);
    }

    public void assertEqualsWithoutId(PointEntity expected, PointEntity actual) {
        assertEquals(expected, actual, true);
    }

    private void assertEquals(PointEntity expected, PointEntity actual, Boolean exceptId) {
        if (!exceptId) {
            Assert.assertEquals(expected.getId(), actual.getId());
        }
        Assert.assertEquals(expected.getQuestionId(), actual.getQuestionId());
        Assert.assertEquals(expected.getQuizId(), actual.getQuizId());
    }

    public void assertNotEquals(PointEntity unexpected, PointEntity actual) {
        assertNotEquals(unexpected, actual, false);
    }

    public void assertNotEqualsWithoutId(PointEntity unexpected, PointEntity actual) {
        assertNotEquals(unexpected, actual, true);
    }

    private void assertNotEquals(PointEntity unexpected, PointEntity actual, Boolean exceptId) {
        if (!exceptId) {
            Assert.assertNotEquals(unexpected.getId(), actual.getId());
        }
        Assert.assertNotEquals(unexpected.getQuestionId(), actual.getQuestionId());
        Assert.assertNotEquals(unexpected.getQuizId(), actual.getQuizId());
    }

    public void assertEquals(PointModelInterface expected, PointModelInterface actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getQuestion(), actual.getQuestion());
        Assert.assertEquals(expected.getQuiz(), actual.getQuiz());
        Assert.assertEquals(expected.getSolutions(), actual.getSolutions());
    }

    public void assertEquals(PointModelInterface expected, PointEntity actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getQuestion().getId(), actual.getQuestionId());
        Assert.assertEquals(expected.getQuiz().getId(), actual.getQuizId());
    }

    public void assertEquals(PointEntity expected, PointModelInterface actual) {
        assertEquals(actual, expected);
        Assert.assertEquals(new QuestionModelEmpty(expected.getQuestionId()), actual.getQuestion());
        Assert.assertEquals(new ModelsListEmpty(), actual.getSolutions());
        Assert.assertEquals(new QuizModelEmpty(expected.getQuizId()), actual.getQuiz());
    }

    public void assertModelsListEquals(List<PointModelInterface> expected, List<PointModelInterface> actual) {
        Assert.assertEquals(expected.size(), actual.size());
        Integer i = 0;
        for (PointModelInterface pointModel: expected) {
            this.assertEquals(pointModel, actual.get(i));
            i++;
        }
    }

    public void assertEntitiesListEquals(List<PointEntity> expected, List<PointEntity> actual) {
        Assert.assertEquals(expected.size(), actual.size());
        Integer i = 0;
        for (PointEntity pointEntity: expected) {
            this.assertEquals(pointEntity, actual.get(i));
            i++;
        }
    }
}
