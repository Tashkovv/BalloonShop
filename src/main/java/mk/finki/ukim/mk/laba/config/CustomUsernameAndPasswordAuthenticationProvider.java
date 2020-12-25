package mk.finki.ukim.mk.laba.config;

import mk.finki.ukim.mk.laba.service.UserRegisterService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomUsernameAndPasswordAuthenticationProvider implements AuthenticationProvider {
    private final UserRegisterService userRegisterService;

    private final PasswordEncoder passwordEncoder;

    public CustomUsernameAndPasswordAuthenticationProvider(UserRegisterService userRegisterService, PasswordEncoder passwordEncoder) {
        this.userRegisterService = userRegisterService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        if ("".equals(name) || "".equals(password)){
            throw new BadCredentialsException("InvalidCredentials!");
        }

        UserDetails userDetails = userRegisterService.loadUserByUsername(name);

        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("InvalidCredentials!");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
