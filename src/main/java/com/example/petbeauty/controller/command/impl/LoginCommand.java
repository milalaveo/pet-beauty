package com.example.petbeauty.controller.command.impl;

import com.example.petbeauty.controller.command.Command;
import com.example.petbeauty.controller.model.User;
import com.example.petbeauty.controller.service.UserService;
import com.example.petbeauty.controller.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class LoginCommand implements Command {
    private final UserService userService = new UserServiceImpl();
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.findUserByUsername(username);
        if (user != null && encoder.matches(password, user.getPassword())) {
            // login successful
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            request.setAttribute("username", user.getUsername());
            return "pages/dashboard.jsp";
        }

        request.setAttribute("error", "Invalid username or password");
        return "pages/login.jsp";
    }
}