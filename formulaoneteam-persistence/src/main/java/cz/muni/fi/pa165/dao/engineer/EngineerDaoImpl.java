package cz.muni.fi.pa165.dao.engineer;

import cz.muni.fi.pa165.dao.base.UserDaoImpl;
import cz.muni.fi.pa165.entity.Engineer;
import org.springframework.stereotype.Repository;

/**
 * Class representing engineer's Dao.
 *
 * @author Ivan Dendis
 */
@Repository
public class EngineerDaoImpl extends UserDaoImpl<Engineer> implements EngineerDao {

    @Override
    protected Class<Engineer> getClassType() {
        return Engineer.class;
    }

    @Override
    protected void validateEntity(Engineer engineer) {
        if (engineer == null || !engineer.isConfigured()) {
            throw new IllegalArgumentException("Engineer is null or not configured");
        }
    }
}
