package easytests.core.models;

import easytests.core.entities.IssueStandardTopicPriorityEntity;
import easytests.support.IssueStandardTopicPrioritySupport;
import org.junit.Test;
import org.meanbean.test.ConfigurationBuilder;


/**
 * @author VlasovIgor
 */
public class IssueStandardTopicPriorityModelTest extends AbstractModelTest {

    private IssueStandardTopicPrioritySupport IssueStandardTopicPrioritySupport = new IssueStandardTopicPrioritySupport();

    @Test
    public void testCommon() throws Exception {
        super.testCommon(IssueStandardTopicPriorityModel.class);
    }

    @Test
    public void testMap() throws Exception {
        final IssueStandardTopicPriorityEntity IssueStandardTopicPriorityEntity = this.IssueStandardTopicPrioritySupport.getEntityFixtureMock(0);
        final IssueStandardTopicPriorityModelInterface IssueStandardTopicPriorityModel = new IssueStandardTopicPriorityModel();

        IssueStandardTopicPriorityModel.map(IssueStandardTopicPriorityEntity);

        this.IssueStandardTopicPrioritySupport.assertEquals(IssueStandardTopicPriorityEntity, IssueStandardTopicPriorityModel);
    }
}
