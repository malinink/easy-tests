package easytests.core.entities;

import easytests.core.models.IssueStandardTopicPriorityModelInterface;
import easytests.support.IssueStandardTopicPrioritySupport;
import org.junit.Test;

/**
 * @author SakhPrace
 */
public class IssueStandardTopicPriorityEntityTest extends AbstractEntityTest {

    private IssueStandardTopicPrioritySupport issueStandardTopicPrioritySupport = new IssueStandardTopicPrioritySupport();

    @Test
    public void testCommon() throws Exception {
        this.testCommon(IssueStandardTopicPriorityEntity.class);
    }

    @Test
    public void testMap() {
        final IssueStandardTopicPriorityModelInterface issueStandardTopicPriorityModel = this.issueStandardTopicPrioritySupport.getModelFixtureMock(0);
        final IssueStandardTopicPriorityEntity issueStandardTopicPriorityEntity = new IssueStandardTopicPriorityEntity();

        issueStandardTopicPriorityEntity.map(issueStandardTopicPriorityModel);

        this.issueStandardTopicPrioritySupport.assertEquals(issueStandardTopicPriorityModel, issueStandardTopicPriorityEntity);
    }
}
