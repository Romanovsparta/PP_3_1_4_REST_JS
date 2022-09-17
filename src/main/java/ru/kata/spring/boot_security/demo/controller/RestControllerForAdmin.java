package ru.kata.spring.boot_security.demo.controller;

import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class RestControllerForAdmin {
    private final UserService us;
    public RestControllerForAdmin(UserService us) {
        this.us = us;
    }

    @GetMapping("/api/users")
    //@CrossOrigin(origins = "*")
    public List<User> showAllUsers() {
        return us.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return us.getUser(id);
    }

    @GetMapping("/api/{email}")
    //@CrossOrigin(origins = "*")
    public User getUserByEmail(@PathVariable String email) {
        return us.getUserByEmail(email);
    }

    @PostMapping
    //@CrossOrigin(origins = "*")
    public User save(@RequestBody User user) {
        us.saveOrUpdate(user);
        return user;
    }

    @PutMapping
    //@CrossOrigin(origins = "*")
    public User update(@RequestBody User user) {
        us.saveOrUpdate(user);
        return user;
    }

    @DeleteMapping("/{id}")
    //@CrossOrigin(origins = "*")
    public int delete(@PathVariable int id){
        us.delete(id);
        return id;
    }
}