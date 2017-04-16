package easytests.models;

import easytests.entities.QuestionEntity;
import easytests.models.empty.ModelsListEmpty;
import easytests.models.empty.QuestionTypeModelEmpty;
import easytests.models.empty.TopicModelEmpty;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;
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
        Configuration configuration = new ConfigurationBuilder()
                .ignoreProperty("topic")
                .ignoreProperty("answers")
                .ignoreProperty("questionType")
                .build();
        new BeanTester().testBean(QuestionModel.class, configuration);
    }

    @Test
    public void testMap() throws Exception {
        final Integer questionId = 1;
        final String text = "test1";
        final Integer questionTypeId = 1;
        final Integer topicId = 1;
        final QuestionEntity questionEntity = Mockito.mock(QuestionEntity.class);

        Mockito.when(questionEntity.getId()).thenReturn(questionId);
        Mockito.when(questionEntity.getText()).thenReturn(text);
        Mockito.when(questionEntity.getQuestionTypeId()).thenReturn(questionTypeId);
        Mockito.when(questionEntity.getTopicId()).thenReturn(topicId);

        final QuestionModel questionModel = new QuestionModel();
        questionModel.map(questionEntity);

        Assert.assertEquals(questionId, questionModel.getId());
        Assert.assertEquals(text, questionModel.getText());
        Assert.assertEquals(new QuestionTypeModelEmpty(questionEntity.getQuestionTypeId()), questionModel.getQuestionType());
        Assert.assertEquals(new ModelsListEmpty(), questionModel.getAnswers());
        Assert.assertEquals(new TopicModelEmpty(questionEntity.getTopicId()), questionModel.getTopic());
    }
}
