package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService us;

    public UserController(UserService us) {
        this.us = us;
    }

    @GetMapping
    public String user(Model model, Principal principal) {
        model.addAttribute("user", us.getUserByName(principal.getName()));
        return "user";
    }
}