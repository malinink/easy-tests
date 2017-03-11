package easytests.entities;

import easytests.models.QuestionModelInterface;
import easytests.models.TopicModelInterface;
import org.junit.Assert;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
import org.mockito.Mockito;

/**
 * @author firkhraag
 */
public class QuestionEntityTest {

    @Test
    public void testCommon() throws Exception {
        new BeanTester().testBean(QuestionEntity.class);
        new EqualsMethodTester().testEqualsMethod(QuestionEntity.class);
        new HashCodeMethodTester().testHashCodeMethod(QuestionEntity.class);
    }

    @Test
    public void testMap() throws Exception {
        final Integer questionId = 1;
        final String text = "test1";
        final Integer type = 1;
        final Integer topicId = 1;
        
        final TopicModelInterface topic = Mockito.mock(TopicModelInterface.class);
        Mockito.when(topic.getId()).thenReturn(topicId);

        final QuestionModelInterface questionModel = Mockito.mock(QuestionModelInterface.class);
        Mockito.when(questionModel.getId()).thenReturn(questionId);
        Mockito.when(questionModel.getText()).thenReturn(text);
        Mockito.when(questionModel.getType()).thenReturn(type);
        Mockito.when(questionModel.getTopic()).thenReturn(topic);

        final QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.map(questionModel);

        Assert.assertEquals(questionId, questionEntity.getId());
        Assert.assertEquals(text, questionEntity.getText());
        Assert.assertEquals(type, questionEntity.getType());
        Assert.assertEquals(topicId, questionEntity.getTopicId());
    }
}
