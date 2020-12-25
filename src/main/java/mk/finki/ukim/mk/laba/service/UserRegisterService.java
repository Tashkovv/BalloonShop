package mk.finki.ukim.mk.laba.service;

import mk.finki.ukim.mk.laba.model.User;
import mk.finki.ukim.mk.laba.model.enums.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserRegisterService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, Role role);
}
