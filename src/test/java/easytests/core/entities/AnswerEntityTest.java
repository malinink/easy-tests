package easytests.core.entities;

import easytests.core.models.AnswerModelInterface;
import easytests.support.AnswersSupport;
import org.junit.Test;


/**
 * @author sakhprace
 */
public class AnswerEntityTest extends AbstractEntityTest {

    private AnswersSupport answersSupport = new AnswersSupport();

    @Test
    public void testCommon() throws Exception {
        this.testCommon(AnswerEntity.class);
    }

    @Test
    public void testMap() throws Exception {
        final AnswerModelInterface answerModel = this.answersSupport.getModelFixtureMock(0);
        final AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.map(answerModel);

        this.answersSupport.assertEquals(answerModel, answerEntity);
    }

}
