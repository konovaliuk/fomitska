package ua.training.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandAbsent implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Config.getInstance().getProperty(Config.LOGIN);
    }
}
