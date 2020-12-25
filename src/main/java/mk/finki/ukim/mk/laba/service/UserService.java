package mk.finki.ukim.mk.laba.service;

import mk.finki.ukim.mk.laba.model.User;

import java.util.Optional;

public interface UserService {
    User login(String username, String password);
    User register(String username, String password, String repeatPassword);
    Optional<User> findUserById(Long id);
    Optional<User> findUserByUsername(String username);
}
