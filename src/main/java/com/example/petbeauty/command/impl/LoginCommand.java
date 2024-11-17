package com.example.petbeauty.command.impl;

import com.example.petbeauty.command.Command;
import com.example.petbeauty.command.Router;
import com.example.petbeauty.exception.ServiceException;
import com.example.petbeauty.service.UserService;
import com.example.petbeauty.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class LoginCommand implements Command {
    private final UserService userService = new UserServiceImpl();
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public Router execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            if (userService.verifyLogin(username, password)) {
                // login successful
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                request.setAttribute("username", username);
                return new Router("pages/dashboard.jsp");
            }

            request.setAttribute("error", "Invalid username or password");
            return new Router("pages/login.jsp");
        } catch (ServiceException e) {
            request.setAttribute("error", "An error occured while performing login. Please try again later");
            return new Router("pages/login.jsp");
        }

        
    }
}