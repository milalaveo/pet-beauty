package com.example.petbeauty.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import com.example.petbeauty.controller.command.Command;

public class DefaultCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "index.jsp";
    }
}
