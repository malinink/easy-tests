package easytests.services;

import easytests.entities.UserEntity;
import easytests.mappers.UsersMapper;
import easytests.models.UserModel;
import easytests.models.UserModelInterface;
import easytests.services.exceptions.DeleteUnidentifiedModelException;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.*;

import static org.mockito.BDDMockito.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;


/**
 * @author malinink
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private UsersMapper usersMapper;

    @Autowired
    private UsersService usersService;

    private UserModelInterface createUserModel(Integer id, String firstName, String lastName, String surname) {
        final UserModelInterface userModel = new UserModel();
        userModel.setId(id);
        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setSurname(surname);
        return userModel;
    }

    private UserEntity createUserEntityMock(Integer id, String firstName, String lastName, String surname) {
        final UserEntity userEntity = Mockito.mock(UserEntity.class);
        Mockito.when(userEntity.getId()).thenReturn(id);
        Mockito.when(userEntity.getFirstName()).thenReturn(firstName);
        Mockito.when(userEntity.getLastName()).thenReturn(lastName);
        Mockito.when(userEntity.getSurname()).thenReturn(surname);
        return userEntity;
    }

    private UserModelInterface mapUserModel(UserEntity userEntity) {
        final UserModelInterface userModel = new UserModel();
        userModel.map(userEntity);
        return userModel;
    }

    private UserEntity mapUserEntity(UserModelInterface userModel) {
        final UserEntity userEntity = new UserEntity();
        userEntity.map(userModel);
        return userEntity;
    }

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<UserEntity> usersEntities = new ArrayList<>(2);
        final UserEntity userEntityFirst = this.createUserEntityMock(1, "FirstName1", "LastName1", "Surname1");
        final UserEntity userEntitySecond = this.createUserEntityMock(2, "FirstName2", "LastName2", "Surname2");
        usersEntities.add(userEntityFirst);
        usersEntities.add(userEntitySecond);
        given(this.usersMapper.findAll()).willReturn(usersEntities);

        final List<UserModelInterface> usersModels = this.usersService.findAll();

        Assert.assertEquals(2, usersModels.size());
        Assert.assertEquals(usersModels.get(0), this.mapUserModel(userEntityFirst));
        Assert.assertEquals(usersModels.get(1), this.mapUserModel(userEntitySecond));
    }

    @Test
    public void testFindAllAbsentList() throws Exception {
        given(this.usersMapper.findAll()).willReturn(new ArrayList<>(0));

        final List<UserModelInterface> usersModels = this.usersService.findAll();

        Assert.assertEquals(0, usersModels.size());
    }

    @Test
    public void testFindPresentModel() throws Exception {
        final Integer id = 1;
        final UserEntity userEntity = this.createUserEntityMock(id, "NewFirstName", "NewLastName", "NewSurname");
        given(this.usersMapper.find(id)).willReturn(userEntity);

        final UserModelInterface userModel = this.usersService.find(id);

        Assert.assertEquals(this.mapUserModel(userEntity), userModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        given(this.usersMapper.find(id)).willReturn(null);

        final UserModelInterface userModel = this.usersService.find(id);

        Assert.assertEquals(null, userModel);
    }

    @Test
    public void testSaveCreatesEntity() throws Exception {
        final UserModelInterface userModel = this.createUserModel(null, "FirstName", "LastName", "Surname");

        this.usersService.save(userModel);

        verify(this.usersMapper, times(1)).insert(this.mapUserEntity(userModel));
    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {
        final UserModelInterface userModel = this.createUserModel(1, "FirstName", "LastName", "Surname");

        this.usersService.save(userModel);

        verify(this.usersMapper, times(1)).update(this.mapUserEntity(userModel));
    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {
        final UserModelInterface userModel = this.createUserModel(1, "FirstName", "LastName", "Surname");

        this.usersService.delete(userModel);

        verify(this.usersMapper, times(1)).delete(this.mapUserEntity(userModel));
    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {
        final UserModelInterface userModel = this.createUserModel(null, "FirstName", "LastName", "Surname");

        exception.expect(DeleteUnidentifiedModelException.class);
        this.usersService.delete(userModel);
    }
}
