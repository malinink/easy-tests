package easytests.services;

import easytests.entities.UserEntity;
import easytests.mappers.UsersMapper;
import easytests.models.UserModel;
import org.apache.catalina.User;
import org.junit.*;
import org.junit.runner.*;
import static org.mockito.BDDMockito.*;
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
    public void saveCreatesEntityTest() {
        final UserModel userModel = new UserModel();
        final UserEntity userEntity = new UserEntity();
        userEntity.map(userModel);
        this.usersService.save(userModel);
        verify(this.usersMapper, times(1)).insert(userEntity);
    }

    @Test
    public void saveUpdatesEntityTest() {
        final UserModel userModel = new UserModel();
        userModel.setId(1);
        final UserEntity userEntity = new UserEntity();
        userEntity.map(userModel);
        this.usersService.save(userModel);
        verify(this.usersMapper, times(1)).update(userEntity);
    }
}
