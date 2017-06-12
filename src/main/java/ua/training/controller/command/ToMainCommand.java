package ua.training.controller.command;

import ua.training.controller.Config;
import ua.training.service.RequestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToMainCommand extends BaseCommand {

    private RequestService requestService = RequestService.getInstance();

    @Override
    public String subCommandExecute(HttpServletRequest request, HttpServletResponse response) {
        boolean isAdmin = Boolean.parseBoolean((String)request.getSession().getAttribute("isAdmin"));
        return redirectToMain(request);
    }

}
