package easytests.core.entities;

import easytests.core.models.UserModelInterface;
import easytests.support.UsersSupport;
import org.junit.Test;
import org.meanbean.test.BeanTester;


/**
 * @author malinink
 */
public class UserEntityTest {

    private UsersSupport usersSupport = new UsersSupport();

    @Test
    public void testCommon() throws Exception {
        new BeanTester().testBean(UserEntity.class);
    }

    @Test
    public void testMap() throws Exception {
        final UserModelInterface userModel = this.usersSupport.getModelMock(
                1,
                "FirstName",
                "LastName",
                "Surname",
                "mail@mail.ru",
                "password",
                true,
                1
        );
        final UserEntity userEntity = new UserEntity();
        userEntity.map(userModel);

        this.usersSupport.assertEquals(userModel, userEntity);
    }

}
