package easytests.core.entities;

import easytests.core.models.AnswerModelInterface;
import easytests.core.models.QuestionModelInterface;
import org.junit.Assert;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
import org.mockito.Mockito;

/**
 * @author rezenbekk
 */
public class AnswerEntityTest {
    @Test
    public void testCommon() throws Exception {
        new BeanTester().testBean(AnswerEntity.class);
        new EqualsMethodTester().testEqualsMethod(AnswerEntity.class);
        new HashCodeMethodTester().testHashCodeMethod(AnswerEntity.class);
    }

    @Test
    public void testMap() throws Exception {
        final Integer id = 3;
        final String txt = "Text";
        final Integer questionId = 5;
        final Integer serialNumber = 3;
        final Boolean right = true;

        final QuestionModelInterface questionModel = Mockito.mock(QuestionModelInterface.class);
        Mockito.when(questionModel.getId()).thenReturn(questionId);

        final AnswerModelInterface answerModel = Mockito.mock(AnswerModelInterface.class);
        Mockito.when(answerModel.getId()).thenReturn(id);
        Mockito.when(answerModel.getTxt()).thenReturn(txt);
        Mockito.when(answerModel.getQuestion()).thenReturn(questionModel);
        Mockito.when(answerModel.getSerialNumber()).thenReturn(serialNumber);
        Mockito.when(answerModel.getRight()).thenReturn(right);

        final AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.map(answerModel);

        Assert.assertEquals(id, answerEntity.getId());
        Assert.assertEquals(txt, answerEntity.getTxt());
        Assert.assertEquals(questionId, answerEntity.getQuestionId());
        Assert.assertEquals(serialNumber, answerEntity.getSerialNumber());
        Assert.assertEquals(right, answerEntity.getRight());
    }

}
