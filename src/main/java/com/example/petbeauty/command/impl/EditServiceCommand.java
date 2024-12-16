package com.example.petbeauty.command.impl;

import com.example.petbeauty.command.Router;
import com.example.petbeauty.exception.ServiceException;
import com.example.petbeauty.service.ServiceService;
import com.example.petbeauty.service.impl.ServiceServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import com.example.petbeauty.command.Command;

public class EditServiceCommand implements Command {
    private final ServiceService serviceService = new ServiceServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");

        try {
            serviceService.editService(id, name, description, price);

            return new Router("pages/services.jsp");
        } catch (ServiceException e) {
            request.setAttribute("error", "An error occured while performing login. Please try again later");
            return new Router("pages/edit_service.jsp");
        }
    }
}
