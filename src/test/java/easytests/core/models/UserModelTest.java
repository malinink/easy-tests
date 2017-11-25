package easytests.core.models;

import easytests.core.entities.UserEntity;
import easytests.support.UsersSupport;
import org.junit.Test;
import org.meanbean.test.BeanTester;


/**
 * @author malinink
 */
public class UserModelTest extends AbstractModelTest {

    private UsersSupport usersSupport = new UsersSupport();

    @Test
    public void testCommon() throws Exception {
        new BeanTester().testBean(UserModel.class);
    }

    @Test
    public void testMap() throws Exception {
        final UserEntity userEntity = this.usersSupport.getEntityFixtureMock(0);
        final UserModel userModel = new UserModel();
        userModel.map(userEntity);

        this.usersSupport.assertEquals(userEntity, userModel);
    }
}
