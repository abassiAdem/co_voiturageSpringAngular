package com.example.co_voiturage.controller;

import com.example.co_voiturage.model.User;
import com.example.co_voiturage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("userRole")
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/Auth")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "Auth";
    }

    @PostMapping("/Auth")
    public String loginUser(@ModelAttribute User user, HttpSession session, Model model) {
        User existingUser = userService.findByUsername(user.getUsername());

        if (existingUser != null && existingUser.getPassword().equals(user.getPassword()) && existingUser.getRole().equals(user.getRole())   ) {
            session.setAttribute("loggedInUser", existingUser);
            session.setAttribute("userRole", existingUser.getRole());

            return "redirect:/rides";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "redirect:/register";
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/register";
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/Auth";
    }
}