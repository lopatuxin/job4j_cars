package ru.job4j.controller;

import lombok.AllArgsConstructor;
import javax.annotation.concurrent.ThreadSafe;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.model.User;
import ru.job4j.service.UserService;

@ThreadSafe
@Controller
@AllArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/register")
    public String getRegistrationPage() {
        return "users/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        var savedUser = service.create(user);
        if (savedUser == null) {
            model.addAttribute("message", "Пользователь с такой почтой уже существует");
        }
        return "cars/list";
    }

    @GetMapping({"/", "users/login"})
    public String getLoginPage() {
        return "users/login";
    }

    @PostMapping("/users/login")
    public String login(@ModelAttribute User user, Model model, HttpServletRequest request) {
        var getUser = service.findByLogin(user.getLogin());
        if (getUser.isEmpty()) {
            model.addAttribute("error", "Почта или пароль введены не верно");
            return "users/login";
        }
        var session = request.getSession();
        session.setAttribute("user", getUser.get());
        return "redirect:/posts";
    }
}
