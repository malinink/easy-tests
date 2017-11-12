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

    protected static Object[][] fixtures = new Object[][]{
            {
                    1,
                    "FirstName1",
                    "LastName1",
                    "Surname1",
                    "email1@gmail.com",
                    "hash1",
                    true,
                    1
            },
            {
                    2,
                    "FirstName2",
                    "LastName2",
                    "Surname2",
                    "email2@gmail.com",
                    "hash2",
                    false,
                    2
            },
            {
                    3,
                    "FirstName3",
                    "LastName3",
                    "Surname3",
                    "email3@gmail.com",
                    "hash3",
                    true,
                    3
            },
    };

    protected static Object[][] additional = new Object[][]{
            {
                    // for insert entity
                    null,
                    "FirstName",
                    "LastName",
                    "Surname",
                    "mail@mail.ru",
                    "password",
                    true,
                    1
            },
            {
                    // for update entity with id = 1
                    1,
                    "NewFirstName",
                    "NewLastName",
                    "NewSurname4",
                    "new.mail@mail.ru",
                    "new.hash",
                    false,
                    2
            },
    };

    public UserEntity getEntityFixtureMock(Integer index) {
        return this.getEntityMock(fixtures[index]);
    }

    public UserEntity getEntityAdditionalMock(Integer index) {
        return this.getEntityMock(additional[index]);
    }

    private UserEntity getEntityMock(Object[] data) {
        return this.getEntityMock(
                (Integer) data[0],
                (String) data[1],
                (String) data[2],
                (String) data[3],
                (String) data[4],
                (String) data[5],
                (Boolean) data[6],
                (Integer) data[7]
        );
    }

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

    public UserModelInterface getModelFixtureMock(Integer index) {
        return this.getModelMock(fixtures[index]);
    }

    public UserModelInterface getModelAdditionalMock(Integer index) {
        return this.getModelMock(additional[index]);
    }

    private UserModelInterface getModelMock(Object[] data) {
        return this.getModelMock(
                (Integer) data[0],
                (String) data[1],
                (String) data[2],
                (String) data[3],
                (String) data[4],
                (String) data[5],
                (Boolean) data[6],
                (Integer) data[7]
        );
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

    public void assertEquals(UserEntity first, UserEntity second) {
        assertEquals(first, second, false);
    }

    public void assertEqualsWithoutId(UserEntity first, UserEntity second) {
        assertEquals(first, second, true);
    }

    private void assertEquals(UserEntity first, UserEntity second, Boolean exceptId) {
        if (!exceptId) {
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

    public void assertNotEquals(UserEntity first, UserEntity second) {
        assertNotEquals(first, second, false);
    }

    public void assertNotEqualsWithoutId(UserEntity first, UserEntity second) {
        assertNotEquals(first, second, true);
    }

    private void assertNotEquals(UserEntity first, UserEntity second, Boolean exceptId) {
        if (!exceptId) {
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
        Assert.assertEquals(first.getSubjects(), second.getSubjects());
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
    }

    public void assertEquals(UserEntity first, UserModelInterface second) {
        assertEquals(second, first);
        Assert.assertEquals(new ModelsListEmpty(), second.getSubjects());
    }

}
