package easytests.models;

import easytests.entities.IssueStandardTopicPriorityEntity;
import easytests.models.empty.IssueStandardModelEmpty;
import easytests.models.empty.TopicModelEmpty;
import org.junit.Assert;
import org.junit.Test;
import org.meanbean.test.*;
import org.mockito.Mockito;

/**
 * @author SingularityA
 */
public class IssueStandardTopicPriorityModelTest {

    @Test
    public void testCommon() {
        Configuration configuration = new ConfigurationBuilder()
                .ignoreProperty("topic")
                .ignoreProperty("issueStandard")
                .build();
        new BeanTester().testBean(IssueStandardTopicPriorityModel.class, configuration);
    }

    @Test
    public void testMap() {
        final Integer id = 5;
        final Integer topicId = 3;
        final Boolean isPreferable = false;
        final Integer issueStandardId = 2;

        final IssueStandardTopicPriorityEntity topicPriorityEntity
                = Mockito.mock(IssueStandardTopicPriorityEntity.class);
        Mockito.when(topicPriorityEntity.getId()).thenReturn(id);
        Mockito.when(topicPriorityEntity.getTopicId()).thenReturn(topicId);
        Mockito.when(topicPriorityEntity.getIsPreferable()).thenReturn(isPreferable);
        Mockito.when(topicPriorityEntity.getIssueStandardId()).thenReturn(issueStandardId);

        final IssueStandardTopicPriorityModelInterface topicPriorityModel = new IssueStandardTopicPriorityModel();
        topicPriorityModel.map(topicPriorityEntity);

        Assert.assertEquals(id, topicPriorityModel.getId());
        Assert.assertEquals(new TopicModelEmpty(topicId), topicPriorityModel.getTopic());
        Assert.assertEquals(isPreferable, topicPriorityModel.getIsPreferable());
        Assert.assertEquals(new IssueStandardModelEmpty(issueStandardId), topicPriorityModel.getIssueStandard());
    }
}
