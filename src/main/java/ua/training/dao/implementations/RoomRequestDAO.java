package ua.training.dao.implementations;

import ua.training.dao.QueryFactory;
import ua.training.dao.QueryTypes;
import ua.training.domain.RoomRequest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomRequestDAO extends AbstractDAO<Long, RoomRequest> {
    private static volatile RoomRequestDAO instance;

    public static RoomRequestDAO getInstance(Connection connection) {
        if (instance == null) {
            synchronized(RoomRequestDAO.class) {
                if (instance == null) {
                    instance = new RoomRequestDAO(connection);
                }
            }
        }
        return instance;
    }

    private RoomRequestDAO(Connection connection) {
        super(connection);
    }

    @Override
    List<RoomRequest> createEntityList(ResultSet set) {
        List<RoomRequest> list = new ArrayList<>();
        try {
            while (set.next()) {
                list.add( new RoomRequest(set.getLong(1),
                                set.getLong(2), set.getLong(3),
                                set.getLong(4), set.getBoolean(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<RoomRequest> findByBookingRequest(Long requestId) {
        String condition = " where fk_bookingrequest =" + requestId;
        String sql = QueryFactory.getQuery(QueryTypes.FIND_BY_ATTRIBUTE, entityName, condition);
        return executeSelectQuery(sql);
    }
}
