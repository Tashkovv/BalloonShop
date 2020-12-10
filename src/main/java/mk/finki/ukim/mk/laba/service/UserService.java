package mk.finki.ukim.mk.laba.service;

import mk.finki.ukim.mk.laba.model.User;

public interface UserService {
    User login(String username, String password);
    User register(String username, String password, String repeatPassword);
}
