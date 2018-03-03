package easytests.support;

import easytests.core.entities.IssueStandardQuestionTypeOptionEntity;
import easytests.core.models.IssueStandardQuestionTypeOptionModelInterface;
import easytests.core.models.empty.IssueStandardModelEmpty;
import easytests.core.models.empty.QuestionTypeModelEmpty;
import org.junit.Assert;
import org.mockito.Mockito;


/**
 * @author VeronikaRevjakina
 */
public class IssueStandardQuestionTypeOptionsSupport {

    protected static Object[][] fixtures = new Object[][]{
            {
                    1,
                    1,
                    1,
                    null,
                    null,
                    1
            },
            {
                    2,
                    2,
                    null,
                    5,
                    null,
                    1
            },
            {
                    3,
                    3,
                    5,
                    10,
                    null,
                    1
            },
            {
                    4,
                    1,
                    1,
                    5,
                    120,
                    2
            },
            {
                    5,
                    3,
                    5,
                    null,
                    300,
                    2
            },
    };

    protected static Object[][] additional = new Object[][]{
            {
                    // for insert entity
                    null,
                    4,
                    2,
                    6,
                    150,
                    1

            },
            {
                    // for update entity with id = 1
                    1,
                    3,
                    2,
                    7,
                    200,
                    3
            },
    };

    public IssueStandardQuestionTypeOptionEntity getEntityFixtureMock(Integer index) {
        return this.getEntityMock(fixtures[index]);
    }


    public IssueStandardQuestionTypeOptionEntity getEntityAdditionalMock(Integer index) {
        return this.getEntityMock(additional[index]);
    }


    private IssueStandardQuestionTypeOptionEntity getEntityMock(Object[] data) {
        return this.getEntityMock(
                (Integer) data[0],
                (Integer) data[1],
                (Integer) data[2],
                (Integer) data[3],
                (Integer) data[4],
                (Integer) data[5]
        );
    }

    private IssueStandardQuestionTypeOptionEntity getEntityMock(
            Integer id,
            Integer questionType,
            Integer minQuestions,
            Integer maxQuestions,
            Integer timeLimit,
            Integer issueStandardId
    ) {
        final IssueStandardQuestionTypeOptionEntity issueStandardQuestionTypeOptionEntity = Mockito.mock(IssueStandardQuestionTypeOptionEntity.class);
        Mockito.when(issueStandardQuestionTypeOptionEntity.getId()).thenReturn(id);
        Mockito.when(issueStandardQuestionTypeOptionEntity.getQuestionTypeId()).thenReturn(questionType);
        Mockito.when(issueStandardQuestionTypeOptionEntity.getMinQuestions()).thenReturn(minQuestions);
        Mockito.when(issueStandardQuestionTypeOptionEntity.getMaxQuestions()).thenReturn(maxQuestions);
        Mockito.when(issueStandardQuestionTypeOptionEntity.getTimeLimit()).thenReturn(timeLimit);
        Mockito.when(issueStandardQuestionTypeOptionEntity.getIssueStandardId()).thenReturn(issueStandardId);
        return issueStandardQuestionTypeOptionEntity;
    }

    public IssueStandardQuestionTypeOptionModelInterface getModelFixtureMock(Integer index) {
        return this.getModelMock(fixtures[index]);
    }

    public IssueStandardQuestionTypeOptionModelInterface getModelAdditionalMock(Integer index) {
        return this.getModelMock(additional[index]);
    }

    private IssueStandardQuestionTypeOptionModelInterface getModelMock(Object[] data) {
        return this.getModelMock(
                (Integer) data[0],
                (Integer) data[1],
                (Integer) data[2],
                (Integer) data[3],
                (Integer) data[4],
                (Integer) data[5]
        );
    }

    private IssueStandardQuestionTypeOptionModelInterface getModelMock(
            Integer id,
            Integer questionType,
            Integer minQuestions,
            Integer maxQuestions,
            Integer timeLimit,
            Integer issueStandardId
    ) {
        final IssueStandardQuestionTypeOptionModelInterface issueStandardQuestionTypeOptionModel = Mockito.mock(IssueStandardQuestionTypeOptionModelInterface.class);
        Mockito.when(issueStandardQuestionTypeOptionModel.getId()).thenReturn(id);
        Mockito.when(issueStandardQuestionTypeOptionModel.getQuestionType()).thenReturn(new QuestionTypeModelEmpty(questionType));
        Mockito.when(issueStandardQuestionTypeOptionModel.getMinQuestions()).thenReturn(minQuestions);
        Mockito.when(issueStandardQuestionTypeOptionModel.getMaxQuestions()).thenReturn(maxQuestions);
        Mockito.when(issueStandardQuestionTypeOptionModel.getTimeLimit()).thenReturn(timeLimit);
        Mockito.when(issueStandardQuestionTypeOptionModel.getIssueStandard()).thenReturn(new IssueStandardModelEmpty(issueStandardId));
        return issueStandardQuestionTypeOptionModel;
    }

