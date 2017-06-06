package ua.training.dao.implementations;

import ua.training.domain.Reservation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO extends AbstractDAO<Long, Reservation> {
    private static volatile ReservationDAO instance;

    public static ReservationDAO getInstance(Connection connection) {
        if (instance == null) {
            synchronized(ReservationDAO.class) {
                if (instance == null) {
                    instance = new ReservationDAO(connection);
                }
            }
        }
        return instance;
    }

    private ReservationDAO(Connection connection) {
        super(connection);
    }

    @Override
    List<Reservation> createEntityList(ResultSet resultSet) {
        List<Reservation> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                list.add(new Reservation( resultSet.getLong(1),
                        resultSet.getLong(2), resultSet.getLong(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
