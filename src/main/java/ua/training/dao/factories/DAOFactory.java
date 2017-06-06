package ua.training.dao.factories;

import ua.training.dao.DBTypes;

public class DAOFactory {
    public static BaseFactory getFactory(DBTypes type) {
        if (type.equals(DBTypes.MySQL)) {
            return new MySQLDAOFactory();
        } else  {
            return new OracleDAOFactory();
        }
    }
    }
