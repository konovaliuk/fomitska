package ua.training.command;

import ua.training.domain.User;
import ua.training.exception.FailedOperationException;
import ua.training.service.AuthorizationService;
import ua.training.service.RequestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements ICommand {
    private AuthorizationService authentification = AuthorizationService.getInstance();
    private RequestService requestService = RequestService.getInstance();
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        String page = null;
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        try {
            User user = authentification.authentificate(login, password);
            boolean isAdmin = user.getFkUsertype().getId() == authentification.getAdminID();
            request.getSession().setAttribute("user", user);
            request.setAttribute("isAdmin", isAdmin);
            if (!isAdmin) {
                setUserParameters(user, request);
            } else {
                setAdminParameters(user, request);
            }
            page = Config.getInstance().getProperty(Config.MAIN);
        }catch (FailedOperationException e) {
            request.setAttribute("exception", e);
            page = Config.getInstance().getProperty(Config.LOGIN);
        }
        return page;
    }

    private void setAdminParameters(User admin, HttpServletRequest request) {
        request.setAttribute("requests", requestService.getAllNewAndChangedRequests());
    }

    private void setUserParameters(User user,HttpServletRequest request) {

    }
}
