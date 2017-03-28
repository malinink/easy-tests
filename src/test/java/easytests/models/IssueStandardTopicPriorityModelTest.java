package easytests.models;

import easytests.entities.IssueStandardTopicPriorityEntity;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.meanbean.test.*;
import org.mockito.Mockito;

/**
 * @author SingularityA
 */
public class IssueStandardTopicPriorityModelTest {
    @Ignore
    @Test
    public void testCommon() {
        Configuration configuration = new ConfigurationBuilder()
                .ignoreProperty("issueStandard")
                .build();
        // TODO: attempt to create specific factory
        // testBean - passes
        new BeanTester().testBean(IssueStandardTopicPriorityModel.class, configuration);
        new EqualsMethodTester().testEqualsMethod(IssueStandardTopicPriorityModel.class, configuration);
        new HashCodeMethodTester().testHashCodeMethod(IssueStandardTopicPriorityModel.class);
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
        Assert.assertEquals(topicId, topicPriorityModel.getTopicId());
        Assert.assertEquals(isPreferable, topicPriorityModel.getIsPreferable());
        Assert.assertNull(topicPriorityModel.getIssueStandard());
    }
}
