package easytests.models;

import easytests.entities.IssueStandardEntity;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.meanbean.test.*;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 * @author SingularityA
 */
public class IssueStandardModelTest {
    @Ignore
    @Test
    public void testCommon() throws Exception {
        Configuration configuration = new ConfigurationBuilder()
                .ignoreProperty("topicPriorities")
                .ignoreProperty("questionTypeOptions")
                .ignoreProperty("subject")
                .build();
        // TODO: attempt to create specific factory
        // testBean - passes
        new BeanTester().testBean(IssueStandardModel.class, configuration);
        new EqualsMethodTester().testEqualsMethod(IssueStandardModel.class, configuration);
        new HashCodeMethodTester().testHashCodeMethod(IssueStandardModel.class);
    }

    @Test
    public void testMap() {
        final Integer id = 5;
        final Integer timeLimit = 3600;
        final Integer questionsNumber = 30;
        final Integer subjectId = 1;

        final IssueStandardEntity issueStandardEntity = Mockito.mock(IssueStandardEntity.class);
        Mockito.when(issueStandardEntity.getId()).thenReturn(id);
        Mockito.when(issueStandardEntity.getTimeLimit()).thenReturn(timeLimit);
        Mockito.when(issueStandardEntity.getQuestionsNumber()).thenReturn(questionsNumber);
        Mockito.when(issueStandardEntity.getSubjectId()).thenReturn(subjectId);

        final IssueStandardModelInterface issueStandardModel = new IssueStandardModel();
        issueStandardModel.map(issueStandardEntity);

        Assert.assertEquals(id, issueStandardModel.getId());
        Assert.assertEquals(timeLimit, issueStandardModel.getTimeLimit());
        Assert.assertEquals(questionsNumber, issueStandardModel.getQuestionsNumber());
        Assert.assertNull(issueStandardModel.getTopicPriorities());
        Assert.assertNull(issueStandardModel.getQuestionTypeOptions());
        Assert.assertNull(issueStandardModel.getSubject());
    }
}
