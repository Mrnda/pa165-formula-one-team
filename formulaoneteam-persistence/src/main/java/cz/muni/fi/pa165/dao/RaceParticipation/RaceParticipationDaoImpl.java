package cz.muni.fi.pa165.dao.RaceParticipation;

import cz.muni.fi.pa165.dao.base.DaoImpl;
import cz.muni.fi.pa165.entity.RaceParticipation;
import org.springframework.stereotype.Repository;

/**
 * @author Adel Chakouri
 */
@Repository
public class RaceParticipationDaoImpl extends DaoImpl<RaceParticipation> implements RaceParticipationDao {

    @Override
    protected Class<RaceParticipation> getClassType() {
        return RaceParticipation.class;
    }

    @Override
    protected void validateEntity(RaceParticipation entity) {
        if (entity == null || !entity.isConfigured()) {
            throw new IllegalArgumentException("RaceParticipation is null or not configured");
        }
    }
}
