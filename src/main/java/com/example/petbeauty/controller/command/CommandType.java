package com.example.petbeauty.controller.command;

import com.example.petbeauty.controller.command.impl.DefaultCommand;
import com.example.petbeauty.controller.command.impl.LoginCommand;
import com.example.petbeauty.controller.command.impl.LogoutCommand;
import com.example.petbeauty.controller.command.impl.MultiplyCommand;
import com.example.petbeauty.controller.command.impl.SignupCommand;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
//    SIGNUP(new SignupCommand()),
    MULTIPLY(new MultiplyCommand()),
    DEFAULT(new DefaultCommand());

    final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command chooseCommand(String command) {
        try {
            return CommandType.valueOf(command.toUpperCase()).command;
        } catch (IllegalArgumentException e) {
            return DEFAULT.command;
        }
    }
}
