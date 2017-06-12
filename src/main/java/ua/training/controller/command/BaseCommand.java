package ua.training.controller.command;

import org.apache.log4j.Logger;
import ua.training.controller.Config;
import ua.training.controller.ControllerHelper;
import ua.training.service.RequestService;
import ua.training.service.RequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseCommand implements ICommand {
    private RequestService requestService = RequestService.getInstance();
    protected final Logger log = Logger.getLogger(this.getClass());

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            return subCommandExecute(request, response);
        } catch (Exception e) {
            request.getSession().removeAttribute("user");
            request.getSession().removeAttribute("isAdmin");
            request.setAttribute("messageError", e.getMessage());
            request.setAttribute("messageTrace", e.getStackTrace());
            log.error("Exception occurred:", e);
            return Config.getInstance().getProperty(Config.ERROR);
        } finally {
            request.setAttribute("isUserAuthorized", ControllerHelper.getInstance().isUserAuthorized(request));
        }
    }

    protected abstract String subCommandExecute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException;

    protected String redirectToMain(HttpServletRequest request) {
        setUserParameters(request);
        return Config.getInstance().getProperty(Config.MAIN);
    }

    protected String redirectAdminToMain(HttpServletRequest request, int page) {
        SetBookingRequestsByPage(request, page);
        return Config.getInstance().getProperty(Config.MAIN);
    }

    private void setUserParameters(HttpServletRequest request) {
        Object isAdminObj = request.getSession().getAttribute("isAdmin");
        int page = 1;
        if (isAdminObj.equals("true")) {
            SetBookingRequestsByPage(request, page);
        } else {
            request.setAttribute("bedTypes", requestService.findBedTypes());
            request.setAttribute("roomRatings", requestService.findRoomRatings());
        }
    }

    private void SetBookingRequestsByPage(HttpServletRequest request, int page) {
        int pageToGet = page != 0 ? page - 1 : 0;
        List<RequestWrapper> list = requestService.getAllNewAndChangedRequests(pageToGet);
        request.setAttribute("page", page);
        int totalPages = requestService.countBookingRequestPages();
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("requests", list);
    }

}
