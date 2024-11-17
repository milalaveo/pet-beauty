package com.example.petbeauty.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

@FunctionalInterface
public interface Command {
    Router execute(HttpServletRequest request) throws ServletException, IOException, SQLException;
}
