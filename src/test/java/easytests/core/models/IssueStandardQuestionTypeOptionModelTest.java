package easytests.core.models;

import easytests.core.entities.IssueStandardQuestionTypeOptionEntity;
import easytests.core.models.empty.IssueStandardModelEmpty;
import org.junit.Assert;
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

    @Test
    public void testCommon() throws Exception {
        Configuration configuration = new ConfigurationBuilder()
                .ignoreProperty("questionType")
                .ignoreProperty("issueStandard")
                .build();
        new BeanTester().testBean(IssueStandardQuestionTypeOptionModel.class, configuration);
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
        Assert.assertEquals(questionTypeId, questionTypeOptionModel.getQuestionType().getId());
        Assert.assertEquals(minQuestions, questionTypeOptionModel.getMinQuestions());
        Assert.assertEquals(maxQuestions, questionTypeOptionModel.getMaxQuestions());
        Assert.assertEquals(timeLimit, questionTypeOptionModel.getTimeLimit());
        Assert.assertEquals(new IssueStandardModelEmpty(issueStandardId), questionTypeOptionModel.getIssueStandard());
    }
}
