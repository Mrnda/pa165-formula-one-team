package cz.muni.fi.pa165.dao.base;

import cz.muni.fi.pa165.entity.base.BaseEntity;
import java.util.List;

/**
 * @author mrnda (Michal Mrnuštík)
 * Data Access Object interface used for managing any entity that is stored within the app.
 * @param <T> entity that is accessed via this Dao object
 */
public interface Dao<T extends BaseEntity> {

    /**
     * Finds entity in the repository using specified {@code id}.
     * @param id specified id of the entity to find.
     * @return instance of the entity or null if id was not found.
     */
    T findById(Long id);

    /**
     * Validates and adds entity specified by {@code entity} into repository.
     * @param entity object to be added.
     */
    void add(T entity);

    /**
     * Validates and deletes entity specified by {@code entity} from the repository.
     * @param id object id to be deleted.
     */
    void delete(long id);

    /**
     * Validates and updates entity specified by {@code entity} in the repository.
     * @param entity object to be updated.
     */
    void update(T entity);

    /**
     * Finds all entities of generic type specified for this Dao.
     * @return list of entities in the repository.
     */
    List<T> findAll();
}
