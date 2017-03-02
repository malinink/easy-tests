package easytests.entities;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author vkpankov
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

        final List<TopicInterface> testTopics = new ArrayList<>();
        testSubject.setTopics(testTopics);

        assertEquals(testTopics, testSubject.getTopics());
    }

    @Test
    public void testUserId() throws Exception {
        final Subject testSubject = new Subject();
        testSubject.setUserId(1);
        assertEquals((Integer)1, testSubject.getUserId());
    }
}