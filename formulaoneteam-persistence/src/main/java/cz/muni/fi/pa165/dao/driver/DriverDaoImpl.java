package cz.muni.fi.pa165.dao.driver;

import cz.muni.fi.pa165.dao.base.UserDaoImpl;
import cz.muni.fi.pa165.entity.Driver;
import org.springframework.stereotype.Repository;

/**
 * @author mrnda (Michal Mrnuštík)
 */
@Repository
public class DriverDaoImpl extends UserDaoImpl<Driver> implements DriverDao {

    @Override
    protected Class<Driver> getClassType() {
        return Driver.class;
    }

    @Override
    protected void validateEntity(Driver entity) {
        if (entity == null || !entity.isConfigured()) {
            throw new IllegalArgumentException("Driver is null or not configured.");
        }
    }
}
