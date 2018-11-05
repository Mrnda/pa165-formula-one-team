package cz.muni.fi.pa165.dao.Race;

import cz.muni.fi.pa165.dao.base.DaoImpl;
import cz.muni.fi.pa165.entity.Race;
import org.springframework.stereotype.Repository;

/**
 * @author Adel Chakouri
 */
@Repository
public class RaceDaoImpl extends DaoImpl<Race> implements RaceDao {

    @Override
    protected Class<Race> getClassType() {
        return Race.class;
    }

    @Override
    protected void validateEntity(Race race) {
        if (race == null || !race.isConfigured()) {
            throw new IllegalArgumentException("Race is null or not configured.");
        }
    }
}
