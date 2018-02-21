package easytests.core.models;

import easytests.core.entities.QuestionEntity;
import easytests.support.QuestionsSupport;
import org.junit.Test;
import org.meanbean.test.ConfigurationBuilder;

/**
 * @author risa_magpie
 */
public class QuestionModelTest extends AbstractModelTest {

    private QuestionsSupport questionsSupport = new QuestionsSupport();

    @Override
    protected ConfigurationBuilder getConfigurationBuilder() {
        return super.getConfigurationBuilder()
                .ignoreProperty("questionType")
                .ignoreProperty("topic")
                .ignoreProperty("answers");
    }

    @Test
    public void testCommon() throws Exception {
        this.testCommon(QuestionModel.class);
    }

    @Test
    public void testMap() throws Exception {
        final QuestionEntity questionEntity = this.questionsSupport.getEntityFixtureMock(0);
        final QuestionModelInterface questionModel = new QuestionModel();
        questionModel.map(questionEntity);

        this.questionsSupport.assertEquals(questionEntity, questionModel);
    }
}