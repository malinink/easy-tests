package easytests.core.models;

import easytests.core.entities.IssueEntity;
import easytests.support.IssueSupport;
import org.junit.Test;
import org.meanbean.test.ConfigurationBuilder;


/**
 * @author greenbarrow
 */
public class IssueModelTest extends AbstractModelTest {

    private IssueSupport issueSupport = new IssueSupport();

    @Test
    public void testCommon() throws Exception {
        super.testCommon(IssueModel.class);
    }

    @Test
    public void testMap() throws Exception {
        final IssueEntity issueEntity = this.issueSupport.getEntityFixtureMock(0);
        final IssueModelInterface issueModel = new IssueModel();

        issueModel.map(issueEntity);

        this.issueSupport.assertEquals(issueEntity, issueModel);
    }
}
