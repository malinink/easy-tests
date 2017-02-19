package easytests.services;

import easytests.entities.User;
import easytests.mappers.UsersMapper;
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
        final User user = new User();
        this.usersService.save(user);
        verify(this.usersMapper, times(1)).insert(user);
    }

    @Test
    public void saveUpdatesEntityTest() {
        final User user = new User();
        user.setId(1);
        this.usersService.save(user);
        verify(this.usersMapper, times(1)).update(user);
    }
}
