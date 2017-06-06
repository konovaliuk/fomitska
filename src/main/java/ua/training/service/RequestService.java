package ua.training.service;

import ua.training.dao.implementations.*;
import ua.training.dao.factories.BaseFactory;
import ua.training.dao.factories.DAOFactory;
import ua.training.dao.DBTypes;
import ua.training.domain.BookingRequest;
import ua.training.domain.RoomRequest;

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
    }

    public boolean saveBookingRequest(BookingRequest request) {
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

    public List<RequestWrapper> getAllNewAndChangedRequests()  {
        List<RequestWrapper> allRequests = new LinkedList<>();
        Long idNew = statusDAO.getStatusId("new");
        Long idChanged = statusDAO.getStatusId("changed");
        List<BookingRequest> list = requestDAO.getRequestByStatus(idNew);
        list.addAll(requestDAO.getRequestByStatus(idChanged));

        for (BookingRequest request : list) {
            request.setFkUser(userDao.findById(request.getFkUser().getId()));
            request.setFkStatus(statusDAO.findById(request.getFkStatus().getId()));
            request.setFkRating(roomRatingDAO.findById(request.getFkRating().getId()));
            allRequests.add(new RequestWrapper(request.getId(), request,
                    roomRequestDAO.findByBookingRequest(request.getId())));
        }
        return allRequests;
    }

    public RequestWrapper getCurrentRequestWrapper(Long id) {
        BookingRequest booking = requestDAO.findById(id);
        return new RequestWrapper(booking.getId(), booking,
                roomRequestDAO.findByBookingRequest(booking.getId()));
    }


}
