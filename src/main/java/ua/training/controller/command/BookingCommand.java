package ua.training.controller.command;


import ua.training.controller.Config;
import ua.training.domain.BedType;
import ua.training.domain.BookingRequest;
import ua.training.domain.RoomRequest;
import ua.training.domain.User;
import ua.training.service.RequestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class BookingCommand extends BaseCommand {
    private static final String BED_AMOUNT = "bedAmt";
    private static final String BED_TYPE = "bedType";
    private static final String EXTRA_BED = "extraBed";
    private RequestService requestService = RequestService.getInstance();

    @Override
    public String subCommandExecute(HttpServletRequest request, HttpServletResponse response) {
        try {
            BookingRequest bookingRequest = getBookingRequest(request);
            bookingRequest.setId(requestService.saveBookingRequest(bookingRequest));
            if (saveRoomRequests(request, bookingRequest)) {
                request.setAttribute("requestSuccess", "booking.success");
            } else {
                request.setAttribute("exceptionMessage", "exception");
            }
            return redirectToMain(request);
        } catch (Exception e) {
            return Config.getInstance().getProperty(Config.ERROR);
        }
    }

    private boolean saveRoomRequests(HttpServletRequest request, BookingRequest bookingRequest) throws SQLException {
        List<RoomRequest> roomRequests = new LinkedList<>();
        int counter = Integer.valueOf(request.getParameter("roomrequestAmt"));
        boolean isRequestSaved = true;
        for (int i = 1; i <= counter; i++) {
            RoomRequest roomRequest = new RoomRequest();
            roomRequest.setFkBookingrequest(bookingRequest);
            Long bedTypeId = Long.valueOf(request.getParameter(BED_TYPE+i));
            roomRequest.setFkBedtype(new BedType(bedTypeId));
            Long bedAmt = Long.valueOf(request.getParameter(BED_AMOUNT+i));
            roomRequest.setBedAmt(bedAmt);
            boolean extraBed = Boolean.parseBoolean(request.getParameter(EXTRA_BED+i));
            roomRequest.setExtraBed(extraBed);
            if (requestService.saveRoomRequest(roomRequest) < 0) {
                isRequestSaved = false;
            }
        }
        return isRequestSaved;
    }

    private BookingRequest getBookingRequest(HttpServletRequest request) {
        return BookingRequest.newBuilder()
                .setCheckinDt(Validator.parseDate(request.getParameter("checkin")))
                .setCheckoutDt(Validator.parseDate(request.getParameter("checkout")))
                .setFkUser(new User(((User)request.getSession().getAttribute("user")).getId()))
                .setFkRating(Long.valueOf(request.getParameter("roomRating")))
                .build();
    }

}
