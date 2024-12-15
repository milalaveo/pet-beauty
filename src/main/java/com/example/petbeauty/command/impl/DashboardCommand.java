package com.example.petbeauty.command.impl;

import com.example.petbeauty.command.Command;
import com.example.petbeauty.command.Router;
import com.example.petbeauty.exception.ServiceException;
import com.example.petbeauty.model.Request;
import com.example.petbeauty.service.RequestService;
import com.example.petbeauty.service.impl.RequestServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class DashboardCommand implements Command {
    private final RequestService requestService = new RequestServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int userId = (int)session.getAttribute("userId");

        if (userId == 0) {
            return new Router("pages/login.jsp");
        }

        try {
            List<Request> requests = requestService.getRequestsByUserId(userId);
            request.setAttribute("requests", requests);

            return new Router("pages/dashboard.jsp");
        } catch (ServiceException e) {
            return new Router("pages/login.jsp");
        }
    }
}
