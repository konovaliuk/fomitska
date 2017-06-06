package ua.training.dao.implementations;

import ua.training.domain.RequestStatus;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestStatusDAO extends AbstractDAO<Long, RequestStatus> {
    private static volatile RequestStatusDAO instance;

    public static RequestStatusDAO getInstance(Connection connection) {
        if (instance == null) {
            synchronized(RequestStatusDAO.class) {
                if (instance == null) {
                    instance = new RequestStatusDAO(connection);
                }
            }
        }
        return instance;
    }

    private RequestStatusDAO(Connection connection) {
        super(connection);
    }

    @Override
    List<RequestStatus> createEntityList(ResultSet resultSet) {
        List<RequestStatus> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                list.add(new RequestStatus( resultSet.getLong(1),
                        resultSet.getNString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Long getStatusId(String status) {
        List<RequestStatus> list = findAll();
        for (RequestStatus e : list) {
            if (e.getDescription().equals(status)) {
                return e.getId();
            }
        }
        return null;
    }

    @Override
    protected String getCorrectIdName() {
        return "status_id";
    }

}
