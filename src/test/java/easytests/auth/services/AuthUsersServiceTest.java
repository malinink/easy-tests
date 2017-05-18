package easytests.auth.services;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.when;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author DoZor-80
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthUsersServiceTest {

    private UserDetailsService details;
    private User expectedUser;
    private String username;

    @Autowired
    private AuthUsersService authUsersService;

    @Before
    public void setUp() {
        details = Mockito.mock(UserDetailsService.class);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_TESTEE"));
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        username = "email1@gmail.com";
        expectedUser = new User(username, "", true, true, true, true, authorities);
    }

    @Test
    public void testLoadUserByUsername() throws Exception {
        when(details.loadUserByUsername(username)).thenReturn(expectedUser);
        Assert.assertEquals(expectedUser, authUsersService.loadUserByUsername(username));
    }
}
