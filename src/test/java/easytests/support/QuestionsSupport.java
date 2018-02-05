package easytests.support;

import easytests.core.entities.QuestionEntity;
import easytests.core.models.QuestionModelInterface;
import easytests.core.models.empty.*;
import org.junit.Assert;
import org.mockito.Mockito;


/**
 * @author RisaMagpie
 */
public class QuestionsSupport {
    protected static Object[][] fixtures = new Object[][]{
            {
                    1,
                    "test1",
                    1,
                    1
            },
            {
                    2,
                    "test2",
                    2,
                    3
            },
            {
                    3,
                    "test3",
                    3,
                    2
            },
    };
    protected static Object[][] additional = new Object[][]{
            {
                    // for insert entity
                    null,
                    "text1",
                    1,
                    1
            },
            {
                    // for update entity with id = 1
                    1,
                    "text2",
                    2,
                    2
            },
    };

    public QuestionEntity getEntityFixtureMock(Integer index) {
        return this.getEntityMock(fixtures[index]);
    }

    public QuestionEntity getEntityAdditionalMock(Integer index) {
        return this.getEntityMock(additional[index]);
    }

    private QuestionEntity getEntityMock(Object[] data) {
        return this.getEntityMock(
                (Integer) data[0],
                (String) data[1],
                (Integer) data[2],
                (Integer) data[3]
        );
    }

    private QuestionEntity getEntityMock(
            Integer id,
            String text,
            Integer questionTypeId,
            Integer topicId) {
        final QuestionEntity questionEntity = Mockito.mock(QuestionEntity.class);
        Mockito.when(questionEntity.getId()).thenReturn(id);
        Mockito.when(questionEntity.getText()).thenReturn(text);
        Mockito.when(questionEntity.getQuestionTypeId()).thenReturn(questionTypeId);
        Mockito.when(questionEntity.getTopicId()).thenReturn(topicId);
        return questionEntity;
    }

    public QuestionModelInterface getModelFixtureMock(Integer index) {
        return this.getModelMock(fixtures[index]);
    }

    public QuestionModelInterface getModelAdditionalMock(Integer index) {
        return this.getModelMock(additional[index]);
    }

    private QuestionModelInterface getModelMock(Object[] data) {
        return this.getModelMock(
                (Integer) data[0],
                (String) data[1],
                (Integer) data[2],
                (Integer) data[3]
        );
    }

    private QuestionModelInterface getModelMock(
            Integer id,
            String text,
            Integer questionTypeId,
            Integer topicId
    ) {
        final QuestionModelInterface questionModel = Mockito.mock(QuestionModelInterface.class);
        Mockito.when(questionModel.getId()).thenReturn(id);
        Mockito.when(questionModel.getText()).thenReturn(text);
        Mockito.when(questionModel.getQuestionType()).thenReturn(new QuestionTypeModelEmpty(questionTypeId));
        Mockito.when(questionModel.getTopic()).thenReturn(new TopicModelEmpty(topicId));
        Mockito.when(questionModel.getAnswers()).thenReturn(new ModelsListEmpty());
        return questionModel;
    }

    public void assertEquals(QuestionEntity expected, QuestionEntity actual) {
        assertEquals(expected, actual, false);
    }

    public void assertEqualsWithoutId(QuestionEntity expected, QuestionEntity actual) {
        assertEquals(expected, actual, true);
    }

    private void assertEquals(QuestionEntity expected, QuestionEntity actual, Boolean exceptId) {
        if (!exceptId) {
            Assert.assertEquals(expected.getId(), actual.getId());
        }
        Assert.assertEquals(expected.getText(), actual.getText());
        Assert.assertEquals(expected.getQuestionTypeId(), actual.getQuestionTypeId());
        Assert.assertEquals(expected.getTopicId(), actual.getTopicId());
    }

    public void assertNotEquals(QuestionEntity unexpected, QuestionEntity actual) {
        assertNotEquals(unexpected, actual, false);
    }

    public void assertNotEqualsWithoutId(QuestionEntity unexpected, QuestionEntity actual) {
        assertNotEquals(unexpected, actual, true);
    }

    private void assertNotEquals(QuestionEntity unexpected, QuestionEntity actual, Boolean exceptId) {
        if (!exceptId) {
            Assert.assertNotEquals(unexpected.getId(), actual.getId());
        }
        Assert.assertNotEquals(unexpected.getText(), actual.getText());
        Assert.assertNotEquals(unexpected.getQuestionTypeId(), actual.getQuestionTypeId());
        Assert.assertNotEquals(unexpected.getTopicId(), actual.getTopicId());
    }

    public void assertEquals(QuestionModelInterface expected, QuestionModelInterface actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getText(), actual.getText());
        Assert.assertNotEquals(expected.getQuestionType(), actual.getQuestionType());
        Assert.assertEquals(expected.getTopic(), actual.getTopic());
        Assert.assertEquals(expected.getAnswers(), actual.getAnswers());
    }

    public void assertEquals(QuestionModelInterface expected, QuestionEntity actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getText(), actual.getText());
        Assert.assertEquals(expected.getQuestionType().getId(), actual.getQuestionTypeId());
        Assert.assertEquals(expected.getTopic().getId(), actual.getTopicId());
    }

    public void assertEquals(QuestionEntity expected, QuestionModelInterface actual) {
        assertEquals(actual, expected);
        Assert.assertEquals(new QuestionModelEmpty(expected.getQuestionTypeId()), actual.getQuestionType());/**Do we need it?**/
        Assert.assertEquals(new ModelsListEmpty(), actual.getAnswers());
    }

}
