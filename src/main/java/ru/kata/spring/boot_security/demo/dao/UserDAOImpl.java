package ru.kata.spring.boot_security.demo.dao;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT user FROM User user", User.class).getResultList();
    }

    public User getUser(int id) {
        return (User) entityManager.createQuery("FROM User WHERE id = :id").setParameter("id", id).getSingleResult();
    }

    public User getUserByName(String name) {
        return (User) entityManager.createQuery("FROM User WHERE username = :username").setParameter("username", name).getSingleResult();
    }

    public void save(User user) {
        entityManager.persist(user);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        if (user.getRole() != null) {
            List<Role> roles = new ArrayList<>();
            if (user.getRole().toLowerCase().contains("admin")) {
                roles.add(new Role(1, "ROLE_ADMIN"));
            }
            if (user.getRole().toLowerCase().contains("user")) {
                roles.add(new Role(2, "ROLE_USER"));
            }
            user.setRoles(roles);
        }
    }

    public void update(int id, User upUser) {
        entityManager.merge(upUser);
        getUser(id).setPassword(new BCryptPasswordEncoder().encode(upUser.getPassword()));
        if (upUser.getRole() != null) {
            List<Role> roles = new ArrayList<>();
            if (upUser.getRole().toLowerCase().contains("admin")) {
                roles.add(new Role(1, "ROLE_ADMIN"));
            }
            if (upUser.getRole().toLowerCase().contains("user")) {
                roles.add(new Role(2, "ROLE_USER"));
            }
            getUser(id).setRoles(roles);
        } else {
            getUser(id).setRoles(null);
        }
    }

    public void delete(int id) {
        entityManager.remove(getUser(id));
    }
}
