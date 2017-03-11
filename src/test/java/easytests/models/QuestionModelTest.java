package easytests.models;

import easytests.entities.QuestionEntity;
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
 * @author firkhraag
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionModelTest {
    @Test
    public void testCommon() throws Exception {
        new BeanTester().testBean(QuestionModel.class);
        new EqualsMethodTester().testEqualsMethod(QuestionModel.class);
        new HashCodeMethodTester().testHashCodeMethod(QuestionModel.class);
    }

    @Test
    public void testMap() throws Exception {
        final Integer questionId = 1;
        final String text = "test1";
        final Integer type = 1;
        final QuestionEntity questionEntity = Mockito.mock(QuestionEntity.class);

        Mockito.when(questionEntity.getId()).thenReturn(questionId);
        Mockito.when(questionEntity.getText()).thenReturn(text);
        Mockito.when(questionEntity.getType()).thenReturn(type);

        final QuestionModel questionModel = new QuestionModel();
        questionModel.map(questionEntity);

        Assert.assertEquals(questionId, questionModel.getId());
        Assert.assertEquals(text, questionModel.getText());
        Assert.assertEquals(type, questionModel.getType());
    }
}
