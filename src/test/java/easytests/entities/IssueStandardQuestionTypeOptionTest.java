package easytests.entities;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author SingularityA
 */
public class IssueStandardQuestionTypeOptionTest {

    @Test
    public void idTest() throws Exception {
        final IssueStandardQuestionTypeOptionInterface issueStandardQuestionTypeOption
                = new IssueStandardQuestionTypeOption();
        issueStandardQuestionTypeOption.setId(1);
        assertEquals((Integer) 1, issueStandardQuestionTypeOption.getId());
    }

    @Test
    public void questionTypeIdTest() throws Exception {
        final IssueStandardQuestionTypeOptionInterface issueStandardQuestionTypeOption
                = new IssueStandardQuestionTypeOption();
        issueStandardQuestionTypeOption.setQuestionTypeId(1);
        assertEquals((Integer) 1, issueStandardQuestionTypeOption.getQuestionTypeId());
    }

    @Test
    public void minQuestionsTest() throws Exception {
        final IssueStandardQuestionTypeOptionInterface issueStandardQuestionTypeOption
                = new IssueStandardQuestionTypeOption();
        issueStandardQuestionTypeOption.setMinQuestions(10);
        assertEquals((Integer) 10, issueStandardQuestionTypeOption.getMinQuestions());
    }

    @Test
    public void maxQuestionsTest() throws Exception {
        final IssueStandardQuestionTypeOptionInterface issueStandardQuestionTypeOption
                = new IssueStandardQuestionTypeOption();
        issueStandardQuestionTypeOption.setMaxQuestions(10);
        assertEquals((Integer) 10, issueStandardQuestionTypeOption.getMaxQuestions());
    }

    @Test
    public void timeLimitTest() throws Exception {
        final IssueStandardQuestionTypeOptionInterface issueStandardQuestionTypeOption
                = new IssueStandardQuestionTypeOption();
        issueStandardQuestionTypeOption.setTimeLimit(600);
        assertEquals((Integer) 600, issueStandardQuestionTypeOption.getTimeLimit());
    }

    @Test
    public void issueStandardIdTest() throws Exception {
        final IssueStandardQuestionTypeOptionInterface issueStandardQuestionTypeOption
                = new IssueStandardQuestionTypeOption();
        issueStandardQuestionTypeOption.setIssueStandardId(1);
        assertEquals((Integer) 1, issueStandardQuestionTypeOption.getIssueStandardId());
    }
}
