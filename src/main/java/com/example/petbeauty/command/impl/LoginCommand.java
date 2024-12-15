package com.example.petbeauty.command.impl;

import com.example.petbeauty.command.Command;
import com.example.petbeauty.command.Router;
import com.example.petbeauty.exception.ServiceException;
import com.example.petbeauty.model.User;
import com.example.petbeauty.service.UserService;
import com.example.petbeauty.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private final UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            if (userService.verifyLogin(username, password)) {
                User user = userService.findUser(username);
                // login successful
                HttpSession session = request.getSession();
                session.setAttribute("username", user.getUsername());
                session.setAttribute("userId", user.getUserId());
                return new Router("controller?command=dashboard");
            }

            request.setAttribute("error", "Invalid username or password");
            return new Router("pages/login.jsp");
        } catch (ServiceException e) {
            request.setAttribute("error", "An error occured while performing login. Please try again later");
            return new Router("pages/login.jsp");
        }
    }
}