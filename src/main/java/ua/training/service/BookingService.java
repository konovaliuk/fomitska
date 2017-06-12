package ua.training.service;

import ua.training.dao.DBTypes;
import ua.training.dao.factories.BaseFactory;
import ua.training.dao.factories.DAOFactory;
import ua.training.dao.implementations.*;
import ua.training.domain.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class BookingService {
    private static volatile BookingService instance;
    private BaseFactory factory;
    private RoomDAO roomDAO;
    private RoomRatingDAO roomRatingDAO;
    private BedTypeDAO bedTypeDAO;
    private ReservationDAO reservationDAO;
    private BookingRequestDAO requestDAO;
    private RoomRequestDAO roomRequestDAO;
    private UserDao userDao;
    private Short DISCOUNT = 5;
    private BillDAO billDAO;
    private RequestStatusDAO statusDAO;

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
        roomRequestDAO = factory.createRoomRequestDAO();
        userDao = factory.createUserDAO();
        billDAO = factory.createBillDAO();
        statusDAO = factory.createRequestStatusDAO();
    }

    public List<Room> showFreeRoomsForRequest(BookingRequest request) {
        List<Room> allFreeRooms = roomDAO.findFreeRooms(request.getCheckinDt(), request.getCheckoutDt());
        List<Room> freeRoomsWithRating = new LinkedList<>();
        for (Room room : allFreeRooms) {
            room.setFkBedtype(bedTypeDAO.findById(room.getFkBedtype().getId()));
            if (room.getFkRating().getId() == request.getFkRating().getId()) {
                room.setFkRating(roomRatingDAO.findById(room.getFkRating().getId()));
                freeRoomsWithRating.add(room);
            }
        }
        return freeRoomsWithRating;
    }

    public boolean saveReservations(Reservation [] reservations) throws SQLException {
        boolean isSaved = true;
        for (Reservation reservation: reservations) {
            if (reservationDAO.create(reservation) < 0){
                isSaved = false;
            }
        }
        BookingRequest bookingRequest = requestDAO.findById(reservations[0]
                .getFkRoomrequest().getFkBookingrequest().getId());
        bookingRequest.setFkStatus(statusDAO.getStatusId("confirmed"));
        requestDAO.update(bookingRequest);
        return isSaved;
    }

    public Bill createBillForReservations(Reservation [] reservations) {
        Bill bill = new Bill();
        BigDecimal price = new BigDecimal(0L);
        Long bookingRequestId = 0L;
        for (Reservation reservation : reservations) {
            RoomRequest roomRequest = roomRequestDAO.findById(reservation.getFkRoomrequest().getId());
            bookingRequestId = roomRequest.getFkBookingrequest().getId();
            BigDecimal priceForReservation = reservationDAO.getPriceForReservation(reservation, roomRequest.isExtraBed());
            price = price.add(priceForReservation);
        }
        BookingRequest bookingRequest = requestDAO.findById(bookingRequestId);
        bill.setFkBookingrequest(bookingRequest);
        bill.setSubtotal(getSubtotal(bookingRequest, price));
        User user = userDao.findById(bookingRequest.getFkUser().getId());
        bill.setFkUser(user);
        bill.setCreationDt(new Timestamp(System.currentTimeMillis()));
        if (getDiscount(user, bookingRequest)) {
            bill.setDiscountPercentage(DISCOUNT);
        }
        return bill;
    }

    private boolean getDiscount(User user, BookingRequest bookingRequest) {
        if (user.getBirthDt() != null) {
            Timestamp birthDate = user.getBirthDt();
            Calendar birth = Calendar.getInstance();
            birth.setTime(birthDate);
            Calendar checkinDate = Calendar.getInstance();
            checkinDate.setTime(bookingRequest.getCheckinDt());
            Calendar checkoutDate = Calendar.getInstance();
            checkoutDate.setTime(bookingRequest.getCheckinDt());
            return checkinDate.get(Calendar.DAY_OF_YEAR) >= birth.get(Calendar.DAY_OF_YEAR) ||
                    checkoutDate.get(Calendar.DAY_OF_YEAR) <= birth.get(Calendar.DAY_OF_YEAR);
        }
        return false;
    }

    private BigDecimal getSubtotal(BookingRequest bookingRequest, BigDecimal price) {
        BigDecimal subtotal = new BigDecimal(0L);
        Long checkin = bookingRequest.getCheckinDt().getTime();
        Long checkout = bookingRequest.getCheckoutDt().getTime();
        int nights = (int) ((checkout - checkin) / (1000 * 60 * 60 *24));
        return price.multiply(new BigDecimal(nights));
    }

    public Long saveBill(Bill bill) throws SQLException {
        return billDAO.create(bill);
    }

    public Bill getBill(Long billId) {
        Bill bill = billDAO.findById(billId);
        bill.setFkUser(userDao.findById(bill.getFkUser().getId()));
        bill.setFkBookingrequest(requestDAO.findById(bill.getFkBookingrequest().getId()));
        return bill;
    }
}
