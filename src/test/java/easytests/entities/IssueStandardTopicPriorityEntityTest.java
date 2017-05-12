package easytests.entities;

import easytests.models.IssueStandardTopicPriorityModelInterface;
import easytests.models.empty.IssueStandardModelEmpty;
import easytests.models.empty.TopicModelEmpty;
import org.junit.Assert;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.mockito.Mockito;

/**
 * @author SingularityA
 */
public class IssueStandardTopicPriorityEntityTest {

    @Test
    public void testCommon() {
        new BeanTester().testBean(IssueStandardTopicPriorityEntity.class);
    }

    @Test
    public void testMap() {
        final Integer id = 5;
        final Integer topicId = 2;
        final Boolean isPreferable = true;
        final Integer issueStandardId = 1;

        final IssueStandardTopicPriorityModelInterface topicPriorityModel
                = Mockito.mock(IssueStandardTopicPriorityModelInterface.class);
        Mockito.when(topicPriorityModel.getId()).thenReturn(id);
        Mockito.when(topicPriorityModel.getTopic()).thenReturn(new TopicModelEmpty(topicId));
        Mockito.when(topicPriorityModel.getIsPreferable()).thenReturn(isPreferable);
        Mockito.when(topicPriorityModel.getIssueStandard()).thenReturn(new IssueStandardModelEmpty(issueStandardId));

        final IssueStandardTopicPriorityEntity topicPriorityEntity = new IssueStandardTopicPriorityEntity();
        topicPriorityEntity.map(topicPriorityModel);

        Assert.assertEquals(id, topicPriorityEntity.getId());
        Assert.assertEquals(topicId, topicPriorityEntity.getTopicId());
        Assert.assertEquals(isPreferable, topicPriorityEntity.getIsPreferable());
        Assert.assertEquals(issueStandardId, topicPriorityEntity.getIssueStandardId());
    }
}
