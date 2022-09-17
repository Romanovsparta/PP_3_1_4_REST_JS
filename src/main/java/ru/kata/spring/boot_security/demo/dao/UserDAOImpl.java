package ru.kata.spring.boot_security.demo.dao;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private final PasswordEncoder passwordEncoder;

    public UserDAOImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT user FROM User user", User.class).getResultList();
    }

    public User getUser(int id) {
        return (User) entityManager.createQuery("FROM User WHERE id = :id").setParameter("id", id).getSingleResult();
    }

    public User getUserByEmail(String email) {
        return (User) entityManager.createQuery("FROM User user JOIN FETCH user.roles WHERE user.email = :email").setParameter("email", email).getSingleResult();
    }

    public void saveOrUpdate(User user) {
        int id = entityManager.merge(user).getId();
        if (user.getPassword().length() > 0) {
            getUser(id).setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (user.getRole() != null) {
            List<Role> roles = new ArrayList<>();
            if (user.getRole().toLowerCase().contains("admin")) {
                roles.add(new Role(1, "ROLE_ADMIN"));
            }
            if (user.getRole().toLowerCase().contains("user")) {
                roles.add(new Role(2, "ROLE_USER"));
            }
            getUser(id).setRoles(roles);
        }
    }

    public void delete(int id) {
        entityManager.remove(getUser(id));
    }
}