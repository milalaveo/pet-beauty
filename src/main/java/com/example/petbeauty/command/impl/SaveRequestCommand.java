package com.example.petbeauty.command.impl;

import com.example.petbeauty.command.Command;
import com.example.petbeauty.command.Router;
import com.example.petbeauty.exception.ServiceException;
import com.example.petbeauty.service.RequestService;
import com.example.petbeauty.service.impl.RequestServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SaveRequestCommand implements Command {
    private final RequestService requestService = new RequestServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int userId = (int)session.getAttribute("userId");

        if (userId == 0) {
            return new Router("pages/login.jsp");
        }

        String idParam = request.getParameter("id");
        String serviceIdParam = request.getParameter("service");
        String executionDateParam = request.getParameter("executionDate");
        String executionTimeParam = request.getParameter("executionTime");

        if (serviceIdParam == null || executionDateParam == null || executionTimeParam == null) {
            request.setAttribute("error", "All fields are required.");
            return new Router("pages/add_request.jsp");
        }

        try {
            int serviceId = Integer.parseInt(serviceIdParam);

            if (idParam == null || idParam.isEmpty()) {
                requestService.createRequest(userId, serviceId, "Pending", executionDateParam, executionTimeParam);
            } else {
                int id = Integer.parseInt(idParam);
                requestService.editRequest(id, userId, serviceId, "Pending", executionDateParam, executionTimeParam);
            }

            return new Router("controller?command=dashboard", Router.Type.FORWARD);
        } catch (ServiceException e) {
            request.setAttribute("error", "Failed to save request. Please try again.");
            return new Router("pages/add_request.jsp");
        }
    }
}
