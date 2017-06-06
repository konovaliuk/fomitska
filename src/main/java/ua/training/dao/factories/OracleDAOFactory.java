package ua.training.dao.factories;

import java.sql.Connection;

public class OracleDAOFactory extends BaseFactory {
    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public void closeConnection() {

    }
}
