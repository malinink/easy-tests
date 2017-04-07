package easytests.models;

import easytests.entities.TesteeEntity;
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
 * @author DoZor-80
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TesteeModelTest {
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
        final Integer groupNumber = 307;
        final TesteeEntity testeeEntity = Mockito.mock(TesteeEntity.class);

        Mockito.when(testeeEntity.getId()).thenReturn(userId);
        Mockito.when(testeeEntity.getFirstName()).thenReturn(firstName);
        Mockito.when(testeeEntity.getLastName()).thenReturn(lastName);
        Mockito.when(testeeEntity.getSurname()).thenReturn(surname);
        Mockito.when(testeeEntity.getGroupNumber()).thenReturn(groupNumber);

        final TesteeModel testeeModel = new TesteeModel();
        testeeModel.map(testeeEntity);

        Assert.assertEquals(userId, testeeModel.getId());
        Assert.assertEquals(firstName, testeeModel.getFirstName());
        Assert.assertEquals(lastName, testeeModel.getLastName());
        Assert.assertEquals(surname, testeeModel.getSurname());
        Assert.assertEquals(groupNumber,testeeModel.getGroupNumber());
    }

}
