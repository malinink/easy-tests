package easytests.entities;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author DoZor-80
 */
public class TesteeTest {
    @Test
    public void testFirstName() throws Exception {
        final Testee testTestee = new Testee();
        testTestee.setFirstName("Ivan");
        assertEquals("Ivan", testTestee.getFirstName());
    }

    @Test
    public void testLastName() throws Exception {
        final Testee testTestee = new Testee();
        testTestee.setLastName("Ivanov");
        assertEquals("Ivanov", testTestee.getLastName());
    }

    @Test
    public void testSurname() throws Exception {
        final Testee testTestee = new Testee();
        testTestee.setSurname("Ivanovich");
        assertEquals("Ivanovich", testTestee.getSurname());
    }

    @Test
    public void testGroupNumber() throws Exception{
        final Testee testTestee = new Testee();
        testTestee.setGroupNumber(307);
        assertEquals((Integer) 307, testTestee.getGroupNumber());
    }
}
