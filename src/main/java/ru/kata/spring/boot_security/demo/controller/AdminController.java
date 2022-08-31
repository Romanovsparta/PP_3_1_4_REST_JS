package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;
import java.util.Arrays;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService us;

    public AdminController(UserService us) {
        this.us = us;
    }

    @GetMapping
    public String admin(Model model, Principal principal) {
        model.addAttribute("admin", us.getUserByEmail(principal.getName()));
        model.addAttribute("allRoles", Arrays.asList("ADMIN", "USER"));
        model.addAttribute("users", us.getAllUsers());
        model.addAttribute("newUser", new User());
        return "admin";
    }
    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        us.save(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        us.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        us.delete(id);
        return "redirect:/admin";
    }
}