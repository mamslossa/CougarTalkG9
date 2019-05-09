package com.plkpiotr.forum.controllers;

import com.plkpiotr.forum.entities.User;
import com.plkpiotr.forum.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Controller
public class RegisterController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

    @Autowired
    public RegisterController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        userRepository.getUserByUsername("toto");
        userRepository.getUserById("CNJTA8R8dAB0eOvgRR7j");
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @GetMapping("register")
    public String displayRegister(Model model) {
        return "register";
    }

    @PostMapping("register")
    public View registerUser(@RequestParam("username") String username, @RequestParam("password") String password,
                             @RequestParam("introduction") String introduction, HttpServletRequest request) {
        String contextPath = request.getContextPath();
        User user = new User(null);
        if (userRepository.getUserByUsername(username) == null) {
            user.setUsername(username);
            if (Objects.equals(introduction, ""))
                user.setIntroduction("Your introduction");
            else
                user.setIntroduction(introduction);
            user.setPassword(passwordEncoder.encode(password));
            user.setCreatedDate(LocalDateTime.now().format(formatter));
            userRepository.save(user);
            return new RedirectView(contextPath + "/login");
        } else
            return new RedirectView(contextPath + "/register");
    }
}