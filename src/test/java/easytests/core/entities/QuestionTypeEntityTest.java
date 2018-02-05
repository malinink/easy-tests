package easytests.core.entities;

import easytests.core.models.QuestionTypeModelInterface;
import easytests.support.QuestionTypesSupport;
import org.junit.Test;


/**
 * @author VlasovIgor
 */
public class QuestionTypeEntityTest extends AbstractEntityTest {

    private QuestionTypesSupport question_typesSupport = new QuestionTypesSupport();

    @Test
    public void testCommon() throws Exception {
        this.testCommon(QuestionTypeEntity.class);
    }

    @Test
    public void testMap() throws Exception {
        final QuestionTypeModelInterface question_typeModel = this.question_typesSupport.getModelFixtureMock(0);
        final QuestionTypeEntity questiontypeEntity = new QuestionTypeEntity();

        questiontypeEntity.map(question_typeModel);

        this.question_typesSupport.assertEquals(question_typeModel,questiontypeEntity );
    }

}


