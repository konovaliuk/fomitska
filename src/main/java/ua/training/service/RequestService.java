package ua.training.service;

import ua.training.dao.implementations.*;
import ua.training.dao.factories.BaseFactory;
import ua.training.dao.factories.DAOFactory;
import ua.training.dao.DBTypes;
import ua.training.domain.BedType;
import ua.training.domain.BookingRequest;
import ua.training.domain.RoomRating;
import ua.training.domain.RoomRequest;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class RequestService {
    private static volatile RequestService instance;
    private BaseFactory factory;
    private BookingRequestDAO requestDAO;
    private RequestStatusDAO statusDAO;
    private UserDao userDao;
    private RoomRatingDAO roomRatingDAO;
    private RoomRequestDAO roomRequestDAO;
    private BedTypeDAO bedTypeDAO;
    private static final int PAGESIZE = 3;

    public static RequestService getInstance() {
        if (instance == null) {
            synchronized(RequestService.class) {
                if (instance == null) {
                    instance = new RequestService();
                }
            }
        }
        return instance;
    }

    private RequestService() {
        factory = DAOFactory.getFactory(DBTypes.MySQL);
        requestDAO = factory.createBookingRequestDAO();
        statusDAO = factory.createRequestStatusDAO();
        userDao = factory.createUserDAO();
        roomRatingDAO = factory.createRoomRatingDAO();
        roomRequestDAO = factory.createRoomRequestDAO();
        bedTypeDAO = factory.createBedTypeDAO();
    }

    public List<BedType> findBedTypes() {
        return bedTypeDAO.findAll();
    }

    public List<RoomRating> findRoomRatings() { return roomRatingDAO.findAll(); }

    public Long saveBookingRequest(BookingRequest request) throws SQLException {
        request.setFkStatus(statusDAO.getStatusId("new"));
        return requestDAO.create(request);
    }

    public boolean cancelRequest(BookingRequest request) {
        request.setFkStatus(statusDAO.getStatusId("cancelled"));
        return requestDAO.update(request);
    }

    public boolean changeRequest(BookingRequest request) {
        request.setFkStatus(statusDAO.getStatusId("changed"));
        return requestDAO.update(request);
    }

    public boolean declineRequest(BookingRequest request) {
        request.setFkStatus(statusDAO.getStatusId("rejected"));
        return requestDAO.update(request);
    }

    public List<RequestWrapper> getAllNewAndChangedRequests(int page)  {
        List<RequestWrapper> allRequests = new LinkedList<>();
        Long idNew = statusDAO.getStatusId("new");
        Long idChanged = statusDAO.getStatusId("changed");
        List<BookingRequest> list = requestDAO.getRequestByStatus(idNew);
        list.addAll(requestDAO.getRequestByStatus(idChanged));
        int counter = 0;
        for (BookingRequest request : list) {
            request.setFkUser(userDao.findById(request.getFkUser().getId()));
            request.setFkStatus(statusDAO.findById(request.getFkStatus().getId()));
            request.setFkRating(roomRatingDAO.findById(request.getFkRating().getId()));
            if (counter > page * PAGESIZE && allRequests.size() < PAGESIZE) {
                allRequests.add(new RequestWrapper(request.getId(), request,
                        roomRequestDAO.findByBookingRequest(request.getId())));
            }
            counter++;
        }
        return allRequests;
    }

    public int countBookingRequestPages() {
       return requestDAO.countRows() / PAGESIZE;
    }

    public RoomRequest getRoomRequestInfo(Long id) {
        RoomRequest request = roomRequestDAO.findById(id);
        request.setFkBookingrequest(requestDAO.findById(request.getFkBookingrequest().getId()));
        request.setFkBedtype(bedTypeDAO.findById(request.getFkBedtype().getId()));
        return request;
    }

    public RequestWrapper getCurrentRequestWrapper(Long id) {
        BookingRequest booking = requestDAO.findById(id);
        booking.setFkUser(userDao.findById(booking.getFkUser().getId()));
        booking.setFkRating(roomRatingDAO.findById(booking.getFkRating().getId()));
        booking.setFkStatus(statusDAO.findById(booking.getFkStatus().getId()));
        return new RequestWrapper(booking.getId(), booking,
                findRoomRequestsByBooking(booking.getId()));
    }

    private List <RoomRequest> findRoomRequestsByBooking(Long bookingId) {
        List <RoomRequest> requests = roomRequestDAO.findByBookingRequest(bookingId);
        for (RoomRequest request : requests) {
            request.setFkBedtype(bedTypeDAO.findById(request.getFkBedtype().getId()));
        }
        return requests;
    }

    public Long getStatusForNewRequest() {
        return statusDAO.getStatusId("new");
    }

    public Long saveRoomRequest(RoomRequest roomRequest) throws SQLException {
        return roomRequestDAO.create(roomRequest);
    }




}
