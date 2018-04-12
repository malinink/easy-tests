package easytests.core.models;

import easytests.core.entities.QuestionEntity;
import easytests.support.QuestionsSupport;
import org.junit.Test;


/**
 * @author RisaMagpie
 */
public class QuestionModelTest extends AbstractModelTest {

    private QuestionsSupport questionsSupport = new QuestionsSupport();

    @Test
    public void testCommon() throws Exception {
        super.testCommon(QuestionModel.class);
    }

    @Test
    public void testMap() throws Exception {
        final QuestionEntity questionEntity = this.questionsSupport.getEntityFixtureMock(0);
        final QuestionModelInterface questionModel = new QuestionModel();

        questionModel.map(questionEntity);

        this.questionsSupport.assertEquals(questionEntity, questionModel);
    }
}
