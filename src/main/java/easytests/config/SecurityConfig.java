package easytests.config;

import easytests.auth.handlers.AuthenticationFailureHandler;
import easytests.auth.services.AuthUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * @author malinink
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthUsersService authUsersService;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authUsersService);
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(authUsersService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final String signInUrl = "/auth/sign-in";
        final String usernameParameter = "login";
        final String userRole = "hasRole('USER')";
        http
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .antMatchers("/users/**").access(userRole)
                .antMatchers("/personal/**").access(userRole)
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
            .csrf()
                .and()
            .rememberMe();
    }
}
