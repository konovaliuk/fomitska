package ua.training.dao.implementations;

import ua.training.domain.User;
import ua.training.exception.FailedOperationException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDAO<Long, User>{
    private static volatile UserDao instance;

    public static UserDao getInstance(Connection connection) {
        if (instance == null) {
            synchronized(UserDao.class) {
                if (instance == null) {
                    instance = new UserDao(connection);
                }
            }
        }
        return instance;
    }

    private UserDao(Connection connection) {
        super(connection);
    }

    @Override
    List<User> createEntityList(ResultSet set) {
        List<User> list = new ArrayList<>();
        try {
            while (set.next()) {
                User user = new User();
                list.add(User.newBuilder(set.getLong(1)).setFkUsertype(set.getLong(2))
                        .setFirstName(set.getString(3))
                        .setLastName(set.getString(4))
                        .setLogin(set.getString(5))
                        .setBirthDt(set.getTimestamp(6))
                        .setMobileNumber(set.getString(7))
                        .setEmail(set.getString(8))
                        .setCity(set.getString(9))
                        .setStreet(set.getString(10))
                        .setHouse(set.getString(11))
                        .setAppartment(set.getInt(12))
                        .setActive(set.getInt(13))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean getUniqueAttribute(String attribute, String value) {
        String sql = String.format("Select count(*) as rowcount from user where %1$s like '%2$s'", attribute, value);
        Integer counter = -1;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
           while(resultSet.next()) {
               counter = resultSet.getInt("rowcount");
           }
            resultSet.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return counter == 0;
    }

    public User findByLogin(String login) {
        List<User> list = executeSelectQuery("Select * from User where login like '" + login + "'");
        return !list.isEmpty() ? list.get(0) : null;
    }
}
