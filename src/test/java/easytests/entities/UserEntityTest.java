package easytests.entities;

import easytests.models.UserModelInterface;
import org.junit.Assert;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
import org.mockito.Mockito;

/**
 * @author malinink
 */
public class UserEntityTest {
    @Test
    public void testCommon() throws Exception {
        new BeanTester().testBean(UserEntity.class);
        new EqualsMethodTester().testEqualsMethod(UserEntity.class);
        new HashCodeMethodTester().testHashCodeMethod(UserEntity.class);
    }

    @Test
    public void testMap() throws Exception {
        final Integer userId = 3;
        final String firstName = "FirstName";
        final String lastName = "LastName";
        final String surname = "Surname";

        final UserModelInterface userModel = Mockito.mock(UserModelInterface.class);
        Mockito.when(userModel.getId()).thenReturn(userId);
        Mockito.when(userModel.getFirstName()).thenReturn(firstName);
        Mockito.when(userModel.getLastName()).thenReturn(lastName);
        Mockito.when(userModel.getSurname()).thenReturn(surname);

        final UserEntity userEntity = new UserEntity();
        userEntity.map(userModel);

        Assert.assertEquals(userId, userEntity.getId());
        Assert.assertEquals(firstName, userEntity.getFirstName());
        Assert.assertEquals(lastName, userEntity.getLastName());
        Assert.assertEquals(surname, userEntity.getSurname());
    }
}
