package easytests.support;

import easytests.core.entities.QuestionTypeEntity;
import easytests.core.models.QuestionTypeModelInterface;

import java.util.List;

import org.junit.Assert;
import org.mockito.Mockito;


/**
 * @author Vlasovigor
 */
public class QuestionTypesSupport {

    protected static Object[][] fixtures = new Object[][]{
            {
                    1,
                    "Один ответ",
            },
            {
                    2,
                    "Много ответов",
            },
            {
                    3,
                    "Нумерация",
            },
            {
                    4,
                    "Текст",
            },
    };

    public QuestionTypeEntity getEntityFixtureMock(Integer index) {
        return this.getEntityMock(fixtures[index]);
    }

    private QuestionTypeEntity getEntityMock(Object[] data) {
        return this.getEntityMock(
                (Integer) data[0],
                (String) data[1]
        );
    }

    private QuestionTypeEntity getEntityMock(
            Integer id,
            String name
    ) {
        final QuestionTypeEntity questionTypeEntity = Mockito.mock(QuestionTypeEntity.class);
        Mockito.when(questionTypeEntity.getId()).thenReturn(id);
        Mockito.when(questionTypeEntity.getName()).thenReturn(name);
        return questionTypeEntity;
    }

    public QuestionTypeModelInterface getModelFixtureMock(Integer index) {
        return this.getModelMock(fixtures[index]);
    }

    private QuestionTypeModelInterface getModelMock(Object[] data) {
        return this.getModelMock(
                (Integer) data[0],
                (String) data[1]
        );
    }

    private QuestionTypeModelInterface getModelMock(
            Integer id,
            String name
    ) {
        final QuestionTypeModelInterface questionTypeModel = Mockito.mock(QuestionTypeModelInterface.class);
        Mockito.when(questionTypeModel.getId()).thenReturn(id);
        Mockito.when(questionTypeModel.getName()).thenReturn(name);
        return questionTypeModel;
    }

    public void assertEquals(QuestionTypeEntity expected, QuestionTypeEntity actual) {
        assertEquals(expected, actual, false);
    }

    public void assertEqualsWithoutId(QuestionTypeEntity expected, QuestionTypeEntity actual) {
        assertEquals(expected, actual, true);
    }

    private void assertEquals(QuestionTypeEntity expected, QuestionTypeEntity actual, Boolean expectId) {
        if (!expectId) {
            Assert.assertEquals(expected.getId(), actual.getId());
        }
        Assert.assertEquals(expected.getName(), actual.getName());
    }

    public void assertNotEquals(QuestionTypeEntity unexpected, QuestionTypeEntity actual) {
        assertNotEquals(unexpected, actual, false);
    }

    public void assertNotEqualsWithoutId(QuestionTypeEntity unexpected, QuestionTypeEntity actual) {
        assertEquals(unexpected, actual, true);
    }

    private void assertNotEquals(QuestionTypeEntity unexpected, QuestionTypeEntity actual, boolean expectedId) {
        if (!expectedId) {
            Assert.assertNotEquals(unexpected.getId(), actual.getId());
        }
        Assert.assertNotEquals(unexpected.getName(), actual.getName());
    }

    public void assertEquals(QuestionTypeModelInterface expected, QuestionTypeModelInterface actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
    }

    public void assertEquals(QuestionTypeModelInterface expected, QuestionTypeEntity actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
    }

    public void assertEquals(QuestionTypeEntity expected, QuestionTypeModelInterface actual) {
        assertEquals(actual, expected);
    }

    public void assertModelsListEquals(List<QuestionTypeModelInterface> expected, List<QuestionTypeModelInterface> actual) {
        Assert.assertEquals(expected.size(), actual.size());
        Integer i = 0;
        for (QuestionTypeModelInterface questionTypeModel : expected) {
            this.assertEquals(questionTypeModel, actual.get(i));
            i++;
        }
    }

    public void assertEntitiesListEquals(List<QuestionTypeEntity> expected, List<QuestionTypeEntity> actual) {
        Assert.assertEquals(expected.size(), actual.size());
        Integer i = 0;
        for (QuestionTypeEntity questionTypeEntity : expected) {
            this.assertEquals(questionTypeEntity, actual.get(i));
            i++;
        }
    }
}
