package mk.finki.ukim.mk.laba.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final CustomUsernameAndPasswordAuthenticationProvider customUsernameAndPasswordAuthenticationProvider;

    public WebSecurityConfig(PasswordEncoder passwordEncoder, CustomUsernameAndPasswordAuthenticationProvider customUsernameAndPasswordAuthenticationProvider) {
        this.passwordEncoder = passwordEncoder;
        this.customUsernameAndPasswordAuthenticationProvider = customUsernameAndPasswordAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/balloons", "/", "/assets/**", "/register", "/balloonOrder").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .failureForwardUrl("/login?error=BadCredentials")
                .defaultSuccessUrl("/balloons", true)
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("balloonClientId")
                .logoutSuccessUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("tashkov")
                .password(passwordEncoder.encode("tash"))
                .authorities("ROLE_USER")
                .and()
                .withUser("admin")
                .password(passwordEncoder.encode("admin"))
                .authorities("ROLE_ADMIN");
//        auth.authenticationProvider(customUsernameAndPasswordAuthenticationProvider);
    }


}
