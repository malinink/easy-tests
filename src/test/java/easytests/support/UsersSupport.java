package easytests.support;

import easytests.core.entities.UserEntity;
import easytests.core.models.UserModelInterface;
import easytests.core.models.empty.ModelsListEmpty;
import org.junit.Assert;
import org.mockito.Mockito;


/**
 * @author malinink
 */
public class UsersSupport {

    public UserEntity getEntityMock(
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

    public UserModelInterface getModelMock(
            Integer id,
            String firstName,
            String lastName,
            String surname,
            String email,
            String password,
            Boolean isAdmin,
            Integer state
    ) {
        final UserModelInterface userModel = Mockito.mock(UserModelInterface.class);
        Mockito.when(userModel.getId()).thenReturn(id);
        Mockito.when(userModel.getFirstName()).thenReturn(firstName);
        Mockito.when(userModel.getLastName()).thenReturn(lastName);
        Mockito.when(userModel.getSurname()).thenReturn(surname);
        Mockito.when(userModel.getEmail()).thenReturn(email);
        Mockito.when(userModel.getPassword()).thenReturn(password);
        Mockito.when(userModel.getIsAdmin()).thenReturn(isAdmin);
        Mockito.when(userModel.getState()).thenReturn(state);
        Mockito.when(userModel.getSubjects()).thenReturn(new ModelsListEmpty());
        return userModel;
    }

    public void asertEquals(UserEntity first, UserEntity second) {
        assertEquals(first, second, false);
    }

    public void asertEqualsWithoutId(UserEntity first, UserEntity second) {
        assertEquals(first, second, true);
    }

    private void assertEquals(UserEntity first, UserEntity second, Boolean exceptId) {
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

    public void asertNotEquals(UserEntity first, UserEntity second) {
        assertNotEquals(first, second, false);
    }

    public void asertNotEqualsWithoutId(UserEntity first, UserEntity second) {
        assertNotEquals(first, second, true);
    }

    private void assertNotEquals(UserEntity first, UserEntity second, Boolean exceptId) {
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

    public void assertEquals(UserModelInterface first, UserModelInterface second) {
        Assert.assertEquals(first.getId(), second.getId());
        Assert.assertEquals(first.getFirstName(), second.getFirstName());
        Assert.assertEquals(first.getLastName(), second.getLastName());
        Assert.assertEquals(first.getSurname(), second.getSurname());
        Assert.assertEquals(first.getEmail(), second.getEmail());
        Assert.assertEquals(first.getPassword(), second.getPassword());
        Assert.assertEquals(first.getIsAdmin(), second.getIsAdmin());
        Assert.assertEquals(first.getState(), second.getState());
        // TODO should we check Subjects here ?
    }

    public void assertEquals(UserModelInterface first, UserEntity second) {
        Assert.assertEquals(first.getId(), second.getId());
        Assert.assertEquals(first.getFirstName(), second.getFirstName());
        Assert.assertEquals(first.getLastName(), second.getLastName());
        Assert.assertEquals(first.getSurname(), second.getSurname());
        Assert.assertEquals(first.getEmail(), second.getEmail());
        Assert.assertEquals(first.getPassword(), second.getPassword());
        Assert.assertEquals(first.getIsAdmin(), second.getIsAdmin());
        Assert.assertEquals(first.getState(), second.getState());
        // TODO should we check Subjects here ?
    }

    public void assertEquals(UserEntity first, UserModelInterface second) {
        assertEquals(second, first);
    }

}
