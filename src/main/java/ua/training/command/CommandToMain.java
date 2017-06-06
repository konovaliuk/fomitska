package ua.training.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandToMain implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Config.getInstance().getProperty(Config.MAIN);
    }
}
