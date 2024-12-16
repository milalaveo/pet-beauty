package com.example.petbeauty.command.impl;

import com.example.petbeauty.command.Command;
import com.example.petbeauty.command.Router;
import com.example.petbeauty.dao.RequestDao;
import com.example.petbeauty.dao.impl.RequestDaoImpl;
import com.example.petbeauty.exception.DaoException;
import com.example.petbeauty.model.Request;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.time.LocalTime;

public class SaveRequestCommand implements Command {
    private final RequestDao requestDao = new RequestDaoImpl();

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
            LocalDate executionDate = LocalDate.parse(executionDateParam);
            LocalTime executionTime = LocalTime.parse(executionTimeParam);

            if (idParam == null || idParam.isEmpty()) {
                Request newRequest = new Request(0, userId, serviceId, "Pending", executionDate, executionTime);
                requestDao.saveRequest(newRequest);
            } else {
                int id = Integer.parseInt(idParam);
                Request updatedRequest = new Request(id, userId, serviceId, "Pending", executionDate, executionTime);
                requestDao.updateRequest(updatedRequest);
            }

            return new Router("controller?command=dashboard", Router.Type.FORWARD);
        } catch (DaoException e) {
            request.setAttribute("error", "Failed to save request. Please try again.");
            return new Router("pages/add_request.jsp");
        }
    }
}
