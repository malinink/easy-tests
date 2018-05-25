package easytests.core.models;

import easytests.core.entities.IssueStandardTopicPriorityEntity;
import easytests.support.IssueStandardTopicPrioritySupport;
import org.junit.Test;


/**
 * @author VlasovIgor
 */
public class IssueStandardTopicPriorityModelTest extends AbstractModelTest {

    private IssueStandardTopicPrioritySupport issueStandardTopicPrioritySupport = new IssueStandardTopicPrioritySupport();

    @Test
    public void testCommon() throws Exception {
        super.testCommon(IssueStandardTopicPriorityModel.class);
    }

    @Test
    public void testMap() throws Exception {
        final IssueStandardTopicPriorityEntity issueStandardTopicPriorityEntity = this.issueStandardTopicPrioritySupport.getEntityFixtureMock(0);
        final IssueStandardTopicPriorityModelInterface issueStandardTopicPriorityModel = new IssueStandardTopicPriorityModel();

        issueStandardTopicPriorityModel.map(issueStandardTopicPriorityEntity);

        this.issueStandardTopicPrioritySupport.assertEquals(issueStandardTopicPriorityEntity, issueStandardTopicPriorityModel);
    }
}
