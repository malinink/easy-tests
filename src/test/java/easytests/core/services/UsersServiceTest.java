package easytests.core.services;

import easytests.core.entities.UserEntity;
import easytests.core.mappers.UsersMapper;
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
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
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

    @Captor
    private ArgumentCaptor<List<UserModelInterface>> usersModelsListCaptor;

    private List<UserEntity> getUsersFixturesEntities() {
        final List<UserEntity> usersEntities = new ArrayList<>(2);
        usersEntities.add(this.usersSupport.getEntityFixtureMock(0));
        usersEntities.add(this.usersSupport.getEntityFixtureMock(1));
        return usersEntities;
    }

    private List<UserModelInterface> getUsersFixturesModels() {
        final List<UserModelInterface> usersModels = new ArrayList<>(2);
        usersModels.add(this.usersSupport.getModelFixtureMock(0));
        usersModels.add(this.usersSupport.getModelFixtureMock(1));
        return usersModels;
    }

    private void assertEquals(List<UserModelInterface> first, List<UserModelInterface> second) {
        Assert.assertEquals(first.size(), second.size());
        Integer i = 0;
        for (UserModelInterface userModel: first) {
            this.usersSupport.assertEquals(userModel, second.get(i));
            i++;
        }
    }

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<UserEntity> usersEntities = this.getUsersFixturesEntities();
        when(this.usersMapper.findAll()).thenReturn(usersEntities);

        final List<UserModelInterface> usersModels = this.usersService.findAll();

        this.assertEquals(this.getUsersFixturesModels(), usersModels);
    }
    @Test
    public void testFindAllAbsentList() throws Exception {
        when(this.usersMapper.findAll()).thenReturn(new ArrayList<>(0));

        final List<UserModelInterface> usersModels = this.usersService.findAll();

        Assert.assertEquals(0, usersModels.size());
    }

    @Test
    public void testFindAllWithOptions() throws Exception {
        final List<UserModelInterface> usersFixturesModels = this.getUsersFixturesModels();
        final List<UserEntity> usersFixturesEntities = this.getUsersFixturesEntities();
        final UsersOptionsInterface usersOptions = mock(UsersOptionsInterface.class);
        when(this.usersMapper.findAll()).thenReturn(usersFixturesEntities);
        when(usersOptions.withRelations(this.usersModelsListCaptor.capture())).thenReturn(usersFixturesModels);

        final List<UserModelInterface> foundedUsersModels = this.usersService.findAll(usersOptions);

        this.assertEquals(usersFixturesModels, this.usersModelsListCaptor.getValue());
        Assert.assertSame(usersFixturesModels, foundedUsersModels);
    }

    @Test
    public void testFindPresentModel() throws Exception {
        final Integer presentId = 1;
        final UserEntity userFixtureEntity = this.usersSupport.getEntityFixtureMock(0);
        when(this.usersMapper.find(presentId)).thenReturn(userFixtureEntity);

        final UserModelInterface userModel = this.usersService.find(presentId);

        this.usersSupport.assertEquals(this.usersSupport.getModelFixtureMock(0), userModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer absentId = 12;
        when(this.usersMapper.find(absentId)).thenReturn(null);

        final UserModelInterface userModel = this.usersService.find(absentId);

        Assert.assertEquals(null, userModel);
    }

    @Test
    public void testFindWithOptionsPresentModel() throws Exception {
        final Integer presentId = 1;
        final ArgumentCaptor<UserModelInterface> userModelCaptor = ArgumentCaptor.forClass(UserModelInterface.class);
        final UserModelInterface userFixtureModel = this.usersSupport.getModelFixtureMock(0);
        final UserEntity userFixtureEntity = this.usersSupport.getEntityFixtureMock(0);
        final UsersOptionsInterface usersOptions = mock(UsersOptionsInterface.class);
        when(this.usersMapper.find(presentId)).thenReturn(userFixtureEntity);
        when(usersOptions.withRelations(userModelCaptor.capture())).thenReturn(userFixtureModel);

        final UserModelInterface foundedUserModel = this.usersService.find(presentId, usersOptions);

        this.usersSupport.assertEquals(userFixtureModel, userModelCaptor.getValue());
        Assert.assertSame(userFixtureModel, foundedUserModel);
    }

    @Test
    public void testFindWithOptionsAbsentModel() throws Exception {
        final Integer absentId = 12;
        final ArgumentCaptor<UserModelInterface> userModelCaptor = ArgumentCaptor.forClass(UserModelInterface.class);
        final UsersOptionsInterface usersOptions = mock(UsersOptionsInterface.class);
        when(this.usersMapper.find(absentId)).thenReturn(null);
        when(usersOptions.withRelations(userModelCaptor.capture())).thenReturn(null);

        final UserModelInterface foundedUserModel = this.usersService.find(absentId, usersOptions);

        Assert.assertNull(userModelCaptor.getValue());
        Assert.assertNull(foundedUserModel);
    }

    @Test
    public void testFindByEmailPresentModel() throws Exception {
        final UserEntity userFixtureEntity = this.usersSupport.getEntityFixtureMock(0);
        when(this.usersMapper.findByEmail(userFixtureEntity.getEmail())).thenReturn(userFixtureEntity);

        final UserModelInterface foundedUserModel = this.usersService.findByEmail(userFixtureEntity.getEmail());

        this.usersSupport.assertEquals(this.usersSupport.getModelFixtureMock(0), foundedUserModel);
    }

    @Test
    public void testFindByEmailAbsentModel() throws Exception {
        final String absentEmail = "absent.email@gmail.com";
        when(this.usersMapper.findByEmail(absentEmail)).thenReturn(null);

        final UserModelInterface userModel = this.usersService.findByEmail(absentEmail);

        Assert.assertEquals(null, userModel);
    }

    @Test
    public void testFindByEmailWithOptions() throws Exception {
        final ArgumentCaptor<UserModelInterface> userModelCaptor = ArgumentCaptor.forClass(UserModelInterface.class);
        final String presentEmail = "new.email@gmail.com";
        final UserModelInterface userFixtureModel = this.usersSupport.getModelFixtureMock(0);
        final UserEntity userFixtureEntity = this.usersSupport.getEntityFixtureMock(0);
        final UsersOptionsInterface usersOptions = Mockito.mock(UsersOptionsInterface.class);
        when(this.usersMapper.findByEmail(presentEmail)).thenReturn(userFixtureEntity);
        when(usersOptions.withRelations(userModelCaptor.capture())).thenReturn(userFixtureModel);

        final UserModelInterface foundedUserModel = this.usersService.findByEmail(presentEmail, usersOptions);

        this.usersSupport.assertEquals(userFixtureModel, userModelCaptor.getValue());
        Assert.assertSame(userFixtureModel, foundedUserModel);
    }

    @Test
    public void testSaveCreatesEntity() throws Exception {
        final ArgumentCaptor<UserEntity> userEntityCaptor = ArgumentCaptor.forClass(UserEntity.class);

        this.usersService.save(this.usersSupport.getModelAdditionalMock(0));

        verify(this.usersMapper, times(1)).insert(userEntityCaptor.capture());
        this.usersSupport.assertEquals(this.usersSupport.getEntityAdditionalMock(0), userEntityCaptor.getValue());
    }

    @Test
    public void testSaveNewEntityUpdatesItsId() throws Exception {
        final UserModelInterface userAdditionalModel = this.usersSupport.getModelAdditionalMock(0);
        doAnswer(invocation -> {
            final UserEntity userEntity = (UserEntity) invocation.getArguments()[0];
            userEntity.setId(5);
            return null;
        }).when(this.usersMapper).insert(Mockito.any(UserEntity.class));

        this.usersService.save(userAdditionalModel);

        verify(userAdditionalModel, times(1)).setId(5);
    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {
        final ArgumentCaptor<UserEntity> userEntityCaptor = ArgumentCaptor.forClass(UserEntity.class);
        this.usersService.save(this.usersSupport.getModelFixtureMock(0));

        verify(this.usersMapper, times(1)).update(userEntityCaptor.capture());
        this.usersSupport.assertEquals(this.usersSupport.getEntityFixtureMock(0), userEntityCaptor.getValue());
    }

    @Test
    public void testSaveWithOptions() throws Exception {
        final UserModelInterface userFixtureModel = this.usersSupport.getModelFixtureMock(0);
        final UsersOptionsInterface usersOptions = Mockito.mock(UsersOptionsInterface.class);

        this.usersService.save(userFixtureModel, usersOptions);

        verify(usersOptions, times(1)).saveWithRelations(userFixtureModel);
        verify(this.usersMapper, times(0)).update(any(UserEntity.class));
    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {
        final ArgumentCaptor<UserEntity> userEntityCaptor = ArgumentCaptor.forClass(UserEntity.class);
        this.usersService.delete(this.usersSupport.getModelFixtureMock(0));

        verify(this.usersMapper, times(1)).delete(userEntityCaptor.capture());
        this.usersSupport.assertEquals(this.usersSupport.getEntityFixtureMock(0), userEntityCaptor.getValue());
    }

    @Test
    public void testDeleteUnidentifiedModelTrowsException() throws Exception {
        final UserModelInterface userAdditionalModel = this.usersSupport.getModelAdditionalMock(0);

        exception.expect(DeleteUnidentifiedModelException.class);
        this.usersService.delete(userAdditionalModel);
    }

    @Test
    public void testDeleteWithOptions() throws Exception {
        final UserModelInterface userFixtureModel = this.usersSupport.getModelFixtureMock(0);
        final UsersOptionsInterface usersOptions = Mockito.mock(UsersOptionsInterface.class);

        this.usersService.delete(userFixtureModel, usersOptions);

        verify(usersOptions, times(1)).deleteWithRelations(userFixtureModel);
        verify(this.usersMapper, times(0)).delete(any(UserEntity.class));
    }

}
