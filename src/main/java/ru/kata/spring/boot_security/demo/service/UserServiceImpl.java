package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.User;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    @Override
    @Transactional(readOnly=true)
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional(readOnly=true)
    public User getUser(int id) {
        return userDAO.getUser(id);
    }

    @Override
    @Transactional(readOnly=true)
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    /*@Override
    @Transactional
    public void saveOrUpdate(User user) {
        userDAO.saveOrUpdate(user);
    }*/

    @Override
    @Transactional
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    @Transactional
    public void update(User upUser) {
        userDAO.update(upUser);
    }

    @Override
    @Transactional
    public void delete(int id) {
        userDAO.delete(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        return getUserByEmail(email);
    }
}
