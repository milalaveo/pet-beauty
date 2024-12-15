package com.example.petbeauty.command.impl;

import com.example.petbeauty.command.Router;
import com.example.petbeauty.dao.ServiceDao;
import com.example.petbeauty.dao.impl.ServiceDaoImpl;
import com.example.petbeauty.exception.DaoException;
import com.example.petbeauty.model.Service;
import jakarta.servlet.http.HttpServletRequest;
import com.example.petbeauty.command.Command;

import java.math.BigDecimal;

public class EditServiceCommand implements Command {
    private final ServiceDao serviceDao = new ServiceDaoImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        BigDecimal price = new BigDecimal(request.getParameter("price"));

        try {
            Service updatedService = new Service(id, name, description, price);
            serviceDao.updateService(updatedService);

            return new Router("pages/services.jsp");
        } catch (DaoException e) {
            request.setAttribute("error", "An error occured while performing login. Please try again later");
            return new Router("pages/edit_service.jsp");
        }
    }
}
