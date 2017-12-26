package easytests.core.entities;

import easytests.core.models.IssueStandardModelInterface;
import easytests.support.IssueStandardSupport;
import org.junit.Test;

/**
 * @author janchk
 */
public class IssueStandardEntityTest extends AbstractEntityTest {

    protected IssueStandardSupport issueStandardSupport = new IssueStandardSupport();

    @Test
    public void testCommon() throws Exception {
        this.testCommon(IssueStandardEntity.class);
    }


    @Test
    public void testMap() {
        final IssueStandardModelInterface issueStandardModel = issueStandardSupport.getModelFixtureMock(2);
        final IssueStandardEntity issueStandardEntity = new IssueStandardEntity();

        issueStandardEntity.map(issueStandardModel);

        this.issueStandardSupport.assertEquals(issueStandardModel, issueStandardEntity);

    }
}
