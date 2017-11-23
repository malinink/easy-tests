package easytests.auth.services;

import easytests.core.models.UserModelInterface;
import easytests.core.services.UsersService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * @author malinink
 */
@Service
public class AuthUsersService implements UserDetailsService {
    @Autowired
    private UsersService usersService;

    private List<GrantedAuthority> getAuthorities(UserModelInterface userModel) {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_TESTEE"));
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        if (userModel.getIsAdmin()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final UserModelInterface userModel = usersService.findByEmail(email);
        if (userModel == null) {
            throw new UsernameNotFoundException("No user found with login: " + email);
        }
        return new User(
                userModel.getEmail(),
                userModel.getPassword(),
                userModel.getState() == 3 ? true : false,
                true,
                true,
                true,
                this.getAuthorities(userModel)
        );
    }
}
