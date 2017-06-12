package ua.training.controller.command;

import com.google.gson.Gson;
import ua.training.domain.Room;
import ua.training.domain.RoomRequest;
import ua.training.service.BookingService;
import ua.training.service.RequestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ApproveCommand extends BaseCommand {
    private static final long serialVersionUID = 1L;
    private RequestService requestService = RequestService.getInstance();
    private BookingService bookingService = BookingService.getInstance();

    @Override
    public String subCommandExecute(HttpServletRequest request, HttpServletResponse response) {
        String requestId = request.getParameter("requestId").trim();
        RoomRequest roomRequest = requestService.getRoomRequestInfo(Long.valueOf(requestId));
        List<Room> result = bookingService.showFreeRoomsForRequest(roomRequest.getFkBookingrequest());
        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        try {
            response.getWriter().write(json);
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }
    }

