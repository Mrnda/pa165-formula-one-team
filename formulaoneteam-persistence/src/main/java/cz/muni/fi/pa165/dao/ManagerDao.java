package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.dao.base.Dao;
import cz.muni.fi.pa165.entity.Manager;
import org.springframework.stereotype.Repository;

/**
 * @author elderanakain (Arcadii Rubailo)
 * Data Access Object interface for Manager entity with basic CRUD operations.
 */
@Repository
public interface ManagerDao extends Dao<Manager> {
    Manager findByName(String name);
}
