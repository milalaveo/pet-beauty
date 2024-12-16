package com.example.petbeauty.command;

import com.example.petbeauty.command.impl.*;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    SIGNUP(new SignupCommand()),
    VERIFY(new VerifyCommand()),
    DASHBOARD(new DashboardCommand()),
    EDIT_SERVICE(new EditServiceCommand()),
    SHOW_SERVICES(new ShowServicesCommand()),
    EDIT_REQUEST(new EditRequestCommand()),
    SAVE_REQUEST(new SaveRequestCommand()),
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
