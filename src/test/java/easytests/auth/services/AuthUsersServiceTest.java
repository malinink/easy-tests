package easytests.auth.services;

import easytests.models.UserModel;
import easytests.models.UserModelInterface;
import easytests.services.UsersService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author DoZor-80
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:database.test.properties"})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/mappersTestData.sql")
public class AuthUsersServiceTest {
    private String email;
    private UsersService usersService;
    private UserModelInterface userModel;

    @Autowired
    private AuthUsersService authUsersService;

    @Before
    public void setUp() {
        usersService = Mockito.mock(UsersService.class);
        email = "email3@gmail.com";
        userModel = new UserModel();
        userModel.setId(1);
        userModel.setFirstName("FirstName3");
        userModel.setLastName("LastName3");
        userModel.setSurname("Surname3");
        userModel.setEmail(email);
        userModel.setPassword("hash3");
        userModel.setIsAdmin(true);
        userModel.setState(3);
    }

    @Test
    public void testLoadUserByUsername() throws Exception {
        when(usersService.findByEmail(email)).thenReturn(userModel);
        final UserDetails user = this.authUsersService.loadUserByUsername(email);
        Assert.assertEquals(userModel.getEmail(), user.getUsername());
        Assert.assertEquals(userModel.getPassword(), user.getPassword());
        Assert.assertEquals(userModel.getState() == 3, user.isEnabled());
    }
}
