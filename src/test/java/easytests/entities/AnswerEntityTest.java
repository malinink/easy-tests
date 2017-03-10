package easytests.entities;

import easytests.models.AnswerModelInterface;
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
        final Boolean right = true;

        final AnswerModelInterface answerModel = Mockito.mock(AnswerModelInterface.class);
        Mockito.when(answerModel.getId()).thenReturn(id);
        Mockito.when(answerModel.getTxt()).thenReturn(txt);
        Mockito.when(answerModel.getQuestionId()).thenReturn(questionId);
        Mockito.when(answerModel.isRight()).thenReturn(right);

        final AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.map(answerModel);

        Assert.assertEquals(id, answerEntity.getId());
        Assert.assertEquals(txt, answerEntity.getTxt());
        Assert.assertEquals(questionId, answerEntity.getQuestionId());
        Assert.assertEquals(right, answerEntity.isRight());
    }

}
