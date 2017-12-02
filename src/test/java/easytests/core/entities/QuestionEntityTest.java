package easytests.core.entities;

import easytests.core.models.QuestionModelInterface;
import easytests.support.QuestionsSupport;
import org.junit.Test;


/**
 * @author RisaMagpie
 */
public class QuestionEntityTest extends AbstractEntityTest {

    protected QuestionsSupport questionsSupport = new QuestionsSupport();

    @Test
    public void testCommon() throws Exception {
        this.testCommon(QuestionEntity.class);
    }

    @Test
    public void testMap() throws Exception {
        final QuestionModelInterface questionModel = this.questionsSupport.getModelFixtureMock(0);
        final QuestionEntity questionEntity = new QuestionEntity();

        questionEntity.map(questionModel);

        this.questionsSupport.assertEquals(questionModel, questionEntity);
    }
}
