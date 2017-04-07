package easytests.models;

import easytests.entities.UserEntity;
import easytests.models.empty.ModelsListEmpty;
import easytests.support.Entities;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author malinink
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserModelTest {
    @Test
    public void testCommon() throws Exception {
        new BeanTester().testBean(UserModel.class);
    }

    @Test
    public void testMap() throws Exception {
        final Integer userId = 3;
        final String firstName = "FirstName";
        final String lastName = "LastName";
        final String surname = "Surname";
        final String email = "email";
        final String password = "password";
        final Boolean isAdmin = false;
        final Integer state = 2;
        final UserEntity userEntity = Entities.createUserEntityMock(
                userId,
                firstName,
                lastName,
                surname,
                email,
                password,
                isAdmin,
                state
        );

        final UserModel userModel = new UserModel();
        userModel.map(userEntity);

        Assert.assertEquals(userId, userModel.getId());
        Assert.assertEquals(firstName, userModel.getFirstName());
        Assert.assertEquals(lastName, userModel.getLastName());
        Assert.assertEquals(surname, userModel.getSurname());
        Assert.assertEquals(email, userModel.getEmail());
        Assert.assertEquals(password, userModel.getPassword());
        Assert.assertEquals(isAdmin, userModel.getIsAdmin());
        Assert.assertEquals(state, userModel.getState());
        Assert.assertEquals(new ModelsListEmpty(), userModel.getSubjects());
    }
}
