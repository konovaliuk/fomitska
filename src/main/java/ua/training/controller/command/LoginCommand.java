package ua.training.controller.command;

import ua.training.controller.Config;
import ua.training.domain.User;
import ua.training.exception.FailedOperationException;
import ua.training.service.AuthorizationService;
import ua.training.service.RequestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand extends BaseCommand {
    private AuthorizationService authentification = AuthorizationService.getInstance();
    private RequestService requestService = RequestService.getInstance();
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    @Override
    public String subCommandExecute(HttpServletRequest request, HttpServletResponse response){
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        try {
            User user = authentification.authentificate(login, password);
            if (!user.isActive()) {
                throw new FailedOperationException("user.inactive");
            }
            String isAdmin = user.getFkUsertype().getId() == authentification.getAdminID()
                    ? "true" : "false";
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("isAdmin", isAdmin);
            return redirectToMain(request);
        }catch (FailedOperationException e) {
            request.setAttribute("messageError", e.getMessage());
            return Config.getInstance().getProperty(Config.LOGIN);
        }
    }

}
