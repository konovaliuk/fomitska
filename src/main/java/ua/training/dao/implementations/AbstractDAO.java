package ua.training.dao.implementations;

import ua.training.dao.IDAO;
import ua.training.dao.QueryFactory;
import ua.training.dao.QueryTypes;
import ua.training.domain.Entity;

import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;

public abstract class AbstractDAO <K, T extends Entity> implements IDAO<K, T> {
    protected Connection connection;
    protected String entityName = getEntityName();
    public final String ID_CONDITION =  String.format(" where %1$s = ", getCorrectIdName());

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    public String getEntityName() {
        Class<T> type = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        return type.getSimpleName();
    }

    public List <T> findAll() {
        String sql = QueryFactory.getQuery(QueryTypes.FIND_ALL, entityName);
        return executeSelectQuery(sql);
    }

    public T findById(K id) {
        String sql = QueryFactory.getQuery(QueryTypes.FIND_BY_ATTRIBUTE, entityName, ID_CONDITION + id );
        List<T> list = executeSelectQuery(sql);
        return list.isEmpty() ? null : list.get(0);
    }

    public boolean delete(K id) {
        String sql = QueryFactory.getQuery(QueryTypes.DELETE, entityName, ID_CONDITION + id);
        return executeUpdateQuery(sql);
    }

    public boolean create(T entity) {
        return executeUpdateQuery(QueryFactory.getQuery(QueryTypes.CREATE, entity));
    }

    protected List<T> executeSelectQuery(String sql) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            return createEntityList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected boolean executeUpdateQuery(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(T entity) {
        return executeUpdateQuery(QueryFactory.getQuery(QueryTypes.UPDATE, entity));
    }

    abstract List<T> createEntityList(ResultSet resultSet);

    protected String getCorrectIdName() {
        return entityName.toLowerCase()+ "_id";
    }


}
