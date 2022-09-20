package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;

@Controller
@RequestMapping
public class AppController {
    private final UserService us;
    public AppController(UserService us) {
        this.us = us;
    }

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/user")
    public String user(Model model, Principal principal) {
        model.addAttribute("admin", us.getUserByEmail(principal.getName()));
        return "admin";
    }

    @GetMapping("/login")
    public RedirectView redirectWithUsingRedirectView() {
        return new RedirectView("/");
    }
}