package ua.training.dao.implementations;

import ua.training.domain.RoomRating;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomRatingDAO extends AbstractDAO<Long, RoomRating> {
    private static volatile RoomRatingDAO instance;

    public static RoomRatingDAO getInstance(Connection connection) {
        if (instance == null) {
            synchronized(RoomRatingDAO.class) {
                if (instance == null) {
                    instance = new RoomRatingDAO(connection);
                }
            }
        }
        return instance;
    }

    private RoomRatingDAO(Connection connection) {
        super(connection);
    }

    @Override
    List<RoomRating> createEntityList(ResultSet resultSet) {
        List<RoomRating> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                list.add(new RoomRating(resultSet.getLong(1),
                        resultSet.getNString(2), resultSet.getBigDecimal(3),
                        resultSet.getBigDecimal(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    protected String getCorrectIdName() {
        return "rating_id";
    }

}
