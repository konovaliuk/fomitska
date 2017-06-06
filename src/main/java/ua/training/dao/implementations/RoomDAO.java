package ua.training.dao.implementations;

import ua.training.dao.QueryFactory;
import ua.training.dao.QueryTypes;
import ua.training.domain.Room;
import ua.training.domain.RoomRating;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO extends AbstractDAO<Long, Room> {
    private static volatile RoomDAO instance;

    public static RoomDAO getInstance(Connection connection) {
        if (instance == null) {
            synchronized(RoomDAO.class) {
                if (instance == null) {
                    instance = new RoomDAO(connection);
                }
            }
        }
        return instance;
    }

    private RoomDAO(Connection connection) {
        super(connection);
    }

    @Override
    List<Room> createEntityList(ResultSet resSet) {
        List<Room> list = new ArrayList<>();
        try {
            while (resSet.next()) {
                list.add(Room.newBuilder(resSet.getLong(1))
                        .setFkRating(resSet.getLong(2))
                        .setFkBedType(resSet.getLong(3))
                        .setRoomNumber(resSet.getLong(4))
                        .setSquare(resSet.getLong(5))
                        .setFloor(resSet.getLong(6))
                        .setCityView(resSet.getInt(7))
                        .setBalcony(resSet.getInt(8))
                        .setExtraBed(resSet.getInt(9))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Room> findByRating(RoomRating rating) {
        List<Room> list = executeSelectQuery(QueryFactory.getQuery(QueryTypes.FIND_BY_ATTRIBUTE,
                entityName, " where fk_rating =" + rating.getId()));
        return list;
    }

    public List<Room> findFreeRooms(Timestamp dtFrom, Timestamp dtTo) {
        String condition = " where room_id not in (select room_id from room inner join reservation on room_id = fk_room" +
                " inner join roomrequest as rr on roomrequest_id = fk_roomrequest" +
                "inner join bookingrequest as br on bookingrequest_id = fk_bookingrequest where" +
                " date(br.checkin_dt) >= '" + dtFrom.toString() + "' and date(br.checkout_dt) <= '" + dtTo.toString() + "')";
        return executeSelectQuery(QueryFactory.getQuery(QueryTypes.FIND_BY_ATTRIBUTE, entityName, condition));
    }


}
