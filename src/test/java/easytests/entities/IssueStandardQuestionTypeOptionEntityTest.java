package easytests.entities;

import easytests.models.IssueStandardModelInterface;
import easytests.models.IssueStandardQuestionTypeOptionModelInterface;
import org.junit.Assert;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
import org.mockito.Mockito;

/**
 * @author SingularityA
 */
public class IssueStandardQuestionTypeOptionEntityTest {
    @Test
    public void testCommon() throws Exception {
        new BeanTester().testBean(IssueStandardQuestionTypeOptionEntity.class);
        new EqualsMethodTester().testEqualsMethod(IssueStandardQuestionTypeOptionEntity.class);
        new HashCodeMethodTester().testHashCodeMethod(IssueStandardQuestionTypeOptionEntity.class);
    }

    @Test
    public void testMap() throws Exception {
        final Integer id = 3;
        final Integer questionTypeId = 5;
        final Integer minQuestions = 10;
        final Integer maxQuestions = 20;
        final Integer timeLimit = 600;
        final Integer issueStandardId = 12;

        final IssueStandardModelInterface issueStandard = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandard.getId()).thenReturn(issueStandardId);

        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel
                = Mockito.mock(IssueStandardQuestionTypeOptionModelInterface.class);

        Mockito.when(questionTypeOptionModel.getId()).thenReturn(id);
        Mockito.when(questionTypeOptionModel.getQuestionTypeId()).thenReturn(questionTypeId);
        Mockito.when(questionTypeOptionModel.getMinQuestions()).thenReturn(minQuestions);
        Mockito.when(questionTypeOptionModel.getMaxQuestions()).thenReturn(maxQuestions);
        Mockito.when(questionTypeOptionModel.getTimeLimit()).thenReturn(timeLimit);
        Mockito.when(questionTypeOptionModel.getIssueStandard()).thenReturn(issueStandard);

        final IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity
                = new IssueStandardQuestionTypeOptionEntity();
        questionTypeOptionEntity.map(questionTypeOptionModel);

        Assert.assertEquals(id, questionTypeOptionEntity.getId());
        Assert.assertEquals(questionTypeId, questionTypeOptionEntity.getQuestionTypeId());
        Assert.assertEquals(minQuestions, questionTypeOptionEntity.getMinQuestions());
        Assert.assertEquals(maxQuestions, questionTypeOptionEntity.getMaxQuestions());
        Assert.assertEquals(timeLimit, questionTypeOptionEntity.getTimeLimit());
        Assert.assertEquals(issueStandardId, questionTypeOptionEntity.getIssueStandardId());
    }
}
