package ua.training.controller;

import ua.training.command.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class ControllerHelper {
    private static volatile ControllerHelper instance;

    public static ControllerHelper getInstance() {
        if (instance == null) {
            synchronized(ControllerHelper.class) {
                if (instance == null) {
                    instance = new ControllerHelper();
                }
            }
        }
        return instance;
    }

    private ControllerHelper() {
        commands.put("login", new LoginCommand());
        commands.put("booking", new BookingCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("appoint", new AppointCommand());
        commands.put("logout", new LogoutCommand());
    }

    HashMap<String, ICommand> commands = new HashMap<String, ICommand>();

    public ICommand getCommand(HttpServletRequest request) {
        ICommand command = commands.get(request.getParameter("command"));
        if (command == null && isUserAuthorized(request)) {
            return new CommandToMain();
        }
        if (command == null) {
            return new CommandAbsent();
        }
        if (!isUserAuthorized(request) && !(command instanceof LoginCommand)
                && !(command instanceof RegistrationCommand)) {
            return new CommandAbsent();
        }
        return command;
    }

    public static boolean isUserAuthorized(HttpServletRequest request) {
        return request.getSession(false).getAttribute("user") != null;
    }

}
