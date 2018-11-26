package cz.muni.fi.pa165.dao.manager;

import cz.muni.fi.pa165.dao.base.UserDaoImpl;
import cz.muni.fi.pa165.entity.Manager;
import org.springframework.stereotype.Repository;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@Repository
public class ManagerDaoImpl extends UserDaoImpl<Manager> implements ManagerDao {

    @Override
    protected Class<Manager> getClassType() {
        return Manager.class;
    }

}
