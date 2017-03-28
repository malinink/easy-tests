package easytests.models;

import easytests.entities.IssueStandardQuestionTypeOptionEntity;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.*;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author SingularityA
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IssueStandardQuestionTypeOptionModelTest {
    @Ignore
    @Test
    public void testCommon() throws Exception {
        Configuration configuration = new ConfigurationBuilder()
                .ignoreProperty("issueStandard")
                .build();
        // TODO: attempt to create specific factory
        // testBean - passes
        new BeanTester().testBean(IssueStandardQuestionTypeOptionModel.class, configuration);
        new EqualsMethodTester().testEqualsMethod(IssueStandardQuestionTypeOptionModel.class, configuration);
        new HashCodeMethodTester().testHashCodeMethod(IssueStandardQuestionTypeOptionModel.class);
    }

    @Test
    public void testMap() throws Exception {
        final Integer id = 3;
        final Integer questionTypeId = 5;
        final Integer minQuestions = 10;
        final Integer maxQuestions = 20;
        final Integer timeLimit = 60;
        final Integer issueStandardId = 2;

        final IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity
                = Mockito.mock(IssueStandardQuestionTypeOptionEntity.class);
        Mockito.when(questionTypeOptionEntity.getId()).thenReturn(id);
        Mockito.when(questionTypeOptionEntity.getQuestionTypeId()).thenReturn(questionTypeId);
        Mockito.when(questionTypeOptionEntity.getMinQuestions()).thenReturn(minQuestions);
        Mockito.when(questionTypeOptionEntity.getMaxQuestions()).thenReturn(maxQuestions);
        Mockito.when(questionTypeOptionEntity.getTimeLimit()).thenReturn(timeLimit);
        Mockito.when(questionTypeOptionEntity.getIssueStandardId()).thenReturn(issueStandardId);

        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel
                = new IssueStandardQuestionTypeOptionModel();
        questionTypeOptionModel.map(questionTypeOptionEntity);

        Assert.assertEquals(id, questionTypeOptionModel.getId());
        Assert.assertEquals(questionTypeId, questionTypeOptionModel.getQuestionTypeId());
        Assert.assertEquals(minQuestions, questionTypeOptionModel.getMinQuestions());
        Assert.assertEquals(maxQuestions, questionTypeOptionModel.getMaxQuestions());
        Assert.assertEquals(timeLimit, questionTypeOptionModel.getTimeLimit());
        Assert.assertNull(questionTypeOptionModel.getIssueStandard());
    }
}
