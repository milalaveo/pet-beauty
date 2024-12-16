package com.example.petbeauty.command.impl;

import com.example.petbeauty.command.Command;
import com.example.petbeauty.command.Router;
import com.example.petbeauty.exception.ServiceException;
import com.example.petbeauty.model.Request;
import com.example.petbeauty.model.Service;
import com.example.petbeauty.service.RequestService;
import com.example.petbeauty.service.ServiceService;
import com.example.petbeauty.service.impl.RequestServiceImpl;
import com.example.petbeauty.service.impl.ServiceServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class EditRequestCommand implements Command {
    private final RequestService requestService = new RequestServiceImpl();
    private final ServiceService serviceService = new ServiceServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        try {
            String id = request.getParameter("id");

            if (id != null && !id.isEmpty()) {
                Request requestData = requestService.getRequestById(Integer.parseInt(id));
                request.setAttribute("request", requestData);
            }

            List<Service> services = serviceService.getAllServices();
            request.setAttribute("services", services);

            return new Router("pages/add_request.jsp", Router.Type.FORWARD);
        } catch (ServiceException e) {
            request.setAttribute("error", "Failed to load request for editing.");
            return new Router("pages/add_request.jsp");
        }
    }
}
