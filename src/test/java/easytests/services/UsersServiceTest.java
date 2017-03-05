package easytests.services;

import easytests.entities.UserEntity;
import easytests.mappers.UsersMapper;
import easytests.models.UserModel;
import easytests.models.UserModelInterface;
import org.junit.*;
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

    @MockBean
    private UsersMapper usersMapper;

    @Autowired
    private UsersService usersService;

    @Test
    public void testFindAbsentModel() {
        final Integer id = 10;
        final UserModelInterface userModel = this.usersService.find(id);
        Assert.assertEquals(null, userModel);
    }

    @Test
    public void testSaveCreatesEntity() {
        final UserModelInterface userModel = new UserModel();
        userModel.setFirstName("FirstName");
        userModel.setLastName("LastName");
        userModel.setSurname("Surname");
        final UserEntity userEntity = new UserEntity();
        userEntity.map(userModel);
        this.usersService.save(userModel);

        verify(this.usersMapper, times(1)).insert(userEntity);
    }

    @Test
    public void testSaveUpdatesEntity() {
        final UserModelInterface userModel = new UserModel();
        userModel.setId(1);
        userModel.setFirstName("FirstName");
        userModel.setLastName("LastName");
        userModel.setSurname("Surname");
        final UserEntity userEntity = new UserEntity();
        userEntity.map(userModel);
        this.usersService.save(userModel);

        verify(this.usersMapper, times(1)).update(userEntity);
    }
}
