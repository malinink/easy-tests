package easytests.core.entities;

import easytests.core.models.IssueStandardQuestionTypeOptionModelInterface;
import easytests.support.IssueStandardQuestionTypeOptionsSupport;
import org.junit.Test;


/**
 * @author VeronikaRevjakina
 */
public class IssueStandardQuestionTypeOptionEntityTest extends AbstractEntityTest {

    private IssueStandardQuestionTypeOptionsSupport issueStandardQuestionTypeOptionsSupport = new IssueStandardQuestionTypeOptionsSupport();

    @Test
    public void testCommon() throws Exception {
        this.testCommon(IssueStandardQuestionTypeOptionEntity.class);
    }

    @Test
    public void testMap() throws Exception {
        final IssueStandardQuestionTypeOptionModelInterface issueStandardQuestionTypeOptionsModel = this.issueStandardQuestionTypeOptionsSupport.getModelFixtureMock(0);
        final IssueStandardQuestionTypeOptionEntity issueStandardQuestionTypeOptionEntity = new IssueStandardQuestionTypeOptionEntity();

        issueStandardQuestionTypeOptionEntity.map(issueStandardQuestionTypeOptionsModel);

        this.issueStandardQuestionTypeOptionsSupport.assertEquals(issueStandardQuestionTypeOptionsModel, issueStandardQuestionTypeOptionEntity);
    }
}
