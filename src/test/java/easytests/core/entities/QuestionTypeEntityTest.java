package easytests.core.entities;

import easytests.core.models.QuestionTypeModelInterface;
import easytests.support.QuestionTypesSupport;
import org.junit.Test;


/**
 * @author VlasovIgor
 */
public class QuestionTypeEntityTest extends AbstractEntityTest {

    private QuestionTypesSupport questionTypesSupport = new QuestionTypesSupport();

    @Test
    public void testCommon() throws Exception {
        this.testCommon(QuestionTypeEntity.class);
    }

    @Test
    public void testMap() throws Exception {
        final QuestionTypeModelInterface questionTypeModel = this.questionTypesSupport.getModelFixtureMock(0);
        final QuestionTypeEntity questionTypeEntity = new QuestionTypeEntity();

        questionTypeEntity.map(questionTypeModel);

        this.questionTypesSupport.assertEquals(questionTypeModel,questionTypeEntity );
    }

}
