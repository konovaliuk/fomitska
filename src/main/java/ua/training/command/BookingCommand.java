package ua.training.command;


import ua.training.domain.BookingRequest;
import ua.training.domain.User;
import ua.training.service.RequestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

public class BookingCommand implements ICommand {
    private RequestService requestService = RequestService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setCheckInDt(Timestamp.valueOf(request.getAttribute("checkin").toString()));
        bookingRequest.setCheckOutDt(Timestamp.valueOf(request.getAttribute("checkout").toString()));
        bookingRequest.setFkUser((User)request.getSession().getAttribute("user"));
        if (requestService.saveBookingRequest(bookingRequest)) {
            return Config.getInstance().getProperty(Config.CONGRATULATION);
        } else {
            return Config.getInstance().getProperty(Config.ERROR);
        }
    }
}
