package ru.kata.spring.boot_security.demo.controller;

import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.model.User;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/admin/api")
public class RestControllerForAdmin {
    private final UserService us;
    public RestControllerForAdmin(UserService us) {
        this.us = us;
    }

    @GetMapping("/auth")
    public User getAdmin(Principal principal) {
        return us.getUserByEmail(principal.getName());
    }

    @GetMapping("/users")
    public List<User> showAllUsers() {
        return us.getAllUsers();
    }

    @PostMapping
    public User save(@RequestBody User user) {
        us.save(user);
        return user;
    }

    @PutMapping
    public User update(@RequestBody User user) {
        us.update(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable int id){
        us.delete(id);
        return id;
    }
}