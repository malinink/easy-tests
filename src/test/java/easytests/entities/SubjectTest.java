package easytests.entities;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author zorigto
 */
public class SubjectTest {
    @Test
    public void testName() throws Exception {
        final Subject testSubject = new Subject();
        testSubject.setName("test");
        assertEquals("test", testSubject.getName());
    }

    @Test
    public void testTopics() throws Exception {
        final Subject testSubject = new Subject();

        final List<TopicInterface> testTopics = new ArrayList<TopicInterface>();
        testSubject.setTopics(testTopics);

        assertEquals(testTopics, testSubject.getTopics());
    }

    @Test
    public void testUserId() throws Exception {
        final Subject testSubject = new Subject();
        testSubject.setUserId(1);
        assertEquals((Integer)1, testSubject.getUserId());
    }

    @Test
    public void testIssueStandard() throws Exception {
        final Subject testSubject = new Subject();

        final IssueStandard testIssueStandard = new IssueStandard();
        testSubject.setIssueStandard(testIssueStandard);

        assertEquals(testIssueStandard, testSubject.getIssueStandard());
    }

}