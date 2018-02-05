package easytests.core.entities;

import easytests.core.models.QuestionModelInterface;
import easytests.core.models.QuestionTypeModelInterface;
import easytests.core.models.TopicModelInterface;
import org.junit.Assert;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.mockito.Mockito;

/**
 * @author firkhraag
 */
public class QuestionEntityTest {

    @Test
    public void testCommon() throws Exception {
        new BeanTester().testBean(QuestionEntity.class);
    }

    @Test
    public void testMap() throws Exception {
        final Integer questionId = 1;
        final String text = "test1";
        final Integer questionTypeId = 1;
        final Integer topicId = 1;

        final TopicModelInterface topic = Mockito.mock(TopicModelInterface.class);
        Mockito.when(topic.getId()).thenReturn(topicId);

        final QuestionTypeModelInterface questionType = Mockito.mock(QuestionTypeModelInterface.class);
        Mockito.when(questionType.getId()).thenReturn(questionTypeId);

        final QuestionModelInterface questionModel = Mockito.mock(QuestionModelInterface.class);
        Mockito.when(questionModel.getId()).thenReturn(questionId);
        Mockito.when(questionModel.getText()).thenReturn(text);
        Mockito.when(questionModel.getQuestionType()).thenReturn(questionType);
        Mockito.when(questionModel.getTopic()).thenReturn(topic);

        final QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.map(questionModel);

        Assert.assertEquals(questionId, questionEntity.getId());
        Assert.assertEquals(text, questionEntity.getText());
        Assert.assertEquals(questionTypeId, questionEntity.getQuestionTypeId());
        Assert.assertEquals(topicId, questionEntity.getTopicId());
    }
}
