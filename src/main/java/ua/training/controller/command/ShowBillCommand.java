package ua.training.controller.command;

import ua.training.controller.Config;
import ua.training.domain.Bill;
import ua.training.service.BookingService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.sql.SQLException;

public class ShowBillCommand extends BaseCommand {
    private BookingService bookingService = BookingService.getInstance();
    public static final double DISCOUNT_COEFFICIENT = 0.95;

    @Override
    protected String subCommandExecute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Long billId = Long.valueOf(request.getParameter("billId"));
        Bill bill = bookingService.getBill(billId);
        request.setAttribute("bill", bill);
        BigDecimal total = bill.getDiscountPercentage()>0
                ? new BigDecimal(String.valueOf(bill.getSubtotal().multiply(new BigDecimal(DISCOUNT_COEFFICIENT))))
                :   bill.getSubtotal();
        request.setAttribute("total", total);
        return Config.getInstance().getProperty(Config.BILL);
    }
}
