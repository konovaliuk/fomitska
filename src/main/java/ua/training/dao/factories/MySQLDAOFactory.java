package ua.training.dao.factories;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MySQLDAOFactory extends BaseFactory {
    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            Context envCtx = (Context) (new InitialContext().lookup("java:comp/env"));
            DataSource datasource = (DataSource) envCtx.lookup("jdbc/hotel");
            connection = datasource.getConnection();
        } catch (SQLException e) {
            System.err.println("not obtained connection " + e);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void closeConnection() {
        try{
            if(connection != null) {
                connection.close();
            }
        } catch(SQLException e) {
            System.err.print(e);
        }
    }


}
