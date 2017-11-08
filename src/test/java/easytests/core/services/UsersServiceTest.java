package easytests.core.services;

import easytests.core.entities.UserEntity;
import easytests.core.mappers.UsersMapper;
import easytests.core.models.UserModel;
import easytests.core.models.UserModelInterface;
import easytests.core.options.UsersOptionsInterface;
import easytests.core.services.exceptions.DeleteUnidentifiedModelException;
import easytests.support.UsersSupport;
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

    private UsersSupport usersSupport = new UsersSupport();

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

    private List<UserEntity> getUsersEntities() {
        final List<UserEntity> usersEntities = new ArrayList<>(2);
        final UserEntity userEntityFirst = this.usersSupport.getEntityMock(
                1,
                "FirstName1",
                "LastName1",
                "Surname1",
                "email1@gmail.com",
                "hash1",
                true,
                1
        );
        final UserEntity userEntitySecond = this.usersSupport.getEntityMock(
                2,
                "FirstName2",
                "LastName2",
                "Surname2",
                "email2@gmail.com",
                "hash2",
                false,
                2
        );
        usersEntities.add(userEntityFirst);
        usersEntities.add(userEntitySecond);
        return usersEntities;
    }

    private List<UserModelInterface> getUsersModels() {
        final List<UserModelInterface> usersModels = new ArrayList<>(2);
        for (UserEntity userEntity: this.getUsersEntities()) {
            usersModels.add(this.mapUserModel(userEntity));
        }
        return usersModels;
    }

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<UserEntity> usersEntities = this.getUsersEntities();
        given(this.usersMapper.findAll()).willReturn(usersEntities);

        final List<UserModelInterface> usersModels = this.usersService.findAll();

        Assert.assertEquals(this.getUsersModels(), usersModels);
    }

    @Test
    public void testFindAllAbsentList() throws Exception {
        given(this.usersMapper.findAll()).willReturn(new ArrayList<>(0));

        final List<UserModelInterface> usersModels = this.usersService.findAll();

        Assert.assertEquals(0, usersModels.size());
    }

    @Test
    public void testFindAllWithOptions() throws Exception {
        final List<UserEntity> usersEntities = this.getUsersEntities();
        final List<UserModelInterface> usersModels = this.getUsersModels();
        final UsersOptionsInterface usersOptions = Mockito.mock(UsersOptionsInterface.class);
        given(this.usersMapper.findAll()).willReturn(usersEntities);
        given(usersOptions.withRelations(Mockito.anyList())).willReturn(usersModels);

        final List<UserModelInterface> foundedUsersModels = this.usersService.findAll(usersOptions);

        verify(usersOptions).withRelations(usersModels);
        Assert.assertEquals(usersModels, foundedUsersModels);
    }

    @Test
    public void testFindPresentModel() throws Exception {
        final Integer id = 1;
        final UserEntity userEntity = this.usersSupport.getEntityMock(
                id,
                "NewFirstName",
                "NewLastName1",
                "NewSurname1",
                "new.email@gmail.com",
                "newhash1",
                false,
                3
        );
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
    public void testFindWithOptions() throws Exception {
        final Integer id = 1;
        final UserEntity userEntity = this.usersSupport.getEntityMock(
                id,
                "NewFirstName",
                "NewLastName1",
                "NewSurname1",
                "new.email@gmail.com",
                "newhash1",
                false,
                3
        );
        final UserModelInterface userModel = this.mapUserModel(userEntity);
        final UsersOptionsInterface usersOptions = Mockito.mock(UsersOptionsInterface.class);
        given(this.usersMapper.find(id)).willReturn(userEntity);
        given(usersOptions.withRelations(userModel)).willReturn(userModel);

        final UserModelInterface foundedUserModel = this.usersService.find(id, usersOptions);

        Assert.assertEquals(userModel, foundedUserModel);
        verify(usersOptions).withRelations(userModel);
    }

    @Test
    public void testFindByEmailPresentModel() throws Exception {
        final String email = "new.email@gmail.com";
        final UserEntity userEntity = this.usersSupport.getEntityMock(
                1,
                "NewFirstName",
                "NewLastName1",
                "NewSurname1",
                email,
                "newhash1",
                false,
                3
        );
        given(this.usersMapper.findByEmail(email)).willReturn(userEntity);

        final UserModelInterface userModel = this.usersService.findByEmail(email);

        Assert.assertEquals(this.mapUserModel(userEntity), userModel);
    }

    @Test
    public void testFindByEmailAbsentModel() throws Exception {
        final String email = "absent.email@gmail.com";
        given(this.usersMapper.findByEmail(email)).willReturn(null);

        final UserModelInterface userModel = this.usersService.findByEmail(email);

        Assert.assertEquals(null, userModel);
    }

    @Test
    public void testFindByEmailWithOptions() throws Exception {
        final String email = "new.email@gmail.com";
        final UserEntity userEntity = this.usersSupport.getEntityMock(
                1,
                "NewFirstName",
                "NewLastName1",
                "NewSurname1",
                email,
                "newhash1",
                false,
                3
        );
        final UserModelInterface userModel = this.mapUserModel(userEntity);
        final UsersOptionsInterface usersOptions = Mockito.mock(UsersOptionsInterface.class);
        given(this.usersMapper.findByEmail(email)).willReturn(userEntity);
        given(usersOptions.withRelations(userModel)).willReturn(userModel);

        final UserModelInterface foundedUserModel = this.usersService.findByEmail(email, usersOptions);

        Assert.assertEquals(userModel, foundedUserModel);
        verify(usersOptions).withRelations(userModel);
    }

    @Test
    public void testSaveCreatesEntity() throws Exception {
        final UserModelInterface userModel = this.usersSupport.getModelMock(
                null,
                "FirstName",
                "LastName",
                "Surname",
                "email@gmail.com",
                "hash",
                true,
                1
        );
        doAnswer(invocation -> {
            final UserEntity userEntity = (UserEntity) invocation.getArguments()[0];
            userEntity.setId(5);
            return null;
        }).when(this.usersMapper).insert(Mockito.any(UserEntity.class));

        this.usersService.save(userModel);

        // TODO realize that bug
        //verify(this.usersMapper, times(1)).insert(this.mapUserEntity(userModel));
        verify(userModel, times(1)).setId(5);
    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {
        final UserModelInterface userModel = this.usersSupport.getModelMock(
                1,
                "FirstName",
                "LastName",
                "Surname",
                "email@gmail.com",
                "hash",
                true,
                1
        );

        this.usersService.save(userModel);

        verify(this.usersMapper, times(1)).update(this.mapUserEntity(userModel));
    }

    @Test
    public void testSaveWithOptions() throws Exception {
        final UserModelInterface userModel = this.usersSupport.getModelMock(
                null,
                "FirstName",
                "LastName",
                "Surname",
                "email@gmail.com",
                "hash",
                true,
                1
        );
        final UsersOptionsInterface usersOptions = Mockito.mock(UsersOptionsInterface.class);

        this.usersService.save(userModel, usersOptions);

        verify(usersOptions).saveWithRelations(userModel);
    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {
        final UserModelInterface userModel = this.usersSupport.getModelMock(
                1,
                "FirstName",
                "LastName",
                "Surname",
                "email@gmail.com",
                "hash",
                true,
                1
        );

        this.usersService.delete(userModel);

        verify(this.usersMapper, times(1)).delete(this.mapUserEntity(userModel));
    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {
        final UserModelInterface userModel = this.usersSupport.getModelMock(
                null,
                "FirstName",
                "LastName",
                "Surname",
                "email@gmail.com",
                "hash",
                true,
                1
        );

        exception.expect(DeleteUnidentifiedModelException.class);
        this.usersService.delete(userModel);
    }

    @Test
    public void testDeleteWithOptions() throws Exception {
        final UserModelInterface userModel = this.usersSupport.getModelMock(
                1,
                "FirstName",
                "LastName",
                "Surname",
                "email@gmail.com",
                "hash",
                true,
                1
        );
        final UsersOptionsInterface usersOptions = Mockito.mock(UsersOptionsInterface.class);

        this.usersService.delete(userModel, usersOptions);

        verify(usersOptions).deleteWithRelations(userModel);
    }

    @Test
    public void testSetAllOptionsDependencies() throws Exception {
        // TODO @malinink test private withServices on Options
        // need we really test realization here ?
    }
}