    public void assertEquals(IssueStandardQuestionTypeOptionEntity expected, IssueStandardQuestionTypeOptionEntity actual) {
        assertEquals(expected, actual, false);
    }

    public void assertEqualsWithoutId(IssueStandardQuestionTypeOptionEntity expected, IssueStandardQuestionTypeOptionEntity actual) {
        assertEquals(expected, actual, true);
    }

    private void assertEquals(IssueStandardQuestionTypeOptionEntity expected, IssueStandardQuestionTypeOptionEntity actual, Boolean exceptId) {
        if (!exceptId) {
            Assert.assertEquals(expected.getId(), actual.getId());
        }
        Assert.assertEquals(expected.getQuestionTypeId(), actual.getQuestionTypeId());
        Assert.assertEquals(expected.getMinQuestions(), actual.getMinQuestions());
        Assert.assertEquals(expected.getMaxQuestions(), actual.getMaxQuestions());
        Assert.assertEquals(expected.getTimeLimit(), actual.getTimeLimit());
        Assert.assertEquals(expected.getIssueStandardId(), actual.getIssueStandardId());
    }

    public void assertNotEquals(IssueStandardQuestionTypeOptionEntity unexpected, IssueStandardQuestionTypeOptionEntity actual) {
        assertNotEquals(unexpected, actual, false);
    }

    public void assertNotEqualsWithoutId(IssueStandardQuestionTypeOptionEntity unexpected, IssueStandardQuestionTypeOptionEntity actual) {
        assertNotEquals(unexpected, actual, true);
    }

    private void assertNotEquals(IssueStandardQuestionTypeOptionEntity unexpected, IssueStandardQuestionTypeOptionEntity actual, Boolean exceptId) {
        if (!exceptId) {
            Assert.assertNotEquals(unexpected.getId(), actual.getId());
        }
        Assert.assertNotEquals(unexpected.getQuestionTypeId(), actual.getQuestionTypeId());
        Assert.assertNotEquals(unexpected.getMinQuestions(), actual.getMinQuestions());
        Assert.assertNotEquals(unexpected.getMaxQuestions(), actual.getMaxQuestions());
        Assert.assertNotEquals(unexpected.getTimeLimit(), actual.getTimeLimit());
        Assert.assertNotEquals(unexpected.getIssueStandardId(), actual.getIssueStandardId());
    }

    public void assertEquals(IssueStandardQuestionTypeOptionModelInterface expected, IssueStandardQuestionTypeOptionModelInterface actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getQuestionType(), actual.getQuestionType());
        Assert.assertEquals(expected.getMinQuestions(), actual.getMinQuestions());
        Assert.assertEquals(expected.getMaxQuestions(), actual.getMaxQuestions());
        Assert.assertEquals(expected.getTimeLimit(), actual.getTimeLimit());
        Assert.assertEquals(expected.getIssueStandard(), actual.getIssueStandard());
    }

    public void assertEquals(IssueStandardQuestionTypeOptionModelInterface expected, IssueStandardQuestionTypeOptionEntity actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getQuestionType().getId(), actual.getQuestionTypeId());
        Assert.assertEquals(expected.getMinQuestions(), actual.getMinQuestions());
        Assert.assertEquals(expected.getMaxQuestions(), actual.getMaxQuestions());
        Assert.assertEquals(expected.getTimeLimit(), actual.getTimeLimit());
        Assert.assertEquals(expected.getIssueStandard().getId(), actual.getIssueStandardId());
    }

    public void assertEquals(IssueStandardQuestionTypeOptionEntity expected, IssueStandardQuestionTypeOptionModelInterface actual) {
        assertEquals(actual, expected);
        Assert.assertEquals(new QuestionTypeModelEmpty(expected.getQuestionTypeId()), actual.getQuestionType());
        Assert.assertEquals(new IssueStandardModelEmpty(expected.getIssueStandardId()), actual.getIssueStandard());
    }
}
