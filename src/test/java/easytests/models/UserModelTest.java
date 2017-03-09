package easytests.models;

import easytests.entities.UserEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
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
        new EqualsMethodTester().testEqualsMethod(UserModel.class);
        new HashCodeMethodTester().testHashCodeMethod(UserModel.class);
    }

    @Test
    public void testMap() throws Exception {
        final Integer userId = 3;
        final String firstName = "FirstName";
        final String lastName = "LastName";
        final String surname = "Surname";
        final UserEntity userEntity = Mockito.mock(UserEntity.class);

        Mockito.when(userEntity.getId()).thenReturn(userId);
        Mockito.when(userEntity.getFirstName()).thenReturn(firstName);
        Mockito.when(userEntity.getLastName()).thenReturn(lastName);
        Mockito.when(userEntity.getSurname()).thenReturn(surname);

        final UserModel userModel = new UserModel();
        userModel.map(userEntity);

        Assert.assertEquals(userId, userModel.getId());
        Assert.assertEquals(firstName, userModel.getFirstName());
        Assert.assertEquals(lastName, userModel.getLastName());
        Assert.assertEquals(surname, userModel.getSurname());
    }
}
