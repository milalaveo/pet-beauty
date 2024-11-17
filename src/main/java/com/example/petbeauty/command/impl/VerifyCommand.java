package com.example.petbeauty.command.impl;

import com.example.petbeauty.command.Command;
import com.example.petbeauty.command.Router;
import com.example.petbeauty.exception.ServiceException;
import com.example.petbeauty.service.UserService;
import com.example.petbeauty.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class VerifyCommand implements Command {
    private final UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        String verificationCode = request.getParameter("verification_code");

        if (verificationCode == null) {
            return new Router();
        }

        try {
            if (userService.confirmSignup(verificationCode)) {
                return new Router("pages/login.jsp");
            }
        } catch (ServiceException e) {
            return new Router();
        }

        return new Router();
    }
}

