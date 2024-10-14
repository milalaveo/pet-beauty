package com.example.petbeauty.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import com.example.petbeauty.controller.command.Command;

public class MultiplyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String inputNum = request.getParameter("num");
        int multiplier = 2;
        int resultNum = Integer.parseInt(inputNum) * multiplier;
        request.setAttribute("input", inputNum);
        request.setAttribute("result", resultNum);
        request.setAttribute("multiplier", multiplier);

        return "pages/multiply.jsp";
    }
}