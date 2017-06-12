package ua.training.controller;

import ua.training.controller.command.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        commands.put("register", new ToRegisterCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("appoint", new AppointCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("approve", new ApproveCommand());
        commands.put("bill", new BillCommand());
        commands.put("decline", new DeclineCommand());
        commands.put("getrequests", new GetRequestsCommand());
        commands.put("showbill", new ShowBillCommand());
        commands.put("tomain", new ToMainCommand());

    }

    HashMap<String, ICommand> commands = new HashMap<String, ICommand>();

    public ICommand getCommand(HttpServletRequest request) {
        String commandParameter = request.getParameter("command");
        ICommand command = commands.get(commandParameter);
        boolean isLogin = command instanceof LoginCommand;
        if ((command == null || isLogin) && isUserAuthorized(request)) {
            return new ToMainCommand();
        }
        if (command == null) {
            return new CommandAbsent();
        }
        if (!isUserAuthorized(request) && !isLogin
                && !(command instanceof RegistrationCommand)
                && !(command instanceof ToRegisterCommand)) {
            return new CommandAbsent();
        }
        return command;
    }

    public boolean isUserAuthorized(HttpServletRequest request) {
        try{
            HttpSession session = request.getSession(false);
            if(session != null){
                return session.getAttribute("user") != null;
            }
        } catch(Exception e){
            return false;
        }
        return false;
    }


}
