package easytests.models;

import easytests.entities.AnswerEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author rezenbekk
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AnswerModelTest {
    @Test
    public void testCommon() throws Exception {
        new BeanTester().testBean(AnswerModel.class);
        new EqualsMethodTester().testEqualsMethod(AnswerModel.class);
        new HashCodeMethodTester().testHashCodeMethod(AnswerModel.class);
    }

    @Test
    public void testMap() throws Exception {
        final Integer id = 3;
        final String txt = "Text";
        final Integer questionId = 5;
        final Boolean right = true;
        final AnswerEntity answerEntity = Mockito.mock(AnswerEntity.class);

        Mockito.when(answerEntity.getId()).thenReturn(id);
        Mockito.when(answerEntity.getTxt()).thenReturn(txt);
        Mockito.when(answerEntity.getQuestionId()).thenReturn(questionId);
        Mockito.when(answerEntity.isRight()).thenReturn(right);

        final AnswerModel answerModel = new AnswerModel();
        answerModel.map(answerEntity);

        Assert.assertEquals(id, answerModel.getId());
        Assert.assertEquals(txt, answerModel.getTxt());
        Assert.assertEquals(questionId, answerModel.getQuestionId());
        Assert.assertEquals(right, answerModel.isRight());
    }
}

