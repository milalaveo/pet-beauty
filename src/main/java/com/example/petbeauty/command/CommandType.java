package com.example.petbeauty.command;

import com.example.petbeauty.command.impl.*;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    SIGNUP(new SignupCommand()),
    VERIFY(new VerifyCommand()),
    DEFAULT(new DefaultCommand());

    final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command chooseCommand(String command) {
        if (command == null || command.isBlank()) {
            return DEFAULT.command;
        }

        try {
            return CommandType.valueOf(command.toUpperCase()).command;
        } catch (IllegalArgumentException e) {
            return DEFAULT.command;
        }
    }
}
