package ua.training.dao.implementations;

import ua.training.domain.Bill;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDAO extends AbstractDAO<Long, Bill> {
    private static volatile BillDAO instance;

    public static BillDAO getInstance(Connection connection) {
        if (instance == null) {
            synchronized(BillDAO.class) {
                if (instance == null) {
                    instance = new BillDAO(connection);
                }
            }
        }
        return instance;
    }

    private BillDAO(Connection connection) {
        super(connection);
    }

    @Override
    List<Bill> createEntityList(ResultSet resultSet) {
        List<Bill> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                list.add(new Bill( resultSet.getLong(1), resultSet.getLong(2),
                        resultSet.getBigDecimal(3), resultSet.getShort(4),
                       resultSet.getTimestamp(5), resultSet.getLong(6), resultSet.getLong(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Bill> findSubtotalGreaterThan(Long amount) {
        List<Bill> list = executeSelectQuery("Select * from BedType where subtotal >= " + amount);
        return list;
    }

}
