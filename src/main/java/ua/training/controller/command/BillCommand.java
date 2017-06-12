package ua.training.controller.command;

import com.google.gson.Gson;
import ua.training.controller.Config;
import ua.training.domain.Bill;
import ua.training.domain.Reservation;
import ua.training.service.BookingService;
import ua.training.service.RequestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

public class BillCommand extends BaseCommand {
    private BookingService bookingService = BookingService.getInstance();
    private RequestService requestService = RequestService.getInstance();

    @Override
    public String subCommandExecute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String reservationsJson  = request.getParameter("reservations");
        Gson gson = new Gson();
        Reservation[] reservations = gson.fromJson(reservationsJson, Reservation[].class);
        if (bookingService.saveReservations(reservations)) {
            Bill bill = bookingService.createBillForReservations(reservations);
            request.setAttribute("bill", bill);
            Long billId = bookingService.saveBill(bill);
            response.setContentType("application/json");
            try {
                response.getWriter().write(billId.toString());
                response.getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Config.getInstance().getProperty(Config.BILL);
    }

}
