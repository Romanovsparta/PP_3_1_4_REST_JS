package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();
    User getUser(int id);
    User getUserByEmail(String email);
    void saveOrUpdate(User user);
    void delete(int id);
}
