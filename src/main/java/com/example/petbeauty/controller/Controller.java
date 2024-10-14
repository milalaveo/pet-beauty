package com.example.petbeauty.controller;

import com.example.petbeauty.controller.command.Command;
import com.example.petbeauty.controller.command.CommandType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "helloServlet", value = "/controller")
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Controller.class);

    public void init() {
        logger.debug("Servlet initialized successfully.");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.debug("Received GET request.");
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        logger.debug("Processed GET request.");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.debug("Received POST request.");
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        logger.debug("Processed POST request.");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        logger.debug("Request context path: {}", request.getContextPath());
        logger.debug("Request URI: {}", request.getRequestURI());
        logger.debug("Request params: {}", request.getParameterMap());
        String commandStr = request.getParameter("command");

        if (commandStr == null || commandStr.isEmpty()) {
            commandStr = "default";
        }

        logger.debug("Selected command: {}", commandStr);

        Command command = CommandType.chooseCommand(commandStr);
        String page = command.execute(request);
        request.getRequestDispatcher(page).forward(request, response);
    }

    public void destroy() {
        logger.info("Servlet destroyed.");
    }
}