package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDAO {
    public List<User> getAllUsers();
    public User getUser(int id);
    public User getUserByName(String name);
    public void save(User user);
    public void update(int id, User upUser);
    public void delete(int id);
}
