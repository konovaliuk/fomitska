package ua.training.dao.implementations;

import ua.training.domain.Password;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PasswordDAO extends AbstractDAO<Long, Password> {
    private static volatile PasswordDAO instance;

    public static PasswordDAO getInstance(Connection connection) {
        if (instance == null) {
            synchronized(PasswordDAO.class) {
                if (instance == null) {
                    instance = new PasswordDAO(connection);
                }
            }
        }
        return instance;
    }

    private PasswordDAO(Connection connection) {
        super(connection);
    }

    @Override
    List<Password> createEntityList(ResultSet resultSet) {
        List<Password> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                list.add(new Password( resultSet.getLong(1),
                        resultSet.getNString(3), resultSet.getTimestamp(4),
                        resultSet.getLong(2), resultSet.getLong(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Password> findAllByUserID(Long id) {
        List<Password> list = executeSelectQuery("Select * from Password where fk_user = " + id);
        return list;
    }

    public Password getExpectedPassword(Long userID) {
        List<Password> passwords = findAllByUserID(userID);
        for (Password password : passwords) {
            if (password.getExpiryDt().after(new Timestamp(System.currentTimeMillis()))) {
                return password;
            }
        }
        return null;
    }

}
