package easytests.entities;

import easytests.models.IssueStandardModelInterface;
import easytests.models.IssueStandardTopicPriorityModelInterface;
import org.junit.Assert;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
import org.mockito.Mockito;

/**
 * @author SingularityA
 */
public class IssueStandardTopicPriorityEntityTest {

    @Test
    public void testCommon() {
        new BeanTester().testBean(IssueStandardTopicPriorityEntity.class);
        new EqualsMethodTester().testEqualsMethod(IssueStandardTopicPriorityEntity.class);
        new HashCodeMethodTester().testHashCodeMethod(IssueStandardTopicPriorityEntity.class);
    }

    @Test
    public void testMap() {
        final Integer id = 5;
        final Integer topicId = 2;
        final Boolean isPreferable = true;
        final Integer issueStandardId = 1;

        final IssueStandardModelInterface issueStandardModel = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModel.getId()).thenReturn(issueStandardId);

        final IssueStandardTopicPriorityModelInterface topicPriorityModel
                = Mockito.mock(IssueStandardTopicPriorityModelInterface.class);
        Mockito.when(topicPriorityModel.getId()).thenReturn(id);
        Mockito.when(topicPriorityModel.getTopicId()).thenReturn(topicId);
        Mockito.when(topicPriorityModel.getIsPreferable()).thenReturn(isPreferable);
        Mockito.when(topicPriorityModel.getIssueStandard()).thenReturn(issueStandardModel);

        final IssueStandardTopicPriorityEntity topicPriorityEntity = new IssueStandardTopicPriorityEntity();
        topicPriorityEntity.map(topicPriorityModel);

        Assert.assertEquals(id, topicPriorityEntity.getId());
        Assert.assertEquals(topicId, topicPriorityEntity.getTopicId());
        Assert.assertEquals(isPreferable, topicPriorityEntity.getIsPreferable());
        Assert.assertEquals(issueStandardId, topicPriorityEntity.getIssueStandardId());
    }
}
