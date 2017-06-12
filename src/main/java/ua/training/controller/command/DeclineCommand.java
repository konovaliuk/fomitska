package ua.training.controller.command;

import ua.training.domain.BookingRequest;
import ua.training.service.RequestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeclineCommand extends BaseCommand {
    private RequestService requestService = RequestService.getInstance();

    @Override
    public String subCommandExecute(HttpServletRequest request, HttpServletResponse response) {
        String requestId = request.getParameter("requestId").trim();
        BookingRequest bookingRequest = requestService
                .getCurrentRequestWrapper(Long.valueOf(requestId))
                .getBookingRequest();
        requestService.declineRequest(bookingRequest);
        return null;
    }
}
