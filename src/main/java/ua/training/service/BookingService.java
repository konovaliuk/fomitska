package ua.training.service;

import ua.training.dao.implementations.*;
import ua.training.dao.factories.BaseFactory;
import ua.training.dao.factories.DAOFactory;
import ua.training.dao.DBTypes;
import ua.training.domain.BookingRequest;
import ua.training.domain.Reservation;
import ua.training.domain.Room;

import java.sql.Timestamp;
import java.util.List;

public class BookingService {
    private static volatile BookingService instance;
    private BaseFactory factory;
    private RoomDAO roomDAO;
    private RoomRatingDAO roomRatingDAO;
    private BedTypeDAO bedTypeDAO;
    private ReservationDAO reservationDAO;
    private BookingRequestDAO requestDAO;

    public static BookingService getInstance() {
        if (instance == null) {
            synchronized(BookingService.class) {
                if (instance == null) {
                    instance = new BookingService();
                }
            }
        }
        return instance;
    }

    private BookingService() {
        factory = DAOFactory.getFactory(DBTypes.MySQL);
        roomDAO = factory.createRoomDAO();
        roomRatingDAO = factory.createRoomRatingDAO();
        bedTypeDAO = factory.createBedTypeDAO();
        reservationDAO = factory.createReservationDAO();
        requestDAO = factory.createBookingRequestDAO();
    }

    public List<Room> showFreeRoomsForRequest(BookingRequest request) {
        return roomDAO.findFreeRooms(request.getCheckinDt(), request.getCheckoutDt());
    }

    public boolean reserveRoom(BookingRequest request, Room room) {
        return reservationDAO.create(
                new Reservation(request.getId(), room.getId()));
    }
}
