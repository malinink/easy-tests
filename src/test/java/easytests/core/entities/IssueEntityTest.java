package easytests.core.entities;

import easytests.core.models.IssueModelInterface;
import easytests.support.IssueSupport;
import org.junit.Test;


/**
 * @author greenbarrow
 */
public class IssueEntityTest extends AbstractEntityTest {

    protected IssueSupport issueSupport = new IssueSupport();

    @Test
    public void testCommon() throws Exception {
        this.testCommon(IssueEntity.class);
    }

    @Test
    public void testMap() throws Exception {
        final IssueModelInterface issueModel = this.issueSupport.getModelFixtureMock(0);
        final IssueEntity issueEntity = new IssueEntity();

        issueEntity.map(issueModel);

        this.issueSupport.assertEquals(issueModel, issueEntity);
    }
}