package easytests.core.models;

import easytests.core.entities.QuizEntity;
import easytests.support.QuizzesSupport;
import org.junit.Test;
import org.meanbean.test.ConfigurationBuilder;


/**
 * @author miron97
 */
public class QuizModelTest extends AbstractModelTest {

    private QuizzesSupport quizzesSupport = new QuizzesSupport();

    @Override
    protected ConfigurationBuilder getConfigurationBuilder() {
        return super.getConfigurationBuilder()
                .ignoreProperty("issue")
                .ignoreProperty("testee");
    }

    @Test
    public void testCommon() throws Exception {
        this.testCommon(QuizModel.class);
    }

    @Test
    public void testMap() throws Exception {
        final QuizEntity quizEntity = this.quizzesSupport.getEntityFixtureMock(0);
        final QuizModel quizModel = new QuizModel();

        quizModel.map(quizEntity);

        this.quizzesSupport.assertEquals(quizEntity, quizModel);
    }

}
