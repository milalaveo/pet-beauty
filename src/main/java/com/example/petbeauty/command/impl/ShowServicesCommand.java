package com.example.petbeauty.command.impl;

import com.example.petbeauty.command.Command;
import com.example.petbeauty.command.Router;
import com.example.petbeauty.exception.ServiceException;
import com.example.petbeauty.model.Service;
import com.example.petbeauty.service.ServiceService;
import com.example.petbeauty.service.impl.ServiceServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class ShowServicesCommand implements Command {
    private final ServiceService serviceService = new ServiceServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        try {
            List<Service> services = serviceService.getAllServices();

            request.setAttribute("services", services);

            return new Router("pages/services.jsp");
        } catch (ServiceException e) {
            request.setAttribute("error", "Failed to load services. Please try again later.");
            return new Router("pages/error.jsp");
        }
    }
}
