package com.example.petbeauty.controller.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

@FunctionalInterface
public interface Command {
    String execute(HttpServletRequest request) throws ServletException, IOException, SQLException;
}
