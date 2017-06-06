package ua.training.dao.factories;

import ua.training.dao.implementations.*;

import java.sql.Connection;

public abstract class BaseFactory {
    protected Connection connection;

    public BaseFactory() {
        connection = getConnection();
    }

    public abstract Connection getConnection();

    public abstract void closeConnection();

    public BedTypeDAO createBedTypeDAO() {
        return BedTypeDAO.getInstance(connection);
    }

    public BillDAO createBillDAO() {
        return BillDAO.getInstance(connection);
    }

    public UserDao createUserDAO() {return UserDao.getInstance(connection);}

    public BookingRequestDAO createBookingRequestDAO() {return BookingRequestDAO.getInstance(connection);}

    public PasswordDAO createPasswordDAO() {return PasswordDAO.getInstance(connection);}

    public RequestStatusDAO createRequestStatusDAO() {return RequestStatusDAO.getInstance(connection);}

    public RoomDAO createRoomDAO() {return RoomDAO.getInstance(connection);}

    public RoomRatingDAO createRoomRatingDAO() {return RoomRatingDAO.getInstance(connection);}

    public UserTypeDAO createUserTypeDAO() {return UserTypeDAO.getInstance(connection);}

    public ReservationDAO createReservationDAO() {return ReservationDAO.getInstance(connection);}

    public RoomRequestDAO createRoomRequestDAO() {return RoomRequestDAO.getInstance(connection);}
}
