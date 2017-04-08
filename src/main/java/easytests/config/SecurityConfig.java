package easytests.config;

import easytests.auth.handlers.AuthenticationFailureHandler;
import easytests.auth.services.AuthUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * @author malinink
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthUsersService authUsersService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final String signInUrl = "/auth/sign-in";
        final String usernameParameter = "login";
        http
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/users/**").access("hasRole('USER')")
                .and()
            .userDetailsService(this.authUsersService)
            .formLogin()
                .loginPage(signInUrl)
                .loginProcessingUrl(signInUrl)
                .usernameParameter(usernameParameter)
                .passwordParameter("password")
                .defaultSuccessUrl("/users")
                .failureHandler(new AuthenticationFailureHandler("/auth/sign-in?error", usernameParameter))
                .and()
            .logout()
                .logoutUrl("/auth/sign-out")
                .logoutSuccessUrl(signInUrl)
                .clearAuthentication(true)
                .and()
            .rememberMe();
    }
}
