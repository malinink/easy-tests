package easytests.entities;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author fortyways
 */
public class IssueTest {

    @Test
    public void testId() throws Exception{
        final Issue testIssue =new Issue();
        testIssue.setId(34);
        assertEquals((long)34,(long)testIssue.getId());
    }
    @Test
    public void testName() throws Exception{
        final Issue testIssue =new Issue();
        testIssue.setName("Issue1");
        assertEquals("Issue1",testIssue.getName());
    }
    @Test
    public void testAuthorId() throws Exception{
        final Issue testIssue =new Issue();
        testIssue.setAuthorId(34);
        assertEquals((long)34,(long)testIssue.getAuthorId());
    }
}
