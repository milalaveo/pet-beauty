package com.example.petbeauty.command.impl;

import com.example.petbeauty.command.Command;
import com.example.petbeauty.command.Router;
import com.example.petbeauty.service.UserService;
import com.example.petbeauty.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class SignupCommand implements Command {
    private final UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeat_password");
        String firstname = request.getParameter("firstname");
        String surname = request.getParameter("lastname");
        String email = request.getParameter("email");

        if (!password.equals(repeatPassword)) {
            request.setAttribute("error", "Passwords do not match.");
            return new Router("pages/signup.jsp");
        }

        try {
            if (!userService.registerUser(username, password, firstname, surname, email)) {
                request.setAttribute("error", "An error occurred while registering the user.");
                return new Router("pages/signup.jsp");
            }

            return new Router("pages/signup-complete.jsp");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            return new Router("pages/signup.jsp");
        }
    }
}
