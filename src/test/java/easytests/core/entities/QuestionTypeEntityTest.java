package easytests.core.entities;

import easytests.core.models.QuestionTypeModelInterface;
import easytests.support.QuestionTypeSupport;
import org.junit.Test;


/**
 * @author VlasovIgor
 */

public class QuestionTypeEntityTest extends AbstractEntityTest {

    private QuestionTypeSupport questiontypesSupport = new QuestionTypeSupport();

    @Test
    public void testCommon() throws Exception {
        this.testCommon(QuestionTypeEntity.class);
    }

    @Test
    public void testMap() throws Exception {
        final QuestionTypeModelInterface questiontypeModel = this.questiontypesSupport.getModelFixtureMock(0);
        final QuestionTypeEntity questiontypeEntity = new QuestionTypeEntity();

        questiontypeEntity.map(questiontypeModel);

        this.questiontypesSupport.assertEquals(questiontypeModel,questiontypeEntity );
    }

}


