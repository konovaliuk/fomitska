package ua.training.dao;

import ua.training.domain.Entity;

import java.sql.SQLException;
import java.util.List;

public interface IDAO <K, T extends Entity> {
    List<T> findAll();
    T findById(K id);
    boolean delete(K id);
    boolean create(T entity);
    boolean update(T entity);
}
