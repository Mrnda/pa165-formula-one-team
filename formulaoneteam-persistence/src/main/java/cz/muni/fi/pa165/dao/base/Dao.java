package cz.muni.fi.pa165.dao.base;

import cz.muni.fi.pa165.entity.base.BaseEntity;

import java.util.List;

/**
 * @author mrnda (Michal Mrnuštík)
 * Data Access Object interface used for managing any entity that is stored within the app.
 * @param <T> entity that is accessed via this Dao object
 */

public interface Dao<T extends BaseEntity> {

    T findById(Long id);

    void add(T entity);

    void delete(T entity);

    void update(T entity);

    List<T> findAll();
}
