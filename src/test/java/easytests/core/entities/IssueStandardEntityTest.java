package easytests.core.entities;

import easytests.core.models.IssueStandardModelInterface;
import easytests.core.models.empty.ModelsListEmpty;
import easytests.core.models.empty.SubjectModelEmpty;
import org.junit.Assert;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.mockito.Mockito;

/**
 * @author SingularityA
 */
public class IssueStandardEntityTest {

    @Test
    public void testCommon() {
        new BeanTester().testBean(IssueStandardEntity.class);
    }

    @Test
    public void testMap() {
        final Integer id = 5;
        final Integer timeLimit = 3600;
        final Integer questionsNumber = 30;
        final Integer subjectId = 3;

        final IssueStandardModelInterface issueStandardModel = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModel.getId()).thenReturn(id);
        Mockito.when(issueStandardModel.getTimeLimit()).thenReturn(timeLimit);
        Mockito.when(issueStandardModel.getQuestionsNumber()).thenReturn(questionsNumber);
        Mockito.when(issueStandardModel.getTopicPriorities()).thenReturn(new ModelsListEmpty());
        Mockito.when(issueStandardModel.getQuestionTypeOptions()).thenReturn(new ModelsListEmpty());
        Mockito.when(issueStandardModel.getSubject()).thenReturn(new SubjectModelEmpty(subjectId));

        final IssueStandardEntity issueStandardEntity = new IssueStandardEntity();
        issueStandardEntity.map(issueStandardModel);

        Assert.assertEquals(id, issueStandardEntity.getId());
        Assert.assertEquals(timeLimit, issueStandardEntity.getTimeLimit());
        Assert.assertEquals(questionsNumber, issueStandardEntity.getQuestionsNumber());
        Assert.assertEquals(subjectId, issueStandardEntity.getSubjectId());
    }
}
