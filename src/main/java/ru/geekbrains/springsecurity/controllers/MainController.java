package ru.geekbrains.springsecurity.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {
    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/authenticated")
    public String pageForAuthenticatedUsers(Principal principal) {
        return "Secure part of web service: " + principal.getName();
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
