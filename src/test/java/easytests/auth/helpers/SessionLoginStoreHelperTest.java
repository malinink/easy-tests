package easytests.auth.helpers;

import javax.servlet.http.HttpSession;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author DoZor-80
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SessionLoginStoreHelperTest {
    @Autowired
    private HttpSession session;

    private SessionLoginStoreHelper storeHelper;

    @Before
    public void setUp() throws Exception {
        storeHelper = new SessionLoginStoreHelper(session);
    }

    @Test
    public void testExists() throws Exception {
        Assert.assertEquals(true, storeHelper.exists());
    }

    @Test
    public void testLogin() throws Exception {
        storeHelper.setLogin("login");
        Assert.assertEquals("login", storeHelper.getLogin());
    }

    @Test
    public void testRemoveLogin() throws Exception {
        storeHelper.setLogin("login");
        storeHelper.removeLogin();
        Assert.assertNull(storeHelper.getLogin());
    }

    @Test
    public void testRemoveAbsentLogin() throws Exception {
        SessionLoginStoreHelper absentSessionLoginStoreHelper = new SessionLoginStoreHelper(null);
        absentSessionLoginStoreHelper.removeLogin();
        Assert.assertEquals(false, absentSessionLoginStoreHelper.exists());
        Assert.assertNull(absentSessionLoginStoreHelper.getLogin());
    }
}
