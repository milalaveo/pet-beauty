package com.example.petbeauty.command.impl;

import com.example.petbeauty.command.Command;
import com.example.petbeauty.command.Router;
import com.example.petbeauty.dao.ServiceDao;
import com.example.petbeauty.dao.impl.ServiceDaoImpl;
import com.example.petbeauty.exception.DaoException;
import com.example.petbeauty.model.Service;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class ShowServicesCommand implements Command {
    private final ServiceDao serviceDao = new ServiceDaoImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        try {
            List<Service> services = serviceDao.findAllServices();

            request.setAttribute("services", services);

            return new Router("pages/services.jsp");
        } catch (DaoException e) {
            request.setAttribute("error", "Failed to load services. Please try again later.");
            return new Router("pages/error.jsp");
        }
    }
}
