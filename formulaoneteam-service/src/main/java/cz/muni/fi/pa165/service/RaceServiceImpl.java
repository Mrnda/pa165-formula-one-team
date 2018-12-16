package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.Race.RaceDao;
import cz.muni.fi.pa165.entity.Race;
import cz.muni.fi.pa165.service.base.BaseEntityServiceImpl;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.springframework.stereotype.Service;

/**
 * @author Adel Chakouri
 */
@Service
public class RaceServiceImpl extends BaseEntityServiceImpl<Race, RaceDao> implements RaceService {

    @Override
    public void validateEntity(Race entity) throws FormulaOneTeamException {
        if (entity == null || !entity.isConfigured()) {
            throw new FormulaOneTeamException("Race entity is null or not configured");
        }
    }

    @Override
    protected Class<Race> getEntityClass() {
        return Race.class;
    }
}
