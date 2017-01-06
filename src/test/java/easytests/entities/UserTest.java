package easytests.entities;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author zorigto
 */
public class UserTest {
    @Test
    public void testFirstName() throws Exception {
        User testUser = new User();
        testUser.setFirstName("Ivan");
        assertEquals("Ivan", testUser.getFirstName());
    }

    @Test
    public void testLastName() throws Exception {
        User testUser = new User();
        testUser.setLastName("Ivanov");
        assertEquals("Ivanov", testUser.getLastName());
    }

    @Test
    public void testSurname() throws Exception {
        User testUser = new User();
        testUser.setSurname("Ivanovich");
        assertEquals("Ivanovich", testUser.getSurname());
    }

}
