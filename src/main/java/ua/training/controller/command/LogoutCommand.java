package ua.training.controller.command;

import ua.training.controller.Config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand extends BaseCommand {

    @Override
    public String subCommandExecute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("isAdmin");
        request.getSession().invalidate();
        return Config.getInstance().getProperty(Config.LOGIN);
    }
}
