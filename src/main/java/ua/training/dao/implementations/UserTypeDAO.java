package ua.training.dao.implementations;

import ua.training.domain.UserType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserTypeDAO extends AbstractDAO<Long, UserType> {
    private static volatile UserTypeDAO instance;

    public static UserTypeDAO getInstance(Connection connection) {
        if (instance == null) {
            synchronized(UserTypeDAO.class) {
                if (instance == null) {
                    instance = new UserTypeDAO(connection);
                }
            }
        }
        return instance;
    }

    private UserTypeDAO(Connection connection) {
        super(connection);
    }

    @Override
    List<UserType> createEntityList(ResultSet resultSet) {
        List<UserType> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                list.add(new UserType( resultSet.getLong(1), resultSet.getNString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Long getRoleID(String role) {
        List<UserType> list = findAll();
        for (UserType type : list) {
            if (type.getTypeName().equals(role)) {
                return type.getId();
            }
        }
        return null;
    }
}
