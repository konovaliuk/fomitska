package ua.training.dao.implementations;

import ua.training.domain.Reservation;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public BigDecimal getPriceForReservation(Reservation reservation, boolean extraBed) {
        String sql = extraBed == true ?  "Select rr.price_with_extra_bed " : "Select rr.price ";
        String condition = " from reservation as res inner join room on fk_room = room_id inner join roomrating " +
                "as rr on rating_id = fk_rating where room_id = " + reservation.getFkRoom().getId();
        BigDecimal price = new BigDecimal(-1L);
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql + condition);
            while (resultSet.next()) {
                price = resultSet.getBigDecimal(1);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    }
}
