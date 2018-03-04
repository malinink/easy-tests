package easytests.core.models;

import easytests.core.entities.AnswerEntity;
import easytests.support.AnswersSupport;
import org.junit.Test;
import org.meanbean.test.ConfigurationBuilder;


/**
 * @author AnyaMaz
 */
public class AnswerModelTest extends AbstractModelTest {

    private AnswersSupport answersSupport = new AnswersSupport();

    @Override
    protected ConfigurationBuilder getConfigurationBuilder() {
        return super.getConfigurationBuilder()
                .ignoreProperty("question");
    }

    @Test
    public void testCommon() throws Exception {
        this.testCommon(AnswerModel.class);
    }

    @Test
    public void testMap() throws Exception {
        final AnswerEntity answerEntity = this.answersSupport.getEntityFixtureMock(0);
        final AnswerModel answerModel = new AnswerModel();

        answerModel.map(answerEntity);

        this.answersSupport.assertEquals(answerEntity, answerModel);
    }
}
