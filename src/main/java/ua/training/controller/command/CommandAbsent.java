package ua.training.controller.command;

import ua.training.controller.Config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandAbsent extends BaseCommand {

    @Override
    public String subCommandExecute(HttpServletRequest request, HttpServletResponse response) {
        return Config.getInstance().getProperty(Config.LOGIN);
    }
}
