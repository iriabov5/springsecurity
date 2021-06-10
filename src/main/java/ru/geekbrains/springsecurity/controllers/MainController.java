package ru.geekbrains.springsecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.springsecurity.entities.User;
import ru.geekbrains.springsecurity.services.UserService;

import java.security.Principal;

@RestController
public class MainController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/authenticated")
    public String pageForAuthenticatedUsers(Principal principal, Model model) {
         User user = userService.findByUsername(principal.getName());
         model.addAttribute(user);
        return "Secure part of web service: " + user.getUsername() + ", " + user.getEmail();

    }

    @GetMapping("/read_profile")
    public String readProfilePage() {
        return "Read profile page";
    }

    @GetMapping("/only_for_admins")
    public String onlyForAdminPage() {
        return "Admins Page";
    }
}