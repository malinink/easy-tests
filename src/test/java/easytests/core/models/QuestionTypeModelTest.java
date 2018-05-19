package easytests.core.models;

import easytests.core.entities.QuestionTypeEntity;
import easytests.support.QuestionTypesSupport;
import org.junit.Test;


/**
 * @author VlasovIgor
 */
public class QuestionTypeModelTest extends AbstractModelTest {

    private QuestionTypesSupport questionTypesSupport = new QuestionTypesSupport();

    @Test
    public void testCommon() throws Exception {
        super.testCommon(QuestionTypeModel.class);
    }

    @Test
    public void testMap() throws Exception {
        final QuestionTypeEntity questionTypeEntity = this.questionTypesSupport.getEntityFixtureMock(0);
        final QuestionTypeModel questionTypeModel = new QuestionTypeModel();

        questionTypeModel.map(questionTypeEntity);

        this.questionTypesSupport.assertEquals(questionTypeEntity, questionTypeModel);
    }
}
