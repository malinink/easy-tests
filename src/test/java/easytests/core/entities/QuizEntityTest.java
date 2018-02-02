package easytests.core.entities;

import easytests.core.models.QuizModelInterface;
import easytests.support.QuizzesSupport;
import org.junit.Test;


/**
 * @author miron97
 */
public class QuizEntityTest extends AbstractEntityTest {

    private QuizzesSupport quizSupport = new QuizzesSupport();

    @Test
    public void testCommon() throws Exception {
        this.testCommon(QuizEntity.class);
    }

    @Test
    public void testMap() throws Exception {
        final QuizModelInterface quizModel = this.quizSupport.getModelFixtureMock(0);
        final QuizEntity quizEntity = new QuizEntity();

        quizEntity.map(quizModel);

        this.quizSupport.assertEquals(quizModel, quizEntity);
    }

}
