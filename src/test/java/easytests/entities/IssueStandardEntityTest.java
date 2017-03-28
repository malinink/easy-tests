package easytests.entities;

import easytests.models.IssueStandardModelInterface;
import easytests.models.IssueStandardQuestionTypeOptionModelInterface;
import easytests.models.IssueStandardTopicPriorityModelInterface;
import easytests.models.SubjectModelInterface;
import org.junit.Assert;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SingularityA
 */
public class IssueStandardEntityTest {

    @Test
    public void testCommon() {
        new BeanTester().testBean(IssueStandardEntity.class);
        new EqualsMethodTester().testEqualsMethod(IssueStandardEntity.class);
        new HashCodeMethodTester().testHashCodeMethod(IssueStandardEntity.class);
    }

    @Test
    public void testMap() {
        final Integer id = 5;
        final Integer timeLimit = 3600;
        final Integer questionsNumber = 30;
        final Integer subjectId = 1;

        final List<IssueStandardTopicPriorityModelInterface> topicPriorityModels = new ArrayList<>(2);
        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModels = new ArrayList<>(2);
        final SubjectModelInterface subjectModel = Mockito.mock(SubjectModelInterface.class);
        Mockito.when(subjectModel.getId()).thenReturn(subjectId);

        final IssueStandardModelInterface issueStandardModel = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModel.getId()).thenReturn(id);
        Mockito.when(issueStandardModel.getTimeLimit()).thenReturn(timeLimit);
        Mockito.when(issueStandardModel.getQuestionsNumber()).thenReturn(questionsNumber);
        Mockito.when(issueStandardModel.getTopicPriorities()).thenReturn(topicPriorityModels);
        Mockito.when(issueStandardModel.getQuestionTypeOptions()).thenReturn(questionTypeOptionModels);
        Mockito.when(issueStandardModel.getSubject()).thenReturn(subjectModel);

        final IssueStandardEntity issueStandardEntity = new IssueStandardEntity();
        issueStandardEntity.map(issueStandardModel);

        Assert.assertEquals(id, issueStandardEntity.getId());
        Assert.assertEquals(timeLimit, issueStandardEntity.getTimeLimit());
        Assert.assertEquals(questionsNumber, issueStandardEntity.getQuestionsNumber());
        Assert.assertEquals(subjectId, issueStandardEntity.getSubjectId());
    }
}
