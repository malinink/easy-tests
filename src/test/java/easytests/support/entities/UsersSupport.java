package easytests.support.entities;

import easytests.core.entities.UserEntity;
import org.junit.Assert;
import org.mockito.Mockito;


/**
 * @author malinink
 */
public abstract class UsersSupport {

    public static UserEntity createMock(
            Integer id,
            String firstName,
            String lastName,
            String surname,
            String email,
            String password,
            Boolean isAdmin,
            Integer state
    ) {
        final UserEntity userEntity = Mockito.mock(UserEntity.class);
        Mockito.when(userEntity.getId()).thenReturn(id);
        Mockito.when(userEntity.getFirstName()).thenReturn(firstName);
        Mockito.when(userEntity.getLastName()).thenReturn(lastName);
        Mockito.when(userEntity.getSurname()).thenReturn(surname);
        Mockito.when(userEntity.getEmail()).thenReturn(email);
        Mockito.when(userEntity.getPassword()).thenReturn(password);
        Mockito.when(userEntity.getIsAdmin()).thenReturn(isAdmin);
        Mockito.when(userEntity.getState()).thenReturn(state);
        return userEntity;
    }

    public static void asertEqualsWithId(UserEntity first, UserEntity second) {
        assertEquals(first, second, false);
    }

    public static void asertEqualsWithoutId(UserEntity first, UserEntity second) {
        assertEquals(first, second, true);
    }

    private static void assertEquals(UserEntity first, UserEntity second, Boolean exceptId) {
        if (exceptId) {
            Assert.assertEquals(first.getId(), second.getId());
        }
        Assert.assertEquals(first.getFirstName(), second.getFirstName());
        Assert.assertEquals(first.getLastName(), second.getLastName());
        Assert.assertEquals(first.getSurname(), second.getSurname());
        Assert.assertEquals(first.getEmail(), second.getEmail());
        Assert.assertEquals(first.getPassword(), second.getPassword());
        Assert.assertEquals(first.getIsAdmin(), second.getIsAdmin());
        Assert.assertEquals(first.getState(), second.getState());
    }

    public static void asertNotEqualsWithId(UserEntity first, UserEntity second) {
        assertNotEquals(first, second, false);
    }

    public static void asertNotEqualsWithoutId(UserEntity first, UserEntity second) {
        assertNotEquals(first, second, true);
    }

    private static void assertNotEquals(UserEntity first, UserEntity second, Boolean exceptId) {
        if (exceptId) {
            Assert.assertNotEquals(first.getId(), second.getId());
        }
        Assert.assertNotEquals(first.getFirstName(), second.getFirstName());
        Assert.assertNotEquals(first.getLastName(), second.getLastName());
        Assert.assertNotEquals(first.getSurname(), second.getSurname());
        Assert.assertNotEquals(first.getEmail(), second.getEmail());
        Assert.assertNotEquals(first.getPassword(), second.getPassword());
        Assert.assertNotEquals(first.getIsAdmin(), second.getIsAdmin());
        Assert.assertNotEquals(first.getState(), second.getState());
    }
}
