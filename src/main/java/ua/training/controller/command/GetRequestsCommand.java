package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class GetRequestsCommand extends BaseCommand {

    @Override
    public String subCommandExecute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Integer page = Integer.parseInt(request.getParameter("page"));
        return redirectAdminToMain(request, page);
    }
}
