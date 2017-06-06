package ua.training.dao.implementations;

import ua.training.domain.BedType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BedTypeDAO extends AbstractDAO<Long, BedType> {
    private static volatile BedTypeDAO instance;

    public static BedTypeDAO getInstance(Connection connection) {
        if (instance == null) {
            synchronized(BedTypeDAO.class) {
                if (instance == null) {
                    instance = new BedTypeDAO(connection);
                }
            }
        }
        return instance;
    }

    private BedTypeDAO(Connection connection) {
        super(connection);
    }

    @Override
    List<BedType> createEntityList(ResultSet resultSet) {
        List<BedType> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                list.add(new BedType( resultSet.getLong(1),
                        resultSet.getNString(2), resultSet.getBigDecimal(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    protected String getCorrectIdName() {
        return "bed_type_id";
    }

    public List<BedType> findByType(String type) {
        List<BedType> list = executeSelectQuery("Select * from BedType where type like '" + type + "'");
        return list;
    }

}
