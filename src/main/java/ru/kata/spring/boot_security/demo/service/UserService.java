package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserService extends UserDetailsService {
    public List<User> getAllUsers();
    public User getUser(int id);
    public User getUserByName(String name);
    public void save(User user);
    public void update(int id, User upUser);
    public void delete(int id);
}
