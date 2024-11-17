package com.example.petbeauty.command.impl;

import com.example.petbeauty.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import com.example.petbeauty.command.Command;

public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router();
    }
}
