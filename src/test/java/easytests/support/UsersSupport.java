package easytests.support;

import easytests.core.entities.UserEntity;
import easytests.core.models.UserModelInterface;
import easytests.core.models.empty.ModelsListEmpty;
import org.junit.Assert;
import org.mockito.Mockito;

import java.util.List;


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

    private UserEntity getEntityMock(
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

    private UserModelInterface getModelMock(
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

    public void assertEquals(UserEntity expected, UserEntity actual) {
        assertEquals(expected, actual, false);
    }

    public void assertEqualsWithoutId(UserEntity expected, UserEntity actual) {
        assertEquals(expected, actual, true);
    }

    private void assertEquals(UserEntity expected, UserEntity actual, Boolean exceptId) {
        if (!exceptId) {
            Assert.assertEquals(expected.getId(), actual.getId());
        }
        Assert.assertEquals(expected.getFirstName(), actual.getFirstName());
        Assert.assertEquals(expected.getLastName(), actual.getLastName());
        Assert.assertEquals(expected.getSurname(), actual.getSurname());
        Assert.assertEquals(expected.getEmail(), actual.getEmail());
        Assert.assertEquals(expected.getPassword(), actual.getPassword());
        Assert.assertEquals(expected.getIsAdmin(), actual.getIsAdmin());
        Assert.assertEquals(expected.getState(), actual.getState());
    }

    public void assertNotEquals(UserEntity unexpected, UserEntity actual) {
        assertNotEquals(unexpected, actual, false);
    }

    public void assertNotEqualsWithoutId(UserEntity unexpected, UserEntity actual) {
        assertNotEquals(unexpected, actual, true);
    }

    private void assertNotEquals(UserEntity unexpected, UserEntity actual, Boolean exceptId) {
        if (!exceptId) {
            Assert.assertNotEquals(unexpected.getId(), actual.getId());
        }
        Assert.assertNotEquals(unexpected.getFirstName(), actual.getFirstName());
        Assert.assertNotEquals(unexpected.getLastName(), actual.getLastName());
        Assert.assertNotEquals(unexpected.getSurname(), actual.getSurname());
        Assert.assertNotEquals(unexpected.getEmail(), actual.getEmail());
        Assert.assertNotEquals(unexpected.getPassword(), actual.getPassword());
        Assert.assertNotEquals(unexpected.getIsAdmin(), actual.getIsAdmin());
        Assert.assertNotEquals(unexpected.getState(), actual.getState());
    }

    public void assertEquals(UserModelInterface expected, UserModelInterface actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getFirstName(), actual.getFirstName());
        Assert.assertEquals(expected.getLastName(), actual.getLastName());
        Assert.assertEquals(expected.getSurname(), actual.getSurname());
        Assert.assertEquals(expected.getEmail(), actual.getEmail());
        Assert.assertEquals(expected.getPassword(), actual.getPassword());
        Assert.assertEquals(expected.getIsAdmin(), actual.getIsAdmin());
        Assert.assertEquals(expected.getState(), actual.getState());
        Assert.assertEquals(expected.getSubjects(), actual.getSubjects());
    }

    public void assertEquals(UserModelInterface expected, UserEntity actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getFirstName(), actual.getFirstName());
        Assert.assertEquals(expected.getLastName(), actual.getLastName());
        Assert.assertEquals(expected.getSurname(), actual.getSurname());
        Assert.assertEquals(expected.getEmail(), actual.getEmail());
        Assert.assertEquals(expected.getPassword(), actual.getPassword());
        Assert.assertEquals(expected.getIsAdmin(), actual.getIsAdmin());
        Assert.assertEquals(expected.getState(), actual.getState());
    }

    public void assertEquals(UserEntity expected, UserModelInterface actual) {
        assertEquals(actual, expected);
        Assert.assertEquals(new ModelsListEmpty(), actual.getSubjects());
    }

    public void assertModelsListEquals(List<UserModelInterface> expected, List<UserModelInterface> actual) {
        Assert.assertEquals(expected.size(), actual.size());
        Integer i = 0;
        for (UserModelInterface userModel: expected) {
            this.assertEquals(userModel, actual.get(i));
            i++;
        }
    }

    public void assertEntitiesListEquals(List<UserEntity> expected, List<UserEntity> actual) {
        Assert.assertEquals(expected.size(), actual.size());
        Integer i = 0;
        for (UserEntity userEntity: expected) {
            this.assertEquals(userEntity, actual.get(i));
            i++;
        }
    }

    public void assertModelsEqualsWithoutIdandSubjects(UserModelInterface expected, UserModelInterface actual) {
        Assert.assertEquals(expected.getFirstName(), actual.getFirstName());
        Assert.assertEquals(expected.getLastName(), actual.getLastName());
        Assert.assertEquals(expected.getSurname(), actual.getSurname());
        Assert.assertEquals(expected.getEmail(), actual.getEmail());
        Assert.assertEquals(expected.getPassword(), actual.getPassword());
        Assert.assertEquals(expected.getIsAdmin(), actual.getIsAdmin());
        Assert.assertEquals(expected.getState(), actual.getState());
    }

    public UserEntity getAdminUser() {
        return this.getEntityMock(
                5,
                "FirstName",
                "LastName",
                "Surname",
                "email@mail.ru",
                "hash",
                true,
                1
        );
    }

    public UserEntity getNotAdminUser() {
        return this.getEntityMock(
                6,
                "FirstName",
                "LastName",
                "Surname1",
                "gmail@gmail.com",
                "hash",
                false,
                1
        );
    }

}
