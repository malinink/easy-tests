package easytests.entities;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by zorigto on 10.11.16.
 */
public class UserTest {
    @Test
    public void testFirstName() throws Exception {
        User tstUser = new User("Ivan","Ivanovich","Ivanov");
        assertEquals("Ivan", tstUser.getFirstName());
    }

    @Test
    public void testLastName() throws Exception {
        User tstUser = new User("Ivan","Ivanovich","Ivanov");
        assertEquals("Ivanovich", tstUser.getLastName());
    }

    @Test
    public void testSurname() throws Exception {
        User tstUser = new User("Ivan","Ivanovich","Ivanov");
        assertEquals("Ivanov", tstUser.getSurname());
    }

}