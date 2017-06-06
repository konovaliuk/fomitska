package ua.training.command;

import ua.training.domain.Room;
import ua.training.service.BookingService;
import ua.training.service.RequestService;
import ua.training.service.RequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AppointCommand implements ICommand {
    private RequestService requestService = RequestService.getInstance();
    private BookingService bookingService = BookingService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long id = (Long) request.getAttribute("requestId");
        RequestWrapper requestWrapper = requestService.getCurrentRequestWrapper(id);
        request.setAttribute("bookingrequest", requestWrapper);
        List<Room> rooms = bookingService.showFreeRoomsForRequest(requestWrapper.getBookingRequest());
        request.setAttribute("rooms", rooms);
        return Config.getInstance().getProperty(Config.APPOINTMENT);
    }
}
