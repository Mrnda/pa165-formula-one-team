package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.RaceParticipation.RaceParticipationDao;
import cz.muni.fi.pa165.entity.RaceParticipation;
import cz.muni.fi.pa165.service.base.BaseEntityServiceImpl;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

/**
 * @author Adel Chakouri
 */
@Service
public class RaceParticipationServiceImpl
        extends BaseEntityServiceImpl<RaceParticipation, RaceParticipationDao>
        implements RaceParticipationService {

    @Override
    public void validateEntity(@Nullable RaceParticipation entity) throws FormulaOneTeamException {
        if (entity == null || !entity.isConfigured()) {
            throw new FormulaOneTeamException("RaceParticipation entity is null or not configured");
        }
    }
}
