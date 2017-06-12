package ua.training.controller.command;

import ua.training.controller.Config;
import ua.training.service.BookingService;
import ua.training.service.RequestService;
import ua.training.service.RequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AppointCommand extends BaseCommand {
    private RequestService requestService = RequestService.getInstance();
    private BookingService bookingService = BookingService.getInstance();

    @Override
    public String subCommandExecute(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.valueOf(request.getParameter("requestId"));
        RequestWrapper requestWrapper = requestService.getCurrentRequestWrapper(id);
        request.setAttribute("wrapper", requestWrapper);
        return Config.getInstance().getProperty(Config.APPOINTMENT);
    }
}
