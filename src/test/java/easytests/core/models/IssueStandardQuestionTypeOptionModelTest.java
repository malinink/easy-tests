package easytests.core.models;

import easytests.core.entities.IssueStandardQuestionTypeOptionEntity;
import easytests.support.IssueStandardQuestionTypeOptionsSupport;
import org.junit.Test;
import org.meanbean.test.ConfigurationBuilder;

/**
 * @author VeronikaRevjakina
 */

public class IssueStandardQuestionTypeOptionModelTest extends AbstractModelTest {

    private IssueStandardQuestionTypeOptionsSupport issueStandardQuestionTypeOptionsSupport = new IssueStandardQuestionTypeOptionsSupport();

    @Override
    protected ConfigurationBuilder getConfigurationBuilder() {
        return super.getConfigurationBuilder()
                .ignoreProperty("questionType")
                .ignoreProperty("issueStandard");
    }

    @Test
    public void testCommon() throws Exception {
        this.testCommon(IssueStandardQuestionTypeOptionModel.class);
    }

    @Test
    public void testMap() throws Exception {
        final IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity=this.issueStandardQuestionTypeOptionsSupport.getEntityFixtureMock(0);
        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel=new IssueStandardQuestionTypeOptionModel();
        questionTypeOptionModel.map(questionTypeOptionEntity);

        this.issueStandardQuestionTypeOptionsSupport.assertEquals(questionTypeOptionEntity,questionTypeOptionModel);
    }
}