package easytests.support;

import easytests.core.entities.AnswerEntity;
import easytests.core.models.AnswerModelInterface;
import easytests.core.models.empty.ModelsListEmpty;
import easytests.core.models.empty.QuestionModelEmpty;
import org.junit.Assert;
import org.mockito.Mockito;

/**
 * @author sakhprace
 */
public class AnswersSupport {

    protected static Object[][] fixtures = new Object[][] {
            {
                    1,
                    "Answer1",
                    1,
                    1,
                    true

            },
            {
                    2,
                    "Answer2",
                    2,
                    2,
                    false
            },
            {
                    3,
                    "Answer3",
                    1,
                    3,
                    true
            },
    };

    protected static Object[][] additional = new Object[][] {
            {
                    // for insert entity
                    null,
                    "Answer",
                    1,
                    1,
                    true
            },
            {
                    // for update entity with id = 1
                    1,
                    "NewAnswer",
                    2,
                    2,
                    false
            },
    };

    public AnswerEntity getEntityFixtureMock(Integer index) {
        return this.getEntityMock(fixtures[index]);
    }

    public AnswerEntity getEntityAdditionalMock(Integer index) {
        return this.getEntityMock(additional[index]);
    }

    private AnswerEntity getEntityMock(Object[] data) {
        return this.getEntityMock(
                (Integer) data[0],
                (String) data[1],
                (Integer) data[2],
                (Integer) data[3],
                (Boolean) data[4]
        );
    }

    private AnswerEntity getEntityMock(
            Integer id,
            String txt,
            Integer questionId,
            Integer serialNumber,
            Boolean right

    ) {
        final AnswerEntity answerEntity = Mockito.mock(AnswerEntity.class);
        Mockito.when(answerEntity.getId()).thenReturn(id);
        Mockito.when(answerEntity.getTxt()).thenReturn(txt);
        Mockito.when(answerEntity.getQuestionId()).thenReturn(questionId);
        Mockito.when(answerEntity.getSerialNumber()).thenReturn(serialNumber);
        Mockito.when(answerEntity.getRight()).thenReturn(right);
        return answerEntity;

    }

    public AnswerModelInterface getModelFixtureMock(Integer index) {
        return this.getModelMock(fixtures[index]);
    }

    public AnswerModelInterface getModelAdditionalMock(Integer index) {
        return this.getModelMock(additional[index]);
    }

    private AnswerModelInterface getModelMock(Object[] data) {
        return this.getModelMock(
                (Integer) data[0],
                (String) data[1],
                (Integer) data[2],
                (Integer) data[3],
                (Boolean) data[4]
        );
    }

    private AnswerModelInterface getModelMock(
            Integer id,
            String txt,
            Integer questionId,
            Integer serialNumber,
            Boolean right
    ) {
        final AnswerModelInterface answerModel = Mockito.mock(AnswerModelInterface.class);
        Mockito.when(answerModel.getId()).thenReturn(id);
        Mockito.when(answerModel.getTxt()).thenReturn(txt);
        Mockito.when(answerModel.getQuestion()).thenReturn(new QuestionModelEmpty(questionId));
        Mockito.when(answerModel.getSerialNumber()).thenReturn(serialNumber);
        Mockito.when(answerModel.getRight()).thenReturn(right);
        return answerModel;
    }

    public void assertEquals(AnswerEntity expected, AnswerEntity actual) {
        assertEquals(expected, actual, false);
    }

    public void assertEqualsWithoutId(AnswerEntity expected, AnswerEntity actual) {
        assertEquals(expected, actual, true);
    }

    private void assertEquals(AnswerEntity expected, AnswerEntity actual, Boolean exceptId) {
        if (!exceptId) {
            Assert.assertEquals(expected.getId(), actual.getId());
        }
        Assert.assertEquals(expected.getTxt(), actual.getTxt());
        Assert.assertEquals(expected.getQuestionId(), actual.getQuestionId());
        Assert.assertEquals(expected.getSerialNumber(), actual.getSerialNumber());
        Assert.assertEquals(expected.getRight(), actual.getRight());
    }

    public void assertNotEquals(AnswerEntity unexpected, AnswerEntity actual) {
        assertNotEquals(unexpected, actual, false);
    }

    public void assertNotEqualsWithoutId(AnswerEntity unexpected, AnswerEntity actual) {
        assertNotEquals(unexpected, actual, true);
    }

    private void assertNotEquals(AnswerEntity unexpected, AnswerEntity actual, Boolean exceptId) {
        if (!exceptId) {
            Assert.assertNotEquals(unexpected.getId(), actual.getId());
        }
        Assert.assertNotEquals(unexpected.getTxt(), actual.getTxt());
        Assert.assertNotEquals(unexpected.getQuestionId(), actual.getQuestionId());
        Assert.assertNotEquals(unexpected.getSerialNumber(), actual.getSerialNumber());
        Assert.assertNotEquals(unexpected.getRight(), actual.getRight());

    }

    public void assertEquals(AnswerModelInterface expected, AnswerModelInterface actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getTxt(), actual.getTxt());
        Assert.assertEquals(expected.getQuestion(), actual.getQuestion());
        Assert.assertEquals(expected.getSerialNumber(), actual.getSerialNumber());
        Assert.assertEquals(expected.getRight(), actual.getRight());

    }

    public void assertEquals(AnswerModelInterface expected, AnswerEntity actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getTxt(), actual.getTxt());
        Assert.assertEquals(expected.getQuestion().getId(), actual.getQuestionId());
        Assert.assertEquals(expected.getSerialNumber(), actual.getSerialNumber());
        Assert.assertEquals(expected.getRight(), actual.getRight());

    }

    public void assertEquals(AnswerEntity expected, AnswerModelInterface actual) {
        assertEquals(actual, expected);
        Assert.assertEquals(new ModelsListEmpty(), actual.getQuestion());
    }

}
