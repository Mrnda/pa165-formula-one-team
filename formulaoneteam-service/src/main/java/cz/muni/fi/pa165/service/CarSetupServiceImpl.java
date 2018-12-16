package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.carsetup.CarSetupDao;
import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.service.base.BaseEntityServiceImpl;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.springframework.stereotype.Service;

/**
 * @author Théo Desjardins
 */
@Service
public class CarSetupServiceImpl extends BaseEntityServiceImpl<CarSetup, CarSetupDao> implements CarSetupService {

    @Override
    public void validateEntity(CarSetup entity) {
        if (entity == null || !entity.isConfigured()) {
            throw new FormulaOneTeamException("CarSetup is null or not configured.");
        }
    }

    @Override
    protected Class<CarSetup> getEntityClass() {
        return CarSetup.class;
    }
}
