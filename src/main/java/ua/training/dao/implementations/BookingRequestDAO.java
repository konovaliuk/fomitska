package ua.training.dao.implementations;

import ua.training.domain.BookingRequest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BookingRequestDAO extends AbstractDAO<Long, BookingRequest> {
    private static volatile BookingRequestDAO instance;

    public static BookingRequestDAO getInstance(Connection connection) {
        if (instance == null) {
            synchronized(BookingRequestDAO.class) {
                if (instance == null) {
                    instance = new BookingRequestDAO(connection);
                }
            }
        }
        return instance;
    }

    private BookingRequestDAO(Connection connection) {
        super(connection);
    }

    @Override
    List<BookingRequest> createEntityList(ResultSet set) {
        List<BookingRequest> list = new ArrayList<>();
        try {
            while (set.next()) {
                list.add(BookingRequest.newBuilder(set.getLong(1))
                        .setUserId(set.getLong(2))
                        .setFkStatus(set.getLong(3))
                        .setFkRating(set.getLong(4))
                        .setCheckInDt(set.getTimestamp(5))
                        .setCheckOutDt(set.getTimestamp(6))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<BookingRequest> getRequestByStatus(Long statusId) {
        List<BookingRequest> newRequests = new LinkedList<>();
        List<BookingRequest> allRequests = findAll();
        for (BookingRequest request : allRequests) {
            if (request.getFkStatus().getId() == statusId){
                newRequests.add(request);
            }
        }
        return newRequests;
    }

    @Override
    protected String getCorrectIdName() {
        return "request_id";
    }

}
