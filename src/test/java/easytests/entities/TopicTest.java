package easytests.entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author loriens  zorigto
 */
public class TopicTest {

    @Test
    public void testName() throws Exception {
        final Topic testTopic = new Topic();
        testTopic.setName("test topic");
        assertEquals("test topic", testTopic.getName());
    }
    
}
