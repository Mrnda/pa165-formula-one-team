package cz.muni.fi.pa165.dao.carsetup;

import cz.muni.fi.pa165.dao.base.DaoImpl;
import cz.muni.fi.pa165.entity.CarSetup;
import org.springframework.stereotype.Repository;

/**
 * @author Théo Desjardins
 */
@Repository
public class CarSetupDaoImpl extends DaoImpl<CarSetup> implements CarSetupDao {

    @Override
    protected Class<CarSetup> getClassType() {
        return CarSetup.class;
    }

    protected void validateEntity(CarSetup carSetup) {
        if (carSetup == null || !carSetup.isConfigured()) {
            throw new IllegalArgumentException("CarSetup is null or not configured.");
        }
    }
}
