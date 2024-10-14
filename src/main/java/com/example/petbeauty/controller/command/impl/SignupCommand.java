package com.example.petbeauty.controller.command.impl;

import com.example.petbeauty.controller.command.Command;
import com.example.petbeauty.controller.model.User;
import com.example.petbeauty.controller.service.UserService;
import com.example.petbeauty.controller.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SignupCommand implements Command {
    private final UserService userService = new UserServiceImpl();
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeat_password");
        String firstname = request.getParameter("firstname");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");

        if (!password.equals(repeatPassword)) {
            request.setAttribute("error", "Passwords do not match.");
            return "pages/signup.jsp";
        }

        String encryptedPassword = encoder.encode(password);

        User user = new User(0, username, encryptedPassword, firstname, surname, email);

        try {
            userService.registerUser(user);

            return "pages/dashboard.jsp";
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            return "pages/signup.jsp";
        }
    }
}
